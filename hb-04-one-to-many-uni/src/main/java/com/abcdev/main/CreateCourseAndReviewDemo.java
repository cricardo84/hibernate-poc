package com.abcdev.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.abcdev.entity.Course;
import com.abcdev.entity.Instructor;
import com.abcdev.entity.InstructorDetail;
import com.abcdev.entity.Review;

public class CreateCourseAndReviewDemo {

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

			//Create a Course
			Course course = new Course("Soccer coaching - How to teach team tactics");
			
			//Create and add some reviews
			course.addReview(new Review("Great course I like it."));
			course.addReview(new Review("It can be better."));
			course.addReview(new Review("Please provide more videos as examples."));
			
			System.out.println(course);
			System.out.println(course.getReviews());
			
			//Saving the course
			System.out.println("Saving the course...!!!");
			session.save(course);
			
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
