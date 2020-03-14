package br.ufs.dcomp.idrug.exception;

public class IdrugException extends Exception {

    public IdrugException(String mensagem) {
        super(mensagem);
    }
    
     public IdrugException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
