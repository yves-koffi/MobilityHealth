Voici une proposition de **maquette pour l'application mobile Mobility Health**. Cette maquette est conçue pour offrir une expérience utilisateur intuitive et couvrir les principales fonctionnalités décrites dans le cahier des charges. Elle est organisée en écrans clés.

---

### **1. Écran d'Accueil (Home)**
- **Objectif** : Donner un aperçu rapide des fonctionnalités principales.
- **Éléments** :
    - **Header** : Logo de l'application, bouton de profil utilisateur.
    - **Boutons Principaux** :
        - "Souscrire à une assurance"
        - "Déclarer un sinistre"
        - "Ma carte d'assuré"
    - **Notifications** : Icône de cloche pour les notifications push.
    - **Bannière** : Informations sur les offres en cours ou les messages importants.

---

### **2. Écran de Connexion/Inscription**
- **Objectif** : Permettre à l'utilisateur de se connecter ou de s'inscrire.
- **Éléments** :
    - **Connexion** :
        - Champs pour l'email/numéro de téléphone et le mot de passe.
        - Option "Mot de passe oublié".
        - Boutons de connexion via OAuth (Google, Facebook).
    - **Inscription** :
        - Formulaire pour les informations personnelles (nom, prénom, date de naissance, etc.).
        - Sélection du pays de résidence.
        - Bouton "Créer un compte".

---

### **3. Écran de Souscription**
- **Objectif** : Guider l'utilisateur dans le processus de souscription.
- **Éléments** :
    - **Étape 1 : Sélection de la Destination**
        - Liste déroulante pour choisir le pays de destination.
        - Affichage des packages disponibles (Basic, Premium, etc.).
    - **Étape 2 : Questionnaire Médical**
        - 3 questions de santé obligatoires.
        - Bouton "Passer à l'étape suivante".
    - **Étape 3 : Paiement**
        - Sélection du mode de paiement (carte bancaire, mobile money).
        - Affichage du montant à payer.
        - Bouton "Payer maintenant".

---

### **4. Écran de Profil Utilisateur**
- **Objectif** : Permettre à l'utilisateur de gérer ses informations personnelles.
- **Éléments** :
    - **Informations Personnelles** :
        - Nom, prénom, date de naissance, nationalité, etc.
        - Bouton "Modifier".
    - **Photo de Profil** :
        - Téléchargement d'une photo.
    - **Pièce d'Identité** :
        - Téléchargement d'une pièce d'identité (passeport, CNI).
    - **Préférences de Notification** :
        - Options pour activer/désactiver les notifications (SMS, email, WhatsApp).

---

### **5. Écran de Déclaration de Sinistre**
- **Objectif** : Permettre à l'utilisateur de déclarer une urgence médicale.
- **Éléments** :
    - **Bouton d'Urgence** : Grand bouton rouge "Déclarer un sinistre".
    - **Géolocalisation** : Affichage automatique de la localisation de l'utilisateur.
    - **Description du Sinistre** :
        - Champs pour décrire les symptômes ou l'urgence.
    - **Bouton "Envoyer l'Alerte"**.

---

### **6. Écran de Suivi des Sinistres**
- **Objectif** : Permettre à l'utilisateur de suivre l'état de ses sinistres.
- **Éléments** :
    - **Liste des Sinistres** :
        - Statut (en cours, traité, clôturé).
        - Date et description du sinistre.
    - **Détails du Sinistre** :
        - Informations détaillées sur le sinistre (médecin référent, partenaire de santé, etc.).
        - Bouton "Contacter le Médecin Référent".

---

### **7. Écran de la Carte d'Assuré Virtuelle**
- **Objectif** : Afficher la carte d'assuré virtuelle.
- **Éléments** :
    - **Carte Virtuelle** :
        - Photo de l'utilisateur.
        - Numéro de police d'assurance.
        - Dates de validité.
    - **Boutons** :
        - "Partager la carte" (QR code ou lien).
        - "Mettre à jour la photo".

---

### **8. Écran des Notifications**
- **Objectif** : Afficher les notifications importantes.
- **Éléments** :
    - **Liste des Notifications** :
        - Notifications de paiement réussi/échoué.
        - Rappels pour le questionnaire médical.
        - Alertes de sinistre en cours.
    - **Options** :
        - Marquer comme lu.
        - Supprimer une notification.

---

### **9. Écran des Paramètres**
- **Objectif** : Permettre à l'utilisateur de configurer l'application.
- **Éléments** :
    - **Langue** : Sélection de la langue de l'application.
    - **Sécurité** :
        - Changement de mot de passe.
        - Activation/désactivation de la double authentification.
    - **Déconnexion** : Bouton pour se déconnecter.

---

### **10. Écran de Paiement**
- **Objectif** : Permettre à l'utilisateur de payer sa souscription.
- **Éléments** :
    - **Montant à Payer** : Affichage clair du montant.
    - **Modes de Paiement** :
        - Carte bancaire (Visa, Mastercard).
        - Mobile Money.
    - **Champs de Saisie** :
        - Numéro de carte, date d'expiration, code CVV.
    - **Bouton "Payer"**.

---

### **11. Écran de Confirmation de Souscription**
- **Objectif** : Confirmer la souscription et afficher les détails de la police.
- **Éléments** :
    - **Message de Confirmation** : "Votre souscription a été validée."
    - **Détails de la Police** :
        - Numéro de police.
        - Dates de validité.
        - Destination couverte.
    - **Boutons** :
        - "Télécharger la police en PDF".
        - "Retour à l'accueil".

---

### **12. Écran de Contact et Support**
- **Objectif** : Permettre à l'utilisateur de contacter le support.
- **Éléments** :
    - **Options de Contact** :
        - Chat en direct avec le support.
        - Numéro de téléphone du support.
        - Formulaire de contact par email.
    - **FAQ** : Section avec les questions fréquentes.

---

### **13. Écran de Géolocalisation des Partenaires de Santé**
- **Objectif** : Afficher les établissements de santé partenaires à proximité.
- **Éléments** :
    - **Carte Interactive** : Affichage des partenaires de santé sur une carte.
    - **Liste des Partenaires** :
        - Nom, adresse, distance.
        - Bouton "Itinéraire" pour la navigation.

---

### **14. Écran de Téléconsultation**
- **Objectif** : Permettre à l'utilisateur de consulter un médecin référent en ligne.
- **Éléments** :
    - **Vidéo en Direct** : Interface pour la téléconsultation.
    - **Chat** : Option pour discuter avec le médecin.
    - **Bouton "Terminer la Consultation"**.

---

### **15. Écran de Statistiques et Reporting (Back-Office)**
- **Objectif** : Afficher les statistiques pour les administrateurs.
- **Éléments** :
    - **Graphiques** :
        - Nombre de souscriptions par mois.
        - Nombre de sinistres déclarés.
    - **Export des Données** : Bouton pour exporter les données en PDF ou Excel.

---

### **Conclusion**
Cette maquette couvre l'ensemble des fonctionnalités décrites dans le cahier des charges. Elle peut être adaptée en fonction des retours des utilisateurs et des évolutions du projet. Les écrans peuvent être conçus avec des outils de conception d'interface comme **Figma**, **Adobe XD**, ou **Sketch** pour une visualisation plus détaillée.