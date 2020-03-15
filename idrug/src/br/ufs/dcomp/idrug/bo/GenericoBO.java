/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.FabricaConstantes;
import br.ufs.dcomp.idrug.dao.FabricaDAO;
import br.ufs.dcomp.idrug.factory.FabricaProvedor;
import br.ufs.dcomp.idrug.factory.FabricaTO;
import br.ufs.dcomp.idrug.util.Validar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sun.security.jca.GetInstance;

/**
 *
 * @author thiag
 */
public class GenericoBO {

    private FabricaTO fabricaTO;
    private FabricaDAO fabricaDAO;
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    protected GenericoBO() {
        fabricaDAO = (FabricaDAO) FabricaProvedor.getFactory(FabricaConstantes.FABRICA_DAO);
        fabricaTO = (FabricaTO) FabricaProvedor.getFactory(FabricaConstantes.FABRICA_TO);

    }

    protected FabricaTO getFabricaTO() {
        return this.fabricaTO;
    }

    protected FabricaDAO getFabricaDAO() {
        return this.fabricaDAO;
    }

    protected EntityManager getEntityManager() {
        if (Validar.nulo(emf)) {
            emf = Persistence.createEntityManagerFactory("psqlPU");
        } else if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("psqlPU");
        }
        if (Validar.nulo(entityManager)) {
            entityManager = emf.createEntityManager();
        } else if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    protected void fecharConexao() {
        entityManager.close();
        emf.close();

    }
}
