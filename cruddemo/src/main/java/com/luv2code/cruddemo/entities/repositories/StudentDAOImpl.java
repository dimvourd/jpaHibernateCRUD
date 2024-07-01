package com.luv2code.cruddemo.entities.repositories;

import com.luv2code.cruddemo.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Override
    public List<Student> findAll() {
        List<Student> students;
        TypedQuery<Student> query = entityManager.createQuery("from Student ORDER BY lastName", Student.class);

        students = query.getResultList();

        return students;
    }

    @Override
    @Transactional
    public Student updateStudent(Student updatedStudent) {

        return entityManager.merge(updatedStudent);
    }

    @Override
    public List<Student> findByLastName(String lastN) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student where lastName=:data", Student.class);

        // set query params
        query.setParameter("data", lastN);
        // results
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long Id) {
//        int i = entityManager.createQuery("DELETE FROM Student where id=:ID", Student.class)
//                .setParameter("ID",Id)
//                .executeUpdate();
//        System.out.println("Deleted: "+ i);
        Student s = findById(Id);
        entityManager.remove(s);
    }

    @Override
    @Transactional
    public void deleteAll(){
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }
}
