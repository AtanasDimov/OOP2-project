package DTOAccount;
import Contracts.AccountsInterface;
import Contracts.AdminInterface;

public class Admin extends AccountBase implements AdminInterface {


    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean CreateOperator() {
        return true;
    }
}
