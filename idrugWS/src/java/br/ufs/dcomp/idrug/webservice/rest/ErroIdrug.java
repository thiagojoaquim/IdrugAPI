package br.ufs.dcomp.idrug.webservice.rest;

public class ErroIdrug {
    
    private String codigo;
    private String mensagem;

    public ErroIdrug() {
    }

    public ErroIdrug(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

       public ErroIdrug(String mensagem) {        
        this.mensagem = mensagem;
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
