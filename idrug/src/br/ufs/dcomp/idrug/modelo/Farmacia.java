/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "farmacia")
public class Farmacia implements EntidadeBase, TipoUsuario {
    @Id
    @Column(name = "cnpj")
    private String cnpj;
    @OneToOne
   @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    private Usuario usuario = new Usuario();
  

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
