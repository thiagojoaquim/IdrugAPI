/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import br.ufs.dcomp.idrug.webservice.FacadeWS;
import br.ufs.dcomp.idrug.webservice.IdrugWSGenerico;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author thiag
 */
@Path("/api/seguranca")
public class SegurancaResource extends IdrugWSGenerico {

    @Context
    private UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/validar")
    public UsuarioTO validarUsuario(ValidarUsuarioTO validarUsuarioTO) throws IdrugException {
        return super.validarUsuario(validarUsuarioTO);
    }
}
