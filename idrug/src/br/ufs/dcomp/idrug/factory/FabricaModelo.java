/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.factory;

import br.ufs.dcomp.idrug.modelo.EntidadeBase;

/**
 *
 * @author thiag
 */
public class FabricaModelo implements FabricaAbstrata<EntidadeBase> {

    @Override
    public EntidadeBase criar(Class<? extends EntidadeBase> tipo) throws Exception {
       return tipo.newInstance();
    }

  
    
}
