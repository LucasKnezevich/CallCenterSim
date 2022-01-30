import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Represents a call center simulation.
 *
 * @author      Lucas Knezevich
 */
public class Simulation {

    public static void main (String[] args) throws FileNotFoundException {
        Simulation sim = new Simulation(10, 20);
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
     */
    public Simulation(int techCount, int custCount) throws FileNotFoundException {
        techs = getTechs("techData_1.csv");
        custs = getCusts("custData_1.csv");
        System.out.println("Number of technicians: " + techs.size());
        System.out.println("Number of customers: " + custs.size());

        Queue<Tech> techQueue = new LinkedList<Tech>();
        Queue<Customer> custQueue = new LinkedList<Customer>();
        Queue<SupportSession> supportSessionQueue = new LinkedList<SupportSession>();

        // Fill the queues
        while (techQueue.size() < techCount) {
            int techNum = (int)Math.random() * (techs.size() + 1);
            techQueue.add(techs.get(techNum));
        }

        while (custQueue.size() < custCount) {
            int custNum = (int)Math.random() * (custs.size() + 1);
            custQueue.add(custs.get(custNum));
        }

        for (Tech t : techQueue) {
            System.out.println(t);
        }

        for (Customer c : custQueue) {
            System.out.println(c);
        }

    }


    /**
     * Method for retrieving technician data from a comma delimited file.
     *
     * @param fileName                  File containing technician data.
     * @return                          ArrayList of Techs.
     */
    private static ArrayList<Tech> getTechs(String fileName) throws FileNotFoundException {
        ArrayList<Tech> techList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(fileName));
            scan.nextLine();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                Tech tech = new Tech(data[0],data[1],data[2],data[3]);
                techList.add(tech);
            }
        } catch (Exception e) {
            System.out.println("Sorry, " + fileName + " could not be found.");
        }
        return techList;
    }

    /**
     * Method for retrieving customer data from a comma delimited file.
     *
     * @param fileName                  The file containing customer data.
     * @return                          The ArrayList of Customers.
     */
    private static ArrayList<Customer> getCusts(String fileName) throws FileNotFoundException {
        ArrayList<Customer> custList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(fileName));
            scan.nextLine();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                Customer cust = new Customer(data[0],data[1],data[2],data[3],data[4]);
                custList.add(cust);
            }
        } catch (Exception e) {
            System.out.println("Sorry, " + fileName + " could not be found.");
        }


        return custList;
    }


}
