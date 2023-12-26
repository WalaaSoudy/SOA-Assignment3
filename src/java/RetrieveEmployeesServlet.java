import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/retrieveEmployeesServlet")
public class RetrieveEmployeesServlet extends HttpServlet {

    // File path for the JSON file
    private static final String JSON_FILE_PATH = "C:\\Users\\ALkamel\\Documents\\NetBeansProjects\\WebApplication3\\src\\java\\employee.json";

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Perform the retrieval and sorting
    List<Employee> filteredAndSortedEmployees = retrieveAndSortEmployees();

    // Set the content type to HTML
    response.setContentType("text/html");

    // Get the PrintWriter
    PrintWriter out = response.getWriter();

    // Write the HTML response
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"en\">");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
    out.println("<title>Retrieved and Sorted Employees</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h2>Retrieved and Sorted Employees</h2>");

    if (!filteredAndSortedEmployees.isEmpty()) {
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Employee ID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Designation</th>");
        out.println("<th>Known Languages</th>");
        
        out.println("</tr>");

        for (Employee employee : filteredAndSortedEmployees) {
            out.println("<tr>");
            out.println("<td>" + employee.getEmployeeID() + "</td>");
            out.println("<td>" + employee.getFirstName() + "</td>");
            out.println("<td>" + employee.getLastName() + "</td>");
            out.println("<td>" + employee.getDesignation() + "</td>");

            // Display known languages in a nested table
            out.println("<td>");
            if (employee.getKnownLanguages() != null && !employee.getKnownLanguages().isEmpty()) {
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<th>Language Name</th>");
                out.println("<th>Score Out of 100</th>");
                out.println("</tr>");

                for (Language language : employee.getKnownLanguages()) {
                    out.println("<tr>");
                    out.println("<td>" + language.getLanguageName() + "</td>");
                    out.println("<td>" + language.getScoreOutof100() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            } else {
                out.println("No known languages");
            }
            out.println("</td>");

            // Display Java grade
           
            // Add more columns for other grades as needed

            out.println("</tr>");
        }

        out.println("</table>");
    } else {
        out.println("<p>No employees found.</p>");
    }

    out.println("</body>");
    out.println("</html>");
}




   private List<Employee> retrieveAndSortEmployees() throws IOException {
    // Read all employees from the JSON file
    List<Employee> allEmployees = JsonUtility.readEmployees(JSON_FILE_PATH);

    // Retrieve employees who know the Java language with a score higher than 50
    List<Employee> filteredEmployees = allEmployees.stream()
            .filter(employee -> employee.getKnownLanguages().stream()
                    .anyMatch(language -> language.getLanguageName().equalsIgnoreCase("Java") && language.getScoreOutof100() > 50))
            .collect(Collectors.toList());

    // Sort the result in ascending order based on the Java score
    filteredEmployees.sort(Comparator.comparingInt(e -> getJavaScore(e)));

    return filteredEmployees;
}

private int getJavaScore(Employee e) {
    // Find the Java score for the employee, return 0 if not found
    for (Language language : e.getKnownLanguages()) {
        if ("Java".equalsIgnoreCase(language.getLanguageName())) {
            return language.getScoreOutof100();
        }
    }
    return 0;
}

}
