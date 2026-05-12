package com.forage.controller;

import com.forage.model.Demande;
import com.forage.model.Devis;
import com.forage.model.DetailsDevis;
import com.forage.model.Statut;
import com.forage.model.StatutDemande;
import com.forage.model.TypeDevis;
import com.forage.service.DemandeService;
import com.forage.service.DevisService;
import com.forage.service.StatutDemandeService;
import com.forage.service.StatutService;
import com.forage.service.TypeDevisService;
import com.forage.service.impl.InMemoryStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    private final DemandeService demandeService;
    private final StatutService statutService;
    private final StatutDemandeService statutDemandeService;
    private final DevisService devisService;
    private final TypeDevisService typeDevisService;
    private final InMemoryStore store;

    public DemandeController(DemandeService demandeService,
                             StatutService statutService,
                             StatutDemandeService statutDemandeService,
                             DevisService devisService,
                             TypeDevisService typeDevisService,
                             InMemoryStore store) {
        this.demandeService = demandeService;
        this.statutService = statutService;
        this.statutDemandeService = statutDemandeService;
        this.devisService = devisService;
        this.typeDevisService = typeDevisService;
        this.store = store;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        List<Demande> demandes = demandeService.listerDemandes();
        Map<String, Integer> statutCounts = new HashMap<>();

        for (Demande demande : demandes) {
            String statut = obtenirStatutActuel(demande);
            statutCounts.put(statut, statutCounts.getOrDefault(statut, 0) + 1);
        }

        modelAndView.addObject("totalDemandes", demandes.size());
        modelAndView.addObject("demandesCreees", statutCounts.getOrDefault("Demande créée", 0));
        modelAndView.addObject("devisCrees", devisService.listerDevis().size());
        modelAndView.addObject("etudesEnCours", statutCounts.getOrDefault("Étude en cours", 0));
        modelAndView.addObject("cloturees", statutCounts.getOrDefault("Demande clôturée", 0));
        return modelAndView;
    }

    @GetMapping("/liste")
    public ModelAndView listeDemandes() {
        ModelAndView modelAndView = new ModelAndView("demande-list");
        List<Demande> demandes = demandeService.listerDemandes();
        Map<Long, String> statusMap = new HashMap<>();
        for (Demande demande : demandes) {
            statusMap.put(demande.getId(), obtenirStatutActuel(demande));
        }
        modelAndView.addObject("demandes", demandes);
        modelAndView.addObject("statusMap", statusMap);
        return modelAndView;
    }

    @GetMapping("/nouvelle")
    public ModelAndView nouvelleDemande() {
        ModelAndView modelAndView = new ModelAndView("demande-form");
        modelAndView.addObject("demande", new Demande());
        return modelAndView;
    }

    @PostMapping("/nouvelle")
    public String creerDemande(@ModelAttribute Demande demande) {
        demandeService.creerDemande(demande);
        return "redirect:/demandes/liste";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailDemande(@PathVariable Long id) {
        Demande demande = demandeService.getDemande(id);
        if (demande == null) {
            return new ModelAndView("redirect:/demandes/liste");
        }

        Devis devis = devisService.getDevisByDemandeId(id);
        ModelAndView modelAndView = new ModelAndView("demande-detail");
        modelAndView.addObject("demande", demande);
        modelAndView.addObject("statutActuel", obtenirStatutActuel(demande));
        modelAndView.addObject("historique", statutDemandeService.historique(id));
        modelAndView.addObject("devis", devis);
        modelAndView.addObject("statuses", statutService.listerStatuts());
        return modelAndView;
    }

    @GetMapping("/{id}/devis/nouveau")
    public ModelAndView nouveauDevis(@PathVariable Long id) {
        Demande demande = demandeService.getDemande(id);
        if (demande == null) {
            return new ModelAndView("redirect:/demandes/liste");
        }
        ModelAndView modelAndView = new ModelAndView("devis-form");
        modelAndView.addObject("demande", demande);
        modelAndView.addObject("typesDevis", typeDevisService.listerTypesDevis());
        return modelAndView;
    }

    @PostMapping("/{id}/devis/nouveau")
    public String creerDevis(@PathVariable Long id,
                             @RequestParam Long typeDevisId,
                             @RequestParam(required = false) String observation,
                             @RequestParam(required = false) String[] libelle,
                             @RequestParam(required = false) String[] quantite,
                             @RequestParam(required = false) String[] prixUnitaire) {
        Demande demande = demandeService.getDemande(id);
        if (demande == null) {
            return "redirect:/demandes/liste";
        }

        Devis devis = new Devis();
        devis.setDemande(demande);
        devis.setObservation(observation);
        TypeDevis typeDevis = typeDevisService.getTypeDevis(typeDevisId);
        devis.setTypeDevis(typeDevis);

        List<DetailsDevis> lignes = new ArrayList<>();
        if (libelle != null) {
            for (int i = 0; i < libelle.length; i++) {
                String label = libelle[i];
                String qteValue = (quantite != null && quantite.length > i) ? quantite[i] : null;
                String prixValue = (prixUnitaire != null && prixUnitaire.length > i) ? prixUnitaire[i] : null;
                if (label == null || label.isBlank()) {
                    continue;
                }
                try {
                    int qte = qteValue != null && !qteValue.isBlank() ? Integer.parseInt(qteValue) : 0;
                    double prix = prixValue != null && !prixValue.isBlank() ? Double.parseDouble(prixValue) : 0;
                    if (qte <= 0 || prix <= 0) {
                        continue;
                    }
                    DetailsDevis detail = new DetailsDevis();
                    detail.setId(store.nextDetailsDevisId());
                    detail.setLibelle(label);
                    detail.setQuantite(qte);
                    detail.setPrixUnitaire(prix);
                    lignes.add(detail);
                } catch (NumberFormatException ignored) {
                    // Ignore invalid detail line
                }
            }
        }
        devis.setLignes(lignes);
        devisService.creerDevis(devis);
        return "redirect:/demandes/detail/" + id;
    }

    @PostMapping("/{id}/statut")
    public String changerStatut(@PathVariable Long id,
                                @RequestParam String statutNom) {
        Demande demande = demandeService.getDemande(id);
        if (demande == null) {
            return "redirect:/demandes/liste";
        }
        Statut statut = statutService.getStatutParNom(statutNom);
        if (statut != null) {
            StatutDemande statutDemande = new StatutDemande();
            statutDemande.setId(store.nextStatutDemandeId());
            statutDemande.setDemande(demande);
            statutDemande.setStatut(statut);
            statutDemande.setDateChangement(java.time.LocalDateTime.now());
            statutDemandeService.ajouterStatut(statutDemande);
        }
        return "redirect:/demandes/detail/" + id;
    }

    @GetMapping("/{id}/statuts")
    public ModelAndView historiqueStatuts(@PathVariable Long id) {
        Demande demande = demandeService.getDemande(id);
        if (demande == null) {
            return new ModelAndView("redirect:/demandes/liste");
        }
        ModelAndView modelAndView = new ModelAndView("statut-historique");
        modelAndView.addObject("demande", demande);
        modelAndView.addObject("historique", statutDemandeService.historique(id));
        return modelAndView;
    }

    private String obtenirStatutActuel(Demande demande) {
        StatutDemande dernier = statutDemandeService.dernierStatut(demande.getId());
        return dernier != null && dernier.getStatut() != null ? dernier.getStatut().getNom() : "Aucun statut";
    }
}
