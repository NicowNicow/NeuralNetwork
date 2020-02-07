import java.util.Scanner;
import java.util.ArrayList;

//Le fichier principal du projet

public class IDMamiferes
{

    public static void main(String args[])
    {
        boolean arret=false;
        int tempo=0;
        String nomFichier;
        ArrayList<Float> echantillonFichier= new ArrayList<Float>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Preparation des echantillons...");
        ArrayList<ArrayList<Float>> echantillons;
        echantillons=FFTCplx.PreparationEchantillons();
        System.out.println("Echantillons prets !");
        System.out.println("*********************");

        System.out.println("Preparation du Reseau de Neurones...");
        ReseauNeurone reseau = new ReseauNeurone(echantillons, sc, 1, echantillons.get(0).size()); //On a besoin que d'une couche ici
        System.out.println("Reseau de Neurones operationnel!");
        System.out.println("*********************");

        while (arret==false)
        {
            while (tempo==0)  //Choix du type d'animal marin pour entrainer le neurone
            {
                try 
                {
                    System.out.println("Que voulez vous faire? 1/Analyser un son 2/Quitter");
                    tempo=Integer.parseInt(sc.nextLine());
                    break;
                } 
                catch (Exception e)
                {
                    tempo=0;
                    break;
                }
            }
            switch (tempo)
            {
                case 1:
                    System.out.println("Donnez le chemin d'acces au fichier .wav (Exemple: .\\sons\\BaleineTest1.wav) :");
                    nomFichier=sc.nextLine();
                    try
                    {
                        echantillonFichier=FFTCplx.FFTCplxtoArrayList(nomFichier);
                        echantillonFichier=FFTCplx.RedimensioneArrayList(echantillonFichier, echantillons.get(0).size()); //On redimensione a la meme taille que les echantillons d'entrainement
                    }
                    catch (Exception e)
                    {
                        System.out.println("Une erreur est survenue lors du traitement de votre fichier!");
                    }
                    reseau.couches.set(0, reseau.couches.get(0).CalculSortiesCouche(reseau.couches.get(0), echantillonFichier));
                    reseau.couches.get(0).AfficheSortie(reseau.couches.get(0));
                    break;

                case 2:
                    arret=true;
                    break;
                
                default:
                    System.out.println("Choisissez une valeur valide !");
                    break;
            }
            tempo=0;
        }
        sc.close();
        System.out.println("Arret du programme...");
        return;
    }

}


