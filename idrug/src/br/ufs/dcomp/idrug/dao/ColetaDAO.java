package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Coleta;
import java.util.List;
import javax.persistence.Query;

public class ColetaDAO extends GenericoDAO<Coleta> {

    public List<Coleta> resgatarColetas(String identificador) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM idrugdb.aguardando_coleta where situacao = 0 and (paciente_cpf = ?"
                + " or farmacia_cnpj = ?)", Coleta.class);
        query.setParameter(1, identificador);
        query.setParameter(2, identificador);
        List<Coleta> coletas = query.getResultList();
        commitar();
        fecharConexao();
        return coletas;
    }

    public Coleta resgatarColeta(int idColeta) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM idrugdb.aguardando_coleta where id_coleta = ?", Coleta.class);
        query.setParameter(1, idColeta);
        List<Coleta> coletas = query.getResultList();
        commitar();
        fecharConexao();
        return coletas.get(0);
    }

    public boolean confirmarColeta(int idColeta, int situacao) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("UPDATE idrugdb.aguardando_coleta SET situacao = ? where id_coleta = ?");
        query.setParameter(1, situacao);
        query.setParameter(2, idColeta);
        return query.executeUpdate() > 0;
    }

}
