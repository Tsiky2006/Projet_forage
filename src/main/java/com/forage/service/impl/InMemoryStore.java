package com.forage.service.impl;

import com.forage.model.Demande;
import com.forage.model.Devis;
import com.forage.model.Statut;
import com.forage.model.StatutDemande;
import com.forage.model.TypeDevis;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryStore {
    private final AtomicLong demandeIdSequence = new AtomicLong(0);
    private final AtomicLong referenceSequence = new AtomicLong(0);
    private final AtomicLong statutDemandeIdSequence = new AtomicLong(0);
    private final AtomicLong devisIdSequence = new AtomicLong(0);
    private final AtomicLong detailIdSequence = new AtomicLong(0);
    private final List<Statut> statuts = new ArrayList<>();
    private final List<TypeDevis> typesDevis = new ArrayList<>();
    private final Map<Long, Demande> demandes = new LinkedHashMap<>();
    private final List<StatutDemande> statutDemandes = new ArrayList<>();
    private final List<Devis> devis = new ArrayList<>();

    public InMemoryStore() {
        statuts.add(createStatut(1L, "Demande créée"));
        statuts.add(createStatut(2L, "Devis créé"));
        statuts.add(createStatut(3L, "Étude en cours"));
        statuts.add(createStatut(4L, "Forage suspendu"));
        statuts.add(createStatut(5L, "Demande clôturée"));

        typesDevis.add(createTypeDevis(1L, "Étude"));
        typesDevis.add(createTypeDevis(2L, "Forage"));
    }

    public Long nextDemandeId() {
        return demandeIdSequence.incrementAndGet();
    }

    public Long nextStatutDemandeId() {
        return statutDemandeIdSequence.incrementAndGet();
    }

    public Long nextDevisId() {
        return devisIdSequence.incrementAndGet();
    }

    public Long nextDetailsDevisId() {
        return detailIdSequence.incrementAndGet();
    }

    public String nextDemandeReference() {
        int sequence = (int) referenceSequence.incrementAndGet();
        String year = String.valueOf(LocalDate.now().getYear());
        return String.format("DEM-%s-%04d", year, sequence);
    }

    public List<Statut> getStatuts() {
        return statuts;
    }

    public List<TypeDevis> getTypesDevis() {
        return typesDevis;
    }

    public Map<Long, Demande> getDemandes() {
        return demandes;
    }

    public List<StatutDemande> getStatutDemandes() {
        return statutDemandes;
    }

    public List<Devis> getDevis() {
        return devis;
    }

    private Statut createStatut(Long id, String nom) {
        Statut statut = new Statut();
        statut.setId(id);
        statut.setNom(nom);
        return statut;
    }

    private TypeDevis createTypeDevis(Long id, String nom) {
        TypeDevis typeDevis = new TypeDevis();
        typeDevis.setId(id);
        typeDevis.setNom(nom);
        return typeDevis;
    }
}
