Voici un **Modèle Conceptuel de Données (MCD)** optimisé pour l'application **Mobility Health**, incluant toutes les entités, attributs et relations nécessaires. Ce MCD est conçu pour couvrir les fonctionnalités décrites dans le cahier des charges tout en étant évolutif et performant.

---
Téléconsultation
### **1. Entités et Attributs**

#### **1.1. Utilisateur (User)**
- `id` (PK) : Identifiant unique de l'utilisateur.
- `nom` : Nom de l'utilisateur.
- `prénom` : Prénom de l'utilisateur.
- `date_naissance` : Date de naissance de l'utilisateur.
- `nationalité` : Nationalité de l'utilisateur.
- `pays_résidence` : Pays de résidence habituel.
- `num_téléphone` : Numéro de téléphone principal.
- `num_whatsapp` : Numéro WhatsApp.
- `email` : Adresse email.
- `mot_de_passe` : Mot de passe chiffré.
- `photo` : Lien vers la photo de profil.
- `pièce_identité` : Lien vers la pièce d'identité (passeport, CNI).
- `role` : Rôle de l'utilisateur (assuré, souscripteur, médecin référent, admin).

#### **1.2. Souscription (Subscription)**
- `id` (PK) : Identifiant unique de la souscription.
- `user_id` (FK) : Référence à l'utilisateur qui a souscrit.
- `date_souscription` : Date de la souscription.
- `date_début` : Date de début de la couverture.
- `date_fin` : Date de fin de la couverture.
- `destination` : Pays de destination.
- `formule` : Type de contrat (Basic, Premium, etc.).
- `statut` : Statut de la souscription (en attente, validé, rejeté).

#### **1.3. Police d'Assurance (InsurancePolicy)**
- `id` (PK) : Identifiant unique de la police.
- `subscription_id` (FK) : Référence à la souscription associée.
- `num_police` : Numéro de police d'assurance.
- `date_validation` : Date de validation de la police.
- `statut` : Statut de la police (actif, inactif).

#### **1.4. Questionnaire Médical (MedicalQuestionnaire)**
- `id` (PK) : Identifiant unique du questionnaire.
- `user_id` (FK) : Référence à l'utilisateur qui a rempli le questionnaire.
- `réponses` : Réponses au questionnaire (stockées en JSON ou texte).
- `date_soumission` : Date de soumission du questionnaire.
- `statut` : Statut du questionnaire (complet, incomplet).

#### **1.5. Paiement (Payment)**
- `id` (PK) : Identifiant unique du paiement.
- `subscription_id` (FK) : Référence à la souscription associée.
- `montant` : Montant du paiement.
- `date_paiement` : Date du paiement.
- `méthode_paiement` : Méthode de paiement (carte bancaire, mobile money).
- `statut` : Statut du paiement (réussi, échoué).

#### **1.6. Médecin Référent (ReferentDoctor)**
- `id` (PK) : Identifiant unique du médecin référent.
- `nom` : Nom du médecin.
- `prénom` : Prénom du médecin.
- `spécialité` : Spécialité du médecin.
- `num_téléphone` : Numéro de téléphone du médecin.
- `email` : Adresse email du médecin.
- `localisation` : Localisation GPS du médecin.

#### **1.7. Partenaire Santé (HealthPartner)**
- `id` (PK) : Identifiant unique du partenaire de santé.
- `nom` : Nom de l'établissement.
- `adresse` : Adresse du partenaire.
- `num_téléphone` : Numéro de téléphone du partenaire.
- `email` : Adresse email du partenaire.
- `localisation` : Localisation GPS du partenaire.

#### **1.8. Sinistre (Claim)**
- `id` (PK) : Identifiant unique du sinistre.
- `user_id` (FK) : Référence à l'utilisateur qui a déclaré le sinistre.
- `date_sinistre` : Date du sinistre.
- `localisation` : Localisation GPS du sinistre.
- `description` : Description du sinistre.
- `statut` : Statut du sinistre (en cours, traité, clôturé).

#### **1.9. Facture (Invoice)**
- `id` (PK) : Identifiant unique de la facture.
- `claim_id` (FK) : Référence au sinistre associé.
- `health_partner_id` (FK) : Référence au partenaire de santé.
- `montant` : Montant de la facture.
- `date_facture` : Date de la facture.
- `statut` : Statut de la facture (en attente, payé).

#### **1.10. Carte d'Assuré (InsuranceCard)**
- `id` (PK) : Identifiant unique de la carte.
- `user_id` (FK) : Référence à l'utilisateur associé.
- `num_carte` : Numéro de la carte d'assuré.
- `date_émission` : Date d'émission de la carte.
- `date_expiration` : Date d'expiration de la carte.
- `statut` : Statut de la carte (actif, inactif).

---

### **2. Relations entre les Entités**

