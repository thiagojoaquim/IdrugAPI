/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Paciente;

/**
 *
 * @author thiag
 */
public class PacienteDAO extends UsuarioDAO<Paciente> {

    public Paciente resgatarPaciente(Paciente paciente) {
        String sql = " SELECT * FROM idrugdb.USUARIO u JOIN idrugdb.PACIENTE f on ( f.usuario_idusuario = U.idusuario) "
                + "where CPF = :identificador and senha = :senha";
        return super.resgatarUsuario(paciente.getCpf(), paciente.getUsuario().getSenha(), sql, Paciente.class);

    }
}
