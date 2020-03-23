package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.bo.DoacaoBO;
import br.ufs.dcomp.idrug.bo.MedicamentoBO;
import br.ufs.dcomp.idrug.bo.SegurancaBO;
import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.constantes.FabricaConstantes;
import br.ufs.dcomp.idrug.dao.FabricaDAO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.factory.FabricaProvedor;
import br.ufs.dcomp.idrug.factory.FabricaTO;
import br.ufs.dcomp.idrug.modelo.Coleta;
import br.ufs.dcomp.idrug.modelo.Doacao;
import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Interesse;
import br.ufs.dcomp.idrug.modelo.Medicamento;
import br.ufs.dcomp.idrug.modelo.MedicamentoDisponivel;
import br.ufs.dcomp.idrug.modelo.Paciente;
import br.ufs.dcomp.idrug.modelo.TipoUsuario;
import br.ufs.dcomp.idrug.to.ColetaTO;
import br.ufs.dcomp.idrug.to.DoacaoTO;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.to.MedicamentoDisponivelTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.ArrayList;
import java.util.List;

public class FacadeWS {

    private FabricaTO fabricaModelo;
    private FabricaDAO fabricaDAO;

    public FacadeWS() {
        fabricaModelo = (FabricaTO) FabricaProvedor.getFactory(FabricaConstantes.FABRICA_TO);
    }

    public void cadastrarFarmacia(FarmaciaTO farmaciaTO) throws IdrugException {
        SegurancaBO objBO = SegurancaBO.getInstancia();
        objBO.cadastrarFarmacia(farmaciaTO.getCnpj(), farmaciaTO.getNome(), farmaciaTO.getSenha(), farmaciaTO.getEmail());
    }

    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException {
        SegurancaBO objBO = SegurancaBO.getInstancia();
        objBO.cadastrarPaciente(pacienteTO.getCpf(), pacienteTO.getNome(),
                pacienteTO.getSenha(), pacienteTO.getEmail(), pacienteTO.getDataNascimento());
    }

    public UsuarioTO validarUsuario(ValidarUsuarioTO validarUsuarioTO) throws IdrugException {
        SegurancaBO objBO = SegurancaBO.getInstancia();
        TipoUsuario usuario = objBO.validarUsuario(validarUsuarioTO.getIdentificador(), validarUsuarioTO.getSenha());
        if (usuario instanceof Farmacia) {
            Farmacia farmacia = (Farmacia) usuario;
            FarmaciaTO farmaciaTO = new FarmaciaTO();
            farmaciaTO.setCnpj(farmacia.getCnpj());
            farmaciaTO.setEmail(farmacia.getUsuario().getEmail());
            farmaciaTO.setNome(farmacia.getUsuario().getNome());
            farmaciaTO.setId(farmacia.getUsuario().getIdUsuario());
            return farmaciaTO;
        } else if (usuario instanceof Paciente) {
            Paciente paciente = (Paciente) usuario;
            PacienteTO pacienteTO = new PacienteTO();
            pacienteTO.setCpf(paciente.getCpf());
            pacienteTO.setEmail(paciente.getUsuario().getEmail());
            pacienteTO.setNome(paciente.getUsuario().getNome());
            pacienteTO.setId(paciente.getUsuario().getIdUsuario());
            return pacienteTO;
        }
        throw IdrugExceptionHelper.criarExcecao(Excecao.ERRO_GENERICO);
    }

    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException {
        MedicamentoBO objBO = MedicamentoBO.getInstancia();
        List<Medicamento> medicamentos = objBO.resagatarMedicamentos();
        List<MedicamentoTO> resp = new ArrayList<>();
        MedicamentoTO medicamentoTO;
        for (Medicamento medicamento : medicamentos) {
            medicamentoTO = new MedicamentoTO();
            medicamentoTO.setProduto(medicamento.getProduto());
            medicamentoTO.setDosagem(medicamento.getDosagem());
            medicamentoTO.setTarja(medicamento.getTarja());
            resp.add(medicamentoTO);
        }
        return resp;
    }

