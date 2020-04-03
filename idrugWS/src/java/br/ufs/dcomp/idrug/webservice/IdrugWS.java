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

public interface IdrugWS {

    public void cadastrarFarmacia(FarmaciaTO farmatFarmaciaTO) throws IdrugException;

    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException;

    public UsuarioTO validarUsuario(ValidarUsuarioTO validarUsuarioTO) throws IdrugException;

    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException;

    public void cadastrarInteresse(InteresseTO interesseTO) throws IdrugException;

    public List<InteresseTO> resgatarInteresses(String cpf) throws IdrugException;

    public void removerInteresse(int id) throws IdrugException;

    public List<DoacaoTO> resgatarDoacoes(String identificador) throws IdrugException;

    public List<ColetaTO> resgatarColetas(String identificador) throws IdrugException;

    public void confirmarColeta(ConfirmarColetaTO confirmarColetaTO) throws IdrugException;

    public List<MedicamentoDisponivelTO> resgatarMedicamentosDisponiveis(String cnpj) throws IdrugException;

    public void cadastrarDisponibilidade(MedicamentoDisponivelTO medicamentoDisponivelTO) throws IdrugException;
}
