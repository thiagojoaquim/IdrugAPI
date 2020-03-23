package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.MedicamentoDisponivelTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.webservice.IdrugWSGenerico;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/api/medicamento")
public class MedicamentoResource extends IdrugWSGenerico {

    @Context
    private UriInfo context;

    public MedicamentoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/resgatar")
    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException {
        return super.resgatarMedicamentos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/resgatar-disponiveis")
    public List<MedicamentoDisponivelTO> resgatarMedicamentosDisponiveis(@QueryParam("cnpj") String cnpj) throws IdrugException {
        return super.resgatarMedicamentosDisponiveis(cnpj);
    }
}
