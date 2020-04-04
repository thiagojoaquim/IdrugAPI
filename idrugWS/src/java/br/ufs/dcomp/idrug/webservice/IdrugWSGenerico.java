package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.ColetaTO;
import br.ufs.dcomp.idrug.to.ConfirmarColetaTO;
import br.ufs.dcomp.idrug.to.DoacaoTO;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.to.MedicamentoDisponivelTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.List;

public class IdrugWSGenerico implements IdrugWS {

    private FacadeWS facade;

    public IdrugWSGenerico() {
        facade = new FacadeWS();
    }

    @Override
    public void cadastrarFarmacia(FarmaciaTO farmaciaTO) throws IdrugException {
        facade.cadastrarFarmacia(farmaciaTO);
    }

    @Override
    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException {
        facade.cadastrarPaciente(pacienteTO);
    }

    @Override
    public UsuarioTO validarUsuario(ValidarUsuarioTO validarUsuarioTO) throws IdrugException {
        return facade.validarUsuario(validarUsuarioTO);
    }

    @Override
    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException {
        return facade.resgatarMedicamentos();
    }

    @Override
    public void cadastrarInteresse(InteresseTO interesseTO) throws IdrugException {
        facade.cadastrarInteresse(interesseTO);
    }

    @Override
    public List<InteresseTO> resgatarInteresses(String cpf) throws IdrugException {
        return facade.resgatarInteresse(cpf);
    }

    @Override
    public void removerInteresse(int id) throws IdrugException {
        facade.deletarInteresse(id);
    }

    @Override
    public List<DoacaoTO> resgatarDoacoes(String identificador) throws IdrugException {
        return facade.resgatarDoacoes(identificador);
    }

    @Override
    public List<ColetaTO> resgatarColetas(String identificador) throws IdrugException {
        return facade.resgatarColetas(identificador);
    }

    @Override
    public void confirmarColeta(ConfirmarColetaTO confirmarColetaTO) throws IdrugException {
        facade.confirmarColeta(confirmarColetaTO.getIdColeta(), confirmarColetaTO.getSituacao());
    }

    @Override
    public List<MedicamentoDisponivelTO> resgatarMedicamentosDisponiveis(String cnpj) throws IdrugException {
        return facade.resgatarMedicamentosDisponiveis(cnpj);
    }

    @Override
    public void cadastrarDisponibilidade(MedicamentoDisponivelTO medicamentoDisponivelTO) throws IdrugException {
        facade.cadastrarDisponibilidade(medicamentoDisponivelTO);
    }
}
