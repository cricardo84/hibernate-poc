package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Course;
import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

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
		Instructor tempInstructor = null;
		
		try {
			//create the objects
			System.out.println("Creating new objects...!!!");

			//Start a transaction
			session.beginTransaction();
			
			//get instructor from db
			int theId = 1;
			tempInstructor = session.get(Instructor.class, theId);
			
			//get course from instructor
			//System.out.println("Within session: Courses: " + tempInstructor.getCourses());
			
			//
			//System.out.println("Within session: Instructor: " + tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done...!!!");

		} finally {
			
			//add clean up code
			session.close();
			
			//Trying to use the fetch Lazy when session was closed
			//Get course from instructor
			System.out.println("Outside session: Courses: " + tempInstructor.getCourses());
			//Get Instructor
			System.out.println("Outside session: Instructor: " + tempInstructor);
			
			factory.close();
		}

	}

}
