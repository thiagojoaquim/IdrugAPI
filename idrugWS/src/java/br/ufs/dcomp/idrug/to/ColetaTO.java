package br.ufs.dcomp.idrug.to;

/**
 *
 * @author thiag
 */
public class ColetaTO {

    private PacienteTO paciente = new PacienteTO();
    private FarmaciaTO farmaciaTO = new FarmaciaTO();
    private int id;
    private String produto;
    private String dosagem;
    private int situacao;

    public PacienteTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteTO paciente) {
        this.paciente = paciente;
    }

    public FarmaciaTO getFarmaciaTO() {
        return farmaciaTO;
    }

    public void setFarmaciaTO(FarmaciaTO farmaciaTO) {
        this.farmaciaTO = farmaciaTO;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

}
