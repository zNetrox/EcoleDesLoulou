package fr.iut.androidprojet.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "student", indices = {@Index(value = {"login"}, unique = true)})
public class Student {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String lastName;
    private String login;


    /*
     * Getters and Setters
     * */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return lastName + " " + name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
