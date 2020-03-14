/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.to;

import java.util.List;

/**
 *
 * @author thiag
 */
public class Medicamento {
    
    private String produto;
    private Long dosagem;
    private List<String> substancias;
    private String tarja;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Long getDosagem() {
        return dosagem;
    }

    public void setDosagem(Long dosagem) {
        this.dosagem = dosagem;
    }

    public List<String> getSubstancias() {
        return substancias;
    }

    public void setSubstancias(List<String> substancias) {
        this.substancias = substancias;
    }

    public String getTarja() {
        return tarja;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }
}
