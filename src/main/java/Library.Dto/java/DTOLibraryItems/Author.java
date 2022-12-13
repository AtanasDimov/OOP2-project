package Library.Dto.java.DTOLibraryItems;


import Library.Dto.java.Contracts.AuthorInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="autor_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Author implements AuthorInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "author")
    private List<BaseLibraryItem> work;

    public Author(){}
    public Author(String name, String description)
    {
        this.description=description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addWork(BaseLibraryItem w)
    {
        if(this.work == null)
            this.work = new ArrayList<>();
        this.work.add(w);
    }

    public void addWork(List<BaseLibraryItem> w){
        this.work.addAll(w);
    }

    public int getId(){
        return this.id;
    }
    public void lazyLoad(List<BaseLibraryItem> w){
        this.work = w;
    }

    public List<BaseLibraryItem> getWork(){
        return this.work;
    }

    @Override
    public String toString() {
        return name +" "+  description;
    }
}
