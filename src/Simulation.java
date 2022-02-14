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
    private ArrayList<Customer> custs;

    /**
     * Techs
     */
    private ArrayList<Tech> techs;

    /**
     * Customers that have called
     */
    private HashSet<Customer> custHash;

    /**
     * Techs that are working
     */
    private HashSet<Tech> techHash;

    /**
     * Techs that are working
     */
    private Queue<Tech> techQueue;

    /**
     * Customers that have called
     */
    private Queue<Customer> custQueue;

    /**
     * Queue of support sessions.
     */
    private PriorityQueue<SupportSession> supportPQ;


    /**
     * Main
     * @param args      The command line arguments.
     */
    public static void main(String[] args){
        Simulation sim = new Simulation(40, 70, "techData_1.csv", "custData_1.csv",
                2, 42);
        sim.run(30);
    }


    /**
     * Full Simulation constructor.
     * @param techCount         Number of techs working.
     * @param custCount         Number of customers that have called.
     * @param techFile          File containing tech data.  Should be a comma delimited file.
     * @param custFile          File containing cust data.  Should be a comma delimited file.
     * @param minCallTime       Minimun length of a call in minutes
     * @param maxCallTime       Maximum length of a call time in minutes
     */
    public Simulation(int techCount, int custCount, String techFile, String custFile, int minCallTime, int maxCallTime){
        Random rand = new Random();

        techs = new ArrayList<>();
        custs = new ArrayList<>();

        techHash = new HashSet<>();
        techQueue = new LinkedList<>();

        custHash = new HashSet<>();
        custQueue = new LinkedList<>();

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

        supportPQ = new PriorityQueue<>();
        while (techQueue.size() > 0 && custQueue.size() > 0) {
            Tech tech = techQueue.remove();
            Customer cust = custQueue.remove();
            techHash.remove(tech);
            custHash.remove(cust);
            SupportSession sesh = new SupportSession(tech, cust,
                    rand.nextInt((maxCallTime + 1) - minCallTime) + minCallTime);
            supportPQ.add(sesh);
        }
    }


    /**
     * Simulates support sessions ending.
     * @param sessionsEnding        The number of support sessions ending.
     */
    public void run(int sessionsEnding) {
        Random rand = new Random();
        int clock = 0;
        int counter = 0;

        while (!supportPQ.isEmpty() && counter < sessionsEnding) {
            // Issue: Lets all calls for the minute finish once number of sessions ending is hit rather than halting once number is hit...
            while (supportPQ.peek() != null && supportPQ.peek().callEndTime() == clock) {
                counter++;
                SupportSession sesh = supportPQ.remove();
                Tech tech = sesh.tech();
                techQueue.add(tech);
                techHash.add(tech);

                System.out.println("Call ended: " + sesh);

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

            clock++;
        }

        System.out.println();
        System.out.println("Simulation duration: " + (clock - 1) + " minutes");
        System.out.println("Calls completed: " + counter);
    }

}