1. **Utilisateur (User)**
    - Peut avoir **plusieurs** souscriptions (`1 User` → `* Subscription`).
    - Peut remplir **un** questionnaire médical (`1 User` → `1 MedicalQuestionnaire`).
    - Peut déclarer **plusieurs** sinistres (`1 User` → `* Claim`).
    - Peut avoir **une** carte d'assuré (`1 User` → `1 InsuranceCard`).

2. **Souscription (Subscription)**
    - Est associée à **une** police d'assurance (`1 Subscription` → `1 InsurancePolicy`).
    - Peut avoir **plusieurs** paiements (`1 Subscription` → `* Payment`).

3. **Sinistre (Claim)**
    - Est associé à **un** partenaire de santé (`1 Claim` → `1 HealthPartner`).
    - Peut générer **plusieurs** factures (`1 Claim` → `* Invoice`).

4. **Médecin Référent (ReferentDoctor)**
    - Peut valider **plusieurs** factures (`1 ReferentDoctor` → `* Invoice`).

5. **Partenaire Santé (HealthPartner)**
    - Peut générer **plusieurs** factures (`1 HealthPartner` → `* Invoice`).

---

### **3. Diagramme MCD (Représentation Visuelle)**

```
+-----------------+          +-----------------+          +-----------------+
|    Utilisateur  |          |   Souscription  |          | PoliceAssurance |
|-----------------|          |-----------------|          |-----------------|
| id (PK)         |<-------->| id (PK)         |<-------->| id (PK)         |
| nom             |          | user_id (FK)    |          | subscription_id |
| prénom          |          | date_souscription|         | num_police      |
| date_naissance  |          | date_début      |          | date_validation |
| nationalité     |          | date_fin        |          | statut          |
| pays_résidence  |          | destination     |          +-----------------+
| num_téléphone   |          | formule         |
| num_whatsapp    |          | statut          |
| email           |          +-----------------+
| mot_de_passe    |
| photo           |
| pièce_identité  |
| role            |
+-----------------+

+-----------------+          +-----------------+          +-----------------+
| Questionnaire   |          |    Paiement     |          | MédecinRéférent |
| Médical         |          |-----------------|          |-----------------|
|-----------------|          | id (PK)         |          | id (PK)         |
| id (PK)         |<-------->| subscription_id |          | nom             |
| user_id (FK)    |          | montant         |          | prénom          |
| réponses        |          | date_paiement   |          | spécialité      |
| date_soumission |          | méthode_paiement|          | num_téléphone   |
| statut          |          | statut          |          | email           |
+-----------------+          +-----------------+          | localisation    |
                                                         +-----------------+

+-----------------+          +-----------------+          +-----------------+
| PartenaireSanté |          |    Sinistre     |          |    Facture      |
|-----------------|          |-----------------|          |-----------------|
| id (PK)         |<-------->| id (PK)         |<-------->| id (PK)         |
| nom             |          | user_id (FK)    |          | claim_id (FK)   |
| adresse         |          | date_sinistre   |          | health_partner_id|
| num_téléphone   |          | localisation    |          | montant         |
| email           |          | description     |          | date_facture    |
| localisation    |          | statut          |          | statut          |
+-----------------+          +-----------------+          +-----------------+

+-----------------+
| CarteAssuré     |
|-----------------|
| id (PK)         |
| user_id (FK)    |
| num_carte       |
| date_émission   |
| date_expiration |
| statut          |
+-----------------+
```

---

### **4. Explications des Relations**

1. **Utilisateur → Souscription** : Un utilisateur peut souscrire à plusieurs polices d'assurance.
2. **Souscription → Police d'Assurance** : Chaque souscription génère une police d'assurance.
3. **Souscription → Paiement** : Une souscription peut avoir plusieurs paiements (en cas de paiement échelonné).
4. **Utilisateur → Sinistre** : Un utilisateur peut déclarer plusieurs sinistres.
5. **Sinistre → Facture** : Chaque sinistre peut générer plusieurs factures (en cas de plusieurs prestations).
6. **Partenaire Santé → Facture** : Les partenaires de santé génèrent des factures pour les prestations fournies.
7. **Médecin Référent → Facture** : Les médecins référents valident les factures avant paiement.
8. **Utilisateur → Carte d'Assuré** : Chaque utilisateur a une carte d'assuré virtuelle.

---

### **5. Optimisations**
- **Normalisation** : Les tables sont normalisées pour éviter la redondance des données.
- **Indexation** : Les clés primaires et étrangères sont indexées pour optimiser les requêtes.
- **Évolutivité** : Le modèle est conçu pour être facilement extensible avec de nouvelles fonctionnalités.

---

Ce MCD est prêt à être implémenté dans une base de données relationnelle (MySQL, PostgreSQL, etc.) ou NoSQL (MongoDB) selon les besoins techniques du projet.