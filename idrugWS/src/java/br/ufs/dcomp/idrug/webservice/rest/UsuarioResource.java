/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.webservice.IdrugWSGenerico;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thiag
 */
public class UsuarioResource extends IdrugWSGenerico {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    @Path("/cadastrar-paciente")
    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException {
        super.cadastrarPaciente(pacienteTO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    @Path("/cadastrar-farmacia")
    public void cadastrarFarmacia(FarmaciaTO farmaciaTO) throws IdrugException {
        super.cadastrarFarmacia(farmaciaTO);
    }

}
