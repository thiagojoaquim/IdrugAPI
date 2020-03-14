/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.factory;

/**
 *
 * @author thiag
 */
public interface FabricaAbstrata<T> {
    
    public T criar(Class<? extends T> tipo) throws Exception;    
}
