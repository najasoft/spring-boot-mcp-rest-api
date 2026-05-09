package spring.ateliers.g5.atelier1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.ateliers.g5.atelier1.model.Developpeur;

import java.util.List;

public interface DevRepository extends JpaRepository<Developpeur, Long> {
    Developpeur findByNom(String nom);

    Developpeur findByEmail(String email);

    @Query("select distinct d from Developpeur d join d.taches t where t.projetDev.id = :idProjet")
    List<Developpeur> getDevs(@Param("idProjet") Long idProjet);
}

