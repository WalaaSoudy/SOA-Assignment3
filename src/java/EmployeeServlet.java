import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {
    private List<Employee> employees;

    // File path for the JSON file
    private static final String JSON_FILE_PATH = "C:\\Users\\ALkamel\\Documents\\NetBeansProjects\\WebApplication3\\src\\java\\employee.json";

    @Override
    public void init() throws ServletException {
        // Initialize the employees list from the JSON file
        try {
            employees = JsonUtility.readEmployees(JSON_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            employees = new ArrayList<>();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addEmployee(request, response);
    }

  private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    int employeeID = Integer.parseInt(request.getParameter("employeeID"));
    String designation = request.getParameter("designation");

    // Check if the employee ID is already in use
    if (isEmployeeIdUnique(employeeID)) {
        List<Language> knownLanguages = new ArrayList<>();
        int MAX_LANGUAGES = 3; // Set the maximum number of languages to 3

        for (int i = 1; i <= MAX_LANGUAGES; i++) {
            String languageName = request.getParameter("language" + i + "Name");
            String languageScoreStr = request.getParameter("language" + i + "Score");

            if (languageName != null && !languageName.isEmpty() && languageScoreStr != null && !languageScoreStr.isEmpty()) {
                int languageScore = Integer.parseInt(languageScoreStr);
                // Add the language to the list
                knownLanguages.add(new Language(languageName, languageScore));
            }
        }

        // Create a new Employee object
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setEmployeeID(employeeID);
        newEmployee.setDesignation(designation);
        newEmployee.setKnownLanguages(knownLanguages);

        // Add the new employee to the list
        employees.add(newEmployee);

        // Write the updated list back to the JSON file
        JsonUtility.writeEmployees(employees, JSON_FILE_PATH);

        // Redirect back to the HTML form or any other page
        response.sendRedirect("addEmployee.html");
    } else {
        // Employee ID is not unique, handle accordingly (e.g., show an error message)
        response.getWriter().println("Employee ID is not unique. Please choose a different ID.");
    }
}

private boolean isEmployeeIdUnique(int employeeID) {
    // Check if the employee ID is unique in the existing list
    for (Employee employee : employees) {
        if (employee.getEmployeeID() == employeeID) {
            return false; // ID is not unique
        }
    }
    return true; // ID is unique
}


}
