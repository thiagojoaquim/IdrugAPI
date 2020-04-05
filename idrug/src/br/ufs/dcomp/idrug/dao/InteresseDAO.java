package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Interesse;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

public class InteresseDAO extends GenericoDAO<Interesse> {

    @Override
    public void salvar(Interesse entidade) throws Exception {
        try {
            comecarTransacao();
            Query query = getEntityManager().createNativeQuery("INSERT INTO idrugdb.REGISTRO_INTERESSE( PACIENTE_CPF,"
                    + " MEDICAMENTO_PRODUTO, MEDICAMENTO_DOSAGEM, DATAINTERESSE) values(?,?,?,?)");
            query.setParameter(1, entidade.getPaciente().getCpf());
            query.setParameter(2, entidade.getMedicamento().getProduto());
            query.setParameter(3, entidade.getMedicamento().getDosagem());
            query.setParameter(4, new Date());
            query.executeUpdate();
            commitar();
            fecharConexao();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public List<Interesse> resgatar(String cpf) throws Exception {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("select * from idrugdb.registro_interesse where paciente_cpf = ?", Interesse.class);
        query.setParameter(1, cpf);
        List<Interesse> lista = query.getResultList();
        commitar();
        fecharConexao();
        return lista;

    }

    public Interesse resgatar(String produto, String dosagem) throws Exception {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("select * from idrugdb.registro_interesse where medicamento_produto = ? "
                + "and medicamento_dosagem = ? order by datainteresse limit 1", Interesse.class);
        query.setParameter(1, produto);
        query.setParameter(2, dosagem);
        List<Interesse> lista = query.getResultList();
        commitar();
        fecharConexao();
        if (lista.size() > 0) {
            return lista.get(0);
        }
        return null;

    }

    public void deletar(int id) throws Exception {
        try {
            comecarTransacao();
            Query query = getEntityManager().createNativeQuery("DELETE FROM idrugdb.registro_interesse where id_interesse = ?");
            query.setParameter(1, id);
            query.executeUpdate();
            commitar();
            fecharConexao();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }
}
