package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
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

            //create a course
            Course tempCourse = new Course("Pacman - How to Score one million");
            System.out.println("\nSaving the course");
            session.save(tempCourse);
            System.out.println("\nCourse Saved: "+ tempCourse);

            Student tempStudent1 = new Student("Gary", "Marshall-Adams", "gary.mars@1w.com");
            Student tempStudent2 = new Student("Mark", "McFarland", "Mark.mcfar@1w.com");
            Student tempStudent3 = new Student("Justin", "Marshall-Adams", "Justin.mars@1w.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            tempCourse.addStudent(tempStudent3);
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            System.out.println("Save Complete: " + tempCourse.getStudents());
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
