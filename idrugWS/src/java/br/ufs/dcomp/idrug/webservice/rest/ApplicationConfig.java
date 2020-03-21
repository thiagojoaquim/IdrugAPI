/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.webservice.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author thiag
 */
@javax.ws.rs.ApplicationPath("idrugWS")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.ufs.dcomp.idrug.webservice.rest.MedicamentoResource.class);
        resources.add(br.ufs.dcomp.idrug.webservice.rest.SegurancaResource.class);
        resources.add(br.ufs.dcomp.idrug.webservice.rest.UsuarioResource.class);
    }
    
}
