package spring.ateliers.g5.atelier1.service;

import spring.ateliers.g5.atelier1.model.Projet;
import spring.ateliers.g5.atelier1.model.ProjetDev;
import spring.ateliers.g5.atelier1.model.ProjetRes;
import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.model.Tache;

import java.util.List;
import java.util.Optional;

public interface ProjetService {
    List<Projet> lesProjets();

    List<ProjetDev> getProjetsDev();

    List<ProjetRes> getProjetsRes();

    Projet ajouterProjet(Projet projet);

    ProjetDev ajouterProjetDev(ProjetDev projetDev);

    ProjetRes ajouterProjetRes(ProjetRes projetRes);

    void ajouterTache(Long idProjet, Tache tache);

    List<Tache> getTaches(Long idProjet);

    List<Developpeur> getDeveloppeursAffectes(Long idProjet);

    Optional<Projet> findById(Long id);
}

