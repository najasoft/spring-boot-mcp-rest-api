package spring.ateliers.g5.atelier1.service;

import org.springframework.stereotype.Service;
import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.model.Projet;
import spring.ateliers.g5.atelier1.model.ProjetDev;
import spring.ateliers.g5.atelier1.model.ProjetRes;
import spring.ateliers.g5.atelier1.model.Tache;
import spring.ateliers.g5.atelier1.repository.DevRepository;
import spring.ateliers.g5.atelier1.repository.ProjetRepository;
import spring.ateliers.g5.atelier1.repository.TacheRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService {

    private final ProjetRepository projetRepository;
    private final TacheRepository tacheRepository;
    private final DevRepository devRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository,
                             TacheRepository tacheRepository,
                             DevRepository devRepository) {
        this.projetRepository = projetRepository;
        this.tacheRepository = tacheRepository;
        this.devRepository = devRepository;
    }

    @Override
    public List<Projet> lesProjets() {
        return projetRepository.findAll();
    }

    @Override
    public List<ProjetDev> getProjetsDev() {
        return projetRepository.findAllProjetDev();
    }

    @Override
    public List<ProjetRes> getProjetsRes() {
        return projetRepository.findAllProjetRes();
    }

    @Override
    public Projet ajouterProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public ProjetDev ajouterProjetDev(ProjetDev projetDev) {
        return projetRepository.save(projetDev);
    }

    @Override
    public ProjetRes ajouterProjetRes(ProjetRes projetRes) {
        return projetRepository.save(projetRes);
    }

    @Override
    public void ajouterTache(Long idProjet, Tache tache) {
        ProjetDev projetDev = projetRepository.getProjetDev(idProjet);
        if (projetDev == null) {
            throw new IllegalArgumentException("ProjetDev introuvable");
        }
        tache.setProjetDev(projetDev);
        tacheRepository.save(tache);
    }

    @Override
    public List<Tache> getTaches(Long idProjet) {
        return projetRepository.getTaches(idProjet);
    }

    @Override
    public List<Developpeur> getDeveloppeursAffectes(Long idProjet) {
        return devRepository.getDevs(idProjet);
    }

    @Override
    public Optional<Projet> findById(Long id) {
        return projetRepository.findById(id);
    }

}

