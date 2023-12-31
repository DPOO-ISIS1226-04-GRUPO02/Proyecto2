package Processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import Model.Car;
import Model.Client;
import Model.Extra;
import Model.Insurance;
import Model.Licence;
import Model.Payment;
import Model.Rental;
import Model.Store;
import Model.User;

public class RentalWriter {

    private static String separator = File.separator;

    /**
     * @param category
     * @param value
     */
    public static void changeTariffs(String category, int value) {
        String filePath = "Entrega2" + separator + "data" + separator + "categories.txt";
        boolean categoryFound = false;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            StringBuilder stringBuilder = new StringBuilder();
            while ((linea = bufferedReader.readLine()) != null) {
                String[] parts = linea.split(",");
                if (parts[0].equals(category)) {
                    stringBuilder.append(parts[0]).append(",").append(value).append(System.lineSeparator());
                    categoryFound = true;
                } else {
                    stringBuilder.append(linea).append(System.lineSeparator());
                }
            }

            bufferedReader.close();

            // If category is not found, add it to the end of the file
            if (!categoryFound) {
                stringBuilder.append(category).append(",").append(value).append(System.lineSeparator());
            }

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    public static void changeCarInformation(Car car){
        String filePath = "Entrega2" + separator + "data" + separator + "cars.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            StringBuilder stringBuilder = new StringBuilder();
            while ((linea = bufferedReader.readLine()) != null) {
                String[] parts = linea.split(",");
                if (parts[1].equals(car.getPlate())) {
                    // Se encontró la línea correspondiente a la placa, se modifica la información
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(car.getAvailableDate().getTime());
                    byte aut = 0;
                    if (car.isAutomatic()){
                        aut = (byte) 1;
                    }
                    stringBuilder.append(car.getBrand()).append(",").append(car.getPlate()).append(",").append(
                        car.getModel()).append(",").append(car.getColor()).append(",").append(formattedDate)
                        .append(",").append(String.valueOf(aut)).append(",").append(car.getCategory()).append(
                        ",").append(String.valueOf(car.getStatus())).append(System.lineSeparator());
                } else {
                    // La línea no corresponde a la placa, se agrega sin cambios
                    stringBuilder.append(linea).append(System.lineSeparator());
                }
            }

            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

            System.out.println("Información del carro modificada con éxito.");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public static void addCar(Car car) {
        String filePath = "Entrega2" + separator + "data" + separator + "cars.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Modo adjunto al final del archivo
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(car.getAvailableDate().getTime());
            byte aut = 0;
            if (car.isAutomatic()){
                aut = (byte) 1;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(car.getBrand()).append(",").append(car.getPlate()).append(",").append(
                car.getModel()).append(",").append(car.getColor()).append(",").append(formattedDate).append(
                ",").append(String.valueOf(aut)).append(",").append(car.getCategory()).append(",").append(
                String.valueOf(car.getStatus())).append(System.lineSeparator());

            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeStoreInformation(Store store) {
        String filePath = "Entrega2" + separator + "data" + separator + "stores" + separator + store.getName() + ".txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            FileWriter fileWriter = new FileWriter(filePath, true);

            StringBuilder stringBuilder = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String OpformattedDate = dateFormat.format(store.getOpHour().getTime());
            String ClformattedDate = dateFormat.format(store.getCloseHour().getTime());
            stringBuilder.append(store.getName()).append(",").append(store.getLocation()).append(",").append(
                OpformattedDate).append(",").append(ClformattedDate).append(",").append(String.valueOf(store.opDays()));

            ArrayList<String> plates = new ArrayList<>();
            for (ArrayList<String> valueList : store.getInventory().values()) {
                plates.addAll(valueList);
            }
            for (String plate: plates){
                stringBuilder.append(",").append(plate);
            }

            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

            System.out.println("Información de la tienda modificada con éxito.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addStore(Store store) {
        // Especifica la ruta de la carpeta stores dentro de la carpeta data
        String folderPath = "Entrega2" + separator + "data" + separator + "stores" + separator;

        // Crea un directorio si no existe
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Crea un archivo con el nombre de la tienda en la carpeta stores
        String filePath = folderPath + store.getName() + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            StringBuilder stringBuilder = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String OpformattedDate = dateFormat.format(store.getOpHour().getTime());
            String ClformattedDate = dateFormat.format(store.getCloseHour().getTime());
            int openingDaysInt = store.opDays() & 0xFF;
            stringBuilder.append(store.getName()).append(",").append(store.getLocation()).append(",").append(
                OpformattedDate).append(",").append(ClformattedDate).append(",").append(Integer.toBinaryString(openingDaysInt));
            ArrayList<String> plates = new ArrayList<>();
            for (ArrayList<String> valueList : store.getInventory().values()) {
                plates.addAll(valueList);
            }
            for (String plate: plates){
                stringBuilder.append(",").append(plate);
            }

            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

            System.out.println("Información de la tienda modificada con éxito.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addClient(Client client) {
        String username = client.getLogin();
        String folderPath = "Entrega2" + separator + "data" + separator + "clients" + separator + username + separator;
    
        File folder = new File(folderPath);
    
        if (!folder.exists()) {
            boolean result = folder.mkdirs();
            if (result) {
                // Client information
                String filePath = folderPath + "clientInfo.txt";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(client.getDateBirth().getTime());
    
                StringBuilder contentBuilder = new StringBuilder();
                contentBuilder.append(client.getName()).append(',')
                        .append(client.getPhone()).append(',')
                        .append(client.getEmail()).append(',')
                        .append(dateString).append(',')
                        .append(client.getNationality()).append(',')
                        .append(client.getLogin());
    
                String content = contentBuilder.toString();
    
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                    bufferedWriter.append(content);
                    bufferedWriter.newLine();
                    System.out.println("\nInformación del usuario guardada con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                // Identification
                String sourceImagePath = client.getIdPhotoPath();
                Path source = Paths.get(sourceImagePath);
                Path target = Paths.get(folderPath + "identification.jpg");
    
                try {
                    Files.copy(source, target);
                    System.out.println("\nIdentificación cargada con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                // License information
                String filePath2 = folderPath + "licenceInfo.txt";
                Licence licence = client.getLicence();
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
                String dateString2 = dateFormat2.format(licence.getExpiration().getTime());
    
                StringBuilder contentBuilder2 = new StringBuilder();
                contentBuilder2.append(licence.getNumber()).append(',')
                        .append(licence.getCountry()).append(',')
                        .append(dateString2);
    
                String content2 = contentBuilder2.toString();
    
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath2, true))) {
                    bufferedWriter.append(content2);
                    bufferedWriter.newLine();
                    System.out.println("\nInformación de la licencia guardada con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                // License photo
                String sourceImagePath2 = licence.getPhotoPath();
                Path source2 = Paths.get(sourceImagePath2);
                Path target2 = Paths.get(folderPath + "license.jpg");
    
                try {
                    Files.copy(source2, target2);
                    System.out.println("\nLicencia cargada con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                // Payment information
                String filePath3 = folderPath + "paymentInfo.txt";
                Payment payment = client.getPayment();
                SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
                String dateString3 = dateFormat3.format(payment.getExpiration().getTime());
    
                StringBuilder contentBuilder3 = new StringBuilder();
                contentBuilder3.append(payment.getNumber()).append(',')
                        .append(dateString3).append(',')
                        .append(payment.getCode()).append(',')
                        .append(payment.getOwner()).append(',')
                        .append(payment.getAddress());
    
                String content3 = contentBuilder3.toString();
    
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath3, true))) {
                    bufferedWriter.append(content3);
                    bufferedWriter.newLine();
                    System.out.println("\nInformación del pago guardada con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    public static void newRental(Rental rental){
        String plate = rental.getCar().getPlate();
        String folderPath = "Entrega2" + separator + "data" + separator + "rentals" + separator + plate;

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
            
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(rental.getPickUp().getTime());
        folderPath += separator + date;
        File folder2 = new File(folderPath);
        folder2.mkdirs();
        File folder3 = new File(folderPath + separator + "extra");
        folder3.mkdirs();
        String filePath = folderPath + separator + "info.txt";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd:HH-mm");
        String datePickString = dateFormat.format(rental.getPickUp().getTime());
        String dateReturnString = dateFormat.format(rental.getReturn().getTime());
        String content = rental.getClient().getLogin() + ',' + rental.getCar().getPlate() + ',' + 
            String.valueOf(rental.getFinalCharge()) + ',' + rental.getOrigin().getName() + ',' + 
            rental.getDestination().getName() + ',' + datePickString + ',' + dateReturnString + ',' + rental.getActive();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(content);
            bufferedWriter.newLine();
            System.out.println("\nInformación del usuario guardada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath2 = folderPath + separator + "insurance.txt";
        ArrayList<Insurance> insurances = rental.getInsurances();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath2, true))) {
            for (Insurance insurance : insurances) {
            bufferedWriter.append(insurance.getName());
            bufferedWriter.newLine();
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
        String filePath3 = folderPath + separator + "secondaryDriver.txt";
        ArrayList<Licence> secondaryLicences = rental.getSecondaryDriver();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath3, true))) {
            for (Licence lic : secondaryLicences) {
            bufferedWriter.append(String.valueOf(lic.getNumber()));
            bufferedWriter.newLine();
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }    
    }

    public static void changeClientInformation(Client person) {
        String username = person.getLogin();
        String separator = File.separator;
    
        // Client information
        String filePath = "Entrega2" + separator + "data" + separator + "clients" + separator + username + separator + "clientInfo.txt";
    
        try (PrintWriter writer = new PrintWriter(filePath)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(person.getDateBirth().getTime());
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append(person.getName()).append(',')
                    .append(person.getPhone()).append(',')
                    .append(person.getEmail()).append(',')
                    .append(dateString).append(',')
                    .append(person.getNationality()).append(',')
                    .append(person.getLogin());

            String content = contentBuilder.toString();

            writer.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    
        // License information
        String filePath2 = "Entrega2" + separator + "data" + separator + "clients" + separator + username + separator + "licenceInfo.txt";
        try {
            File file2 = new File(filePath2);
            FileWriter fr2 = new FileWriter(file2, false);
            fr2.write("");
            fr2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Licence licence = person.getLicence();
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
            String dateString2 = dateFormat2.format(licence.getExpiration().getTime());
            StringBuilder contentBuilder2 = new StringBuilder();
            contentBuilder2.append(licence.getNumber()).append(',')
                    .append(licence.getCountry()).append(',')
                    .append(dateString2);

            String content2 = contentBuilder2.toString();

            bufferedWriter.write(content2);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
    
        // Payment information
        String filePath3 = "Entrega2" + separator + "data" + separator + "clients" + separator + username + separator + "paymentInfo.txt";
        try {
            File file3 = new File(filePath3);
            FileWriter fr3 = new FileWriter(file3, false);
            fr3.write("");
            fr3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath3, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Payment payment = person.getPayment();
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
            String dateString3 = dateFormat3.format(payment.getExpiration().getTime());
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append(payment.getNumber()).append(',')
                    .append(dateString3).append(',')
                    .append(payment.getCode()).append(',')
                    .append(payment.getOwner()).append(',')
                    .append(payment.getAddress());

            String content3 = contentBuilder.toString();

            bufferedWriter.write(content3);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void changeRentalInformation(Rental rental){
        String plate = rental.getCar().getPlate();
        String folderPath = "Entrega2" + separator + "data" + separator + "rentals" + separator + plate;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(rental.getPickUp().getTime());

        folderPath = folderPath + separator + date;
        String filePath = folderPath + separator + "info.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd:HH-mm");
            String datePickString = dateFormat.format(rental.getPickUp().getTime());
            String dateReturnString = dateFormat.format(rental.getReturn().getTime());
            String content = rental.getClient().getLogin() + ',' + rental.getCar().getPlate() + ',' + 
                String.valueOf(rental.getFinalCharge()) + ',' + rental.getOrigin().getName() + ',' + 
                rental.getDestination().getName() + ',' + datePickString + ',' + dateReturnString + ',' + rental.getActive();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                bufferedWriter.append(content);
                bufferedWriter.newLine();
                System.out.println("\nInformación del usuario actualizada con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        filePath = folderPath + separator + "insurance.txt";
        try{
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            ArrayList<Insurance> insurances = rental.getInsurances();
            for (Insurance insurance : insurances) {
            bufferedWriter.append(insurance.getName());
            bufferedWriter.newLine();
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        filePath = folderPath + separator + "secondaryDriver.txt";
        try{
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        ArrayList<Licence> secondaryLicences = rental.getSecondaryDriver();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Licence lic : secondaryLicences) {
            bufferedWriter.append(String.valueOf(lic.getNumber()));
            bufferedWriter.newLine();
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        filePath = folderPath + separator + "extras";
        File directory = new File(filePath);
        File[] files = directory.listFiles();
        if (files != null && files.length>0){
            for (File file: files){
                file.delete();
            }
        }
        ArrayList<Extra> extras = rental.getExtras();
        if (extras != null && extras.size() > 0){
        for (int i = 0; i < extras.size() ; i++){
            Extra extra = extras.get(i);
            String txtfilePath = filePath + separator + "extra" + i + ".txt";
            String content = extra.getType() + "," + String.valueOf(extra.getCost()) + "," + extra.getSpecification(); 
            try {
                FileWriter fwtxt = new FileWriter (txtfilePath);
                fwtxt.write(content);
                fwtxt.close();

            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
    }

    public static void newUser(User user) {
        String filePath = "Entrega2" + separator + "data" + separator + "users.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Modo adjunto al final del archivo
            String userEntry = user.getUsername() + "," + user.getPassword() + "," + String.valueOf(user.getAccess()) + 
                "," + user.getWorkplace() + System.lineSeparator();
            fileWriter.write(userEntry);
            fileWriter.close();
            System.out.println("Nuevo usuario agregado con éxito.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void newInsurance(Insurance insurance)
    {
        String filePath = "Entrega2" + separator + "data" +separator + "insurances.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Modo adjunto al final del archivo
            String insuranceEntry = "\n" + insurance.getName() + "," + String.valueOf(insurance.getCost()) + "," + 
                insurance.getSpecification() + "," + Boolean.toString(insurance.isActive()) + System.lineSeparator();
            fileWriter.write(insuranceEntry);
            fileWriter.close();
            System.out.println("Nuevo seguro agregado con éxito.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeInsuranceStatus(ArrayList<Insurance> insurances)
    {
        String filePath = "Entrega2" + separator + "data" + separator + "insurances.txt";
        try 
        {
            FileWriter fw = new FileWriter(filePath, false);
            for (Insurance insurance: insurances) 
            {
                String entry = insurance.getName() + "," + String.valueOf(insurance.getCost()) + "," + 
                    insurance.getSpecification() + "," + Boolean.toString(insurance.isActive()) + System.lineSeparator();
                fw.write(entry);
            }
            fw.close();
            System.out.println("Los cambios a los seguros fueron hechos.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void newSecondaryLicence (Licence licence)
    {
        String txtFileName = "Entrega2" + separator + "data" +separator + "secondaryLicence" + separator + licence.getNumber() + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(txtFileName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String date = sdf.format(licence.getExpiration().getTime());
            String information = licence.getNumber() + "," + licence.getCountry() + "," + date;
            fileWriter.write(information);
            fileWriter.close();
            Path originalPath = Paths.get(licence.getPhotoPath());
            byte[] photoData = Files.readAllBytes(originalPath);
            Path destinyPath = Paths.get("data" +separator + "secondaryLicence" + separator + licence.getNumber() + ".jpg");
            Files.write(destinyPath, photoData);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}



