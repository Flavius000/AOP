
public interface StudentListObserver {
    void NotifyAdd(Student newStudent);
    void NotifyDelete(Student newStudent);
    void NotifyUpdate(Student oldStudent, Student newStudent);
}
