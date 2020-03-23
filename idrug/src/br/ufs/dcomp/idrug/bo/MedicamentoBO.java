/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.MedicamentoDAO;
import br.ufs.dcomp.idrug.dao.MedicamentoDisponivelDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Medicamento;
import br.ufs.dcomp.idrug.modelo.MedicamentoDisponivel;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.List;

/**
 *
 * @author thiag
 */
public class MedicamentoBO extends GenericoBO {

    private static MedicamentoBO instancia;

    private MedicamentoBO() {
    }

    public static MedicamentoBO getInstancia() {
        if (Validar.nulo(instancia)) {
            instancia = new MedicamentoBO();
        }
        return instancia;
    }

    public List<Medicamento> resagatarMedicamentos() throws IdrugException {
        try {
            MedicamentoDAO medicamentoDAO = (MedicamentoDAO) getFabricaDAO().criar(MedicamentoDAO.class);
            List<Medicamento> medicamento = medicamentoDAO.resgatarMedicamentos();
            return medicamento;
        } catch (IdrugException idrugException) {
            throw idrugException;
        } catch (Exception exception) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.NAO_FOI_POSSIVEL_RESGATAR_MEDICAMENTOS, exception);
        }
    }

    public List<MedicamentoDisponivel> resgatarMedicamentosDisponiveis(String cnpj) throws IdrugException {
        try {
            MedicamentoDisponivelDAO medicamentoDisponivelDAO = (MedicamentoDisponivelDAO) getFabricaDAO()
                    .criar(MedicamentoDisponivelDAO.class);
            List<MedicamentoDisponivel> medicamentos = medicamentoDisponivelDAO.resgatarMedicamentosDisponiveis(cnpj);
            return medicamentos;
        } catch (IdrugException idrugException) {
            throw idrugException;
        } catch (Exception exception) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.NAO_FOI_POSSIVEL_RESGATAR_MEDICAMENTOS, exception);
        }
    }

    public void cadastrarMedicamentosDisponiveis(MedicamentoDisponivel medicamentoDisponivel) throws IdrugException {
        try {
            MedicamentoDisponivelDAO medicamentoDisponivelDAO = (MedicamentoDisponivelDAO) getFabricaDAO()
                    .criar(MedicamentoDisponivelDAO.class);
            medicamentoDisponivelDAO.salvar(medicamentoDisponivel);
        } catch (Exception exception) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.NAO_FOI_POSSIVEL_RESGATAR_MEDICAMENTOS, exception);
        }
    }
}
