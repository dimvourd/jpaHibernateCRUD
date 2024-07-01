package com.luv2code.cruddemo.entities.repositories;

import com.luv2code.cruddemo.entities.Student;

import java.util.List;

public interface StudentDAO {

    Student saveAndReturnObj(Student student);
    void save(Student student);

    Student findById(Long id);
    List<Student> findAll();

    Student updateStudent(Student updatedStudent);

    List <Student> findByLastName(String lastName);

    void deleteById(Long Id);

    void deleteAll();
}
