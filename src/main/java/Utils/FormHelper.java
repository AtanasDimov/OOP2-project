package Utils;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.Form.Form;

import java.util.List;

public class FormHelper {
    public static List<Form> GetAllActiveForms(){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        List<Form> activeForms = (List<Form>)(Object)lr.GetListOfObject(QueryGenerator.GetActiveForms());
        return activeForms;
    }

    public static int GetNumberOfActiveForms(){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        int count = (int)lr.GetObject(QueryGenerator.GetNumberOfActiveForms());
        return count;
    }

    public static void AcceptForm(int id){
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        Form form = (Form)lr.GetObject(QueryGenerator.GetForm(id));
        form.setAccepted(true);
        form.setActive(false);

        ReaderAccount reader = new ReaderAccount(form.getFirstName(), form.getLastName(), form.getUsername(), form.getPassword());

        lr.UpdateObject(form);

        AccountHelper.RegisterReader(reader);
    }

    public static void CreateForm(String firstName, String lastName, String username, String password){
        AccountHelper.RegisterReaderForm(firstName, lastName, username, password);
    }
}
