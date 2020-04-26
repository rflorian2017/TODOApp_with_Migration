package com.mac.repository;

import com.mac.model.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository implements CrudRepository<Task, Integer> {

    private EntityManager entityManager;

    public TaskRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t from Task t").getResultList();
    }

    public void save(Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Integer id) {

    }

    public Task findById(Integer id) {
        return null;
    }


}
