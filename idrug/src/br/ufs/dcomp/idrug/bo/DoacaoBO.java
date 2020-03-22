/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.InteresseDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Interesse;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.List;

/**
 *
 * @author thiag
 */
public class DoacaoBO extends GenericoBO {

    private static DoacaoBO instancia;

    public static DoacaoBO getInstancia() {
        if (instancia == null) {
            instancia = new DoacaoBO();
        }
        return instancia;
    }

    private void validarInteresse(Interesse interesse) throws IdrugException {
        if (!Validar.cpf(interesse.getPaciente().getCpf())) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.CPF_INVALIDO);
        }
        if (Validar.nulo(interesse.getMedicamento().getDosagem())
                || Validar.nulo(interesse.getMedicamento().getDosagem())) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.DADOS_INFORMADOS_INCORRETOS);
        }
    }

    public void cadastrarInteresse(Interesse interesse) throws IdrugException {
        try {
            if (!Validar.cpf(interesse.getPaciente().getCpf())) {
                throw IdrugExceptionHelper.criarExcecao(Excecao.CPF_INVALIDO);
            }
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            interesseDAO.salvar(interesse);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.ERRO_GENERICO, ex);
        }
    }

    public List<Interesse> resgatarInteresse(String cpf) throws IdrugException {
        try {
            if (!Validar.cpf(cpf)) {
                throw IdrugExceptionHelper.criarExcecao(Excecao.CPF_INVALIDO);
            }
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            return interesseDAO.resgatar(cpf);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.getExcecao(ex);
        }
    }

    public void deletarInteresse(Interesse interesse) throws IdrugException {
        validarInteresse(interesse);
        try {
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            interesseDAO.deletar(interesse);
        } catch (Exception exception) {
           throw IdrugExceptionHelper.getExcecao(exception);
        }
    }
}
