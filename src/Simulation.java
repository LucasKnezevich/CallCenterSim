import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashSet;

/**
 * Represents a call center simulation.
 *
 * @author      Lucas Knezevich
 */
public class Simulation {
    /**
     * Customers
     */
    private final static ArrayList<Customer> custs = new ArrayList<>();

    /**
     * Techs
     */
    private final static ArrayList<Tech> techs = new ArrayList<>();

    /**
     * Customers that have called
     */
    private final static HashSet<Customer> custHash = new HashSet<>();

    /**
     * Techs that are working
     */
    private final static HashSet<Tech> techHash = new HashSet<>();

    /**
     * Techs that are working
     */
    private final static Queue<Tech> techQueue = new LinkedList<>();

    /**
     * Customers that have called
     */
    private final static Queue<Customer> custQueue = new LinkedList<>();

    /**
     * Queue of support sessions.
     */
    private final static Queue<SupportSession> supportSessionQueue = new LinkedList<>();


    /**
     *
     * @param args      The command line arguments.
     */
    public static void main(String[] args){
        Simulation sim = new Simulation(15, 30, "techData_1.csv", "custData_1.csv");
        run(10);
    }


    /**
     * Full Simulation constructor.
     * @param techCount         Number of techs working.
     * @param custCount         Number of customers that have called.
     * @param techFile          File containing tech data.  Should be a comma delimited file.
     * @param custFile          File containing cust data.  Should be a comma delimited file.
     */
    public Simulation(int techCount, int custCount, String techFile, String custFile){

        try {
            Scanner scan = new Scanner(new File(techFile));
            scan.nextLine();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                Tech tech = new Tech(data[0],data[1],data[2],data[3]);
                techs.add(tech);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, techData_1.csv could not be found.  Simulation terminated.");
            System.exit(0);
        }

        // Get the customers
        try {
            Scanner scan = new Scanner(new File(custFile));
            scan.nextLine();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                Customer cust = new Customer(data[0],data[1],data[2],data[3],data[4]);
                custs.add(cust);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, custData_1.csv could not be found.  Simulation terminated.");
            System.exit(0);
        }

        // Fill the HashSets and Queues
        while (techHash.size() < techCount) {
            int techNum = (int)Math.floor(Math.random() * techs.size());
            int techHashSize = techHash.size();
            techHash.add(techs.get(techNum));
            if (techHashSize < techHash.size()) {
                techQueue.add(techs.get(techNum));
            }
        }

        while (custHash.size() < custCount) {
            int custNum = (int)Math.floor(Math.random() * custs.size());
            int custHashSize = custHash.size();
            custHash.add(custs.get(custNum));
            if (custHashSize < custHash.size()) {
                custQueue.add(custs.get(custNum));
            }
        }

        while (techQueue.size() > 0 && custQueue.size() > 0) {
            SupportSession sesh = new SupportSession(techQueue.remove(), custQueue.remove());
            supportSessionQueue.add(sesh);
        }

    }


    /**
     * Simulates support sessions ending.
     * @param sessionsEnding        The number of support sessions ending.
     */
    public static void run(int sessionsEnding){
        for (int i = 0; i < sessionsEnding; i++) {
            if (!supportSessionQueue.isEmpty()) {
                Tech tech = supportSessionQueue.peek().tech();
                Customer cust = supportSessionQueue.peek().customer();

                /*
                    Note to self:
                    Seems like maybe tech & cust should be removed from queue in Simulation when they are put in to a
                    support session since they are no longer waiting for a support session.  However, this could
                    lead to issues where the hashsets are out of sync with the queues, so they would need to be removed
                    from the hashsets too.  But then the same customer/tech could go back into the hashsets/queues while
                    they are in a support session...
                 */
                techQueue.remove(tech);  // Leaving in hashset since it is unordered, and same tech going back into queue
                custQueue.remove(cust);
                custHash.remove(cust);

                SupportSession sesh = supportSessionQueue.remove();
                System.out.println("Call ended: " + sesh.toString());

                techQueue.add(tech);

                int desiredCustCount = custHash.size() + 1;
                while (desiredCustCount > custHash.size()) {
                    int custNum = (int) Math.floor(Math.random() * custs.size());
                    custHash.add((custs.get(custNum)));
                    if (desiredCustCount == custHash.size()) {
                        custQueue.add(custs.get(custNum));
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Doesn't matter
            }
        }
    }

}
