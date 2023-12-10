import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class OnlineReservationSystem {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

   
    private static void initializeUsers() {
        // You can replace this with a database or any other authentication mechanism
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    private static boolean isValidUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void makeReservation(Scanner scanner, String username) {
        // Gather reservation details from the user
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter departure place: ");
        String fromPlace = scanner.nextLine();

        System.out.print("Enter destination: ");
        String toDestination = scanner.nextLine();

        // Generate a PNR number (You might want a more sophisticated method)
        String pnr = generatePNR();

        // Create a Reservation object and store it
        Reservation reservation = new Reservation(trainNumber, classType, dateOfJourney, fromPlace, toDestination);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful!");
        System.out.println("Your PNR number is: " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.next();

        if (reservations.containsKey(pnr)) {
            Reservation reservation = reservations.get(pnr);

            // Display reservation details
            System.out.println("Reservation Details:");
            System.out.println(reservation);

            // Ask for confirmation
            System.out.print("Do you want to cancel this reservation? (Enter 'OK' to confirm): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("OK")) {
                reservations.remove(pnr);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("Reservation cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number. No reservation found.");
        }
    }

    private static String generatePNR() {
        // This is a simple method to generate a PNR. You might want to use a more sophisticated approach.
        return "PNR" + System.currentTimeMillis();
    }

    static class Reservation {
        private int trainNumber;
        private String classType;
        private String dateOfJourney;
        private String fromPlace;
        private String toDestination;

        public Reservation(int trainNumber, String classType, String dateOfJourney, String fromPlace, String toDestination) {
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.toDestination = toDestination;
        }

        @Override
        public String toString() {
            return "Train Number: " + trainNumber +
                    "\nClass Type: " + classType +
                    "\nDate of Journey: " + dateOfJourney +
                    "\nFrom: " + fromPlace +
                    "\nTo: " + toDestination;
        }
    }
     public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Reservation System");

        // Login
        String username, password;
        do {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
        } while (!isValidUser(username, password));

        System.out.println("Login successful!");

        // Main menu
        int choice;
        do {
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(scanner, username);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
            }
        } while (choice != 3);

        System.out.println("Thank you for using the Online Reservation System!");

        scanner.close();
    }

}
