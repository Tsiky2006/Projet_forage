package com.forage.service;

import com.forage.model.Statut;
import java.util.List;

public interface StatutService {
    List<Statut> listerStatuts();
    Statut getStatut(Long id);
    Statut getStatutParNom(String nom);
}
