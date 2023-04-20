import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimezoneValidateFilter extends HttpFilter {
    private final TimeZoneQueryParams queryParams = new TimeZoneQueryParams();

    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws ServletException, IOException{

        try {
            if (queryParams.parseQueryParams(req).contains("UTC")) {
                chain.doFilter(req, resp);
            } else {
                resp.setStatus(400);
                resp.setContentType("application/json");
                if (!resp.isCommitted() && resp.getWriter() != null) {
                    resp.getWriter().write("Invalid timezone!");
                    resp.getWriter().close();
                }
            }
        } catch(NullPointerException e) {
        }
        chain.doFilter(req, resp);
    }
}