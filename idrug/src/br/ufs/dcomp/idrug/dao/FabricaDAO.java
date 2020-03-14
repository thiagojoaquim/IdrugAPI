/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.factory.FabricaAbstrata;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiag
 */
public class FabricaDAO implements FabricaAbstrata<GenericoDAO>{

    @Override
    public GenericoDAO criar(Class<? extends GenericoDAO> tipo) throws Exception {
        return tipo.newInstance();
    }

}
