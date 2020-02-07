import java.util.ArrayList; 
import java.util.Scanner;

/* Ce fichier gère la création d'une couche de neurones */

public class CoucheNeurone{
    protected ArrayList<Neurone> neurones;
    public ArrayList<Float> sortiesCouche;

    
    public CoucheNeurone(int nbrNeurones, int nbrEntrees, Scanner sc, ArrayList<ArrayList<Float>> echantillons)
    {
        this.neurones= InitNeuroneCouche(nbrNeurones, nbrEntrees, sc, echantillons);
        this.sortiesCouche= new ArrayList<Float>(nbrNeurones);
    }


    protected ArrayList<Neurone> InitNeuroneCouche(int nbrNeurones, int nbrEntrees, Scanner sc, ArrayList<ArrayList<Float>> echantillons) //Cette fonction initialise et entraine les neurones d'une couche
    { 
        int fctTransfert=0;
        float maximum=(float)0; //Uniquement utile si fctTransfert=8, ie competitive
        boolean erreurMax=true; //Pareil
        ArrayList<Neurone> neurones = new ArrayList<Neurone>();
        fctTransfert=FonctionsTransfert.ChoixFctTransfert(sc);
        if (fctTransfert==8) //Donc si la fonction choisie est la competitive
        {
            while(erreurMax==true)
            {
                try 
                {
                    System.out.println("Entrez la valeur du maximum pour la fonction competitive : ");
                    maximum=Float.parseFloat(sc.nextLine());
                    erreurMax=false;
                } 
                catch (Exception e)
                {
                    System.out.println("Choix invalide, recommencez!");
                    while (sc.hasNextLine()) sc.nextLine();
                    erreurMax=true;
                }
            }  
        }
        for (int Indice=0; Indice<nbrNeurones; Indice++){
            neurones.add(new Neurone(nbrEntrees));
            (neurones.get(Indice)).fctTransfert=fctTransfert;
            (neurones.get(Indice)).maximum=maximum;
            neurones.set(Indice,Entrainement.ChoixEntrainement(neurones.get(Indice),sc,echantillons));
            System.out.println("===============");
        }
        return(neurones);
    }


    public CoucheNeurone CalculSortiesCouche(CoucheNeurone Couche, ArrayList<Float> entrees) //Cette fonction calcule l'intégralité des sorties de la couche pour un tableau de floats donne
    {
        for (int Indice=0; Indice<Couche.neurones.size(); Indice++) Couche.sortiesCouche.add((float)0); //On initialise tout a 0
        for (int Indice=0; Indice<neurones.size(); Indice++)
        {
            Couche.neurones.set(Indice, (neurones.get(Indice)).calculSommeNeurone(neurones.get(Indice), entrees));
            Couche.neurones.set(Indice, (FonctionsTransfert.AppliqueFctTransfert(neurones.get(Indice))));
            Couche.sortiesCouche.set(Indice, Couche.neurones.get(Indice).sortieTransfert);
        }
        return(Couche);
    }


    public void AfficheSortie(CoucheNeurone Couche) //Pour afficher toutes les sorties de la couche
    {
        System.out.println("Les sorties de la couche sont : ");
        for (int Indice=0; Indice<Couche.neurones.size(); Indice++)
        {
            System.out.println("Sortie du neurone " +(Indice+1) +" de la couche : " +Couche.sortiesCouche.get(Indice));
        }
        return;
    }

}