public class StudentListObserverImpl implements StudentListObserver {
    private int id;
    private static int idmax = 0;

    StudentListObserverImpl()
    {
        this.id = idmax++;
    }

    public void NotifyAdd(Student newStudent) {
        System.out.print("{"+id +"} Student added " );
        System.out.print("First Name: " + newStudent.getFirstName());
        System.out.print("  Last Name: " + newStudent.getLastName());
        System.out.println("  group: " + newStudent.getGrp());
    }

    public void NotifyDelete(Student newStudent) {
        System.out.print("{"+id +"} Student deleted " );
        System.out.print("First Name: " + newStudent.getFirstName());
        System.out.print("  Last Name: " + newStudent.getLastName());
        System.out.println("  group: " + newStudent.getGrp());
    }

    public void NotifyUpdate(Student oldStudent, Student newStudent) {
        System.out.print("{"+id +"} Student updated " );
        System.out.print("First Name(old): " + oldStudent.getFirstName());
        System.out.print("  Last Name(old): " + oldStudent.getLastName());
        System.out.print("  group(old): " + oldStudent.getGrp());

        System.out.print("First Name: " + newStudent.getFirstName());
        System.out.print("  Last Name: " + newStudent.getLastName());
        System.out.println("  group: " + newStudent.getGrp());
    }
}
