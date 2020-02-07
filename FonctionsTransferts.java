import java.lang.Math;

/* Ce fichier permet de décrire les différentes fonctions de transfert utilisable par les neurones */

public class FonctionsTransferts{

    public static Neurone Seuil(Neurone neurone)   //Cette methode applique la fonction seuil a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<0) neurone.sortieTransfert=0;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=1;
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
        if (neurone.sommeSortie<0) neurone.sortieTransfert=-1;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=1;
        return(neurone);
    }


    public static Neurone LineaireSatureeSymetrique(Neurone neurone) //Cette methode applique la fonction Lineaire Saturee Symetrique a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<-1) neurone.sortieTransfert=-1;
        if ((neurone.sommeSortie>=-1)&&(neurone.sommeSortie<=1)) neurone.sortieTransfert=neurone.sommeSortie;
        if (neurone.sommeSortie>1) neurone.sortieTransfert=1;
        return(neurone);
    }


    public static Neurone LineairePositive(Neurone neurone) //Cette methode applique la fonction Lineaire Positive a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie<0) neurone.sortieTransfert=0;
        if (neurone.sommeSortie>=0) neurone.sortieTransfert=neurone.sommeSortie;
        return(neurone);
    }


    public static Neurone TangeanteHyperbolique(Neurone neurone) //Cette methode applique la fonction tangeante hyperbolique a la somme de sortie d'un neurone
    {
        neurone.sortieTransfert=(float)((Math.exp((double)neurone.sommeSortie)-Math.exp((double)-neurone.sommeSortie))/(Math.exp((double)neurone.sommeSortie)+Math.exp((double)-neurone.sommeSortie)));
        return(neurone);
    }


    public static Neurone Competitive(Neurone neurone, float maximum) //Cette methode applique la fonction competitive a la somme de sortie d'un neurone
    {
        if (neurone.sommeSortie==maximum) neurone.sortieTransfert=1;
        else {neurone.sortieTransfert=0;}
        return(neurone);
    }
}