package controller;

import jakarta.persistence.TypedQuery;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserController {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Perform CRUD operations
//              addUser(session);  // Uncomment to add users
//            findUser(session, 2); // Replace '3' with the actual user ID you want to find
//            updateUser(session, 3); // Replace '3' with the actual user ID you want to update
//            deleteUser(session, 4); // Replace '4' with the actual user ID you want to delete
//            findUserHql(factory,session);
//            getRecordById(factory,session);
//            getMaxSalary(session);
            getMaxSalaryGroupBy(session);
//            getMaxSalaryGroupBy(); // --> For testing line 175
            namedQueryExample(session);

        } finally {
            session.close();
            factory.close();
        }
    }








    public static void addUser(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            User uOne = new User("Moh Haseeb", "haseeb@gmail.com", "has123", 20, 2000.69, "NYC");
            User uTwo = new User("James Santana", "James@gmail.com", "James123", 25, 2060.69, "Dallas");
            User uThree = new User("AH Shahparan", "Shahparan@gmail.com", "Shahparan123", 30, 3060.69, "Chicago");
            User uFour = new User("Christ", "christ@gmail.com", "147852", 35, 35000.3, "NJ");
            User uFive = new User("Sid", "Sid@gmail.com", "s258", 29, 4000.36, "LA");

            session.persist(uOne);
            session.persist(uTwo);
            session.persist(uThree);
            session.persist(uFour);
            session.persist(uFive);

            transaction.commit();
            System.out.println("Users added successfully");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void findUser(Session session, int userId) {
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            if (user != null) {
                System.out.println("User found: " + user.getFullName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("password: " + user.getPassword());
            } else {
                System.out.println("No user found with ID: " + userId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void updateUser(Session session, int userId) {
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setFullName("M Haseeb");
                user.setEmail("mhaseeb@perscholas");
                user.setPassword("123456");
                session.update(user);
                transaction.commit();
                System.out.println("User updated successfully");
            } else {
                System.out.println("No user found to update.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    public static void deleteUser(Session session, int userId) {
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found to delete.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    public static void findUserHql(SessionFactory factory,Session session) {
        String hqlFrom = "FROM User"; // Example of HQL to get all records of user class
        String hqlSelect = "SELECT u FROM User u";
        TypedQuery<User> query = session.createQuery(hqlFrom, User.class);
        List<User> results = query.getResultList();

        System.out.printf("%s%13s%17s%34s%n","|User Id","|Full name","|Email","|Password");
        for (User u:results) {
            System.out.printf(" %-10d %-20s %-30s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword());
        }
    }


    public static void getRecordById(SessionFactory factory, Session session) {
        String hql = "FROM User u WHERE u.id > 2 ORDER BY u.salary DESC";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        List<User> results = query.getResultList();
        System.out.printf("%s%13s%17s%34s%21s%n", "|User Id", "|Full name", "|Email", "|Password", "|Salary");
        for (User u : results) {
            System.out.printf(" %-10d %-20s %-30s %-23s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword(), u.getSalary());
        }
    }

    public static void getRecords (Session session) {
        TypedQuery<Object[]> query = session.createQuery(
                "SELECT U.salary, U.fullName FROM User AS U", Object[].class);
        List<Object[]> results = query.getResultList();
        System.out.printf("%s%13s%n","Salary","City");
        for (Object[] a : results) {
            System.out.printf("%-16s%s%n",a[0],a[1]);
        }
    }

    public static void getMaxSalary(Session session) {
        String hql = "SELECT max(U.salary) FROM User U";
        TypedQuery<Object> query = session.createQuery(hql,Object.class);
        Object result = query.getSingleResult();
        System.out.printf("%s%s","Maximum Salary:",result);
    }


    public static void getMaxSalaryGroupBy(Session session)
    {
        String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
        TypedQuery query = session.createQuery(hql);
        List<Object[]> result =query.getResultList();
        for (Object[] o : result) {
            System.out.println("Total salary " +o[0] +" | city: "+ o[1] );
        }

    }


//    For testing - not necessarily correct but works
//    It's better to create Configuration once

//    public static void getMaxSalaryGroupBy()
//    {
//        SessionFactory factory = new Configuration().configure().buildSessionFactory(); // --> Already declared line 14
//        Session session = factory.openSession();
//        try{
//                                         // --> Line 15
//            String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
//            TypedQuery query = session.createQuery(hql);
//            List<Object[]> result =query.getResultList();
//            for (Object[] o : result) {
//                System.out.println("Total salary " +o[0] +" | city: "+ o[1] );
//            }
//        }
//        finally {
//            session.close();
//            factory.close();
//        }
//
//    }

    public static void namedQueryExample(Session session) {
        String hql = "FROM User u WHERE u.id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", 2);
        List<User> result = query.getResultList();

        System.out.printf("%s%13s%17s%34s%21s%n", "|User Id", "|Full name", "|Email", "|Password", "|Salary");
        for (User u : result) {
            System.out.printf(" %-10d %-20s %-30s %-23s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword(), u.getSalary());
        }
    }


}
