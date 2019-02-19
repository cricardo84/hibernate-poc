package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			System.out.println("Deleting an instructor...!!!");

			//Start a transaction
			session.beginTransaction();
			
			int theId = 1;
			Instructor instructorToDelete = session.get(Instructor.class, theId);
			
			//Instructor retrieved
			System.out.println("Instructor found: " + instructorToDelete);
			
			//Delete the instructor
			if (instructorToDelete != null) {
				System.out.println("Deleting the instructor using CASCADE mode:" + instructorToDelete);
				session.delete(instructorToDelete);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done...!!!");
			
		} catch (Exception e) {
			factory.close();
		}

	}

}
