package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Commodity implements Serializable {
    private String id;
    private String name;

    private String domain;

    public Commodity() {
    }

    public Commodity(String id, String name, String domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return id.equals(commodity.id) && domain.equals(commodity.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
