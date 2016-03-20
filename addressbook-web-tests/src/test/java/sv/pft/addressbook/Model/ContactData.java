package sv.pft.addressbook.Model;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String address;
    private final String mobile;
    private final String email;
    private final String group;
    private int id;



    public ContactData(int id, String name, String lastname, String address, String mobile, String email, String group) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(String name, String lastname, String address, String mobile, String email, String group) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;

        this.email = email;
        this.group = group;
        this.id = 0;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
