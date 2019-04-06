import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ManageStudent {

    private static final Logger LOGGER = Logger.getLogger(ManageStudent.class.getName());

    private static SessionFactory factory;

    private List<StudentListObserver> observers;

    public void Subscribe(StudentListObserver ob)
    {
        observers.add(ob);
    }

    public ManageStudent() {

        try {
            observers = new ArrayList<>();
            Configuration c = new Configuration();
            c.addClass(Student.class);
            factory = c.configure().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an Student in the database */
    public Integer addStudent(String fname, String lname, int group){

        LOGGER.info("Add new student");

        Session session = factory.openSession();
        Transaction tx = null;
        Integer StudentID = null;

        try {
            tx = session.beginTransaction();
            Student Student = new Student(fname, lname, group);
            StudentID = (Integer) session.save(Student);
            tx.commit();

            for(StudentListObserver ob : observers)
                ob.NotifyAdd(Student);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return StudentID;
    }

    /* Method to  READ all the Students */
    public void listStudents( ){

        LOGGER.info("List students ");

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List Students = session.createQuery("FROM Student").list();
            for (Iterator iterator = Students.iterator(); iterator.hasNext();){
                Student Student = (Student) iterator.next();
                System.out.print("First Name: " + Student.getFirstName());
                System.out.print("  Last Name: " + Student.getLastName());
                System.out.println("  group: " + Student.getGrp());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE group for an Student */
    public void updateStudent(Integer StudentID, int group ){

        LOGGER.info("Update student: " + StudentID);

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student Student = (Student)session.get(Student.class, StudentID);
            Student old = new Student(Student.getFirstName(),Student.getLastName(),Student.getGrp());

            Student.setGrp( group );
            session.update(Student);
            tx.commit();

            for(StudentListObserver ob : observers)
                ob.NotifyUpdate(old,Student);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an Student from the records */
    public void deleteStudent(Integer StudentID){

        LOGGER.info("Delete student: " + StudentID);

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student Student = (Student)session.get(Student.class, StudentID);
            session.delete(Student);
            tx.commit();

            for(StudentListObserver ob : observers)
                ob.NotifyDelete(Student);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
