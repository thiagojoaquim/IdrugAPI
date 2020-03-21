/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.exception.IdrugException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author thiag
 */
public class IdrugExceptionWrapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
        ErroIdrug erroRest = new ErroIdrug(exception.getMessage());
        if (exception instanceof IdrugException) {
            httpStatus = Response.Status.BAD_REQUEST;
            IdrugException secException = (IdrugException) exception;
            erroRest.setCodigo(secException.getCodigo());
        } else if (exception instanceof NullPointerException) {
            httpStatus = Response.Status.BAD_REQUEST;
            erroRest.setCodigo(Excecao.DADOS_INFORMADOS_INCORRETOS.codigo);
            erroRest.setMensagem(Excecao.DADOS_INFORMADOS_INCORRETOS.mensagem);
        } else if (exception instanceof UnsupportedOperationException) {
            httpStatus = Response.Status.NOT_IMPLEMENTED;
        }
        return Response.status(httpStatus).type(MediaType.APPLICATION_JSON).entity(erroRest).build();
    }

}
