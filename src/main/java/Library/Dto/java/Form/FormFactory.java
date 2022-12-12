package Library.Dto.java.Form;

public class FormFactory {
    public static Form CreateForm(String firstName, String lastName, String username, String password){
        return new Form(firstName, lastName, username, password);
    }
}
