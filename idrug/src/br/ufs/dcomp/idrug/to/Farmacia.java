/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.to;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author thiag
 */

@Table(name = "farmacia")
public class Farmacia implements EntidadeBase {
    @Column(name = "cnpj")
    private String cnpj;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "usuario_idusuario")
    private Usuario usuario;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
}
