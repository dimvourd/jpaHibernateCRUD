package com.luv2code.cruddemo;

import com.luv2code.cruddemo.entities.Student;
import com.luv2code.cruddemo.entities.repositories.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO dao){
		return runner -> {
			System.out.println("Hello World.");
			createStudent(dao);
//			System.out.println(
//				findStudentById( 1L, dao)
//				);

			readAllStudents(dao);

			// System.out.println(updateStudentById(dao, 1L));

			try {
				deleteStudentById(2L, dao);
			} catch (InvalidDataAccessApiUsageException e){
				System.out.println(e);
			}
		};
	}

	public void deleteStudentById(Long id, StudentDAO dao){
		dao.deleteById(id);
	}

	public Student updateStudentById(StudentDAO dao, Long id){
		Student s = findStudentById(id, dao);
		s.setLastName("Updated");
		dao.updateStudent(s);
		return s;
	}
	public void readAllStudents(StudentDAO dao) {
		List<Student> students = dao.findAll();
		for(var student : students) System.out.println(student);
	}
	public void createStudent(StudentDAO dao){
		System.out.println("Creating new student: ");
		Student student = new Student("Paul", "Walker", "pWalker@gmail.com");
		System.out.println(student);

		System.out.println("Void save: ");

		dao.save(student);

		Student student2 = new Student("Vincent", "Mcalister", "vMCAlister@gmail.com");

		System.out.println("Save and return DB object: " +
			dao.saveAndReturnObj(student2)
		);
	}

	public  Student findStudentById(Long id, StudentDAO dao){

		return dao.findById(id);
	}
}
