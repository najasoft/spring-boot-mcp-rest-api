package spring.ateliers.g5.atelier1.mcp;

import java.util.List;
import java.util.Optional;

import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import spring.ateliers.g5.atelier1.model.Developpeur;
import spring.ateliers.g5.atelier1.model.Projet;
import spring.ateliers.g5.atelier1.model.ProjetDev;
import spring.ateliers.g5.atelier1.model.ProjetRes;
import spring.ateliers.g5.atelier1.model.Tache;
import spring.ateliers.g5.atelier1.service.ProjetService;

/**
 * MCP Tools pour les opérations sur les Projets et Tâches
 */
@Service
public class ProjetMcpTools {

    private final ProjetService projetService;

    public ProjetMcpTools(ProjetService projetService) {
        this.projetService = projetService;
    }

    @McpTool(name = "lesProjets", description = "Récupère la liste de tous les projets")
    public List<Projet> lesProjets() {
        return projetService.lesProjets();
    }

    @McpTool(name = "getProjetsDev", description = "Récupère la liste de tous les projets de développement")
    public List<ProjetDev> getProjetsDev() {
        
        return projetService.getProjetsDev();
    }

    @McpTool(name = "getProjetsRes", description = "Récupère la liste de tous les projets de recherche")
    public List<ProjetRes> getProjetsRes() {
        return projetService.getProjetsRes();
    }

    @McpTool(name = "findProjetById", description = "Récupère un projet spécifique par son ID")
    public Optional<Projet> findProjetById(
            @McpArg(name = "id", description = "ID du projet", required = true) Long id) {
        return projetService.findById(id);
    }

    @McpTool(name = "ajouterProjetDev", description = "Ajoute un nouveau projet de développement")
    public ProjetDev ajouterProjetDev(
            @McpArg(name = "description", description = "Description du projet", required = true) String description,
            @McpArg(name = "langage", description = "Langage de programmation", required = true) String langage) {
        ProjetDev projetDev = new ProjetDev();
        projetDev.setDescription(description);
        projetDev.setLangage(langage);
        return projetService.ajouterProjetDev(projetDev);
    }

    @McpTool(name = "ajouterProjetRes", description = "Ajoute un nouveau projet de recherche")
    public ProjetRes ajouterProjetRes(
            @McpArg(name = "description", description = "Description du projet", required = true) String description,
            @McpArg(name = "budget", description = "Budget alloué au projet", required = true) double budget) {
        ProjetRes projetRes = new ProjetRes();
        projetRes.setDescription(description);
        projetRes.setBudget(budget);
        return projetService.ajouterProjetRes(projetRes);
    }

    @McpTool(name = "ajouterTache", description = "Ajoute une nouvelle tâche à un projet de développement")
    public void ajouterTache(
            @McpArg(name = "idProjet", description = "ID du projet de développement", required = true) Long idProjet,
            @McpArg(name = "libelle", description = "Libellé ou description de la tâche", required = true) String libelle) {
        Tache tache = new Tache();
        tache.setLibelle(libelle);
        projetService.ajouterTache(idProjet, tache);
    }

    @McpTool(name = "getTaches", description = "Récupère la liste de toutes les tâches d'un projet de développement")
    public List<Tache> getTaches(
            @McpArg(name = "idProjet", description = "ID du projet de développement", required = true) Long idProjet) {
        return projetService.getTaches(idProjet);
    }

    @McpTool(name = "getDeveloppeursAffectes", description = "Récupère la liste des développeurs affectés à un projet")
    public List<Developpeur> getDeveloppeursAffectes(
            @McpArg(name = "idProjet", description = "ID du projet", required = true) Long idProjet) {
        return projetService.getDeveloppeursAffectes(idProjet);
    }
}

