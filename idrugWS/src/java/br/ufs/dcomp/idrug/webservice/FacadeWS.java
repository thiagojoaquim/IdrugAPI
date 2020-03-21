package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.bo.MedicamentoBO;
import br.ufs.dcomp.idrug.bo.SegurancaBO;
import br.ufs.dcomp.idrug.constantes.Excecao;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.exception.IdrugExceptionHelper;
import br.ufs.dcomp.idrug.modelo.Farmacia;
import br.ufs.dcomp.idrug.modelo.Medicamento;
import br.ufs.dcomp.idrug.modelo.Paciente;
import br.ufs.dcomp.idrug.modelo.TipoUsuario;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.ArrayList;
import java.util.List;

public class FacadeWS {

    public FacadeWS() {
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
}
