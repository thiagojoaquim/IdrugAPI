/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;

/**
 *
 * @author thiag
 */
public class IdrugWSGenerico implements IdrugWS {

    @Override
    public void cadastrarFarmacia(FarmaciaTO farmaciaTO) throws IdrugException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarUsuario(UsuarioTO usuarioTO) throws IdrugException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
