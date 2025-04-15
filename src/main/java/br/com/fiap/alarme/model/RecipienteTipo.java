package br.com.fiap.alarme.model;

public enum RecipienteTipo {
    VIDRO("vidro"),
    PLASTICO("plástico"),
    PAPEL("papel"),
    METAL("metal");

    private String tipo;

    RecipienteTipo(String status){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}