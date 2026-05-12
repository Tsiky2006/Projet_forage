package com.forage.service;

import com.forage.model.TypeDevis;
import java.util.List;

public interface TypeDevisService {
    List<TypeDevis> listerTypesDevis();
    TypeDevis getTypeDevis(Long id);
}
