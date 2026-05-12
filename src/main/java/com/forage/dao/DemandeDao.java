package com.forage.dao;

import com.forage.model.Demande;
import java.util.List;

public interface DemandeDao {
    void save(Demande demande);
    Demande findById(Long id);
    List<Demande> findAll();
}