    public void cadastrarInteresse(InteresseTO interesseTO) throws IdrugException {
        DoacaoBO doacao = DoacaoBO.getInstancia();
        try {
            Interesse interesse = (Interesse) fabricaModelo.criar(Interesse.class);
            Paciente paciente = (Paciente) fabricaModelo.criar(Paciente.class);
            Medicamento medicamento = (Medicamento) fabricaModelo.criar(Medicamento.class);
            medicamento.setDosagem(interesseTO.getDosagem());
            medicamento.setProduto(interesseTO.getProduto());
            paciente.setCpf(interesseTO.getCpf());
            interesse.setMedicamento(medicamento);
            interesse.setPaciente(paciente);
            doacao.cadastrarInteresse(interesse);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.getExcecao(ex);
        }

    }

    public List<InteresseTO> resgatarInteresse(String cpf) throws IdrugException {
        DoacaoBO doacao = DoacaoBO.getInstancia();
        List<Interesse> interesses = doacao.resgatarInteresse(cpf);
        List<InteresseTO> interessesTO = new ArrayList<>();
        InteresseTO interesseTO;
        for (Interesse interesse : interesses) {
            interesseTO = new InteresseTO();
            interesseTO.setDataInteresse(interesse.getData());
            interesseTO.setDosagem(interesse.getMedicamento().getDosagem());
            interesseTO.setProduto(interesse.getMedicamento().getProduto());
            interesseTO.setCpf(cpf);
            interesseTO.setId(interesse.getIdInteresse());
            interessesTO.add(interesseTO);
        }
        return interessesTO;
    }

    public void deletarInteresse(int id) throws IdrugException {

        DoacaoBO doacao = DoacaoBO.getInstancia();
        Interesse interesse;
        try {

            doacao.deletarInteresse(id);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.getExcecao(ex);
        }
    }

    public List<DoacaoTO> resgatarDoacoes(String cpf) throws IdrugException {
        DoacaoBO doacaoBO = DoacaoBO.getInstancia();
        List<Doacao> doacoes = doacaoBO.resgatarDoacoes(cpf);
        List<DoacaoTO> doacoesTO = new ArrayList<>();
        DoacaoTO doacaoTO;
        for (Doacao doacao : doacoes) {
            doacaoTO = new DoacaoTO();
            doacaoTO.setData(doacao.getDataDoacao());
            doacaoTO.setDosagem(doacao.getMedicamento().getDosagem());
            doacaoTO.setProduto(doacao.getMedicamento().getProduto());
            doacaoTO.setCpf(cpf);
            doacaoTO.setId(doacao.getId());
            doacoesTO.add(doacaoTO);
        }
        return doacoesTO;
    }

    public List<ColetaTO> resgatarColetas(String cpf) throws IdrugException {
        DoacaoBO doacaoBO = DoacaoBO.getInstancia();
        List<Coleta> coletas = doacaoBO.resgatarColetas(cpf);
        List<ColetaTO> coletasTO = new ArrayList<>();
        ColetaTO coletaTO;
        for (Coleta coleta : coletas) {
            coletaTO = new ColetaTO();
            coletaTO.setId(coleta.getId());
            coletaTO.setDosagem(coleta.getMedicamento().getDosagem());
            coletaTO.setProduto(coleta.getMedicamento().getProduto());
            coletaTO.getFarmaciaTO().setCnpj(coleta.getFarmacia().getCnpj());
            coletaTO.getFarmaciaTO().setNome(coleta.getFarmacia().getUsuario().getNome());
            coletasTO.add(coletaTO);
        }
        return coletasTO;
    }

    public List<MedicamentoDisponivelTO> resgatarMedicamentosDisponiveis(String cnpj) throws IdrugException {
        MedicamentoBO medicamentoBO = MedicamentoBO.getInstancia();
        List<MedicamentoDisponivel> medicamentos = medicamentoBO.resgatarMedicamentosDisponiveis(cnpj);
        List<MedicamentoDisponivelTO> medicamentosDisponivelTO = new ArrayList<>();
        MedicamentoDisponivelTO medicamentoDisponivelTO;
        for (MedicamentoDisponivel medicamentoDisponivel : medicamentos) {
            medicamentoDisponivelTO = new MedicamentoDisponivelTO();
            medicamentoDisponivelTO.setId(medicamentoDisponivel.getId());
            medicamentoDisponivelTO.setDosagem(medicamentoDisponivel.getMedicamento().getDosagem());
            medicamentoDisponivelTO.setProduto(medicamentoDisponivel.getMedicamento().getProduto());
            medicamentoDisponivelTO.setCnpj(medicamentoDisponivel.getFarmacia().getCnpj());
            medicamentosDisponivelTO.add(medicamentoDisponivelTO);
        }
        return medicamentosDisponivelTO;

    }
}
