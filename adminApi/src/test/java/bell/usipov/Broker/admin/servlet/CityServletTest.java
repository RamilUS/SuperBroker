package bell.usipov.broker.admin.servlet;

import bell.usipov.broker.admin.jms.CitySender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Проверка CityServlet
 */
@RunWith(MockitoJUnitRunner.class)
public class CityServletTest {

    private String reloadStatus = "message";
    private String forwargUrl = "/index.jsp";

    @Mock
    private CitySender sender;

    @InjectMocks
    private CityServlet servlet;

    /**
     * Проверка метода doPost при правильно введеных данных
     * @throws ServletException иссключение метода doPost
     * @throws IOException      иссключение метода doPost
     */
    @Test
    public void servletTest() throws ServletException, IOException {

        String testCity = "Berlin";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("city")).thenReturn(testCity);
        when(request.getRequestDispatcher(forwargUrl)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(sender, atLeast(1)).send(testCity);
        verify(request, atLeast(1)).setAttribute(reloadStatus, "uploading weather from Yahoo");
        verify(request, times(1)).getRequestDispatcher(forwargUrl);
        verify(dispatcher).forward(request, response);
    }

    /**
     * Проверка при пустом названии города
     * @throws ServletException иссключение метода doPost
     * @throws IOException      иссключение метода doPost
     */
    @Test
    public void nullCityNameTest() throws ServletException, IOException {

        String message = "";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("city")).thenReturn(message);
        when(request.getRequestDispatcher(forwargUrl)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verifyZeroInteractions(sender);
        verify(request, atLeast(1)).setAttribute(reloadStatus, "city name form must not to be null or empty");
        verify(request, atLeast(1)).getRequestDispatcher(forwargUrl);
        verify(dispatcher, atLeast(1)).forward(request, response);
    }
}