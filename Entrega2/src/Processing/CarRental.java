package Processing;

import java.util.HashMap;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.JOptionPane;

import Model.Car;
import Model.Store;
import Model.Client;
import Model.Insurance;
import Model.Licence;
import Model.Payment;
import Model.Rental;
import Model.Extra;

public class CarRental {

	private static HashMap<String, Client> clients;
	private static HashMap<String, Car> cars;
	private static HashMap<String, Store> stores;
	private static HashMap<String, Integer> categories;
	private static HashMap<String, Insurance> insurances;
	private static HashMap<Car, ArrayList<Rental>> rentals;
	private static HashMap<Long, Licence> secondaryLicences;

	public static void loadCarRental() throws IOException, ParseException
	{
		clients = RentalLoader.loadClients();
		cars = RentalLoader.loadCars();
		stores = RentalLoader.loadStores();
		categories = RentalLoader.loadCategories();
		insurances = RentalLoader.loadInsurances();
		rentals = RentalLoader.loadRentals(clients);
		secondaryLicences = RentalLoader.loadSecondaryLicence();
	}

	public static void registerNewClient(String name, long phone, String email, Calendar dateBirth, String nationality, 
		String idPhotoPath, long cardNumber, Calendar cardExpiration, short cardCode, String cardOwner, String cardAddress, 
		String login, long licenceNumber, String licenceCountry, Calendar licenceExpiration, String licencePhotoPath)
	{
		Payment payment = new Payment(cardNumber, cardExpiration, cardCode, cardOwner, cardAddress);
		Licence licence = new Licence(licenceNumber, licenceCountry, licenceExpiration, licencePhotoPath);
		Client person = new Client(name, phone, email, dateBirth, nationality, idPhotoPath, licence, payment, login);
		
		clients.put(login, person);
		RentalWriter.addClient(person);	
	}

	public static Client getClient(String login)
	{
		return clients.get(login);
	}

	public static boolean clientExists(String login)
	{
		Client client = getClient(login);
		if (client.equals(null)) return false;
		else return true;
	}

	public static Car getCar(String plate)
	{
		return cars.get(plate);
	}

	public static boolean carExists(String plate)
	{
		Car car = getCar(plate);
		if (car.equals(null)) return false;
		else return true;
	}

	public static Store getStore(String storeName)
	{
		return stores.get(storeName);
	}

	public static String getStoreByPlate(String plate)
	{
		for (String storeString: stores.keySet()) {
			Store store = getStore(storeString);
			for (String category: categories.keySet()) {
				if (store.getInventory().get(category).contains(plate)) return storeString;
			}
		}
		return null;
	}

	public static boolean storeExists(String name)
	{
		Store store = getStore(name);
		if (store == null) return false;
		else return true;
	}

	public static Set<String> getCategories()
	{
		return categories.keySet();
	}

	public static Set<String> getStores()
	{
		return stores.keySet();
	}

	public static boolean insuranceExists(String name)
	{
		if (insurances.keySet().contains(name)) return true;
		else return false;
	}

	public static Set<String> getInsurances()
	{
		return insurances.keySet();
	}
 
	public static void addInsurance(String name, int cost, String specs) 
	{
		Insurance insurance = new Insurance(name, cost, specs, true);
		insurances.put(name, insurance);
		RentalWriter.newInsurance(insurance);
	}

	public static boolean changeInsuranceStatus(String name)
	{
		boolean truth = insurances.get(name).isActive();
		insurances.get(name).setActive(!truth);
		RentalWriter.changeInsuranceStatus(new ArrayList<Insurance>(insurances.values()));
		return !truth;	
	}

	public static void modifyInfoClient(String login, String selectedInfo, String infoString) 
	{	
		Client client = getClient(login);
		if (selectedInfo.equals("Nombre"))
		{
			client.setName(infoString);
		}
		if (selectedInfo.equals("Teléfono"))
		{
			long phone = Long.parseLong(infoString);
			client.setPhone(phone);
		}
		if (selectedInfo.equals("Email"))
		{
			client.setEmail(infoString);
		}
		if (selectedInfo.equals("Id Photo Path"))
		{
			client.setIdPhotoPath(infoString);
		}
		RentalWriter.changeClientInformation(client);

	}

