package br.ufs.dcomp.idrug.modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doacoes")
public class Doacao implements EntidadeBase {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "paciente_cpf", referencedColumnName = "cpf")
    private Paciente paciente = new Paciente();
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "farmacia_cnpj", referencedColumnName = "cnpj")
    private Farmacia farmacia = new Farmacia();
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medicamento_produto", referencedColumnName = "produto")
    @JoinColumn(name = "medicamento_dosagem", referencedColumnName = "dosagem")
    private Medicamento medicamento = new Medicamento();
    @Column(name = "datadoacao")
    private Date dataDoacao;
    @Id
    @Column(name = "id_doacao")
    private int id;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
