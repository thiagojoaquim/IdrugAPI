/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Medicamento;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author thiag
 */
public class MedicamentoDAO extends GenericoDAO<Medicamento> {

    public MedicamentoDAO() {
    }

    public List<Medicamento> resgatarMedicamentos() {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT DISTINCT ON (produto) produto,dosagem,substancia,tarja FROM idrugdb.medicamento", Medicamento.class);
        List<Medicamento> medicamentos = query.getResultList();
        commitar();
        return medicamentos;
    }

}
