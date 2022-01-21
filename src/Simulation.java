import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a call center simulation.
 *
 * @author      Lucas Knezevich
 * @version     20220120
 */
public class Simulation {

    public static void main (String[] args) throws FileNotFoundException {
        Simulation sim = new Simulation();
        System.out.println("Number of technicians: " + sim.techs.size());
        System.out.println("Number of customers: " + sim.custs.size());

    }

    /**
     *  Technicians
     */
    private final ArrayList<Tech> techs;

    /**
     *  Customers
     */
    private final ArrayList<Customer> custs;


    /**
     * Constructor for Simulation.
     *
     * @throws FileNotFoundException    When a data file isn't found.
     */
    public Simulation() throws FileNotFoundException {
        techs = getTechs("techData_1.csv");
        custs = getCusts("custData_1.csv");
    }


    /**
     * Method for retrieving technician data from a comma delimited file.
     *
     * @param fileName                  The file containing technician data.
     * @return                          The ArrayList of Techs.
     * @throws FileNotFoundException    When the data file isn't found.
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
     *
     * @param fileName                  The file containing customer data.
     * @return                          The ArrayList of Customers.
     * @throws FileNotFoundException    When the data file isn't found.
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
