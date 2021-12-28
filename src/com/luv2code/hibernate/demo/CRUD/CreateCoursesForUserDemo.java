package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForUserDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            // start the transaction
            session.beginTransaction();

            int studentId = 3;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("User found: " + tempStudent.getFirstName());
            System.out.println("Assigned courses: " + tempStudent.getCourses());

            Course courseTemp1 = new Course("Rubik's Cube");
            Course courseTemp2 = new Course("Atari Game Development");

            courseTemp1.addStudent(tempStudent);
            courseTemp2.addStudent(tempStudent);

            System.out.println("\nSaving courses");
            session.save(courseTemp1);
            session.save(courseTemp2);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
