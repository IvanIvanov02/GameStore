package gamestore.gamestore.Repositories;

import gamestore.gamestore.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {
}
