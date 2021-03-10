import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<List<String>> records = new ArrayList<>();

        String url = "https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/testy.csv";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"/pocet_testu.php\">\n" +
                "  <label for=\"sheep_count\">Počet testu za den: </label>" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Po\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ut\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"St\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ct\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Pa\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"So\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ne\">" +
                "</form><br>");
        out.println("<form action=\"/zmena_v_poctu_testu.php\">\n" +
                "  <label for=\"sheep_count\">Změna v počtu testu za den: </label>" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Po\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ut\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"St\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ct\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Pa\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"So\">" +
                "  <input type=\"submit\" id=\"den\" name=\"den\" value=\"Ne\">" +
                "</form><br>");

        out.println("</body></html>");
    }
}