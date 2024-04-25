package controller;

import model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DepartmentController {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Perform CRUD operations
//              addDepartment(session);  // Uncomment to add Department
//            findDepartment(session, 2); // Replace '3' with the actual Department ID you want to find
//            updateDepartment(session, 3); // Replace '3' with the actual Department ID you want to update
//            deleteDepartment(session, 3); // Replace '4' with the actual Department ID you want to delete
        } finally {
            session.close();
            factory.close();
        }
    }





    public static void addDepartment(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            Department dOne = new Department( "Art",  "California");
            Department dTwo = new Department("Dep2", "Arizona");
            Department dThree = new Department( "Dep3", "Colorado");


            session.persist(dOne);
            session.persist(dTwo);
            session.persist(dThree);
//            session.persist(dFour);
//            session.persist(dFive);

            transaction.commit();
            System.out.println("Departments added successfully");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            transaction.rollback();
            System.out.println("Issue with Department insertion");

        }
    }

    public static void findDepartment(Session session, int did) {
        Transaction transaction = session.beginTransaction();
        try {
            Department dept = session.get(Department.class, did);
            if (dept != null) {
                System.out.println("Department found: " + dept.getDid());
                System.out.println("Name: " + dept.getName());
                System.out.println("State: " + dept.getState());
            } else {
                System.out.println("No Department found with ID: " + did);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void updateDepartment(Session session, int did) {
        Transaction transaction = session.beginTransaction();
        try {
            Department dept = session.get(Department.class, did);
            if (dept != null) {
                //dept.setDid(101);
                dept.setName("New Dept");
                dept.setState("Texas");
//                session.update(dept); //<--old version
                session.merge(dept); //<--current method in recent version
                transaction.commit();
                System.out.println("Department updated successfully");
            } else {
                System.out.println("No department found to update.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    public static void deleteDepartment(Session session, int did) {
        Transaction transaction = session.beginTransaction();
        try {
            Department dept = session.get(Department.class, did);
            if (dept != null) {
                session.delete(dept);
                transaction.commit();
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("No Department found to delete.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}










