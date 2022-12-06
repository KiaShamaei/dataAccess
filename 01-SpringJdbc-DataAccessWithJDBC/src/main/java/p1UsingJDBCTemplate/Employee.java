package p1UsingJDBCTemplate;

/*@Entity
@Table(name = "Employee")*/
public class Employee {
    /*  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id")*/
    private int id;
    // @Column(name = "first_name")
    private String firstname;
    //@Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Employee(String firstname, String lastName, String username, String password) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee(int id, String firstname, String lastName, String username, String password) {

        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }


}
