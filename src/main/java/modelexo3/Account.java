package modelexo3;

public class Account {
    private String email;
    private double id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
