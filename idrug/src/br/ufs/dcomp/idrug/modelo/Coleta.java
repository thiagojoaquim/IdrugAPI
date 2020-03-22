package br.ufs.dcomp.idrug.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "aguardando_coleta")
public class Coleta implements EntidadeBase {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "paciente_cpf", referencedColumnName = "cpf")
    private Paciente paciente;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medicamento_produto", referencedColumnName = "produto")
    @JoinColumn(name = "medicamento_dosagem", referencedColumnName = "dosagem")
    private Medicamento medicamento;
    @Id
    @Column(name = "id_coleta")
    private int id;
    @ManyToOne
    @JoinColumn(name = "farmacia_cnpj", referencedColumnName = "cnpj")
    private Farmacia farmacia;
    @Column(name = "situacao")
    private int situacao;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
}
