package bell.usipov.broker.admin.servlet;


import bell.usipov.broker.admin.jms.CitySender;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для обработки index.jps
 */
public class CityServlet extends HttpServlet {


    private final CitySender citySender;

    @Inject
    public CityServlet(CitySender citySender) {
        this.citySender = citySender;
    }

    /**
     * Получение названиея города и обработка запроса со страницы index.jsp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cityReq = req.getParameter("city");

        if (cityReq == null || cityReq.isEmpty()) {
            reloadJsp(req, resp, "city name form must not to be null or empty");
            return;
        }
        citySender.send(cityReq);
        reloadJsp(req, resp, "uploading weather from Yahoo");
    }

    private void reloadJsp(HttpServletRequest req, HttpServletResponse resp, String reloadStatus) throws ServletException, IOException {
        req.setAttribute("message", reloadStatus);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
