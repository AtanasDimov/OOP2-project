package Library.Dto.java.DTOAccount;
import Library.Dto.java.Contracts.AccountsInterface;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "Account")
public class AccountBase implements AccountsInterface {

    @Id
    private int accountId;
    private String username;
    private String password;


    public AccountBase(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountBase() {

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
}
