package sv.pvt.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Ltana on 24.04.2016.
 */
@Entity
@Table(name = "mantis_user_table")
public class UsersData {

    @Expose
    @Column(name = "username")
    private String name;

    @Expose
    @Column(name = "email")
    private String email;

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;


    public String getName() {
        return name;
    }

    public UsersData withName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public UsersData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "UsersData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public UsersData withEmail(String email) {
        this.email = email;
        return this;
    }
}

