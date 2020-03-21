package br.ufs.dcomp.idrug.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements EntidadeBase, TipoUsuario {
    @Column(name = "nomeusuario")
    private String nome;
    @Column(name = "datacadastro")
    private Date dataCadastro;
    @Column(name = "senhausuario")
    private String senha;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;
    @Column(name = "isativo")
    private boolean isAtivo;
    @Column(name = "email")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date data) {
        this.dataCadastro = data;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsativo(boolean isativo) {
        this.isAtivo = isativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }                
}
