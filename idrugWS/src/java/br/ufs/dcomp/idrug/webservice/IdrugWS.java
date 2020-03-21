/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice;

import br.ufs.dcomp.idrug.exception.IdrugException;
import br.ufs.dcomp.idrug.to.FarmaciaTO;
import br.ufs.dcomp.idrug.to.MedicamentoTO;
import br.ufs.dcomp.idrug.to.PacienteTO;
import br.ufs.dcomp.idrug.to.UsuarioTO;
import java.util.List;

/**
 *
 * @author thiag
 */
public interface IdrugWS {

    public void cadastrarFarmacia(FarmaciaTO farmatFarmaciaTO) throws IdrugException;

    public void cadastrarPaciente(PacienteTO pacienteTO) throws IdrugException;

    public boolean validarUsuario(UsuarioTO usuarioTO, String identificador) throws IdrugException;

    public List<MedicamentoTO> resgatarMedicamentos() throws IdrugException;
}
