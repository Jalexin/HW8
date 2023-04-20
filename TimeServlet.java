import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        if (request.getParameter("timezone") == null || request.getParameter("timezone") == "") {
            Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentTime = dateFormat.format(currentDate);
            PrintWriter writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head></head>");
            writer.println("<body>");
            writer.println("<h1></h1>");
            writer.println("<p>" + currentTime + "</p>");
            writer.println("</body>");
            writer.println("</html>");
        }
        else {
            String timezone = request.getParameter("timezone").replaceAll("[^\\d]", "");
            LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
            int offset = Integer.parseInt(timezone.substring(0));

            ZoneOffset zoneOffset = ZoneOffset.ofHours(offset);
            OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.UTC).withOffsetSameInstant(zoneOffset);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'XXX");
            String currentTime = offsetDateTime.format(formatter);
            PrintWriter writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head></head>");
            writer.println("<body>");
            writer.println("<h1></h1>");
            writer.println("<p>" + currentTime + "</p>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}

