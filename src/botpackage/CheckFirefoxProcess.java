package botpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CheckFirefoxProcess {
	private static ArrayList<String> listeDesMatchingProcesses = new ArrayList<String>();
	private static ArrayList<String> listeDesPID = new ArrayList<String>();
	
	
	private static void getMatchingProcesses() throws Exception {
		listeDesMatchingProcesses.clear();
		// Execute this command line
		Process process = Runtime.getRuntime().exec("ps -ax"); 
		// Save the result in a Buffer
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		// the string we're looking for in the list of processes
		String search = "/Applications/Firefox.app/Contents/MacOS/";
		String search2 = "geckodriver";
		String line = null;
		// Read the result of the command line & save the matching processes
		while ((line=r.readLine()) != null)  {
			if ((line.indexOf(search) != -1) && (line.indexOf(search2)) == -1) {
				listeDesMatchingProcesses.add(line);
			}
		}
	}
	
	private static void afficherListe(ArrayList<String> liste) {
		if (liste.size() == 0) {
			System.out.println("No match found");
		}
		else {
			for (String string : liste) {
				System.out.println(string);
			}
		}
	}

	public static Boolean isFirefoxRunning() throws Exception {
		getMatchingProcesses();
		Boolean result = true;
		int nb = listeDesMatchingProcesses.size();
		if (nb == 0) {
			result = !result;
		}
		return result;
	}
	
	private static void getPID() throws Exception {
		listeDesPID.clear();
		if (listeDesMatchingProcesses.size() != 0) {
			String result="";
			for (String string : listeDesMatchingProcesses) {
				listeDesPID.add(result + string.charAt(1) + string.charAt(2)+ string.charAt(3)+ string.charAt(4));
			}
		}
	}
	
	public static void killFirefoxProcesses() throws Exception {
		getPID();
		for (String PID : listeDesPID) {
			Runtime.getRuntime().exec("kill " + PID);
		}
	}
	
	

}
