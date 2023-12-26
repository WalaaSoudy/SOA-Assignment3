import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {

    private static final String JSON_FILE_PATH = "C:/Users/ALkamel/Documents/NetBeansProjects/WebApplication3/src/java/employee.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get parameters from the request
        String searchEmployeeIDStr = request.getParameter("searchEmployeeID");
        String searchDesignation = request.getParameter("searchDesignation");

        // Perform the search
        List<Employee> searchResults = performSearch(searchEmployeeIDStr, searchDesignation);

        // Convert the search results to JSON
        String jsonResponse = convertToJson(searchResults);

        // Set the JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    private List<Employee> performSearch(String searchEmployeeIDStr, String searchDesignation) throws IOException {
        // Read all employees from the JSON file
        List<Employee> allEmployees = JsonUtility.readEmployees(JSON_FILE_PATH);

        // Initialize the list to store search results
        List<Employee> searchResults = new ArrayList<>();

        // Check if search by EmployeeID is requested
        if (searchEmployeeIDStr != null && !searchEmployeeIDStr.isEmpty()) {
            int searchEmployeeID = Integer.parseInt(searchEmployeeIDStr);
            // Find employees with the specified EmployeeID
            for (Employee employee : allEmployees) {
                if (employee.getEmployeeID() == searchEmployeeID) {
                    searchResults.add(employee);
                    break;  // Assuming EmployeeID is unique, so we can stop the search once found
                }
            }
        }

        // Check if search by Designation is requested
        if (searchDesignation != null && !searchDesignation.isEmpty()) {
            // Find employees with the specified Designation
            for (Employee employee : allEmployees) {
                if (employee.getDesignation().equalsIgnoreCase(searchDesignation)) {
                    searchResults.add(employee);
                }
            }
        }

        return searchResults;
    }

    private String convertToJson(List<Employee> employees) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(employees);
    }
}
