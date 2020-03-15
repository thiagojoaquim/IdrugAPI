/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.to.TipoUsuario;
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
                .append(" FROM idrugdb.USUARIO U LEFT JOIN idrugdb.FARMACIA F ON (u.IDUSUARIO = F.USUARIO_IDUSUARIO)")
                .append(" LEFT JOIN idrugdb.PACIENTE P ON (u.IDUSUARIO = P.USUARIO_IDUSUARIO)")
                .append(" Where (CPF = :id or CNPJ = :id2) AND senhausuario = :senha");
        Query query = getEntityManager().createNativeQuery(sqlQuery.toString());
        query.setParameter("id", identificador);
        query.setParameter("id2", identificador);
        query.setParameter("senha", senha);
       return query.getResultList().size() > 0;

    }
  

}
