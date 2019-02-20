package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Course;
import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;
import com.abcdev.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		//Create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .buildSessionFactory();

		try {
			//Get current session
			Session session = factory.getCurrentSession();
			
			//Begin transaction
			System.out.println("Beginning transaction...!!!");
			session.beginTransaction();

			//Get the course
			int theId = 10;
			Course myCourse = session.get(Course.class, theId);
			
			//print the course
			System.out.println("The course: " + myCourse);
			
			//print the course reviews
			System.out.println("The course reviews: " + myCourse.getReviews());
			
			//Deleting the Course and reviews
			System.out.println("Deleting the course...!!!");
			session.delete(myCourse);
			
			//Commit transaction
			System.out.println("Commiting the transaction...!!!");
			session.getTransaction().commit();
			
			System.out.println("Ending the session by releasing the JDBC connection and cleanning up...!!!");
			session.close();
			
		} catch (Exception e) {
			factory.close();
		}

	}

}
