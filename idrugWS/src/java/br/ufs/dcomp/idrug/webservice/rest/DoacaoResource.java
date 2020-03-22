/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.webservice.IdrugWSGenerico;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author thiag
 */
@Path("/api/doacao")
public class DoacaoResource extends IdrugWSGenerico {

    @Context
    private UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    @Path("/interesse/cadastrar")
    public void cadastrarInteresse(InteresseTO interesseTO) throws IdrugException {
        super.cadastrarInteresse(interesseTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/interesse/resgatar")
    public List<InteresseTO> resgatarInteresses(@QueryParam("cpf") String cpf) throws IdrugException {
        return super.resgatarInteresses(cpf);
    }

}
