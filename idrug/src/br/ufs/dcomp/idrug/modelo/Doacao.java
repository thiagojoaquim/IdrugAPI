package br.ufs.dcomp.idrug.modelo;

import java.util.Date;

public class Doacao implements EntidadeBase {
    
    private Paciente paciente = new Paciente();
    private Farmacia farmacia = new Farmacia();
    private Medicamento medicamento = new Medicamento();
    private Date dataDoacao;

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
    
    
}
