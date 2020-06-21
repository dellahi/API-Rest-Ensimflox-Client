package modelexo3;

import java.util.List;

public class Channel {
    private double id;
    private List<String> members;
    private String name;
    private String owner;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
