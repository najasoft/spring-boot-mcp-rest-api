package spring.ateliers.g5.atelier1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.service.DevService;

import java.util.List;

@RestController
@RequestMapping("/devs")
public class DevController {

    private final DevService devService;

    public DevController(DevService devService) {
        this.devService = devService;
    }

    @PostMapping
    public Developpeur ajouter(@RequestBody Developpeur developpeur) {
        return devService.ajouter(developpeur);
    }

    @PostMapping("/{idDev}/{idTache}")
    public void affecter(@PathVariable Long idDev, @PathVariable Long idTache) {
        try {
            devService.affecterTache(idDev, idTache);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping
    public List<Developpeur> get() {
        return devService.getDevs();
    }

    @GetMapping("/{id}")
    public Developpeur get(@PathVariable Long id) {
        return devService.getDeveloppeur(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Developpeur introuvable"));
    }

    @GetMapping("/email/{email}")
    public Developpeur getByEmail(@PathVariable String email) {
        Developpeur developpeur = devService.getDeveloppeur(email);
        if (developpeur == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Developpeur introuvable");
        }
        return developpeur;
    }

    @GetMapping("/projet/{idProjet}")
    public List<Developpeur> getDevs(@PathVariable Long idProjet) {
        return devService.getDevsByProjet(idProjet);
    }
}

