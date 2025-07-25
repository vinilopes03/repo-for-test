package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_06Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_06 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_06();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        writer = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        String maliciousValue = "en-US\r\nSet-Cookie: sessionId=12345";
        System.setProperty("ADD", maliciousValue);

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the cookie was added with the malicious value
        verify(response).addCookie(argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousValue)
        ));
    }

    @Test
    public void testGood() throws Throwable {
        // Call the good method
        servlet.good(request, response);

        // Verify that no malicious cookies are added
        verify(response, never()).addCookie(argThat(cookie -> 
            cookie.getValue().contains("\r\n")
        ));
    }
}