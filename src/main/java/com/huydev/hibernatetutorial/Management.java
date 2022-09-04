package com.huydev.hibernatetutorial;

import com.huydev.hibernatetutorial.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.*;

public class Management {
    public static void main(String[] args) {
        showFirstLevelCaching();
    }

    public static void showFirstLevelCaching() {
        UUID id = UUID.fromString("a5e239f3-f5d3-463d-9187-90f086251d56");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Group group = (Group) session.get(Group.class, id);
            //this code will divide transaction to 2 object
            System.out.println(group);
            session.getTransaction().commit();
            session.close(); //when close session before other session, hibernate will create new object

            session = sessionFactory.openSession();
            session.beginTransaction();
            group = null; //add null to disconnect with the first transaction
            group = (Group) session.get(Group.class, id);
//            System.out.println(group);
            session.getTransaction().commit();
            session.close();



//            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public static void useNameQueryGroup() {
        UUID id = UUID.fromString("a5e239f3-f5d3-463d-9187-90f086251d56");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.getNamedQuery(Constants.GROUP_BY_NAME);
            query.setParameter("name", "Java Group");
            System.out.println(query.list());



//            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }
    public static void useCriteriaGroup() {
        UUID id = UUID.fromString("a5e239f3-f5d3-463d-9187-90f086251d56");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Criteria groupCriteria = session.createCriteria(Group.class);
//            groupCriteria.add(Restrictions.eq("id", id));
            SimpleExpression like = Restrictions.like("name", "JavaS%");
            SimpleExpression equal =  Restrictions.eq("id", id);
            LogicalExpression logicalExpression = Restrictions.or(like, equal);
            groupCriteria.add(logicalExpression);
            System.out.println(groupCriteria.list());


//            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }
    public static void deleteGroup() {
        UUID id = UUID.fromString("e3e11b1a-9073-4bb1-a300-205035dc50d9");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryStr = "DELETE FROM Group WHERE id = :id";
            Query query = session.createQuery(queryStr);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }
    public static void updateGroup() {
        UUID id = UUID.fromString("e3e11b1a-9073-4bb1-a300-205035dc50d9");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryStr = "UPDATE Group SET name = :name WHERE id = :id";
            Query query = session.createQuery(queryStr);
            query.setParameter("id", id);
            query.setParameter("name", "Java%");
            int result = query.executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void QueryGroup() {
        UUID id = UUID.fromString("e3e11b1a-9073-4bb1-a300-205035dc50d9");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryStr = "SELECT name FROM Group WHERE id = :id and name like :name";
            Query query = session.createQuery(queryStr);
            query.setParameter("id", id);
            query.setParameter("name", "Java%");
            List<Group> groups = (List<Group>) query.list();
            System.out.println(groups);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void getGroup() {
        UUID id = UUID.fromString("03160172-6737-4aa3-be4b-9b417aa1129d");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Group javaGroup = (Group) session.get(Group.class, id);
            System.out.println(javaGroup);
            javaGroup.setName("New Java Group");
            session.delete(javaGroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void createGroup() {
        Group javaGroup = new Group("Java Group");
        Group jsGroup = new Group("JavaScript Group");
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(javaGroup);
            session.save(jsGroup);

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void createFresherAndGroup() {
        Fresher fresher1 = new Fresher();
        Fresher fresher2 = new Fresher();
        Group group1 = new Group("Group 1");
        Group group2 = new Group("Group 2");
        Set<Fresher> freshers = new HashSet<>();
        freshers.add(fresher1);
        freshers.add(fresher2);
        Set<Group> groups = new HashSet<>();
        groups.add(group1);
        groups.add(group2);
        fresher1.setName("Fresher 1");
        fresher2.setName("Fresher 2");
        fresher1.setGroups(groups);
        fresher2.setGroups(groups);
        group1.setFreshers(freshers);
        group2.setFreshers(freshers);
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(fresher1);
            session.save(fresher2);
            session.save(group1);
            session.save(group2);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void createFresher() {
        Address address = new Address("Nguyen Thai Hoc", "Thot Not");
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Hibernate"));
        courses.add(new Course("Java"));
        Fresher fresher = new Fresher("Le Van Dat", address, courses);
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Course x : courses) {
                session.save(x);
            }
            session.save(address);
            session.save(fresher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void createCourse() {
        List<Syllabus> syllabusList = new ArrayList<>();
        syllabusList.add(new Syllabus("Nene", 2));
        syllabusList.add(new Syllabus("Nome", 1));

        Syllabus syllabus = new Syllabus("HMeme", 30);
        Syllabus offlineSyllabus = new Syllabus("Offmeme", 100);
        Course course = new Course("heelo", new Date(), syllabusList);

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
//            Course hibernate = (Course) session.get(Course.class, 2);
//            System.out.println(hibernate.getId());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }
    }
}
