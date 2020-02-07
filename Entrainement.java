import java.util.ArrayList; 
import java.util.Scanner;

/* Ce fichier permet de gérer les différentes méthodes d'entrainement d'un Neurone */

public abstract class Entrainement{

    public static Neurone NeuroneEntrainementET(Neurone neurone) //Cette methode entraine un neurone avec une porte ET (par defaut en seuil)
    {
        ArrayList<ArrayList<Float>> tableET = new ArrayList<ArrayList<Float>>(4); //La y'a rien d'Interressant, je cree la table ET de facon a pouvoir l'utiliser

        ArrayList<Float> ligne1 = new ArrayList<Float>(2);
        ligne1.add((float)0);
        ligne1.add((float)0);

        ArrayList<Float> ligne2 = new ArrayList<Float>(2);
        ligne2.add((float)0);
        ligne2.add((float)1);

        ArrayList<Float> ligne3 = new ArrayList<Float>(2);
        ligne3.add((float)1);
        ligne3.add((float)0);

        ArrayList<Float> ligne4 = new ArrayList<Float>(2);
        ligne4.add((float)1);
        ligne4.add((float)1);

        tableET.add(ligne1);
        tableET.add(ligne2);
        tableET.add(ligne3);
        tableET.add(ligne4);

        ArrayList<Float> resultatsAttendus =new ArrayList<Float>(4);
        resultatsAttendus.add((float)0);
        resultatsAttendus.add((float)0);
        resultatsAttendus.add((float)0);
        resultatsAttendus.add((float)1);  //Ca fait beaucoup de lignes

        float vitesseApprentissage=(float)0.1;
        boolean valide=false;
        boolean failDansBoucle=false;
        int compteTour=1;

        while (valide==false)
        {
            System.out.println("Tour " +compteTour);
            failDansBoucle=false;
            for (int Indice=0; Indice<4; Indice++)  
            {
                neurone=neurone.calculSommeNeurone(neurone,tableET.get(Indice));
                neurone=FonctionsTransfert.AppliqueFctTransfert(neurone);
                System.out.println(neurone.sortieTransfert);
                if ((neurone.sortieTransfert>resultatsAttendus.get(Indice)-0.015)&&(neurone.sortieTransfert<resultatsAttendus.get(Indice)+0.015))
                {
                    continue;
                }
                else if ((neurone.sortieTransfert<resultatsAttendus.get(Indice)-0.015)||(neurone.sortieTransfert>resultatsAttendus.get(Indice)+0.015))
                {
                    neurone=neurone.modifiePoids(neurone, tableET.get(Indice), vitesseApprentissage, resultatsAttendus.get(Indice));
                    failDansBoucle=true;
                    continue;
                }
            }
            compteTour+=1;
            if (failDansBoucle==true) valide=false;
            else if (failDansBoucle==false) valide=true;

        }
        return(neurone);
    }


    public static Neurone NeuroneEntrainementOU(Neurone neurone) //Cette methode entraine un neurone avec une porte OU (par defaut en seuil)
    {
        ArrayList<ArrayList<Float>> tableOU = new ArrayList<ArrayList<Float>>(4); //La y'a rien d'Interressant, je cree la table OU de facon a pouvoir l'utiliser

        ArrayList<Float> ligne1 = new ArrayList<Float>(2);
        ligne1.add((float)0);
        ligne1.add((float)0);

        ArrayList<Float> ligne2 = new ArrayList<Float>(2);
        ligne2.add((float)0);
        ligne2.add((float)1);

        ArrayList<Float> ligne3 = new ArrayList<Float>(2);
        ligne3.add((float)1);
        ligne3.add((float)0);

        ArrayList<Float> ligne4 = new ArrayList<Float>(2);
        ligne4.add((float)1);
        ligne4.add((float)1);

        tableOU.add(ligne1);
        tableOU.add(ligne2);
        tableOU.add(ligne3);
        tableOU.add(ligne4);

        ArrayList<Float> resultatsAttendus =new ArrayList<Float>(4);
        resultatsAttendus.add((float)0);
        resultatsAttendus.add((float)1);
        resultatsAttendus.add((float)1);
        resultatsAttendus.add((float)1);  //Ca fait beaucoup de lignes

        float vitesseApprentissage=(float)0.1;
        boolean valide=false;
        boolean failDansBoucle=false;
        int compteTour=1;

        while (valide==false)
        {
            System.out.println("Tour " +compteTour);
            failDansBoucle=false;
            for (int Indice=0; Indice<4; Indice++)  
            {
                neurone=neurone.calculSommeNeurone(neurone,tableOU.get(Indice));
                neurone=FonctionsTransfert.AppliqueFctTransfert(neurone);
                System.out.println(neurone.sortieTransfert);
                if ((neurone.sortieTransfert>resultatsAttendus.get(Indice)-0.015)&&(neurone.sortieTransfert<resultatsAttendus.get(Indice)+0.015))
                {
                    continue;
                }
                else if ((neurone.sortieTransfert<resultatsAttendus.get(Indice)-0.015)||(neurone.sortieTransfert>resultatsAttendus.get(Indice)+0.015))
                {
                    neurone=neurone.modifiePoids(neurone, tableOU.get(Indice), vitesseApprentissage, resultatsAttendus.get(Indice));
                    failDansBoucle=true;
                    continue;
                }
            }
            compteTour+=1;
            if (failDansBoucle==true) valide=false;
            else if (failDansBoucle==false) valide=true;

        }
        return(neurone);
    }


