package com.forage.dao;

import com.forage.model.StatutDemande;
import java.util.List;

public interface StatutDemandeDao {
    void save(StatutDemande statutDemande);
    List<StatutDemande> findByDemandeId(Long demandeId);
}
