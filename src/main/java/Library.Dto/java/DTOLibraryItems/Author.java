package Library.Dto.java.DTOLibraryItems;



import Library.Dto.java.Contracts.AuthorInterface;
import Library.Dto.java.Contracts.LibraryItemInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class Author implements AuthorInterface {
    private String[] name;
    private String description;
    private List<LibraryItemInterface> work = new ArrayList<>();

    public Author(String[] name) {
        this.name = name;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addWork(LibraryItemInterface w)
    {
        this.work.add(w);
    }

    public void addWork(List<LibraryItemInterface> w){
        this.work.addAll(w);
    }

    public List<LibraryItemInterface> getWork(){
        return this.work;
    }
}
