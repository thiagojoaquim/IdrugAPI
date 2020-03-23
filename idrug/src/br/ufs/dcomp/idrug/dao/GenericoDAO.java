package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.EntidadeBase;
import br.ufs.dcomp.idrug.util.Validar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericoDAO<T extends EntidadeBase> {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public GenericoDAO() {
    }

    public GenericoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(T entidade) throws Exception {
        try {
            comecarTransacao();
            entityManager.persist(entidade);
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void atualizar(T entidade) throws Exception {
        try {
            comecarTransacao();
            entityManager.merge(entidade);
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void deletar(T entidade) throws Exception {
        try {
            comecarTransacao();
            entityManager.remove(entidade);
            commitar();
        } catch (Exception e) {
            rollback();
            throw e;
        }
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

    public void comecarTransacao() {
        getEntityManager().getTransaction().begin();
    }

    public void commitar() {
        getEntityManager().getTransaction().commit();

    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }
    
    public void fecharConexao() {
        getEntityManager().close();
        emf.close();
    }
}
