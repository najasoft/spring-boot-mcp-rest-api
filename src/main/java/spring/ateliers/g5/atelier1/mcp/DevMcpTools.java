package spring.ateliers.g5.atelier1.mcp;

import java.util.List;
import java.util.Optional;

import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.service.DevService;

/**
 * MCP Tools pour les opérations sur les Développeurs
 */
@Service
public class DevMcpTools {

    private final DevService devService;

    public DevMcpTools(DevService devService) {
        this.devService = devService;
    }

    @McpTool(name = "getDevs", description = "Récupère la liste de tous les développeurs")
    public List<Developpeur> getDevs() {
        return devService.getDevs();
    }

    @McpTool(name = "getDeveloppeur", description = "Récupère un développeur spécifique par son ID")
    public Optional<Developpeur> getDeveloppeur(
            @McpArg(name = "idDev", description = "ID du développeur", required = true) Long idDev) {
        return devService.getDeveloppeur(idDev);
    }

    @McpTool(name = "getDeveloppeurByEmail", description = "Récupère un développeur par son adresse email")
    public Developpeur getDeveloppeurByEmail(
            @McpArg(name = "email", description = "Adresse email du développeur", required = true) String email) {
        return devService.getDeveloppeur(email);
    }

    @McpTool(name = "getDevsByProjet", description = "Récupère la liste des développeurs affectés à un projet")
    public List<Developpeur> getDevsByProjet(
            @McpArg(name = "idProjet", description = "ID du projet", required = true) Long idProjet) {
        return devService.getDevsByProjet(idProjet);
    }

    @McpTool(name = "ajouterDeveloppeur", description = "Ajoute un nouveau développeur")
    public Developpeur ajouterDeveloppeur(
            @McpArg(name = "nom", description = "Nom du développeur", required = true) String nom,
            @McpArg(name = "email", description = "Adresse email unique du développeur", required = true) String email) {
        Developpeur developpeur = new Developpeur();
        developpeur.setNom(nom);
        developpeur.setEmail(email);
        return devService.ajouter(developpeur);
    }

    @McpTool(name = "affecterTache", description = "Affecte une tâche à un développeur")
    public void affecterTache(
            @McpArg(name = "idDev", description = "ID du développeur", required = true) Long idDev,
            @McpArg(name = "idTache", description = "ID de la tâche", required = true) Long idTache) {
        devService.affecterTache(idDev, idTache);
    }
}

