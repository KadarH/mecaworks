package me.kadarh.mecaworks.domain.user;

import lombok.Data;

@Data
public class Quantite {

    private Long quantite = 0L;
    private Long quantiteLocation = 0L;
    private Long chargeTotale = 0L;
    private Long chargeLocataire = 0L;
    private Float prix;

    public Quantite(Long quantite, Long quantiteLocation) {
        this.quantite = quantite;
        this.quantiteLocation = quantiteLocation;
    }

    public Quantite() {
    }
}
