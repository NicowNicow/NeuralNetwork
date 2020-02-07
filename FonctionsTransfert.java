import java.lang.Math;
import java.util.Scanner;

/* Ce fichier permet de décrire les différentes fonctions de transfert utilisable par les neurones */

public class FonctionsTransfert{

    public static Neurone Seuil(Neurone neurone)   //Cette methode applique la fonction seuil a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<0) neurone.sortieTransfert=(float)0;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=(float)1;
        return(neurone);
    }


    public static Neurone Lineaire(Neurone neurone)  //Cette methode applique la fonction lineaire a la somme de sortie d'un neurone
    {
        return(neurone);
    }


    public static Neurone Sigmoide(Neurone neurone)  //Cette methode applique la fonction sigmoide a la somme de sortie d'un neurone
    {
        neurone.sortieTransfert=1/(float)(1+Math.exp(-neurone.sommeSortie));
        return(neurone);
    }


    public static Neurone SeuilSymetrique(Neurone neurone) //Cette methode applique la fonction Seuil Symetrique a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<0) neurone.sortieTransfert=-(float)1;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=(float)1;
        return(neurone);
    }


    public static Neurone LineaireSatureeSymetrique(Neurone neurone) //Cette methode applique la fonction Lineaire Saturee Symetrique a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<-1) neurone.sortieTransfert=(float)-1;
        if ((neurone.sommeSortie>=-1)&&(neurone.sommeSortie<=1)) neurone.sortieTransfert=neurone.sommeSortie;
        if (neurone.sommeSortie>1) neurone.sortieTransfert=(float)1;
        return(neurone);
    }


    public static Neurone LineairePositive(Neurone neurone) //Cette methode applique la fonction Lineaire Positive a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<0) neurone.sortieTransfert=(float)0;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=neurone.sommeSortie;
        return(neurone);
    }


    public static Neurone TangeanteHyperbolique(Neurone neurone) //Cette methode applique la fonction tangeante hyperbolique a la somme de sortie d'un neurone
    {
        neurone.sortieTransfert=(float)((Math.exp((double)neurone.sommeSortie)-Math.exp((double)-neurone.sommeSortie))/(Math.exp((double)neurone.sommeSortie)+Math.exp((double)-neurone.sommeSortie)));
        return(neurone);
    }


    public static Neurone Competitive(Neurone neurone) //Cette methode applique la fonction competitive a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie==neurone.maximum) neurone.sortieTransfert=(float)1;
        else {neurone.sortieTransfert=(float)0;}
        return(neurone);
    }


    public static int ChoixFctTransfert(Scanner sc) //Cette methode permet à l'utilisateur de choisir la fonction de transfert a appliquer a chaque neurone
    {
        int choixFct=0;
        int tempo=0;
        while (choixFct==0)
        {
            try 
            {
                System.out.println("Type de fonction de transfert de cette couche ?");
                System.out.println(" 1/Seuil 2/Seuil Symetrique 3/Lineaire 4/Lineaire Saturee Symetrique 5/Lineaire Positive 6/Sigmoide 7/Tangeant Hyperbolique 8/Competitive");
                tempo=Integer.parseInt(sc.nextLine()); //Parce que nextInt bug et je sais pas pourquoi, donc je l'evite comme je peux
                if ((tempo==1)||(tempo==2)||(tempo==3)||(tempo==4)||(tempo==5)||(tempo==6)||(tempo==7)||(tempo==8))
                {
                    choixFct=tempo;
                }
                else
                {
                    System.out.println("Cette valeur ne correspond pas a une fonction de tranfert!");
                    choixFct=0;
                }
                break;
            } 
            catch (Exception e)
            {
                System.out.println("Choix invalide, recommencez!");
                while (sc.hasNextLine()) sc.nextLine();
                choixFct=0;
            }
        }
        //sc.close; Quand je ferme le scanner, le programme finit par bloquer, j'essayerai de corriger ca si j'ai le temps
        return(choixFct);
    }


    public static Neurone AppliqueFctTransfert(Neurone neurone) //Cette methode applique la fonction de transfert choisi au neurone; pas bien interessant, c'est une suite de condition sur un int
    {
        if(neurone.fctTransfert==1) neurone=Seuil(neurone);
        if(neurone.fctTransfert==2) neurone=SeuilSymetrique(neurone);
        if(neurone.fctTransfert==3) neurone=Lineaire(neurone);
        if(neurone.fctTransfert==4) neurone=LineaireSatureeSymetrique(neurone);
        if(neurone.fctTransfert==5) neurone=LineairePositive(neurone);
        if(neurone.fctTransfert==6) neurone=Sigmoide(neurone);
        if(neurone.fctTransfert==7) neurone=TangeanteHyperbolique(neurone);
        if(neurone.fctTransfert==8) neurone=Competitive(neurone);
        return(neurone);
    }
}

