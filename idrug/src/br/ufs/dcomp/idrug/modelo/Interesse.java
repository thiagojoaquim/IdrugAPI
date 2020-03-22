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
@Table(name = "registro_interesse")
public class Interesse implements EntidadeBase {

    @Id
    @Column(name = "id_interesse")
    private int idInteresse;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "paciente_cpf", referencedColumnName = "cpf")
    private Paciente Paciente = new Paciente();
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medicamento_produto", referencedColumnName = "produto")
    @JoinColumn(name = "medicamento_dosagem", referencedColumnName = "dosagem")
    private Medicamento medicamento = new Medicamento();
    @Column(name = "datainteresse")
    private Date data;
    @Column(name = "receitamedica")
    private byte[] receita;

    public int getId() {
        return idInteresse;
    }

    public void setId(int id) {
        this.idInteresse = id;
    }

    public Paciente getPaciente() {
        return Paciente;
    }

    public void setPaciente(Paciente Paciente) {
        this.Paciente = Paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public byte[] getReceita() {
        return receita;
    }

    public void setReceita(byte[] receita) {
        this.receita = receita;
    }

    public int getIdInteresse() {
        return idInteresse;
    }

    public void setIdInteresse(int idInteresse) {
        this.idInteresse = idInteresse;
    }

}
