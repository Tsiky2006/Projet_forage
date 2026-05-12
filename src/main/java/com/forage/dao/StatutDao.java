package com.forage.dao;

import com.forage.model.Statut;
import java.util.List;

public interface StatutDao {
    Statut findById(Long id);
    List<Statut> findAll();
}
