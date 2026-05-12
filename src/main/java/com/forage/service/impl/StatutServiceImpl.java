package com.forage.service.impl;

import com.forage.model.Statut;
import com.forage.service.StatutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatutServiceImpl implements StatutService {

    private final InMemoryStore store;

    public StatutServiceImpl(InMemoryStore store) {
        this.store = store;
    }

    @Override
    public List<Statut> listerStatuts() {
        return new ArrayList<>(store.getStatuts());
    }

    @Override
    public Statut getStatutParNom(String nom) {
        return store.getStatuts().stream()
                .filter(statut -> statut.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Statut getStatut(Long id) {
        return store.getStatuts().stream()
                .filter(statut -> statut.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
