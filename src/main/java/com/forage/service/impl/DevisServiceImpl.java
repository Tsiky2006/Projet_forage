package com.forage.service.impl;

import com.forage.model.Devis;
import com.forage.model.Statut;
import com.forage.model.StatutDemande;
import com.forage.service.DevisService;
import com.forage.service.StatutDemandeService;
import com.forage.service.StatutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DevisServiceImpl implements DevisService {

    private final InMemoryStore store;
    private final StatutService statutService;
    private final StatutDemandeService statutDemandeService;

    public DevisServiceImpl(InMemoryStore store, StatutService statutService, StatutDemandeService statutDemandeService) {
        this.store = store;
        this.statutService = statutService;
        this.statutDemandeService = statutDemandeService;
    }

    @Override
    public void creerDevis(Devis devis) {
        devis.setId(store.nextDevisId());
        devis.setDateCreation(LocalDateTime.now());
        store.getDevis().add(devis);

        Statut statutDevisCree = statutService.getStatutParNom("Devis créé");
        if (statutDevisCree != null && devis.getDemande() != null) {
            StatutDemande statutDemande = new StatutDemande();
            statutDemande.setId(store.nextStatutDemandeId());
            statutDemande.setDemande(devis.getDemande());
            statutDemande.setStatut(statutDevisCree);
            statutDemande.setDateChangement(LocalDateTime.now());
            statutDemandeService.ajouterStatut(statutDemande);
        }
    }

    @Override
    public Devis getDevisById(Long id) {
        return store.getDevis().stream()
                .filter(devis -> devis.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Devis getDevisByDemandeId(Long demandeId) {
        return store.getDevis().stream()
                .filter(devis -> devis.getDemande() != null && devis.getDemande().getId().equals(demandeId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Devis> listerDevis() {
        return new ArrayList<>(store.getDevis());
    }
}
