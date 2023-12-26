import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/displayEmployeeServlet")
public class DisplayEmployeeServlet extends HttpServlet {

    // File path for the JSON file
    private static final String JSON_FILE_PATH = "C:\\Users\\ALkamel\\Documents\\NetBeansProjects\\WebApplication3\\src\\java\\employee.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Get the PrintWriter object to write the HTML page
        PrintWriter out = response.getWriter();

        // Read all employees from the JSON file
        List<Employee> employees = JsonUtility.readEmployees(JSON_FILE_PATH);

        // Write the HTML page
        out.println("<html>");
         out.println("<head>");
    out.println("<title>Employee Data</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"display.css\">");
    out.println("</head>");
        out.println("<body>");
        out.println("<h2>Employees Data</h2>");

        if (employees != null && !employees.isEmpty()) {
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>First Name</th>");
            out.println("<th>Last Name</th>");
            out.println("<th>Employee ID</th>");
            out.println("<th>Designation</th>");
            out.println("<th>Known Languages</th>");
            out.println("</tr>");

            for (Employee employee : employees) {
                out.println("<tr>");
                out.println("<td>" + employee.getFirstName() + "</td>");
                out.println("<td>" + employee.getLastName() + "</td>");
                out.println("<td>" + employee.getEmployeeID() + "</td>");
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

                out.println("</tr>");
            }

            out.println("</table>");
        } else {
            out.println("<p>No employee data available.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
