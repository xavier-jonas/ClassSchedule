import gui.MainFrame;
import model.StateFullSchedule;

public class Start {

	/**
	 * 
	 */
	public static void main(String[] args) {

		// faudrait p-e verifier ici que la JVM soie à une bonne version
		
		
		StateFullSchedule state=new StateFullSchedule();
		MainFrame mf=new MainFrame(state);

		// faudrait lancer un thread de "fin d'application" (handHookThread ?)
		// pour sauvegarder dans un fichier caché l'état et vérifier au démarage
		// si un tel fichier existe.
		
	}

}
