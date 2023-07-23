package gamestore.gamestore.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private Boolean isLogged;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Game> userGames;

    @OneToMany(mappedBy = "user",targetEntity = Order.class,fetch = FetchType.EAGER)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    @Column(name = "is_administrator")
    private Boolean isAdministrator;

    public User() { }

}
