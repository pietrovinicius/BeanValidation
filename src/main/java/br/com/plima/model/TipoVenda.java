package br.com.plima.model;

public enum TipoVenda {

    VendaBrinde("Brinde"),
    VendaPadrao("Padrao");

    private String tipo;

    TipoVenda(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
