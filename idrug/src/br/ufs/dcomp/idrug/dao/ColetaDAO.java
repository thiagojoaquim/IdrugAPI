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

    public Coleta resgatarColeta(String cnpj, String cpf, String produto, String dosagem) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("SELECT * FROM idrugdb.aguardando_coleta where paciente_cpf = ? "
                + "and farmacia_cnpj = ? and medicamento_produto = ? and medicamento_dosagem = ? and situacao = 0", Coleta.class);
        query.setParameter(1, cpf);
        query.setParameter(2, cnpj);
        query.setParameter(3, produto);
        query.setParameter(1, dosagem);
        List<Coleta> coletas = query.getResultList();
        commitar();
        fecharConexao();
        if (coletas.size() > 0) {
            return coletas.get(0);
        } else {
            return null;
        }
    }

    public boolean confirmarColeta(int idColeta, int situacao) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("UPDATE idrugdb.aguardando_coleta SET situacao = ? where id_coleta = ?");
        query.setParameter(1, situacao);
        query.setParameter(2, idColeta);
        return query.executeUpdate() > 0;
    }

    public void salvar(Coleta coleta) {
        comecarTransacao();
        Query query = getEntityManager().createNativeQuery("INSERT INTO idrugdb.aguardando_coleta(farmacia_cnpj, paciente_cpf, medicamento_produto,"
                + "medicamento_dosagem) VALUES(?,?,?,?)");
        query.setParameter(1, coleta.getFarmacia().getCnpj());
        query.setParameter(2, coleta.getPaciente().getCpf());
        query.setParameter(3, coleta.getMedicamento().getProduto());
        query.setParameter(4, coleta.getMedicamento().getDosagem());
        commitar();
        fecharConexao();

    }

}
