package com.forage.service;

import com.forage.model.Devis;
import java.util.List;

public interface DevisService {
    void creerDevis(Devis devis);
    Devis getDevisById(Long id);
    Devis getDevisByDemandeId(Long demandeId);
    List<Devis> listerDevis();
}
