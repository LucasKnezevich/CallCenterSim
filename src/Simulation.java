import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


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
     * @throws FileNotFoundException        Thrown when a file isn't found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Simulation sim = new Simulation(10, 20);
        try {
            run(3);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    /**
     * Full Simulation constructor.
     * @param techCount         Number of techs working.
     * @param custCount         Number of customers that have called.
     * @throws FileNotFoundException        Thrown when the file isn't found.
     */
    public Simulation(int techCount, int custCount) throws FileNotFoundException {
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
        } catch (Exception e) {
            System.out.println("Sorry, custData_1.csv could not be found.");
        }

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
        } catch (Exception e) {
            System.out.println("Sorry, techData_1.csv could not be found.");
        }


        // Fill the queues
        while (techQueue.size() < techCount) {
            int techNum = ThreadLocalRandom.current().nextInt(0,techs.size());
            if (!techQueue.contains(techs.get(techNum))) {
                techQueue.add(techs.get(techNum));
            }
        }

        while (custQueue.size() < custCount) {
            int custNum = ThreadLocalRandom.current().nextInt(0,custs.size());
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
     * Simulates three support sessions ending.
     * @param sessionsEnding            The number of support sessions ending.
     * @throws InterruptedException     Thrown when thread is interrupted.
     */
    public static void run(int sessionsEnding) throws InterruptedException {
        for (int i = 0; i < sessionsEnding; i++) {
            if (!supportSessionQueue.isEmpty()) {
                SupportSession sesh = supportSessionQueue.remove();
                System.out.println("Call ended: " + sesh.toString());
            }
            Thread.sleep(1000);
        }
    }

}
