package br.ufs.dcomp.idrug.to;

import java.util.Date;

/**
 *
 * @author thiag
 */
public class InteresseTO {

    private String cpf;
    private String produto;
    private String dosagem;
    private Date dataInteresse;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(Date dataInteresse) {
        this.dataInteresse = dataInteresse;
    }        

}
