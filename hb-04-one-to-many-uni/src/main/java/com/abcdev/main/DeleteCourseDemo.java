package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Course;
import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			System.out.println("Beginning transaction...!!!");
			//Start a transaction
			session.beginTransaction();
			
			//Get a course to delete
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			//Deleting the course
			System.out.println("Deleting the course: " + tempCourse);
			session.delete(tempCourse);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Finishing transaction...!!!");
			
		} finally {
			
			//add clean up code
			session.close();
			
			factory.close();
		}

	}

}
