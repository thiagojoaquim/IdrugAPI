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

}
