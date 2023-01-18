package homework_8;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        String timezone = req.getParameter("timezone");

        resp.getWriter().write(getTime(parsTimeParam(req)));
        resp.getWriter().close();
    }
    private String parsTimeParam(HttpServletRequest request){
        if(request.getParameterMap().containsKey("timezone")){
            return request.getParameter("timezone").replace(' ','+');
        }
        return "UTC";
    }

    public String getTime(String timezone){
        String b =
                ZonedDateTime.now(ZoneId.of(timezone))
                        .format(DateTimeFormatter
                                .ofPattern("dd-MM-yyyy HH:mm:ss z"));
        return "Date now: "+b;
    }
}
