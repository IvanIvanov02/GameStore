package gamestore.gamestore.Entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public int getId() {
        return id;
    }
}
