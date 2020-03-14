/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.to;

/**
 *
 * @author thiag
 */
public class Anuncio implements EntidadeBase {

    private String descricao;
    private Byte[] midia;
    private Integer tipoAnuncio;
    private Farmacia farmacia;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Byte[] getMidia() {
        return midia;
    }

    public void setMidia(Byte[] midia) {
        this.midia = midia;
    }

    public Integer getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(Integer tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

}
