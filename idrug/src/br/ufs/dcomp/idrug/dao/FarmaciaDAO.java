/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Farmacia;

/**
 *
 * @author thiag
 */
public class FarmaciaDAO extends UsuarioDAO<Farmacia> {

    public Farmacia resgatarFarmacia(Farmacia farmacia) {
        String sql = " SELECT * FROM idrugdb.USUARIO u JOIN idrugdb.FARMACIA f on ( f.usuario_idusuario = U.idusuario) "
                + "where cnpj = :identificador and senhausuario = :senha";
        return super.resgatarUsuario(farmacia.getCnpj(), farmacia.getUsuario().getSenha(), sql, Farmacia.class);
    }
}
