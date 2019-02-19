package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Course;
import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class CreateInstructorCoursesDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			System.out.println("Creating new objects...!!!");

			//Start a transaction
			session.beginTransaction();
			
			//get instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//create some courses
			Course course1 = new Course("Java Core");
			Course course2 = new Course("Java concurrency");
			
			//add courses to instructor
			tempInstructor.add(course1);
			tempInstructor.add(course2);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done...!!!");
			
		} finally {
			
			//add clean up code
			session.close();
			
			factory.close();
		}

	}

}
