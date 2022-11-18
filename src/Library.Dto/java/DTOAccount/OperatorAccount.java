package DTOAccount;

import Contracts.OperatorInterface;

public class OperatorAccount extends AccountBase implements OperatorInterface {


    public OperatorAccount(String username, String password) {
        super(username, password);
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
