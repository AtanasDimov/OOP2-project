package Library.Dto.java.DTOAccount;

import Library.Dto.java.Contracts.OperatorInterface;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("2")

public class OperatorAccount extends AccountBase implements OperatorInterface {


    public OperatorAccount(String username, String password) {
        super(username, password);
    }

    public OperatorAccount() {

    }

    @Override
    public boolean CreateReaderAccount() {
        return false;
    }

    @Override
    public boolean DeleteReaderAccount() {
        return true;
    }
}
