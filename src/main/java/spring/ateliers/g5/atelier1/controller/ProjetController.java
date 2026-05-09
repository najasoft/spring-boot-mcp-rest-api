package spring.ateliers.g5.atelier1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.model.Projet;
import spring.ateliers.g5.atelier1.model.ProjetDev;
import spring.ateliers.g5.atelier1.model.ProjetRes;
import spring.ateliers.g5.atelier1.model.Tache;
import spring.ateliers.g5.atelier1.service.ProjetService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public List<Projet> findAll() {
        return projetService.lesProjets();
    }

    @GetMapping("/{id}")
    public Projet findById(@PathVariable Long id) {
        return projetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet introuvable"));
    }

    @GetMapping("/dev")
    public List<ProjetDev> findAllProjetDev() {
        return projetService.getProjetsDev();
    }

    @GetMapping("/res")
    public List<ProjetRes> findAllProjetRes() {
        return projetService.getProjetsRes();
    }

    @PostMapping
    public Projet add(@RequestBody Projet projet) {
        return projetService.ajouterProjet(projet);
    }

    @PostMapping("/dev")
    public ProjetDev addProjetDev(@RequestBody ProjetDev projetDev) {
        return projetService.ajouterProjetDev(projetDev);
    }

    @PostMapping("/res")
    public ProjetRes addProjetRes(@RequestBody ProjetRes projetRes) {
        return projetService.ajouterProjetRes(projetRes);
    }

    @PostMapping("/{idProjet}/taches")
    public void ajouterTache(@PathVariable Long idProjet, @RequestBody Tache tache) {
        try {
            projetService.ajouterTache(idProjet, tache);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/{idProjet}/taches")
    public List<Tache> getTaches(@PathVariable Long idProjet) {
        return projetService.getTaches(idProjet);
    }

    @GetMapping("/{idProjet}/devs")
    public List<Developpeur> getDeveloppeurs(@PathVariable Long idProjet) {
        return projetService.getDeveloppeursAffectes(idProjet);
    }
}
