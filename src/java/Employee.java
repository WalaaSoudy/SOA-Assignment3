import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private int employeeID;
    private String designation;
    private List<Language> knownLanguages;

    // Constructors, getters, and setters

    public Employee() {
        // Default constructor
    }

    public Employee(String firstName, String lastName, int employeeID, String designation, List<Language> knownLanguages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.designation = designation;
        this.knownLanguages = knownLanguages;
    }

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Language> getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(List<Language> knownLanguages) {
        this.knownLanguages = knownLanguages;
    }
     public void addKnownLanguage(String languageName, int scoreOutof100) {
        if (knownLanguages == null) {
            knownLanguages = new ArrayList<>();
        }
        knownLanguages.add(new Language(languageName, scoreOutof100));
    }

    void setAttributes(List<String> attributes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
