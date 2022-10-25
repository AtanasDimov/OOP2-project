package DTOAccount;

import Contracts.OperatorInterface;

public class OperatorAccount extends AccountBase implements OperatorInterface {


    public OperatorAccount(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean CreateReaderProfile() {
        return true;
    }

    @Override
    public boolean DeleteReaderProfile() {
        return true;
    }
}
