# Compte Rendu de TP4 : Persistance de données

la persistance des données et parfois des états d'un programme réfère au mécanisme responsable de la sauvegarde et de la restauration des données. 
Ces mécanismes font en sorte qu'un programme puisse se terminer sans que ses données et son état d'exécution soient perdus.

**Le but de cette aplication:**
Enregistrer une liste des étudiants par sérialisation dans un fichier afin de le récupérer lors de l'exécution suivante de l'application.
1. Ajouter des etudiants a un fichier ("myFile.dat")
et pour cela on va ajouter un étudiant à une liste(déserialisée) puis remplacer(serialiser) la liste du fichier "myFile.dat" par la nouvelle liste qui contient l'étudiant enregistrée.


![SecondFrag](https://user-images.githubusercontent.com/80216049/159104393-0a13df99-78fb-4625-ae59-c70241700133.PNG)

2. Récupérer et afficher la liste des étudiants(déserialisés) persistée dans le fichier 

![image](https://user-images.githubusercontent.com/80216049/159104610-a69321c8-0eaf-4957-b240-0d1e8237e26c.png)

## Les interfaces de cette application 

![1](https://user-images.githubusercontent.com/80216049/159104696-384c3e68-bb60-47f2-afee-6d583567b429.jpg)

![2](https://user-images.githubusercontent.com/80216049/159104700-68d285cc-b1df-42e4-b56a-683230e7727d.jpg)

![3](https://user-images.githubusercontent.com/80216049/159104703-d4fe51e1-cc65-4ddf-b41c-0ac93e706851.jpg)
