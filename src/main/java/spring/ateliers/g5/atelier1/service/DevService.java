package spring.ateliers.g5.atelier1.service;

import spring.ateliers.g5.atelier1.model.Developpeur;

import java.util.List;
import java.util.Optional;

public interface DevService {

    Developpeur ajouter(Developpeur developpeur);

    List<Developpeur> getDevs();

    Optional<Developpeur> getDeveloppeur(Long idDev);

    Developpeur getDeveloppeur(String email);

    List<Developpeur> getDevsByProjet(Long idProjet);

    void affecterTache(Long idDev, Long idTache);
}

