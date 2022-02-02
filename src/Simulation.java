import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * All technicians.
     */
    private final ArrayList<Tech> techs;

    /**
     * All customers.
     */
    private final ArrayList<Customer> custs;

    /**
     * Queue of working techs.
     */
    private Queue<Tech> techQueue = new LinkedList<>();

    /**
     * Queue of customers that have called in.
     */
    private Queue<Customer> custQueue = new LinkedList<>();

    /**
     * Queue of support sessions.
     */
    private static Queue<SupportSession> supportSessionQueue = new LinkedList<>();

    /**
     *
     * @param args      The command line arguments.
     */
    public static void main(String[] args){
        Simulation sim = new Simulation(10, 20);
        run(3);
    }

    /**
     * Full Simulation constructor.
     * @param techCount         Number of techs working.
     * @param custCount         Number of customers that have called.
     */
    public Simulation(int techCount, int custCount){
        // Get the customers
        custs = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("custData_1.csv"));
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

        // Get the techs
        techs = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("techData_1.csv"));
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


        // Fill the queues
        while (techQueue.size() < techCount) {
            int techNum = (int)Math.floor(Math.random() * techs.size());
            if (!techQueue.contains(techs.get(techNum))) {
                techQueue.add(techs.get(techNum));
            }
        }

        while (custQueue.size() < custCount) {
            int custNum = (int)Math.floor(Math.random() * custs.size());
            if (!custQueue.contains(custs.get(custNum))) {
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
     * @param sessionsEnding            The number of support sessions ending.
     */
    public static void run(int sessionsEnding){
        for (int i = 0; i < sessionsEnding; i++) {
            if (!supportSessionQueue.isEmpty()) {
                SupportSession sesh = supportSessionQueue.remove();
                System.out.println("Call ended: " + sesh.toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }

}
