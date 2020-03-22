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
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public List<Interesse> resgatar(String cpf) throws Exception {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("select * from idrugdb.registro_interesse where paciente_cpf = ?", Interesse.class);
        query.setParameter(1, cpf);
        return query.getResultList();

    }
    
     @Override
    public void deletar(Interesse entidade) throws Exception {
        try {
            comecarTransacao();
            Query query = getEntityManager().createNativeQuery("DELETE FROM idrugdb.registro_interesse where paciente_cpf =? and "
                    + " medicamento_produto = ? and medicamento_dosagem = ?");
            query.setParameter(1, entidade.getPaciente().getCpf());
            query.setParameter(2, entidade.getMedicamento().getProduto());
            query.setParameter(3, entidade.getMedicamento().getDosagem());
            query.executeUpdate();
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }
}
