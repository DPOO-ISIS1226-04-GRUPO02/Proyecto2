package Processing;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Set;

import Model.User;

public class Users
{
	private static HashMap<String, User> logins;

	public static void loadUsers() throws IOException, ParseException
	{
		logins = RentalLoader.usersInformation();
	}
	
	public static User registerNewUser(String login, String password, int access, String workplace)
	{
		if (logins.containsKey(login)) return null;
		User created = new User(login, password, access, workplace);
		logins.put(login, created);
		RentalWriter.newUser(created);
		return created;
	}

	public static User registerNewUser(String login, String password, int access, String login2, String password2)
	{
		if (logins.containsKey(login)) return null;
		User user = loadUser(login2, password2);
		String workplace = user.getWorkplace();
		User created = new User(login, password, access, workplace);
		logins.put(login, created);
		RentalWriter.newUser(created);
		return created;	
	}
	
	public static User loadUser(String username, String password)
	{	
		User possibility = logins.get(username);
		if (possibility.getPassword().equals(password)) return possibility;
		else return null;
	}
	
	public static String[] getUsernames()
	{
    	Set<String> keySet = logins.keySet(); // Obtener el conjunto de claves como un conjunto de cadenas
    	String[] usernames = new String[keySet.size()]; // Crear una matriz de cadenas del tamaño del conjunto
    	int index = 0;
    	for (String username : keySet) { // Iterar sobre el conjunto y agregar cada nombre de usuario a la matriz
    	    usernames[index] = username;
    	    index++;
    	}
    	return usernames; // Devolver la matriz de nombres de usuario
	}

	public static HashMap<String, User> getUsers()
	{
		return logins;
	}
	
}
