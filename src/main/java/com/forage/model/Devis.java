package com.forage.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Devis {
    private Long id;
    private Demande demande;
    private TypeDevis typeDevis;
    private String observation;
    private LocalDateTime dateCreation;
    private List<DetailsDevis> lignes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<DetailsDevis> getLignes() {
        return lignes;
    }

    public void setLignes(List<DetailsDevis> lignes) {
        this.lignes = lignes;
    }

    public double getMontantTotal() {
        return lignes.stream()
                .mapToDouble(DetailsDevis::getSousTotal)
                .sum();
    }
}
