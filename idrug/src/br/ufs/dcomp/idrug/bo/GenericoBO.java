package br.ufs.dcomp.idrug.bo;

import br.ufs.dcomp.idrug.constantes.FabricaConstantes;
import br.ufs.dcomp.idrug.dao.FabricaDAO;
import br.ufs.dcomp.idrug.factory.FabricaProvedor;
import br.ufs.dcomp.idrug.factory.FabricaModelo;
import br.ufs.dcomp.idrug.util.Validar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericoBO {

    private FabricaModelo fabricaTO;
    private FabricaDAO fabricaDAO;
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    protected GenericoBO() {
        fabricaDAO = (FabricaDAO) FabricaProvedor.getFactory(FabricaConstantes.FABRICA_DAO);
        fabricaTO = (FabricaModelo) FabricaProvedor.getFactory(FabricaConstantes.FABRICA_MODELO);

    }

    protected FabricaModelo getFabricaTO() {
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
