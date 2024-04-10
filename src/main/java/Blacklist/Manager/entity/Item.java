package Blacklist.Manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore // Prevent infinite recursion during serialization
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<Blacklist> blacklists;

    // Constructors, getters, and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Blacklist> getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(List<Blacklist> blacklists) {
        this.blacklists = blacklists;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", blacklists=" + blacklists +
                '}';
    }
}