	public static void modifyPaymentMethod(String login, long cardNumber, Calendar cardExpiration, short cardCode, String cardOwner, String cardAddress )
	{
		Client client = getClient(login);
		client.setPayment(new Payment(cardNumber, cardExpiration, cardCode, cardOwner, cardAddress));
		RentalWriter.changeClientInformation(client);		
	}

	public static void modifyLicence (String login, long number, String country, Calendar expiration, String photoPath)
	{
		Client client = getClient(login);
		Licence licence = new Licence(number, country, expiration, photoPath);
		client.setLicence(licence);
		RentalWriter.changeClientInformation(client);
	}

	public static String reserveCar(String login, String category, String origin, String destination,
		Calendar pickUpDateTime, Calendar returnDateTime)
	{
		Store originStore = getStore(origin);
		Store destinationStore = getStore(destination);
		Client person = getClient(login);
		ArrayList<String> categoryList = originStore.getInventory().get(category);
		if (categoryList.size() == 0) return "No hay carros en la categoría seleccionada.";
		if (!person.getActiveRental().equals(null)) return "Usted ya tiene una renta activa.";
		int i = 0;
		boolean found = false;
		Car car = null;
		while (!found && i < categoryList.size()) 
		{
			String plate = categoryList.get(i);
			byte status = getCar(plate).getStatus();
			Calendar availableIn = getCar(plate).getAvailableDate();
			if (status == (byte) 0 && availableIn.before(pickUpDateTime))
			{
				found = true;
				car = getCar(plate);
				car.setStatus((byte) 1);
			}
			i += 1;
		}
		if (car.equals(null)) return "No hay carros de esta categoría disponibles para la renta en la sede " + origin;
		int base = categories.get(category);
		Rental newRental = new Rental(person, car, base, new ArrayList<Insurance>(), originStore, 
			destinationStore, pickUpDateTime, returnDateTime, new ArrayList<Licence>(), new ArrayList<Extra>(), true);
		person.setActiveRental(newRental);
		RentalWriter.changeClientInformation(person);
		RentalWriter.newRental(newRental);
		RentalWriter.changeCarInformation(car);
		return null;	
	}

	public static void reserveCar(String plate, String destination, int days)
	{
		Calendar returnCalendar = Calendar.getInstance();
		returnCalendar.add(Calendar.DAY_OF_MONTH, days);
		Rental rental = new Rental(null, getCar(plate), days, new ArrayList<Insurance>(), getStore(getStoreByPlate(plate)), 
			getStore(destination), Calendar.getInstance(), returnCalendar, new ArrayList<Licence>(), new ArrayList<Extra>(), true);
		rental.getCar().setAvailableTime(days);
		RentalWriter.changeCarInformation(getCar(plate));
		RentalWriter.newRental(rental);
	}

	public static ArrayList<Licence> addLicence(String login, long licenceNumber, String licenceCountry, 
		String licenceExpString, String licencePhotoPath) throws ParseException 
	{
		Client client = clients.get(login);
		ArrayList<Licence> licences = client.getActiveRental().getSecondaryDriver();
		Calendar licenceExpiration = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date licenceExpDate = (Date) formatter.parse(licenceExpString);
		licenceExpiration.setTime(licenceExpDate);
		Licence licence = new Licence(licenceNumber, licenceCountry, licenceExpiration, licencePhotoPath);
		licences.add(licence);
		client.getActiveRental().setLicences(licences);
		secondaryLicences.put(licenceNumber, licence);
		RentalWriter.newSecondaryLicence(licence);
		return licences;
	}

	public static boolean confirmPickUp(String login, String workplace)
	{
		if (!clientExists(login)) return false;
		Client person = getClient(login);
		if (person.getActiveRental()== null) return false;
		Rental rental = person.getActiveRental();
		if (!workplace.equals(rental.getOrigin().getName())) return false;
		rental.setActive(true);
		Car car = rental.getCar();
		car.setStatus((byte)2);
		RentalWriter.changeRentalInformation(rental);
		RentalWriter.changeCarInformation(car);
		return true;
	}

