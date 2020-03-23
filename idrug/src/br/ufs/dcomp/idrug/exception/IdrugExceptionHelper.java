/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.exception;

import br.ufs.dcomp.idrug.constantes.Excecao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiag
 */
public class IdrugExceptionHelper {

    public static IdrugException getExcecao(Exception ex) throws IdrugException {
        if (ex instanceof IdrugException) {
            return (IdrugException) ex;
        } else {
            Logger.getLogger(IdrugExceptionHelper.class.getName()).log(Level.SEVERE, ex.getMessage());
            return new IdrugException(Excecao.ERRO_GENERICO.mensagem, Excecao.ERRO_GENERICO.codigo, ex.getCause());
        }

    }

    public static IdrugException criarExcecao(Excecao excecao, Exception ex) throws IdrugException {
        if (ex instanceof IdrugException) {
            return (IdrugException) ex;
        } else {
            Logger.getLogger(IdrugExceptionHelper.class.getName()).log(Level.SEVERE, ex.getMessage());
            return new IdrugException(excecao.mensagem, excecao.codigo, ex.getCause());
        }
    }

    public static IdrugException criarExcecao(Excecao excecao) throws IdrugException {
        return new IdrugException(excecao.mensagem, excecao.codigo);
    }
}
