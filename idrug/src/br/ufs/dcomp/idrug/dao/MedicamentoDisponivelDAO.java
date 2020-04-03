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

    public void inserirOuAtualizarMedicamentoDisponivel(MedicamentoDisponivel medicamentoDisponivel) {
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
            getEntityManager().persist(medicamentoDisponivel);
        }
    }

}
