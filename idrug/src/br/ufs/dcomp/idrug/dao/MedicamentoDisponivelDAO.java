/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.MedicamentoDisponivel;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author thiag
 */
public class MedicamentoDisponivelDAO extends GenericoDAO<MedicamentoDisponivel> {

    public List<MedicamentoDisponivel> resgatarMedicamentosDisponiveis(String cnpj) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM"
                + " idrugdb.MEDICAMENTO_DISPONIVEL WHERE FARMACIA_CNPJ = ?", MedicamentoDisponivel.class);
        query.setParameter(1, cnpj);
        List<MedicamentoDisponivel> medicamentos = query.getResultList();
        commitar();
        fecharConexao();
        return medicamentos;
    }

    public void salvar(MedicamentoDisponivel md) throws Exception {
        Query query = getEntityManager().createNativeQuery("INSERT INTO idrugdb.medicamento_disponivel"
                + "(medicamento_produto, medicamento_dosagem, datavalidade, farmacia_cnpj, quantidade_disponivel) VALUES"
                + "( ?,?,?,?, ?)");
        query.setParameter(1, md.getMedicamento().getProduto());
        query.setParameter(2, md.getMedicamento().getDosagem());
        query.setParameter(3, md.getDataValidade());
        query.setParameter(4, md.getFarmacia().getCnpj());
        query.setParameter(5, md.getQuantidade());
        query.executeUpdate();
        commitar();
        fecharConexao();
    }

    public void inserirOuAtualizarMedicamentoDisponivel(MedicamentoDisponivel medicamentoDisponivel) throws Exception {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM idrugdb.medicamento_disponivel WHERE "
                + "medicamento_produto = ? and medicamento_dosagem = ? and datavalidade = ? and farmacia_cnpj = ?", MedicamentoDisponivel.class);
        query.setParameter(1, medicamentoDisponivel.getMedicamento().getProduto());
        query.setParameter(2, medicamentoDisponivel.getMedicamento().getDosagem());
        query.setParameter(3, medicamentoDisponivel.getDataValidade());
        query.setParameter(4, medicamentoDisponivel.getFarmacia().getCnpj());
        MedicamentoDisponivel md = null;
        if (query.getResultList().size() > 0) {
            md = (MedicamentoDisponivel) query.getResultList().get(0);
        }
        if (md == null) {
            salvar(medicamentoDisponivel);
        } else {
            query = getEntityManager().createNativeQuery("UPDATE idrugdb.medicamento_disponivel SET quantidade_disponivel = ? where farmacia_cnpj = ? "
                    + "and medicamento_dosagem = ? and datavalidade = ? and medicamento_produto = ?");
            query.setParameter(1, medicamentoDisponivel.getQuantidade().intValue());
            query.setParameter(2, medicamentoDisponivel.getFarmacia().getCnpj());
            query.setParameter(3, medicamentoDisponivel.getMedicamento().getDosagem());
            query.setParameter(4, medicamentoDisponivel.getDataValidade());
            query.setParameter(5, medicamentoDisponivel.getMedicamento().getProduto());
            query.executeUpdate();           
        }
        commitar();
        fecharConexao();
    }

    public MedicamentoDisponivel verificarDisponibilidade(String produto, String dosagem) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM"
                + " idrugdb.MEDICAMENTO_DISPONIVEL WHERE MEDICAMENTO_PRODUTO = ? AND MEDICAMENTO_DOSAGEM = ?", MedicamentoDisponivel.class);        
        query.setParameter(1, produto);
        query.setParameter(2, dosagem);
        MedicamentoDisponivel medicamentoDisponivel = null;
        List<MedicamentoDisponivel> medicamentos = query.getResultList();
        if (medicamentos.size() > 0) {
            medicamentoDisponivel = medicamentos.get(0);
        }
        commitar();
        fecharConexao();
        return medicamentoDisponivel;
    }
}
