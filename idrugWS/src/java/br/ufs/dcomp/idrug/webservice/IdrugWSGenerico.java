/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.List;

/**
 *
 * @author thiag
 */
public class IdrugWSGenerico implements IdrugWS {

    private FacadeWS facade;

    public IdrugWSGenerico() {
        facade = new FacadeWS();
    }

    @Override
    public void cadastrarFarmacia(FarmaciaTO farmaciaTO) throws IdrugException {
        facade.cadastrarFarmacia(farmaciaTO);
    }

    @Override
    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException {
        facade.cadastrarPaciente(pacienteTO);
    }

    @Override
    public UsuarioTO validarUsuario(ValidarUsuarioTO validarUsuarioTO) throws IdrugException {
        return facade.validarUsuario(validarUsuarioTO);
    }

    @Override
    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException {
        return facade.resgatarMedicamentos();
    }
}
