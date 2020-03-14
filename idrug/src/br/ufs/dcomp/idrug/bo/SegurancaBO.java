/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.constantes.FabricaConstantes;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.factory.FabricaAbstrata;
import br.ufs.dcomp.idrug.factory.FabricaProvedor;
import br.ufs.dcomp.idrug.to.Usuario;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jdk.internal.util.xml.impl.Input;

/**
 *
 * @author thiag
 */
public class SegurancaBO extends GenericoBO{
    
    private SegurancaBO instancia;
    
    private SegurancaBO() {
    }
    
    public SegurancaBO getInstancia() {
        
        if(instancia == null){
            instancia = new SegurancaBO();
        }
        return instancia;
    }
    
    public static String gerarHash(String mensagem) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(mensagem.getBytes("utf8"));
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }
    
    public String validarUsuario(String identificador, String senha) throws IdrugException {
       
        try {
            FabricaAbstrata fabricaDAO = FabricaProvedor.getFactory(FabricaConstantes.FABRICA_DAO);
            FabricaAbstrata fabricaTO = FabricaProvedor.getFactory(FabricaConstantes.FABRICA_TO);
            String hashSenha = gerarHash(senha + identificador);
            if(hashSenha.equals(senha)) {
                
                Usuario usuarioTO = (Usuario) fabricaTO.criar(Usuario.class);
               // usuario.set
                
            } else {
                throw new IdrugException(Excecao.USUARIO_SENHA_INVALIDO.mensagem);
            }
                
            
        } catch (Exception e) {
        }
        return null;
    }
}
