package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;

public class CreateDemo {

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
			System.out.println("Creating new objects...!!!");
			
			Instructor instructor = new Instructor("Ricardo", "Varillas", "cricardo@gmail.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "Soccer");
			
			instructor.setInstructorDetail(instructorDetail);
			
			//Start a transaction
			session.beginTransaction();
			
			//save the instructor
			System.out.println("Saving the instructor using CASCADE mode:" + instructor);
			session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done...!!!");
			
		} catch (Exception e) {
			factory.close();
		}

	}

}
