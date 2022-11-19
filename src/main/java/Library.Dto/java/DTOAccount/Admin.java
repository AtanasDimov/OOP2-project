package Library.Dto.java.DTOAccount;

import Library.Dto.java.Contracts.AdminInterface;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("1")
public class Admin extends AccountBase implements AdminInterface {


    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {

    }

    @Override
    public boolean CreateOperator() {
        return true;
    }
}
