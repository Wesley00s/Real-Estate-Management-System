package entities.person;

public class Contact {
    private String id;
    private String email;
    private int phone;

    public Contact(String id, String email, int phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return STR."""
                ID: \{getId()}
                E-mail: \{getEmail()}
                Phone: \{getPhone()}""";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
