package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.bo.MedicamentoBO;
import br.ufs.dcomp.idrug.bo.SegurancaBO;
import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.modelo.Medicamento;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
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

    public boolean validarUsuario(UsuarioTO usuario, String identificador) throws IdrugException {
        SegurancaBO objBO = SegurancaBO.getInstancia();
        return objBO.validarUsuario(identificador, usuario.getSenha());
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
