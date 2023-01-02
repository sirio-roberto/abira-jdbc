package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    //(Id, Name, UserType, Email, TimeCreated)
    private String id;
    private String name;
    private String userType;
    private String email;
    private Date timeCreated;

    private Commodity commodity;

    public User() {
    }

    public User(String id, String name, String userType, String email, Date timeCreated, Commodity commodity) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.email = email;
        this.timeCreated = timeCreated;
        this.commodity = commodity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", timeCreated=" + timeCreated +
                ", commodity=" + commodity +
                '}';
    }
}
