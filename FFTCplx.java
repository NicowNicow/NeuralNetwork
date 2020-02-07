import java.util.ArrayList;

public class FFTCplx{
	public final static int TailleFFTtest = 16;
	public final static int Periode = 1;
	private Complexe[] resultat;


	public Complexe[] resultat(){ return resultat;}
	// Sous-signal obligatoirement découpé par pas de deux
	private static Complexe[] demiSignal(Complexe[] signal, int depart)
	{
		Complexe[] sousSignal = new Complexe[signal.length/2];
		for (int i = 0; i < sousSignal.length; ++i)
			sousSignal[i] = signal[depart+2*i];
		return sousSignal;
	}
	

	public static Complexe[] appliqueSur(Complexe[] signal)
	{
		Complexe[] trSignal = new Complexe[signal.length];
		if (signal.length == 1)	// Cas trivial de la FFT d'une valeur unique
			trSignal[0] = new ComplexeCartesien(signal[0].reel(), signal[0].imag());
		else
		{
			// Découpage des deux sous-groupes de données sur lesquels on applique la FFT
			// (voir définition de cours)
			final Complexe[] P0 = appliqueSur(demiSignal(signal, 0));
			final Complexe[] P1 = appliqueSur(demiSignal(signal, 1));
			// On applique le regroupement "papillon" pour créer le résultat final
			for (int k = 0; k < signal.length/2; ++k)
			{
				final ComplexePolaire expo = new ComplexePolaire(1., -2.*Math.PI*k/signal.length);
				final Complexe temp = P0[k];
				trSignal[k] = temp.plus(expo.fois(P1[k]));
				trSignal[k+signal.length/2] = temp.moins(expo.fois(P1[k]));
			}
		}
		return trSignal;
	}


	// public FFTCplx (String[] args)
	// {
	// 			if (args.length == 1)
	// 	{
	// 		//System.out.println("Lecture du fichier WAV "+args[0]);
	// 		Son son = new Son(args[0]);
	// 		//System.out.println("Fichier "+args[0]+" : "+son.donnees().length+" echantillons a "+son.frequence()+"Hz");
	// 		Complexe[] signalTest = new Complexe[256];
	// 		for (int i = 0; i < 256; ++i)
	// 			signalTest[i] = new ComplexeCartesien(son.donnees()[i], 0);
	// 		// On applique la FFT sur ce signal
	// 		resultat = appliqueSur(signalTest);
	// 	}
	// 	else
	// 		System.out.println("Veuillez donner le nom d'un fichier WAV en paramètre SVP.");
	// 	// Création d'un signal test simple
	// }



	public FFTCplx (String args)
	{
		Son son = new Son(args);
		Complexe[] signalTest = new Complexe[256];
		for (int i = 0; i < 256; ++i)
			signalTest[i] = new ComplexeCartesien(son.donnees()[i], 0);
		// On applique la FFT sur ce signal
		resultat = appliqueSur(signalTest);
	}


	public static ArrayList<Float> FFTCplxtoArrayList(String nomFichier) //Les neurones prennent en entree des ArrayList, donc je met en vitesse les resultats de la FFT dans une ArrayList
	{
		ArrayList<Float> Resultats = new ArrayList<Float>();
		FFTCplx test = new FFTCplx(nomFichier);
		for (int Indice=0; Indice<test.resultat.length; Indice++)
		{
			Resultats.add((float)test.resultat()[Indice].mod());
		}
		return(Resultats);
	}


	public static ArrayList<Float> RedimensioneArrayList(ArrayList<Float> echantillon, int minimum) //Faut que toutes mes entrees soit de la meme taille, donc il faut les redimensionner
	{
		if(echantillon.size()>minimum)
		{
			echantillon.subList(minimum-1, echantillon.size()-1).clear(); //On vide les elements en surplus de la liste
		}
		if(echantillon.size()<minimum) //Ce cas la est utile que pour les inputs de l'utilisateur, vu que le minimum est determine avant (fonction ci dessous)
		{
			while (echantillon.size()!=minimum)
			{
				echantillon.add((float)0); //On remplit le reste de la ArrayList avec des 0
			}
		}
		return(echantillon);
	}


	public static int CompareListeEchantillon(ArrayList<ArrayList<Float>> echantillons) //Cette fonction permet de trouver le plus petit echantillon dans toutes les sorties de la FFT
	{
		int minimum=0;
		int tempo=0;
		for (int Indice=0; Indice<echantillons.size(); Indice++)
		{
			if ((tempo==0)||(tempo>echantillons.get(Indice).size())) tempo=echantillons.get(Indice).size();
		}
		minimum=tempo;
		return(minimum);
	}


	public static ArrayList<ArrayList<Float>> PreparationEchantillons() //Cette fonction prepare les echantillons pour l'apprentissage
	{
		String[] nomFichier= {".\\sons\\Baleine1.wav", ".\\sons\\Baleine2.wav", ".\\sons\\Baleine3.wav", ".\\sons\\Baleine4.wav", ".\\sons\\Baleine5.wav",
							  ".\\sons\\Baleine6.wav", ".\\sons\\Baleine7.wav", ".\\sons\\Baleine8.wav", ".\\sons\\Baleine9.wav", ".\\sons\\Baleine10.wav",
							  ".\\sons\\Dauphin1.wav", ".\\sons\\Dauphin2.wav", ".\\sons\\Dauphin3.wav", ".\\sons\\Dauphin4.wav", ".\\sons\\Dauphin5.wav",
							  ".\\sons\\Dauphin6.wav", ".\\sons\\Dauphin7.wav", ".\\sons\\Dauphin8.wav", ".\\sons\\Dauphin9.wav", ".\\sons\\Dauphin10.wav",
							  ".\\sons\\Narval1.wav", ".\\sons\\Narval2.wav", ".\\sons\\Narval3.wav", ".\\sons\\Narval4.wav", ".\\sons\\Narval5.wav",
							  ".\\sons\\Narval6.wav", ".\\sons\\Narval7.wav", ".\\sons\\Narval8.wav", ".\\sons\\Narval9.wav", ".\\sons\\Narval10.wav",
							  ".\\sons\\Orque1.wav", ".\\sons\\Orque2.wav", ".\\sons\\Orque3.wav", ".\\sons\\Orque4.wav", ".\\sons\\Orque5.wav",
							  ".\\sons\\Orque6.wav", ".\\sons\\Orque7.wav", ".\\sons\\Orque8.wav", ".\\sons\\Orque9.wav", ".\\sons\\Orque10.wav"}; //Tableau des fichiers pour l'entrainement
		
		ArrayList<ArrayList<Float>> echantillons = new ArrayList<ArrayList<Float>>();
		int minimum=0;
		for (int Indice=0; Indice<nomFichier.length; Indice++)
		{
			echantillons.add(FFTCplxtoArrayList(nomFichier[Indice]));
		}
		minimum=CompareListeEchantillon(echantillons);
		for (int Indice=0; Indice<echantillons.size(); Indice++)
		{
			echantillons.set(Indice, RedimensioneArrayList(echantillons.get(Indice), minimum));
		}
		return(echantillons);
	}

}


