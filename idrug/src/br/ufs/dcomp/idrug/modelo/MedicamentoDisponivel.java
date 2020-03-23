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
@Table(name = "medicamento_disponivel")
public class MedicamentoDisponivel implements EntidadeBase {
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "farmacia_cnpj")
    private Farmacia farmacia;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medicamento_produto")
    @JoinColumn(name = "medicamento_dosagem")
    private Medicamento medicamento;
    private Date dataValidade;
    @Id
    @Column(name = "id_medicamento_disponivel")
    private int id;
    @Column(name = "quantidade_disponivel")
    private int quantidade;

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

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
