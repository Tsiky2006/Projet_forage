package com.forage.service.impl;

import com.forage.model.Demande;
import com.forage.model.Statut;
import com.forage.model.StatutDemande;
import com.forage.service.DemandeService;
import com.forage.service.StatutDemandeService;
import com.forage.service.StatutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {

    private final InMemoryStore store;
    private final StatutService statutService;
    private final StatutDemandeService statutDemandeService;

    public DemandeServiceImpl(InMemoryStore store, StatutService statutService, StatutDemandeService statutDemandeService) {
        this.store = store;
        this.statutService = statutService;
        this.statutDemandeService = statutDemandeService;
    }

    @Override
    public void creerDemande(Demande demande) {
        demande.setId(store.nextDemandeId());
        demande.setReference(store.nextDemandeReference());
        demande.setDateDemande(LocalDateTime.now());
        store.getDemandes().put(demande.getId(), demande);

        Statut demandeCree = statutService.getStatutParNom("Demande créée");
        if (demandeCree != null) {
            StatutDemande statutDemande = new StatutDemande();
            statutDemande.setId(store.nextStatutDemandeId());
            statutDemande.setDemande(demande);
            statutDemande.setStatut(demandeCree);
            statutDemande.setDateChangement(LocalDateTime.now());
            statutDemandeService.ajouterStatut(statutDemande);
        }
    }

    @Override
    public Demande getDemande(Long id) {
        return store.getDemandes().get(id);
    }

    @Override
    public List<Demande> listerDemandes() {
        return new ArrayList<>(store.getDemandes().values());
    }
}
