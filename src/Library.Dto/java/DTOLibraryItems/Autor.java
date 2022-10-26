package DTOLibraryItems;

import Contracts.LibraryItemInterface;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private String[] name;
    private String description;
    private List<LibraryItemInterface> work = new ArrayList<>();

    public Autor(String[] name) {
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
