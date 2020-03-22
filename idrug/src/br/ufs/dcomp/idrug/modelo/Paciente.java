package br.ufs.dcomp.idrug.modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "paciente")
public class Paciente implements EntidadeBase, TipoUsuario {
    @Id
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "datanascimento")
    private Date dataNascimento;
    @OneToOne(cascade = {CascadeType.ALL})
     @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    private Usuario usuario = new Usuario();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
