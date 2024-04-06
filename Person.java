import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    public String name;
    public String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toCSV() {
        return getId() + "," + getName() + "," + getEmail();
        // Add other fields if needed
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nEmail: " + email;
    }
}
