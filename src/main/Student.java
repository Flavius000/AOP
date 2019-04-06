
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int grp;

    public Student() {}

    public Student(String fname, String lname, int group) {
        this.firstName = fname;
        this.lastName = lname;
        this.grp = group;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String first_name ) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String last_name ) {
        this.lastName = last_name;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }
}