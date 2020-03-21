/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.modelo.EntidadeBase;
import br.ufs.dcomp.idrug.modelo.Usuario;
import br.ufs.dcomp.idrug.util.Validar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author thiag
 */
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

}
