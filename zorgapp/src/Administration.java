import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class Administration represents the core of the application by showing
 * the main menu, from where all other functionality is accessible, either
 * directly or via sub-menus.
 * An Administration instance needs a User as input, which is passed via the
 * constructor to the data member 'currentUser'.
 * The patient data is available via the data member currentPatient.
 */
class Administration {
    static final int STOP = 0;
    static final int VIEW = 1;
    static final int CHANGE_PATIENT = 2;

    private List<Patient> patients; // List of patients
    Patient currentPatient; // The currently selected patient
    User currentUser; // The current user of the program.

    /**
     * Constructor
     */
    Administration(User user) {
        currentUser = user;
        patients = new ArrayList<>();
        // Add patients to the list
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29),1.80,60));
        patients.add(new Patient(2, "Doe", "John", LocalDate.of(1990, 5, 15), 1.75,60));
        patients.add(new Patient(3, "Smith", "Jane", LocalDate.of(1985, 8, 22), 1.65, 70));

        // Set the current patient to the first patient in the list
        currentPatient = patients.getFirst();

        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }

    void menu() {
        var scanner = new Scanner(System.in); // User input via this scanner.

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("Huidig patient: %s\n", currentPatient.fullName());

            /*
             * Print menu on screen
             */
            System.out.format("%d: STOP\n", STOP);
            System.out.format("%d: Bekijk patiënt informatie\n", VIEW);
            System.out.format("%d: Verander patiënt\n", CHANGE_PATIENT);

            System.out.print("Maak #keuze: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case STOP: // Interrupt the loop
                    nextCycle = false;
                    break;

                case VIEW:
                    currentPatient.viewData();
                    break;

                case CHANGE_PATIENT:
                    changePatient();
                    break;

                default:
                    System.out.println("Voer een geldig cijfer in");
                    break;
            }
        }
    }

    /**
     * Method to change the current patient
     */
    void changePatient() {
        var scanner = new Scanner(System.in); // User input via this scanner.
        System.out.println("Selecteer een patiënt door zijn/haar nummer in te voeren:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.format("%d: %s\n", i + 1, patients.get(i).fullName());
        }
        System.out.print("Patiëntnummer invoeren: ");
        int patientNumber = scanner.nextInt();
        if (patientNumber > 0 && patientNumber <= patients.size()) {
            currentPatient = patients.get(patientNumber - 1);
            System.out.println("Patiënt veranderd naar: " + currentPatient.fullName());
        } else {
            System.out.println("Ongeldig patiëntnummer");
            changePatient();
        }
    }
}