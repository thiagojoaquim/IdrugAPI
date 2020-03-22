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

    public void cadastrarInteresse(Interesse interesse) throws IdrugException {
        try {
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            interesseDAO.salvar(interesse);
        } catch (Exception ex) {
           throw IdrugExceptionHelper.criarExcecao(Excecao.ERRO_GENERICO, ex);
        }
    }

    public List<Interesse> resgatarInteresse(String cpf) throws IdrugException {
        try {
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            return interesseDAO.resgatar(cpf);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.ERRO_GENERICO, ex);
        }
    }
}
