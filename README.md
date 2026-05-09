# Atelier1 - API REST Spring Boot + Serveur MCP

Application Spring Boot de gestion de projets, tâches et développeurs.
Le projet expose deux interfaces complémentaires :

- une API REST pour les clients HTTP classiques ;
- un serveur MCP pour les clients IA (GitHub Copilot Chat, MCP Inspector, etc.).

## 1. Vue d'ensemble

Architecture en couches :

- `controller/` : points d'accès REST ;
- `mcp/` : outils MCP exposés via `@McpTool` ;
- `service/` : logique métier ;
- `repository/` : accès à la base de données ;
- `model/` : entités JPA.

L'API REST et MCP utilisent les mêmes services métier et la même base de données.

## 2. Prérequis

- Java 25
- Maven Wrapper (`mvnw`, `mvnw.cmd`)
- MySQL local
- Node.js 18+ (uniquement pour MCP Inspector)

## 3. Configuration

Fichier : `application.properties`

Configuration de la base de données :

- `spring.datasource.url=jdbc:mysql://localhost:3306/atelier1_db5?createDatabaseIfNotExist=true`
- `spring.datasource.username=root`
- `spring.datasource.password=root`
- `spring.jpa.hibernate.ddl-auto=update`

Configuration MCP :

- `spring.ai.mcp.server.name=Atelier1 MCP Server`
- `spring.ai.mcp.server.protocol=STREAMABLE`
- `spring.ai.mcp.server.streamable-http.mcp-endpoint=/mcp`

## 4. Démarrage

Démarrer l'application Spring Boot avec Maven Wrapper.
Par défaut, l'application est accessible sur `http://localhost:8080`.

## 5. API REST (Spring Boot)

Contrôleurs REST :

- `controller/ProjetController.java`
- `controller/DevController.java`

Routes principales :

- `GET /projets`
- `GET /projets/{id}`
- `GET /projets/dev`
- `GET /projets/res`
- `POST /projets`
- `POST /projets/dev`
- `POST /projets/res`
- `POST /projets/{idProjet}/taches`
- `GET /projets/{idProjet}/taches`
- `GET /projets/{idProjet}/devs`
- `GET /devs`
- `GET /devs/{id}`
- `GET /devs/email/{email}`
- `GET /devs/projet/{idProjet}`
- `POST /devs`
- `POST /devs/{idDev}/{idTache}`

Swagger UI (si activée) :

- `http://localhost:8080/swagger-ui/index.html`

## 6. Serveur MCP

Point d'accès MCP :

- `http://localhost:8080/mcp`

Outils exposés :

### Projets / Tâches (`ProjetMcpTools`)

- `lesProjets`
- `getProjetsDev`
- `getProjetsRes`
- `findProjetById`
- `ajouterProjetDev(description, langage)`
- `ajouterProjetRes(description, budget)`
- `ajouterTache(idProjet, libelle)`
- `getTaches(idProjet)`
- `getDeveloppeursAffectes(idProjet)`

### Développeurs (`DevMcpTools`)

- `getDevs`
- `getDeveloppeur(idDev)`
- `getDeveloppeurByEmail(email)`
- `getDevsByProjet(idProjet)`
- `ajouterDeveloppeur(nom, email)`
- `affecterTache(idDev, idTache)`

## 7. VS Code + GitHub Copilot Chat

Le serveur MCP peut être ajouté en mode HTTP dans la configuration MCP de VS Code, en pointant vers :

- `http://localhost:8080/mcp`

Une fois visible dans la liste des outils Copilot, les outils MCP peuvent être invoqués directement dans le chat.

## 8. MCP Inspector

MCP Inspector permet de tester et de debugger les outils MCP manuellement.

Configuration Inspector :

- Transport : `Streamable HTTP` (ou `HTTP` selon la version)
- URL serveur : `http://localhost:8080/mcp`

Vérifications recommandées :

1. Lister les outils
2. Tester un outil de lecture
3. Tester un outil d'écriture
4. Vérifier la persistance avec un outil de lecture

## 9. Notes

- L'API REST et MCP coexistent et peuvent être utilisés en parallèle selon le type de client.