	public static String addExtra(String login, String type, int cost, String specification)
	{
		Rental active = clients.get(login).getActiveRental();
		if (active.equals(null)) return "Primero debe crear una reserva para poder añadir extras";
		ArrayList<Extra> extras = active.getExtras();
		extras.add(new Extra(type, cost, specification));
		active.setExtras(extras);
		return null;
	}

	public static String confirmReturn(String login, int days, String employeeLogin, String employeePassword)
	{
		Client person = getClient(login);
		if (person.getActiveRental() == null) return "El cliente no tiene una renta activa.";
		Rental rental = person.getActiveRental();
		rental.setActive(false);
		Store destination = stores.get(Users.loadUser(employeeLogin, employeePassword).getWorkplace());
		if (!destination.equals(rental.getDestination())) 
		{
			String extraCost = JOptionPane.showInputDialog("Ingrese el recargo por devolver el carro a una tienda distinta: ");
			addExtra(login, "Devuelto a una tienda distinta", Integer.parseInt(extraCost),
				"El carro fue devuelto a una tienda distinta a la que fue inicialmente especificada.");
		}
		rental.setDestination(destination);
		int total = 0;
		if (Calendar.getInstance().after(rental.getReturn())) {
			rental.setReturn(Calendar.getInstance());
			total = rental.getFinalCharge();
		} else {
			total = rental.getFinalCharge();
			rental.setReturn(Calendar.getInstance());
		}
		Car car = rental.getCar();
		car.setAvailableTime(days);
		car.setStatus((byte) 0);
		destination.addCar(car);
		RentalWriter.changeRentalInformation(rental);
		RentalWriter.changeCarInformation(car);
		RentalWriter.changeStoreInformation(destination);
		return "Reserva finalizada con éxito. Debe pagar un total de " + Integer.toString(total);
	}

	public static void registerCar(String brand, String plate, String model, String color, 
		boolean isAutomatic, String category, int availableIn, String store)
	{
		Car carro = new Car(brand, plate, model, color, isAutomatic, category, availableIn, (byte) 0);
		carro.setStatus((byte) 0);
		cars.put(plate, carro);
		Store st = stores.get(store);
		st.getInventory().get(category).add(plate);
		RentalWriter.addCar(carro);
	} 

	public static void newStore(String name, String location, Calendar openingTime, Calendar closingTime, 
		byte OpeningDays)
	{
		HashMap <String, ArrayList<String>> inventory = new HashMap<String, ArrayList<String>>();
		Store store = new Store(name, location, openingTime, closingTime, OpeningDays, inventory);
		stores.put(name, store);
		RentalWriter.addStore(store);
	}

	public static void changeVehicleStatus(String plate, byte status)
	{
		Car car = cars.get(plate);
		car.setStatus(status);
		RentalWriter.changeCarInformation(car);
	}

	public static String getPastRentals(Car car)
	{
		ArrayList<Rental> listRentals = rentals.get(car);
		String result = "";
		int i = 1;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		for (Rental rental: listRentals) {
			result += String.format("Renta #%d", i);
        	String formattedDateOut = simpleDateFormat.format(rental.getPickUp().getTime());
			result += String.format("\n\tFecha de inicio de la renta: %s", formattedDateOut);
			String formattedDateIn = simpleDateFormat.format(rental.getReturn().getTime());
			result += String.format("\n\tFecha de finalización de la renta: %s", formattedDateIn);
			result += String.format("\n\tCosto total de la renta: %8d$", rental.getFinalCharge());
			result += "\n\n";
		}
		result += "Información completa de las rentas pasadas en ./data/rentals/" + car.getPlate();
		return result;
	}

	public static void setTariff(String category, int amount)
	{
		categories.put(category, amount);
		RentalWriter.changeTariffs(category, amount);
	}
}