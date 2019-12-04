package propra.grpproj.logic;

import javax.swing.JOptionPane;



////////////////////////////////////////////////////////////////////////////
// Handle all the user related actions (login, logout, delete, register)
//
// @author: Marius Discher
//
//
//


public class UserHandling 
{
	
	
	public boolean user_register(String email, String passwd, boolean pub) 
	{
		
		boolean success = false;
		
		// Erzeugen von BenutzerID
		// Select last BenutzerID und dann +1 bzw eins hoch rechnen.
		
		// if pub = true, dann auch in Datenbank setzen
		
		// verbindung zu DB aufbauen
		// checken, ob email vllt doch schon vorhanden?
		// verschl�sseln von PW 
		// int password = passwd.hashCode();
		// Best�tigung rausgeben
		
		
		// Suche noch einmal nach email 
		// wenn vorhanden = true, ansonsten Fehler ausgeben, dass DB nicht beschrieben werden konnte.
		// Exception = DBCannotbeWriten
		//
		
		return success;
	}
	
	

	public void user_login(String email, String passwd) 
	{
		
		// Daten kommen von Sockets
		// Von der Datenbank die Info, ob pub
		
		
		email = JOptionPane.showInputDialog("Bitte Email eingeben");
		passwd = JOptionPane.showInputDialog("Bitte Passwort");
		boolean check = true; 
		boolean pub = true;
		
		if (check == true) {
			
			Encrypt e = new Encrypt();
			boolean encrypt_check;
			
			encrypt_check = e.login(email, passwd);
			
			if (encrypt_check == true) {
				
				System.out.println("Login erfolgreich!");
				// Login erfolgreich, weiterleitung an n�chste Seite
			} else {
				
				System.out.println("Login nicht erfolgreich! Passwort falsch");
				// Passwort falsch, neu eingeben!
				
			}
			
		} else {
			System.out.println("registrieren?");
			// An Sockets Anfrage f�r registrierung schicken
		}
		
		
		System.out.println(email);
		System.out.println(passwd);
		
		

	}
	
	public void createTempUser () 
	{
		
		// Get the last temp User number and then +1
		// MAX()
		
		int i = 00000001;
		String email = "temp" + i + "@krombacher_quiz.de";
		String name = "temp" + i;
		String passwd = "temporaer";
		boolean pub = false;
		
		user_register(email, passwd, pub);
		
		
	}
	
	public void logout () 
	{
		
	}
	
	public boolean deleteUser (String id, String passwd) 
	{
		
		// Check the password
		
		Encrypt e = new Encrypt();
		boolean success; 
		success = e.delete(id,passwd);
		return success;
		
		
		
		
		
	}
	
	

}
