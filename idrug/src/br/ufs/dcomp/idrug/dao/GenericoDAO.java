/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.dao;

import br.ufs.dcomp.idrug.to.EntidadeBase;
import br.ufs.dcomp.idrug.to.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author thiag
 */
public class GenericoDAO<T extends EntidadeBase> {
    
    private EntityManager entityManager;
    
    public GenericoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void salvar(T entidade) {
        entityManager.persist(entidade);
    }
    
    public void atualizar(T entidade) {
        entityManager.merge(entidade);        
    }
    
    public void deletar(T entidade) {
        entityManager.remove(entidade);
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("psqlPU");
        EntityManager em = emf.createEntityManager();
        GenericoDAO<Usuario> genericoDAO = new GenericoDAO<>(em);
        Usuario usuario = new Usuario();
        usuario.setNome("Thiago");
        usuario.setSenha("teste");
        usuario.setDataCadastro(new Date());
        em.getTransaction().begin();
        genericoDAO.salvar(usuario);
        em.getTransaction().commit();
        em.close();
        
    }
}
