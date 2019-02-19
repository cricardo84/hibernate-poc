package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
			//Begin a transaction
			System.out.println("Begin transaction...!!!");
			session.beginTransaction();
			
			//get the instructor detail object
			int theId = 25;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("instructor detail: " + instructorDetail);
			
			//print the associated instructor
			System.out.println("instructor: " + instructorDetail.getInstructor());
			
			//Commit transaction
			session.getTransaction().commit();
			System.out.println("Done...!!!");
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
