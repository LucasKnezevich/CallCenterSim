import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    public static void main (String[] args) throws FileNotFoundException {

        ArrayList<Tech> techs = getTechs("techData_1.csv");
        for (Tech tech : techs) {
            System.out.println(tech);
        }

        ArrayList<Customer> custs = getCusts("custData_1.csv");
        for (Customer cust : custs) {
            System.out.println(cust);
        }

    }

    /**
     * Method for retrieving technician data from a comma delimited file.
     * @param fileName      The file with data.0
     * @return              The ArrayList of tech data.
     * @throws FileNotFoundException
     */
    private static ArrayList<Tech> getTechs(String fileName) throws FileNotFoundException {
        ArrayList<Tech> techList = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        scan.nextLine();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");
            Tech tech = new Tech(data[0],data[1],data[2],data[3]);
            techList.add(tech);
        }

        return techList;
    }

    /**
     * Method for retrieving customer data from a comma delimited file.
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private static ArrayList<Customer> getCusts(String fileName) throws FileNotFoundException {
        ArrayList<Customer> custList = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        scan.nextLine();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");
            Customer cust = new Customer(data[0],data[1],data[2],data[3],data[4]);
            custList.add(cust);
        }

        return custList;
    }

}
