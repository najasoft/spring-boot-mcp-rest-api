package spring.ateliers.g5.atelier1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.ateliers.g5.atelier1.model.Projet;
import spring.ateliers.g5.atelier1.model.ProjetDev;
import spring.ateliers.g5.atelier1.model.ProjetRes;
import spring.ateliers.g5.atelier1.model.Tache;

import java.util.List;

public interface ProjetRepository  extends JpaRepository<Projet,Long> {

	@Query("select p from ProjetDev p")
	List<ProjetDev> findAllProjetDev();

	@Query("select p from ProjetRes p")
	List<ProjetRes> findAllProjetRes();

	@Query("select t from Tache t where t.projetDev.id = :idProjet")
	List<Tache> getTaches(@Param("idProjet") Long idProjet);

	@Query("select p from ProjetDev p where p.id = :idProjet")
	ProjetDev getProjetDev(@Param("idProjet") Long idProjet);
}
