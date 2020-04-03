/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.ColetaTO;
import br.ufs.dcomp.idrug.to.ConfirmarColetaTO;
import br.ufs.dcomp.idrug.to.DoacaoTO;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.to.MedicamentoDisponivelTO;
import br.ufs.dcomp.idrug.webservice.IdrugWSGenerico;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/coleta/confirmar")
    public void confirmarColeta(ConfirmarColetaTO confirmarColetaTO) throws IdrugException {
        super.confirmarColeta(confirmarColetaTO.getIdColeta(), confirmarColetaTO.getSituacao());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/interesse/resgatar")
    public List<InteresseTO> resgatarInteresses(@QueryParam("cpf") String cpf) throws IdrugException {
        return super.resgatarInteresses(cpf);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    @Path("/interesse/deletar")
    public void removerInteresse(@QueryParam("id") int id) throws IdrugException {
        super.removerInteresse(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/resgatar")
    public List<DoacaoTO> resgatarDoacoes(@QueryParam("identificador") String identificador) throws IdrugException {
        return super.resgatarDoacoes(identificador);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/coleta/resgatar")
    public List<ColetaTO> resgatarColetas(@QueryParam("identificador") String identificador) throws IdrugException {
        return super.resgatarColetas(identificador);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/coleta/resgatar")
    public List<MedicamentoDisponivelTO> resgatarMedicamentosDisponiveis(@QueryParam("cnpj") String cnpj) throws IdrugException {
        return super.resgatarMedicamentosDisponiveis(cnpj);
    }
}
