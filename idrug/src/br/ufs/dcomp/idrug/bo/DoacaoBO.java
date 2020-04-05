package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.Constantes;
import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.dao.ColetaDAO;
import br.ufs.dcomp.idrug.dao.DoacaoDAO;
import br.ufs.dcomp.idrug.dao.InteresseDAO;
import br.ufs.dcomp.idrug.dao.MedicamentoDisponivelDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Coleta;
import br.ufs.dcomp.idrug.modelo.Doacao;
import br.ufs.dcomp.idrug.modelo.Interesse;
import br.ufs.dcomp.idrug.modelo.MedicamentoDisponivel;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.Date;
import java.util.List;

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

    private boolean verificarMatch(String produto, String dosagem) throws Exception {
        MedicamentoDisponivelDAO medicamentoDisponivelDAO
                = (MedicamentoDisponivelDAO) getFabricaDAO().criar(MedicamentoDisponivelDAO.class);
        InteresseDAO interesseDAO
                = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
        ColetaDAO coletaDAO
                = (ColetaDAO) getFabricaDAO().criar(ColetaDAO.class);
        MedicamentoDisponivel md = medicamentoDisponivelDAO.verificarDisponibilidade(produto, dosagem);
        if (Validar.nulo(md)) {
            return false;
        }
        Interesse interesse = interesseDAO.resgatar(produto, dosagem);
        if (Validar.nulo(interesse)) {
            return false;
        }
        if (!Validar.nulo(coletaDAO.resgatarColeta(md.getFarmacia().getCnpj(), interesse.getPaciente().getCpf(), produto, dosagem))) {
            return false;
        }
        Coleta coleta = (Coleta) getFabricaModelo().criar(Coleta.class);
        coleta.getFarmacia().setCnpj(md.getFarmacia().getCnpj());
        coleta.getMedicamento().setDosagem(dosagem);
        coleta.getMedicamento().setProduto(produto);
        coleta.getPaciente().setCpf(dosagem);
        coletaDAO.salvar(coleta);
        interesseDAO.deletar(interesse.getId());
        md.setQuantidade(md.getQuantidade().intValue() - 1);
        medicamentoDisponivelDAO.inserirOuAtualizarMedicamentoDisponivel(md);

        coletaDAO.salvar(coleta);
        return true;
    }

    public void cadastrarInteresse(Interesse interesse) throws IdrugException {
        try {
            if (!Validar.cpf(interesse.getPaciente().getCpf())) {
                throw IdrugExceptionHelper.criarExcecao(Excecao.CPF_INVALIDO);
            }
            InteresseDAO interesseDAO = (InteresseDAO) getFabricaDAO().criar(InteresseDAO.class);
            interesseDAO.salvar(interesse);
           // verificarMatch(interesse.getMedicamento().getProduto(), interesse.getMedicamento().getDosagem());
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
        if (!Validar.cpf(identificador) && !Validar.cnpj(identificador)) {
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
                coleta.setSituacao(situacao);
                coletaDAO.atualizar(coleta);
            }
            if(situacao == Constantes.STATUS_COLETA_NEGADA_FARMACIA){
                Coleta coleta = coletaDAO.resgatarColeta(idColeta);
                coleta.setSituacao(situacao);
                coletaDAO.atualizar(coleta);
            }
            if(situacao == Constantes.STATUS_COLETA_NEGADA_PACIENTE){
                Coleta coleta = coletaDAO.resgatarColeta(idColeta);
                coleta.setSituacao(situacao);
                coletaDAO.atualizar(coleta);
            }
        } catch (Exception e) {
            throw IdrugExceptionHelper.getExcecao(e);
        }
    }

    public void cadastrarDisponibilidade(String produto, String dosagem, String cnpj, Date dataValidade, int quantidade) throws IdrugException {
        if (!Validar.cnpj(cnpj)) {
            throw IdrugExceptionHelper.criarExcecao(Excecao.CNPJ_INVALIDO);
        }
        try {
            MedicamentoDisponivelDAO medicamentoDisponivelDAO = (MedicamentoDisponivelDAO) getFabricaDAO().criar(MedicamentoDisponivelDAO.class);
            MedicamentoDisponivel medicamentoDisponivel = (MedicamentoDisponivel) getFabricaModelo().criar(MedicamentoDisponivel.class);
            medicamentoDisponivel.setDataValidade(dataValidade);
            medicamentoDisponivel.getFarmacia().setCnpj(cnpj);
            medicamentoDisponivel.getMedicamento().setDosagem(dosagem);
            medicamentoDisponivel.getMedicamento().setProduto(produto);
            medicamentoDisponivel.setQuantidade(quantidade);
            medicamentoDisponivelDAO.inserirOuAtualizarMedicamentoDisponivel(medicamentoDisponivel);
         //   verificarMatch(produto, dosagem);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.getExcecao(ex);
        }

    }
}
