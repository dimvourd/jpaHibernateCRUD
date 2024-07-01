package com.luv2code.cruddemo.entities.repositories;

import com.luv2code.cruddemo.entities.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class StudentDAOImpl implements StudentDAO {

    EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager em){
        this.entityManager = em;
    }

    @Override
    @Transactional
    public Student saveAndReturnObj(Student student) {
        return entityManager.merge(student);
    }
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
