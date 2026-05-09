package spring.ateliers.g5.atelier1.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public  class Projet {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 120)
    private String description;

    public long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
    