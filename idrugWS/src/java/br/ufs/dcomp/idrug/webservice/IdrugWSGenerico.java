package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.ColetaTO;
import br.ufs.dcomp.idrug.to.DoacaoTO;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.InteresseTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import br.ufs.dcomp.idrug.to.ValidarUsuarioTO;
import java.util.List;

public  class IdrugWSGenerico implements IdrugWS {

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
    public List<DoacaoTO> resgatarDoacoes(String cpf) throws IdrugException {
        return facade.resgatarDoacoes(cpf);
    }

    @Override
    public List<ColetaTO> resgatarColetas(String cpf) throws IdrugException {
        return facade.resgatarColetas(cpf);
    }

    @Override
    public void confirmarColeta(int idColeta) throws IdrugException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
