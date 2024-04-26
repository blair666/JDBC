package org.example;

import model.Department;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        manyToOne();
    }

    public static void manyToOne(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        //creating departments
        Department dept1 = new Department("IT");
        Department dept2 = new Department("HR");

        //creating teacher
        Teacher t1 = new Teacher("1000","MHaseeb",dept1);
        Teacher t2 = new Teacher("2220","Shahparan",dept1);
        Teacher t3 = new Teacher("3000","James",dept1);
        Teacher t4 = new Teacher("40000","Joseph",dept2);
        //Storing Departments in database
        session.persist(dept1);
        session.persist(dept2);
        //Storing teachers  in database
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        transaction.commit();  }
}