import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
    public static void main(final String[] args) throws Exception {

        StudentListObserver observer_2 = new StudentListObserverImpl();
        StudentListObserver observer_1 = new StudentListObserverImpl();

        ManageStudent manager = new ManageStudent();

        manager.Subscribe(observer_1);
        manager.Subscribe(observer_2);


        Integer empID1 = manager.addStudent("Zara", "Ali", 1000);
        Integer empID2 = manager.addStudent("Daisy", "Das", 5000);
        Integer empID3 = manager.addStudent("John", "Paul", 10000);

        //manager.listStudents();

        manager.updateStudent(empID1, 5000);

        manager.deleteStudent(empID2);

        //manager.listStudents();
    }
}