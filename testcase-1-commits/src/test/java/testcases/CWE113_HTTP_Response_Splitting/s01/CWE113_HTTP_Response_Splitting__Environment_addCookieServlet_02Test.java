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
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        responseWriter = new StringWriter();
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        System.setProperty("ADD", "en-US%0d%0aSet-Cookie:sessionId=12345");

        // Mock the response writer
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        // Call the vulnerable method
        servlet.bad(request, response);

        // Verify that the cookie was added with the unvalidated input
        verify(response).addCookie(argThat(cookie -> "lang".equals(cookie.getName()) && "en-US%0d%0aSet-Cookie:sessionId=12345".equals(cookie.getValue())));

        // Check if the response contains the injected header
        String responseContent = responseWriter.toString();
        assertTrue(responseContent.contains("Set-Cookie:sessionId=12345"), "Response should contain the injected Set-Cookie header");
    }
}