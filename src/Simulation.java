import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    private final static Queue<SupportSession> supportPQ = new LinkedList<>();


    /**
     *
     * @param args      The command line arguments.
     */
    public static void main(String[] args){
        Simulation sim = new Simulation(30, 45, "techData_1.csv", "custData_1.csv",
                2, 42);
        run(30);
    }


    /**
     * Full Simulation constructor.
     * @param techCount         Number of techs working.
     * @param custCount         Number of customers that have called.
     * @param techFile          File containing tech data.  Should be a comma delimited file.
     * @param custFile          File containing cust data.  Should be a comma delimited file.
     */
    public Simulation(int techCount, int custCount, String techFile, String custFile, int minCallTime, int maxCallTime){

        Random rand = new Random();

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
            int techNum = rand.nextInt(techs.size());
            if (!techHash.contains(techs.get(techNum))) {
                techHash.add(techs.get(techNum));
                techQueue.add(techs.get(techNum));
            }
        }

        while (custHash.size() < custCount) {
            int custNum = rand.nextInt(custs.size());
            if (!custHash.contains(custs.get(custNum))) {
                custHash.add(custs.get(custNum));
                custQueue.add(custs.get(custNum));
            }
        }

        while (techQueue.size() > 0 && custQueue.size() > 0) {
            SupportSession sesh = new SupportSession(techQueue.remove(), custQueue.remove(),
                    rand.nextInt(maxCallTime - minCallTime) + minCallTime);
            supportPQ.add(sesh);
        }

    }


    /**
     * Simulates support sessions ending.
     * @param sessionsEnding        The number of support sessions ending.
     */
    public static void run(int sessionsEnding){
        Random rand = new Random();
        int counter = 0;

//        while (!supportPQ.isEmpty()) {
//            if () {
//
//            }
//
//            counter++;
//        }


        for (int i = 0; i < sessionsEnding; i++) {
            if (!supportPQ.isEmpty()) {
                SupportSession sesh = supportPQ.remove();
                Tech tech = sesh.tech();
                Customer cust = sesh.customer();

                // Leaving in tech hashset since it is unordered, and same tech going back into queue...
                techQueue.remove(tech);
                techQueue.add(tech);

                custQueue.remove(cust);
                custHash.remove(cust);

                System.out.println("Call ended: " + sesh.toString());

                int desiredCustCount = custHash.size() + 1;
                while (desiredCustCount > custHash.size()) {
                    int custNum = rand.nextInt(custs.size());
                    if (!custHash.contains(custs.get(custNum))) {
                        custHash.add((custs.get(custNum)));
                        custQueue.add(custs.get(custNum));
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Doesn't matter
            }

            counter++;
        }
        System.out.println();
        System.out.println("Time passed: " + counter + " minutes");
    }

}
