
# Projet Genie_Logiciel - Borne de Recharge

## Description du Projet
Ce projet vise à suivre et gérer l'occupation d'un parc de bornes de recharge pour véhicules électriques, permettant aux clients de trouver et de réserver des bornes disponibles. Il met en pratique les compétences acquises en cours et permet de travailler en équipe pour livrer un produit maintenable et fonctionnel dans les délais impartis.

## Fonctionnalités Réalisées

### Gestion des Clients
- Inscription des clients avec informations personnelles et numéro de carte de débit.
- Possibilité d'associer plusieurs véhicules à un client.
- Enregistrement temporaire de véhicules empruntés ou loués lors de la réservation.

### Réservation des Bornes
- Saisie du numéro de plaque d'immatriculation ou de réservation pour utiliser une borne.
- Autorisation de l'utilisation des bornes sans réservation si disponibles.


### Gestion des Bornes
- Mise à jour de l'état des bornes (disponible, réservée, occupée, indisponible).


## Fonctionnalités en Cours de Réalisation
### Gestion des Bornes
- Information des clients sur les bornes indisponibles pour des raisons matérielles.
  
### Réservation des Bornes
- Gestion des périodes d'attente pour les réservations manquées.
- Possibilité de prolonger une réservation existante sous certaines conditions.

### Facturation et Notifications
- Facturation des clients pour les périodes réservées et les dépassements.
- Envoi de notifications pour les dépassements de temps et les pénalités.
- Génération de relevés mensuels pour les clients enregistrés.
  
### Interface Utilisateur
- Développement d'une application mobile pour faciliter la réservation et la gestion des bornes par les clients.
- Affichage de la disponibilité des bornes en temps réel.
- Modifications et annulations de réservations via l'application.

### Gestion Avancée des Réservations
- Fusion de réservations contiguës et gestion des intervalles minimums entre les réservations.
- Options de réservations garanties pour les contrats mensuels et les entreprises clientes.

## Structure du Projet
- **src/** : Dossier contenant le code source de l'application.
- **target/** : Dossier généré contenant les artefacts de build.
- **pom.xml** : Fichier de configuration Maven.

## Instructions pour Cloner le Projet
1. **Cloner le dépôt** :
   ```sh
   git clone -b TestRefactored https://github.com/maghafriachraf/GL.git
   cd GL
   ```



## Contributeurs
- Maghafri Achraf 
- Abbad El Andaloussi Mamoune
- Thaili Mehdi
