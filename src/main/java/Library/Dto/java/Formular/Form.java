package Library.Dto.java.Formular;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class Form {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;
    private LocalDate DateOfRegistry;

    public Form(int id, String firstName, String lastName, String username, String password) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Password = password;
        DateOfRegistry = LocalDate.now();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
