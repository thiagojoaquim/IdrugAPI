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
import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Interesse;
import br.ufs.dcomp.idrug.modelo.Medicamento;
import br.ufs.dcomp.idrug.modelo.Paciente;
import br.ufs.dcomp.idrug.modelo.TipoUsuario;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            interessesTO.add(interesseTO);
        }
        return interessesTO;
    }

    public void deletarInteresse(InteresseTO interesseTO) throws IdrugException {

        DoacaoBO doacao = DoacaoBO.getInstancia();
        Interesse interesse;
        try {
            interesse = (Interesse) fabricaModelo.criar(Interesse.class);

            Paciente paciente = (Paciente) fabricaModelo.criar(Paciente.class);
            Medicamento medicamento = (Medicamento) fabricaModelo.criar(Medicamento.class);
            medicamento.setDosagem(interesseTO.getDosagem());
            medicamento.setProduto(interesseTO.getProduto());
            paciente.setCpf(interesseTO.getCpf());
            interesse.setMedicamento(medicamento);
            interesse.setPaciente(paciente);
            doacao.deletarInteresse(interesse);
        } catch (Exception ex) {
            throw IdrugExceptionHelper.getExcecao(ex);
        }
    }
}
