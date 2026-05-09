package spring.ateliers.g5.atelier1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ateliers.g5.atelier1.model.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {
}

