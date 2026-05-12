package com.forage.service.impl;

import com.forage.model.StatutDemande;
import com.forage.service.StatutDemandeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatutDemandeServiceImpl implements StatutDemandeService {

    private final InMemoryStore store;

    public StatutDemandeServiceImpl(InMemoryStore store) {
        this.store = store;
    }

    @Override
    public void ajouterStatut(StatutDemande statutDemande) {
        store.getStatutDemandes().add(statutDemande);
    }

    @Override
    public List<StatutDemande> historique(Long demandeId) {
        return store.getStatutDemandes().stream()
                .filter(entry -> entry.getDemande() != null && entry.getDemande().getId().equals(demandeId))
                .sorted(Comparator.comparing(StatutDemande::getDateChangement))
                .collect(Collectors.toList());
    }

    @Override
    public StatutDemande dernierStatut(Long demandeId) {
        return store.getStatutDemandes().stream()
                .filter(entry -> entry.getDemande() != null && entry.getDemande().getId().equals(demandeId))
                .max(Comparator.comparing(StatutDemande::getDateChangement))
                .orElse(null);
    }
}
