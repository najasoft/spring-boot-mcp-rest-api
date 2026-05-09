package spring.ateliers.g5.atelier1.service;

import org.springframework.stereotype.Service;
import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.model.Tache;
import spring.ateliers.g5.atelier1.repository.DevRepository;
import spring.ateliers.g5.atelier1.repository.TacheRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DevServiceImpl implements DevService {

    private final DevRepository devRepository;
    private final TacheRepository tacheRepository;

    public DevServiceImpl(DevRepository devRepository, TacheRepository tacheRepository) {
        this.devRepository = devRepository;
        this.tacheRepository = tacheRepository;
    }

    @Override
    public Developpeur ajouter(Developpeur developpeur) {
        return devRepository.save(developpeur);
    }

    @Override
    public List<Developpeur> getDevs() {
        return devRepository.findAll();
    }

    @Override
    public Optional<Developpeur> getDeveloppeur(Long idDev) {
        return devRepository.findById(idDev);
    }

    @Override
    public Developpeur getDeveloppeur(String email) {
        return devRepository.findByEmail(email);
    }

    @Override
    public List<Developpeur> getDevsByProjet(Long idProjet) {
        return devRepository.getDevs(idProjet);
    }

    @Override
    public void affecterTache(Long idDev, Long idTache) {
        Developpeur developpeur = devRepository.findById(idDev)
                .orElseThrow(() -> new IllegalArgumentException("Developpeur introuvable"));
        Tache tache = tacheRepository.findById(idTache)
                .orElseThrow(() -> new IllegalArgumentException("Tache introuvable"));

        developpeur.getTaches().add(tache);
        devRepository.save(developpeur);
    }
}

