/*
package com.dange.tanmay.dao;
import java.util.ArrayList;
import java.util.List;
//import com.dange.tanmay.repository.StudentRepository;
import org.junit.Test;
import com.dange.tanmay.model.Student;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class StudentDAOImplTest {
	@Autowired
	private TestEntityManager entityManager;

//	@Autowired
//	StudentRepository studentRepository;

	StudentDAOImpl s = new StudentDAOImpl();
	List<Student> sList = new ArrayList<Student>();
	
	@Test
	public void test1() {	
	sList = s.getAllStudentList();
	
	for(Student s1 : sList)
	{

//do check
	}
	}
	
	
	//getStudentByName
	
	@Test
	public void test2() {	
	sList = s.getStudentByName("Tanmay");
	
	for(Student s1 : sList)
	{//do checking
	}
	}
	
	@Test
	public void test3() {	
	sList = s.getStudentByAge(30);
	
	for(Student s1 : sList)
	{
//do checking
	}
	}
	
	@Test
	public void test4() {	
	sList = s.getStudentByRegistrationNumber("GA12387");
	
	for(Student s1 : sList)
	{
//do checking
	}
	}
	
		
	@Test
	public void test5() {
	//	studentRepository.save(new Student(1,"Shubham",35,"GA12390"));
	}
	
	*/
/*@Test
	@Ignore
	public void test6() {	
	s.deleteStudent("Shubham");
	}
*//*

}
*/
