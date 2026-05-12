package com.forage.service.impl;

import com.forage.model.TypeDevis;
import com.forage.service.TypeDevisService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeDevisServiceImpl implements TypeDevisService {

    private final InMemoryStore store;

    public TypeDevisServiceImpl(InMemoryStore store) {
        this.store = store;
    }

    @Override
    public List<TypeDevis> listerTypesDevis() {
        return new ArrayList<>(store.getTypesDevis());
    }

    @Override
    public TypeDevis getTypeDevis(Long id) {
        return store.getTypesDevis().stream()
                .filter(type -> type.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
