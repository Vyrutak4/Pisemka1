import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/zmena_v_poctu_testu.php")
public class ZmenaVPoctuTestuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        int denInt = 0;
        String den = request.getParameter("den");
        switch (den) {
            case "Po":
                denInt = 1;
                break;
            case "Ut":
                denInt = 2;
                break;
            case "St":
                denInt = 3;
                break;
            case "Ct":
                denInt = 4;
                break;
            case "Pa":
                denInt = 5;
                break;
            case "So":
                denInt = 6;
                break;
            case "Ne":
                denInt = 7;
                break;
        }
        List<List<String>> records = new ArrayList<>();

        String url = "https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/testy.csv";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }

        int counter = 0;
        int lastPocet = 0;
        for (List<String> list :
                records) {
            for (String record :
                    list) {
                if (counter > 2) {
                    if ((counter - 2) % 21 == denInt * 3 - 2) {
                        out.println(record);
                    } else if ((counter - 2) % 21 == denInt * 3 - 1) {
                        int output = Integer.parseInt(record) - lastPocet;
                        lastPocet = Integer.parseInt(record);
                        out.println(output);
                        out.print("<br>");
                    }
                }
                counter++;
            }

        }
        out.println("</body></html>");
    }
}