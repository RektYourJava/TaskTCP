PROTOCOLE TCP
===================


PROTOCOLE du serveur TCP pour le gestionnaire de Taches.

---

Le Serveur TCP renvoie un menu permettant à l'utilisateur de choisir ce qu'il souhaite faire lors de sa connexion.

> **Affichage du menu:**

> - **1-Créer Tache** 
> (Saisir "1:nom_tache:nom_createur:nom_executeur").
> - **2-Executer Tache**
> (Saisir "2:nom_executant:num_tache").
> -  **3-Afficher les Taches**
> (Saisir "3").
> - **4-Supprimer une Tache"**
> (Saisir 4:num_tache).

#### <i class="icon-file"></i> **Créer Tache**

Pour créer une tache le client renvoie au serveur une commande du type :

Commande : "*1:nom_tache:nom_createur:nom_executant*"
Ex : "*1:laver:maxime:pierre*"

**Retour Serveur** : 
>Exemple : 
>\>Tache créée | Tache : laver | Createur : maxime | Executant : pierre | n°0

---
**Erreur** : 
Si la commande est mauvaise le serveur avertie le client

>\> Erreur de syntaxe.
	

#### <i class="icon-refresh"></i> **Executer Tache**

Pour executer une tache le client renvoie au serveur une commande du type :

Commande : "*2:nom_executant:num_tache*"
Ex : "*1:pierre:0*"

**Retour Serveur** : 
>Exemple : 
>\>Tache n°0 Done

---
**Erreur** : 
    Si le client n'est pas rattaché à la tache, le serveur avertie le client
    
>\> Client non attaché à une tache /  Tache inexistante
	
Si la commande est mauvaise le serveur avertie le client

>\> Erreur de syntaxe.

#### <i class="icon-upload"></i> **Afficher les Taches**
Pour afficher l'ensemble des taches le client renvoie au serveur une commande du type : 

Commande : "*3*"

**Retour Serveur** :
Si la liste des taches est vides.
>Exemple : 
>\>|Tache : laver | Createur : maxime | Executeur : pierre | Etat : DONE|

Sinon
>\> Liste des taches vide

---
**Erreur** : 
Si la commande est mauvaise le serveur avertie le client

>\> Erreur de syntaxe.

#### <i class="icon-trash"></i> **Supprimer Tache**
Pour supprimer une tache le client renvoie au serveur une commande du type : 

Commande : "*4:num_tache*"

**Retour Serveur** :

>Exemple : 
>\>Tache supprimée.

---
**Erreur** : 
Si la tache n'existe pas

>\> Tache inexistante.

Si la commande est mauvaise le serveur avertie le client

>\> Erreur de syntaxe.

----------