    public static Neurone NeuroneEntrainementMamiferes(Neurone neurone, int choixAnimal, ArrayList<ArrayList<Float>> echantillons) //Cette methode entraine un neurone avec une ArrayList de Sons Prédéfinis
    {
        float vitesseApprentissage=(float)0.1;
        boolean valide=false;
        boolean failDansBoucle=false;
        int compteTour=1;
        ArrayList<Integer> resultatsAttendus = new ArrayList<Integer>(); //40 c'est le nombre de fichiers pour l'entrainement
        resultatsAttendus=ResultatsAnimal(choixAnimal);
        while (valide==false)
        {
            System.out.println("Tour " +compteTour);
            failDansBoucle=false;
            for (int Indice=0; Indice<40; Indice++)  //40 c'est toujours le nombre de fichier pour l'entrainement
            {
                neurone=neurone.calculSommeNeurone(neurone,echantillons.get(Indice));
                neurone=FonctionsTransfert.AppliqueFctTransfert(neurone);
                System.out.println(neurone.sortieTransfert);
                if ((neurone.sortieTransfert>resultatsAttendus.get(Indice)-0.015)&&(neurone.sortieTransfert<resultatsAttendus.get(Indice)+0.015))
                {
                    continue;
                }
                else if ((neurone.sortieTransfert<resultatsAttendus.get(Indice)-0.015)||(neurone.sortieTransfert>resultatsAttendus.get(Indice)+0.015))
                {
                    neurone=neurone.modifiePoids(neurone, echantillons.get(Indice), vitesseApprentissage, resultatsAttendus.get(Indice));
                    failDansBoucle=true;
                    continue;
                }
            }
            compteTour+=1;
            if (failDansBoucle==true) valide=false;
            else if (failDansBoucle==false) valide=true;
        }
        return(neurone);
    }


    public static Neurone ChoixEntrainement(Neurone neurone, Scanner sc, ArrayList<ArrayList<Float>> echantillons) //Cette fonction permet de choisir la facon dont on entraine un neurone, ainsi que le type d'animal marin, si necessaire
    {
        int choixEnt=0;
        int choixAnimal=0;
        int tempo=0;
        while (choixEnt==0) //Choix du type d'entrainement
        {
            try 
            {
                System.out.println("Type d'entrainement de ce neurone ?");
                System.out.println(" 1/Porte ET 2/Porte OU 3/Animaux Marins ");
                tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                if ((tempo==1)||(tempo==2)||(tempo==3))
                {
                    choixEnt=tempo;
                }
                else
                {
                    System.out.println("Cette valeur ne correspond pas a un type d'entrainement!");
                    choixEnt=0;
                }
                break;
            } 
            catch (Exception e)
            {
                System.out.println("Choix invalide, recommencez!");
                while (sc.hasNextLine()) sc.nextLine();
                choixEnt=0;
            }
        }
        switch(choixEnt)
        {
            case 1:
                neurone=NeuroneEntrainementET(neurone);
                break;
            case 2:
                neurone=NeuroneEntrainementOU(neurone);
                break;
            case 3:
                while (choixAnimal==0)  //Choix du type d'animal marin pour entrainer le neurone
                {
                    try 
                    {
                        System.out.println("Quel animal marin pour ce neurone ?");
                        System.out.println(" 1/Baleine 2/Dauphin 3/Narval 4/Orque");
                        tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                        if ((tempo==1)||(tempo==2)||(tempo==3)||(tempo==4))
                        {
                            choixAnimal=tempo;
                        }
                        else
                        {
                            System.out.println("Cette valeur ne correspond pas a un animal!");
                            choixAnimal=0;
                        }
                        break;
                    } 
                    catch (Exception e)
                    {
                        System.out.println("Choix invalide, recommencez!");
                        while (sc.hasNextLine()) sc.nextLine();
                        choixAnimal=0;
                    }
                }
                neurone=NeuroneEntrainementMamiferes(neurone,choixAnimal, echantillons);
                break;
        }
        System.out.println("Neurone Pret !");
        return(neurone);
    }


    public static ArrayList<Integer> ResultatsAnimal(int choixAnimal) //Cette fonction prepare le tableau des solutions pour l'apprentissage en fonction de l'animal choisi
    {
        ArrayList<Integer> resultatsAttendus = new ArrayList<Integer>();
        for (int Indice=0; Indice<40; Indice++) resultatsAttendus.add(0); //On commence par tout mettre a 0
        switch (choixAnimal)
        {
            case 1:
                for (int Indice=0; Indice<10; Indice++) resultatsAttendus.set(Indice, 1);
                break;

            case 2:
                for (int Indice=10; Indice<20; Indice++) resultatsAttendus.set(Indice, 1);
                break;

            case 3:
                for (int Indice=20; Indice<30; Indice++) resultatsAttendus.set(Indice, 1);
                break;

            case 4:
                for (int Indice=30; Indice<40; Indice++) resultatsAttendus.set(Indice, 1);
                break;
        }
        return(resultatsAttendus);
    }
}