package DTOLibraryItems;

public class RefrenceItem {
    private String name;
    private RefrenceEnum type;

    public RefrenceItem(String name, RefrenceEnum type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RefrenceEnum getType() {
        return type;
    }

    public void setType(RefrenceEnum type) {
        this.type = type;
    }
}
