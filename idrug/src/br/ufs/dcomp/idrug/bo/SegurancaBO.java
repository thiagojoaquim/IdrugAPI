/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.FarmaciaDAO;
import br.ufs.dcomp.idrug.dao.PacienteDAO;
import br.ufs.dcomp.idrug.dao.UsuarioDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Paciente;
import br.ufs.dcomp.idrug.modelo.TipoUsuario;
import br.ufs.dcomp.idrug.modelo.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.Date;

/**
 *
 * @author thiag
 */
public class SegurancaBO extends GenericoBO {

    private static SegurancaBO instancia;

    private SegurancaBO() {
        super();
    }

    public static SegurancaBO getInstancia() {

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

    public TipoUsuario validarUsuario(String identificador, String senha) throws IdrugException {

        try {
            TipoUsuario usuario;
            String hashSenha = hashCode(identificador + senha);

            if (Validar.cpf(identificador)) {
                PacienteDAO pacienteDAO = (PacienteDAO) getFabricaDAO().criar(PacienteDAO.class);
                Paciente paciente = (Paciente) getFabricaTO().criar(Paciente.class);
                paciente.setCpf(identificador);
                paciente.getUsuario().setSenha(hashSenha);
                return pacienteDAO.resgatarPaciente(paciente);
            } else if (Validar.cnpj(identificador)) {
                Farmacia farmacia = (Farmacia) getFabricaTO().criar(Farmacia.class);
                FarmaciaDAO farmaciaDAO = (FarmaciaDAO) getFabricaDAO().criar(FarmaciaDAO.class);
                farmacia.setCnpj(senha);
                farmacia.getUsuario().setSenha(hashSenha);
                return farmaciaDAO.resgatarFarmacia(farmacia);

            } else {
                throw new IdrugException(Excecao.USUARIO_SENHA_INVALIDO.mensagem, Excecao.USUARIO_SENHA_INVALIDO.codigo);
            }
        } catch (Exception e) {
            throw IdrugExceptionHelper.getExcecao(e);
        }
    }

    public void cadastrarFarmacia(String cnpj, String nome, String senha, String email) throws IdrugException {
        try {
            Farmacia farmacia = (Farmacia) getFabricaTO().criar(Farmacia.class);
            UsuarioDAO farmaciaDAO = (UsuarioDAO) getFabricaDAO().criar(UsuarioDAO.class);

            if (!Validar.cnpj(cnpj)) {
                throw new IdrugException(Excecao.CNPJ_INVALIDO.mensagem, Excecao.CNPJ_INVALIDO.codigo);
            }
            farmacia.getUsuario().setSenha(hashCode(cnpj + senha));
            farmacia.setCnpj(cnpj);
            farmacia.getUsuario().setNome(nome);
            farmacia.getUsuario().setDataCadastro(new Date());
            farmacia.getUsuario().setEmail(email);
            farmaciaDAO.salvar(farmacia);
        } catch (IdrugException idrugException) {
            throw idrugException;
        } catch (Exception exception) {
            throw new IdrugException(Excecao.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO.mensagem, exception.getCause());
        } finally {

        }
    }

    public String cadastrarPaciente(String cpf, String nome, String senha, String email, Date dataNascimento) throws IdrugException {

        try {
            Paciente paciente = (Paciente) getFabricaTO().criar(Paciente.class);
            UsuarioDAO pacienteDAO = (UsuarioDAO) getFabricaDAO().criar(UsuarioDAO.class);
            if (!Validar.cpf(cpf)) {
                throw new IdrugException(Excecao.CPF_INVALIDO.mensagem, Excecao.CPF_INVALIDO.codigo);
            }
            paciente.getUsuario().setSenha(hashCode(cpf + senha));
            paciente.setCpf(cpf);
            paciente.getUsuario().setNome(nome);
            paciente.getUsuario().setDataCadastro(new Date());
            paciente.getUsuario().setEmail(email);
            paciente.setDataNascimento(dataNascimento);
            pacienteDAO.salvar(paciente);
        } catch (IdrugException idrugException) {
            throw idrugException;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new IdrugException(Excecao.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO.mensagem, exception.getCause());
        }
        return null;
    }
}
