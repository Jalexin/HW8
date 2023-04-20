import javax.servlet.http.HttpServletRequest;

public class TimeZoneQueryParams {
    public String parseQueryParams(HttpServletRequest request) {
        String timezoneParam = request.getParameter("timezone");
        if (timezoneParam != null) {
            return timezoneParam.replace(" ", "+").toUpperCase();
        } else {
            return null;
        }
    }
}