import java.time.LocalDate;

class Patient {
    static final int RETURN = 0;
    static final int SURNAME = 1;
    static final int FIRSTNAME = 2;
    static final int DATEOFBIRTH = 3;

    int id;
    String surname;
    String firstName;
    LocalDate dateOfBirth;
    double bmi;
    double length;
    double weight;

    /**
     * Constructor
     */
    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double length, double weight) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.length = length;
        this.weight = weight;
        this.bmi = calculateBMI(weight, length);
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    public double calculateBMI(double weightInKg, double heightInMeters) {
        double bmi = weightInKg / (heightInMeters * heightInMeters);
        return bmi;
    }

    public int calcAge(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        int birthYear = dateOfBirth.getYear();
        int yearToday = today.getYear();
        int age = yearToday - birthYear;
        return age;
    }

    /**
     * Display patient data.
     */
    void viewData() {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth); // date time format?//
        System.out.format("%-17s %s\n", "BMI:", bmi);
        System.out.format("%-17s %s\n", "Leeftijd:", calcAge(dateOfBirth));
    }

    /**
     * Shorthand for a Patient's full name
     */
    String fullName() {
        return String.format("%s %s [%d-%d-%d]", firstName, surname, dateOfBirth.getDayOfMonth(), dateOfBirth.getMonthValue(), dateOfBirth.getYear() );
    }
}

