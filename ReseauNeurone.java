import java.util.ArrayList; 
import java.util.Scanner;

//Ce fichier gere la creation d'un reseau complet de neurones 

public class ReseauNeurone{
    protected ArrayList<CoucheNeurone> couches;
    public ArrayList<Float> sortiesReseau;


    public ReseauNeurone(ArrayList<ArrayList<Float>> echantillons, Scanner sc)
    {
       this.couches=InitReseau(sc, echantillons);
       this.sortiesReseau= new ArrayList<Float>(this.couches.get(this.couches.size()-1).neurones.size()); //La taille est set par rapport au nombre de neurones dans la derniere couche
    }


    public ReseauNeurone(ArrayList<ArrayList<Float>> echantillons, Scanner sc, int nbrCouches, int nbrEntrees) //Le constructeur avec nombre de couches et nombre d'entrees deja fourni
    {
       this.couches=InitReseau(sc, echantillons, nbrCouches, nbrEntrees);
       this.sortiesReseau= new ArrayList<Float>(this.couches.get(this.couches.size()-1).neurones.size()); //La taille est set par rapport au nombre de neurones dans la derniere couche
    }


    protected ArrayList<CoucheNeurone> InitReseau(Scanner sc, ArrayList<ArrayList<Float>> echantillons) //Cette fonction permet d'initialiser un reseau complet de neurones
    {
        int nbrCouches=0;
        int nbrEntrees=0;
        int nbrNeurones=0;
        int tempo=0;
        boolean firstCouche=true;
        ArrayList<CoucheNeurone> couches= new ArrayList<CoucheNeurone>();
        while (nbrCouches==0)
        {
            try 
            {
                System.out.println("Nombre de couches de ce reseau ?");
                tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                nbrCouches=tempo;
            } 
            catch (Exception e)
            {
                System.out.println("Choix invalide, recommencez!");
                while (sc.hasNextLine()) sc.nextLine();
                nbrCouches=0;
            }
        }
        for (int Indice=0; Indice<nbrCouches; Indice++)
        {
            if (firstCouche==true)
            {
                while (nbrEntrees==0)
                {
                    try 
                    {
                        System.out.println("Nombre d'entree de ce reseau ?");
                        tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                        nbrEntrees=tempo;
                    } 
                    catch (Exception e)
                    {
                        System.out.println("Choix invalide, recommencez!");
                        while (sc.hasNextLine()) sc.nextLine();
                        nbrEntrees=0;
                    }
                }
                while (nbrNeurones==0)
                {
                    try 
                    {
                        System.out.println("Nombre de neurones de la premiere couche ?");
                        tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                        nbrNeurones=tempo;
                    } 
                    catch (Exception e)
                    {
                        System.out.println("Choix invalide, recommencez!");
                        while (sc.hasNextLine()) sc.nextLine();
                        nbrNeurones=0;
                    }
                }
                firstCouche=false;
                couches.add(new CoucheNeurone(nbrNeurones, nbrEntrees, sc, echantillons));
            }
            else
            {
                nbrEntrees=nbrNeurones; //Nombre d'entree de la nouvelle couche
                nbrNeurones=0;
                while (nbrNeurones==0)
                {
                    try 
                    {
                        System.out.println("Nombre de neurones de la couche " +(Indice+1) +" ?");
                        tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                        nbrNeurones=tempo;
                    } 
                    catch (Exception e)
                    {
                        System.out.println("Choix invalide, recommencez!");
                        while (sc.hasNextLine()) sc.nextLine();
                    }
                }
                couches.add(new CoucheNeurone(nbrNeurones, nbrEntrees, sc, echantillons));
            }
        }
        return(couches);
    }


    protected ArrayList<CoucheNeurone> InitReseau(Scanner sc, ArrayList<ArrayList<Float>> echantillons, int nbrCouches, int nbrEntrees) //Cette fonction permet d'initialiser un reseau simple couche
    {
        int nbrNeurones=0;
        int tempo=0;
        ArrayList<CoucheNeurone> couches= new ArrayList<CoucheNeurone>(nbrCouches);
        for (int Indice=0; Indice<nbrCouches; Indice++)
        {
            nbrNeurones=0;
            while (nbrNeurones==0)
            {
                try 
                {
                    System.out.println("Nombre de neurones de la couche " +(Indice+1) +" ?");
                    tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                    nbrNeurones=tempo;
                } 
                catch (Exception e)
                {
                    System.out.println("Choix invalide, recommencez!");
                    while (sc.hasNextLine()) sc.nextLine();
                }
            }
            couches.add(new CoucheNeurone(nbrNeurones, nbrEntrees, sc, echantillons));
        }
        return(couches);
    }


    public ReseauNeurone AssocieSortiesReseau(ReseauNeurone reseau) //Associe les sorties de la derniere couche a la sortie du reseau
    {
        for (int Indice=0; Indice<reseau.couches.get(reseau.couches.size()-1).neurones.size(); Indice++)
        {
            reseau.sortiesReseau.set(Indice, reseau.couches.get(reseau.couches.size()-1).sortiesCouche.get(Indice));
        }
        return(reseau);
    }


    public void AfficheSorties(ReseauNeurone reseau)
     {
        System.out.println("Les sorties du reseau sont : ");
        for (int Indice=0; Indice<reseau.sortiesReseau.size(); Indice++)
        {
            System.out.println("Sortie " +(Indice+1) +" du reseau : " +reseau.sortiesReseau.get(Indice));
        }
        return;
     }
}