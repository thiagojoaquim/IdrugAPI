/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento")
public class Medicamento implements EntidadeBase{
    @Id
    private String produto;
    @Id
    private String dosagem;
    private String substancia;
    private String tarja;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getSubstancias() {
        return substancia;
    }

    public void setSubstancias(String substancias) {
        this.substancia = substancias;
    }

    public String getTarja() {
        return tarja;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }
}
