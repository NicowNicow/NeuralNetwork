import java.util.Random;
import java.lang.Math;
import java.util.ArrayList; 


/* Ce fichier  decrit la classe et les methodes pour creer un neurone */


public class Neurone{ //Jvais toujours utiliser 1 pour le biais
    protected float sommeSortie;   //La somme de sortie c'est celle avant la fonction de transfert
    public int fctTransfert; //Le type de fonction de tranfert a appliquer au neurone
    public float maximum;  //maximum est utilise uniquement pour la fonction competitive
    public float sortieTransfert;  //La sortie de la fonction de transfert;Le poids du Biais on va le set de base a 0
    public ArrayList<Float> poids; //Jvais utiliser des tableaux dynamiques ici, ca va me permettre de gerer facilement les cas ou on utilise plus de 2 entrees 

    //Avant d'entrainer les neurones, je set aleatoirement les poids pour chaque entree, entre 0 et 1/sqrt(nbrEntrees)


    public Neurone(int nbrEntrees)   //Constructeur de la neurone
    {
        this.sortieTransfert=(float)0;
        this.sommeSortie=(float)0;
        this.fctTransfert=0;
        this.maximum=(float)0; 
        this.poids = new ArrayList<Float>(nbrEntrees);
        Random random= new Random();
        for (int Indice=0; Indice<nbrEntrees; Indice++)
        {
            float temp= (float)(1/Math.sqrt(nbrEntrees))*random.nextFloat();
            //System.out.println("Poids Initial " +(Indice+1) +" : " +temp);
            (this.poids).add(temp);
        }
        
        (this.poids).add((float)0); //Le Poids du biais ce sera forcément le dernier poids de la liste, pour être sûr
        //System.out.println("Poids Initial du biais : " +this.poids.get(this.poids.size()-1));
        //System.out.println("==================");
    }


    public Neurone calculSommeNeurone(Neurone neurone, ArrayList<Float> entree) //Celle la va faire le calcul de sortie pre-fonction de transfert
    {
        neurone.sommeSortie=0;
        for (int Indice=0; Indice<entree.size();Indice++) //A partir de la, on considere que l'utilisateur a bien rentre le bon nombre d'entree par rapport a ce qu'il a declare au debut 
        {
            neurone.sommeSortie+=(float)entree.get(Indice)*(neurone.poids).get(Indice);
        }
        neurone.sommeSortie+=(float)neurone.poids.get((neurone.poids).size()-1);
        return(neurone);
    }


    public Neurone modifiePoids(Neurone neurone, ArrayList<Float> entree, float vitesseApprentissage, float solutionDesiree) //La modification des poids
    {
        for (int Indice=0; Indice<entree.size();Indice++) //A partir de la, on considere que l'utilisateur a bien rentre le bon nombre d'entree par rapport a ce qu'il a declare au debut 
        {
            (neurone.poids).set(Indice, (neurone.poids).get(Indice) + vitesseApprentissage*entree.get(Indice)*(solutionDesiree-neurone.sortieTransfert));
        }
        (neurone.poids).set(neurone.poids.size()-1,(neurone.poids).get((neurone.poids).size()-1) + vitesseApprentissage*(solutionDesiree-neurone.sortieTransfert)); //Modification du poids du Biais
        return(neurone);
    }


    public void AffichePoids(Neurone neurone)  //Cette methode permet d'afficher les derniers poids en date de la neurone
    {
        for (Integer Indice=0; Indice<neurone.poids.size()-1; Indice++)
        {
            System.out.println("Poids final " +(Indice+1) +" : "+neurone.poids.get(Indice));
        }
        System.out.println("Poids final du biais : " +neurone.poids.get(neurone.poids.size()-1));
        return;
    }


    public void AfficheSortie(Neurone neurone) //Cette methode permet de return la sortie de la neurone
    {
        System.out.println("La sortie du neurone est : " +neurone.sortieTransfert);
        return;
    }


}

