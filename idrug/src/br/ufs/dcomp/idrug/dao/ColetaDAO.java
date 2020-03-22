package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Coleta;
import java.util.List;
import javax.persistence.Query;

public class ColetaDAO extends GenericoDAO<Coleta> {

    public List<Coleta> resgatarColetas(String cpf) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM idrugdb.coleta where situacao = 1 and cpf = ?", Coleta.class);
        query.setParameter(1, cpf);
        List<Coleta> coletas = query.getResultList();
        commitar();
        return coletas;
    }

}
