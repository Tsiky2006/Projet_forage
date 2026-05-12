package com.forage.service;

import com.forage.model.StatutDemande;
import java.util.List;

public interface StatutDemandeService {
    void ajouterStatut(StatutDemande statutDemande);
    List<StatutDemande> historique(Long demandeId);
}
