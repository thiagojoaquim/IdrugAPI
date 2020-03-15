/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.UsuarioDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.Farmacia;
import br.ufs.dcomp.idrug.to.Paciente;
import br.ufs.dcomp.idrug.to.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import br.ufs.dcomp.idrug.util.Validar;

/**
 *
 * @author thiag
 */
public class SegurancaBO extends GenericoBO {

    private SegurancaBO instancia;

    private SegurancaBO() {
        super();
    }

    public SegurancaBO getInstancia() {

        if (instancia == null) {
            instancia = new SegurancaBO();
        }
        return instancia;
    }

    private String hashCode(String mensagem) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(mensagem.getBytes("utf8"));
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }

    public String validarUsuario(String identificador, String senha) throws IdrugException {

        try {
            Usuario usuario = (Usuario) getFabricaTO().criar(Usuario.class);
            String hashSenha = hashCode(senha + identificador);
            if (hashSenha.equals(senha)) {
                usuario.setSenha(hashSenha);
                if (Validar.cpf(identificador)) {
                    Paciente paciente = (Paciente) getFabricaTO().criar(Paciente.class);
                    paciente.setCpf(identificador);
                    paciente.setUsuario(usuario);
                }
            } else {
                throw new IdrugException(Excecao.USUARIO_SENHA_INVALIDO.mensagem);
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void cadastrarFarmacia(Farmacia farmacia) throws IdrugException {

        try {
            UsuarioDAO farmaciaDAO = (UsuarioDAO) getFabricaDAO().criar(UsuarioDAO.class);
            if (!Validar.cnpj(farmacia.getCnpj())) {
                throw new IdrugException(Excecao.CNPJ_INVALIDO.mensagem);
            }
            farmacia.getUsuario().setSenha(hashCode(farmacia.getUsuario().getNome() + farmacia.getUsuario().getSenha()));

            farmaciaDAO.salvar(farmacia);
        } catch (IdrugException idrugException) {
            throw idrugException;
        } catch (Exception exception) {
            throw new IdrugException(Excecao.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO.mensagem, exception.getCause());
        } finally {

        }
    }

    public String cadastrarPaciente(Paciente paciente) throws IdrugException {

        try {
            UsuarioDAO pacienteDAO = (UsuarioDAO) getFabricaDAO().criar(UsuarioDAO.class);
            if (!Validar.cpf(paciente.getCpf())) {
                throw new IdrugException(Excecao.CPF_INVALIDO.mensagem);
            }
            paciente.getUsuario().setSenha(hashCode(paciente.getUsuario().getNome() + paciente.getUsuario().getSenha()));
            pacienteDAO.salvar(paciente);
        } catch (Exception exception) {
            throw new IdrugException(Excecao.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO.mensagem, exception.getCause());
        }
        return null;
    }
}
