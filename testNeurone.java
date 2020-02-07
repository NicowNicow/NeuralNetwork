import java.util.Scanner;

public class testNeurone{

   
    public static void main(String[] args) 
    {
        Scanner sc= new Scanner(System.in); //Je le passe en argument dans les fonctions pour pouvoir le fermer à la fin sans que ca bug
        System.out.println("==== Porte ET ====");
        Neurone neuroneET= new Neurone(2);
        System.out.println("Pour une porte ET, on conseille de choisir une fonction de type seuil."); //Elle marche juste pas du tout avec la Tangeant Hyperbolique, par contre sigmoide prend du temps mais marche
        neuroneET.fctTransfert=FonctionsTransfert.ChoixFctTransfert(sc);
        System.out.println("Lancement de l'apprentissage");
        neuroneET=Entrainement.NeuroneEntrainementET(neuroneET);
        System.out.println("==================");
        neuroneET.AffichePoids(neuroneET);
        System.out.println("==================");
        System.out.println("");

        System.out.println("==== Porte OU ====");
        Neurone neuroneOU= new Neurone(2);
        System.out.println("Pour une porte OU, on conseille de choisir une fonction de type seuil.");
        neuroneOU.fctTransfert=FonctionsTransfert.ChoixFctTransfert(sc);
        System.out.println("Lancement de l'apprentissage");
        neuroneOU=Entrainement.NeuroneEntrainementOU(neuroneOU);
        System.out.println("==================");
        neuroneOU.AffichePoids(neuroneOU);
        System.out.println("==================");
        sc.close();
        return;
    }
    
}

