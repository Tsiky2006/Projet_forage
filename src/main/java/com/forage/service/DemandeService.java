package com.forage.service;

import com.forage.model.Demande;
import java.util.List;

public interface DemandeService {
    void creerDemande(Demande demande);
    Demande getDemande(Long id);
    List<Demande> listerDemandes();
}
