package gamestore.gamestore.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
   @ManyToOne
   private User user;
   @ManyToMany(targetEntity = Game.class,fetch = FetchType.EAGER)
   private List<Game> orderedGames;

   public Order() { }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(List<Game> orderedGames) {
        this.orderedGames = orderedGames;
    }
}
