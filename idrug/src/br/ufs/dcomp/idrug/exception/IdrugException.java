package br.ufs.dcomp.idrug.exception;

public class IdrugException extends Exception {

    private String codigo;
    public IdrugException(String mensagem, String codigo) {
        super(mensagem);
        this.codigo = codigo;
    }
    
    public IdrugException(String mensagem, String codigo, Throwable cause) {
        super(mensagem, cause);
        this.codigo = codigo;
    }
        
     public IdrugException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
     
     
}
