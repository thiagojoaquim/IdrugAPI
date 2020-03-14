/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.factory;

import br.ufs.dcomp.idrug.constantes.FabricaConstantes;
import br.ufs.dcomp.idrug.dao.FabricaDAO;

/**
 *
 * @author thiag
 */
public class FabricaProvedor {

    public static FabricaAbstrata getFactory(String fabrica) {
        switch (fabrica) {
            case (FabricaConstantes.FABRICA_DAO):
                return new FabricaDAO();
            case (FabricaConstantes.FABRICA_TO):
                return new FabricaTO();

            default:
                return null;
        }
    }
}
