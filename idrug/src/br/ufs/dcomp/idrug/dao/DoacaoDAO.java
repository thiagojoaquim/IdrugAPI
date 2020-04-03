/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Doacao;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author thiag
 */
public class DoacaoDAO extends GenericoDAO<Doacao> {

    @Override
    public void salvar(Doacao entidade) throws Exception {
        try {
            comecarTransacao();
            Query query = getEntityManager().createNativeQuery("INSERT INTO idrugdb.DOACOES( PACIENTE_CPF,"
                    + " MEDICAMENTO_PRODUTO, MEDICAMENTO_DOSAGEM, DATADOACAO, FARMACIA_CNPJ) values(?,?,?,?,?)");
            query.setParameter(1, entidade.getPaciente().getCpf());
            query.setParameter(2, entidade.getMedicamento().getProduto());
            query.setParameter(3, entidade.getMedicamento().getDosagem());
            query.setParameter(4, new Date());
            query.setParameter(5, entidade.getFarmacia().getCnpj());
            query.executeUpdate();
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            fecharConexao();
        }
    }

    public List<Doacao> resgatarDoacoes(String identificador) throws Exception {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("select * from idrugdb.doacoes where paciente_cpf = ? or farmacia_cnpj = ?", Doacao.class);
        query.setParameter(1, identificador);
        query.setParameter(2, identificador);
        List<Doacao> lista = query.getResultList();
        commitar();
        return lista;
    }

    @Override
    public void deletar(Doacao entidade) throws Exception {
        try {
            comecarTransacao();
            Query query = getEntityManager().createNativeQuery("DELETE FROM idrugdb.doacoes where id_doacao = ?");
            query.setParameter(1, entidade.getId());
            query.executeUpdate();
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            fecharConexao();
        }
    }
}
