package DTOLibraryItems;

public class ReferenceItem {
    private String name;
    private ReferenceEnum type;

    public ReferenceItem(String name, ReferenceEnum type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReferenceEnum getType() {
        return type;
    }

    public void setType(ReferenceEnum type) {
        this.type = type;
    }
}
