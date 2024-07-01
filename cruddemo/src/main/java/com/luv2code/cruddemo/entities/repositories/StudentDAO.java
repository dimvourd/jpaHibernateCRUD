package com.luv2code.cruddemo.entities.repositories;

import com.luv2code.cruddemo.entities.Student;

public interface StudentDAO {

    Student saveAndReturnObj(Student student);
    void save(Student student);
}
