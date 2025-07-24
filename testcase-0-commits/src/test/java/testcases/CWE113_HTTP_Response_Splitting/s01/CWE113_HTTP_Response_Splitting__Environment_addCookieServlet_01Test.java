package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        responseWriter = new StringWriter();
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=12345");

        // Mock the response writer
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        // Call the vulnerable method
        servlet.bad(request, response);

        // Verify that the response contains the injected cookie
        String responseContent = responseWriter.toString();
        assertTrue(responseContent.contains("Set-Cookie: sessionId=12345"), "Response should contain the injected cookie.");
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Mock the response writer
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        // Call the goodG2B method
        servlet.good(request, response);

        // Verify that the response does not contain any injected cookie
        String responseContent = responseWriter.toString();
        assertTrue(!responseContent.contains("Set-Cookie: sessionId=12345"), "Response should not contain the injected cookie.");
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Set up the environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=12345");

        // Mock the response writer
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        // Call the goodB2G method
        servlet.good(request, response);

        // Verify that the response does not contain the injected cookie
        String responseContent = responseWriter.toString();
        assertTrue(!responseContent.contains("Set-Cookie: sessionId=12345"), "Response should not contain the injected cookie.");
    }
}