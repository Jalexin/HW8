import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private final TimeZoneParams queryParams = new TimeZoneParams();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ZoneId zoneId = ZoneId.of(queryParams.parseTZParams(req));
        Clock clock = Clock.system(zoneId);
        String time = LocalDateTime.now(clock).format(DateTimeFormatter.
                ofPattern("yyyy-MM-dd hh:mm:ss ")) + zoneId;
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(time);
        resp.getWriter().close();
    }
}