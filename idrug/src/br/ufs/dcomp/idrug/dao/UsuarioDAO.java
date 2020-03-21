/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Paciente;
import br.ufs.dcomp.idrug.modelo.TipoUsuario;
import br.ufs.dcomp.idrug.modelo.Usuario;
import javax.persistence.Query;

/**
 *
 * @author thiag
 * @param <T>
 */
public class UsuarioDAO<T extends TipoUsuario> extends GenericoDAO {

    public boolean validarUsuario(String identificador, String senha) {
        comecarTransacao();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT IDUSUARIO")
                .append(" FROM USUARIO U LEFT JOIN FARMACIA F ON (u.IDUSUARIO = F.USUARIO_IDUSUARIO)")
                .append(" LEFT JOIN PACIENTE P ON (u.IDUSUARIO = P.USUARIO_IDUSUARIO)")
                .append(" Where (CPF = :id or CNPJ = :id2) AND senhausuario = :senha");
        Query query = getEntityManager().createNativeQuery(sqlQuery.toString(), Paciente.class);
        query.setParameter("id", identificador);
        query.setParameter("id2", identificador);
        query.setParameter("senha", senha);
        return query.getResultList().size() > 0;
    }

    protected <T extends TipoUsuario> T resgatarUsuario(String identificador,
            String senha, String sql, Class<T> clazz) {
        Query query = getEntityManager().createNativeQuery(sql, clazz);
        query.setParameter("identificador", identificador);
        query.setParameter("Senha", senha);
        return clazz.cast(query.getResultList().get(0));

    }

}
