package Library.Dto.java.DTOAccount;
import Library.Dto.java.Contracts.AccountsInterface;

import javax.persistence.*;

@Entity(name="Accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="account_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "Accounts")
public class AccountBase implements AccountsInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String username;
    private String password;


    public AccountBase(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountBase() {

    }

    public int getAccountId(){
        return accountId;
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
