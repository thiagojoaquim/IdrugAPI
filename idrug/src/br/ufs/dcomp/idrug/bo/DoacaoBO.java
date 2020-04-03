/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Constantes;
import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.ColetaDAO;
import br.ufs.dcomp.idrug.dao.DoacaoDAO;
import br.ufs.dcomp.idrug.dao.InteresseDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Coleta;
import br.ufs.dcomp.idrug.modelo.Doacao;
import br.ufs.dcomp.idrug.modelo.Interesse;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.Date;
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

    public void deletarInteresse(int id) throws IdrugException {
        try {
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            interesseDAO.deletar(id);
        } catch (Exception exception) {
            throw IdrugExceptionHelper.getExcecao(exception);
        }
    }

    public List<Doacao> resgatarDoacoes(String identificador) throws IdrugException {
        if (!Validar.cpf(identificador) && !Validar.cnpj(identificador)) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.DADOS_INFORMADOS_INCORRETOS);
        }
        try {
            DoacaoDAO doacaoDAO = (DoacaoDAO) getFabricaDAO().criar(DoacaoDAO.class);
            return doacaoDAO.resgatarDoacoes(identificador);
        } catch (Exception e) {
            throw IdrugExceptionHelper.getExcecao(e);
        }
    }

    public List<Coleta> resgatarColetas(String identificador) throws IdrugException {
        if (!Validar.cpf(identificador) || !Validar.cnpj(identificador)) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.DADOS_INFORMADOS_INCORRETOS);
        }
        try {
            ColetaDAO coletaDAO = (ColetaDAO) getFabricaDAO().criar(ColetaDAO.class);
            return coletaDAO.resgatarColetas(identificador);
        } catch (Exception e) {
            throw IdrugExceptionHelper.getExcecao(e);
        }
    }


    public void confirmarColeta(int idColeta, int situacao) throws IdrugException {

        try {
            ColetaDAO coletaDAO = (ColetaDAO) getFabricaDAO().criar(ColetaDAO.class);
            if (!coletaDAO.confirmarColeta(idColeta, situacao)) {
                throw IdrugExceptionHelper.criarExcecao(Excecao.COLETA_NAO_CONFIRMADA);
            }
            if (situacao == Constantes.STATUS_COLETA_CONCLUIDA) {
                DoacaoDAO doacaoDAO = (DoacaoDAO) getFabricaDAO().criar(DoacaoDAO.class);
                Doacao doacao = (Doacao) getFabricaModelo().criar(Doacao.class);
                Coleta coleta = coletaDAO.resgatarColeta(idColeta);
                doacao.setFarmacia(coleta.getFarmacia());
                doacao.setDataDoacao(new Date());
                doacao.setPaciente(coleta.getPaciente());
                doacao.setMedicamento(coleta.getMedicamento());
                doacaoDAO.salvar(doacao);
            }
        } catch (Exception e) {
            throw IdrugExceptionHelper.getExcecao(e);
        }
    }
}
