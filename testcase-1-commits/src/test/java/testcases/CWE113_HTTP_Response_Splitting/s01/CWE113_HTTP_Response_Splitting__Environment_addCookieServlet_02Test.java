package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Capture the output of the response
        StringWriter responseWriter = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with the vulnerable data
        Mockito.verify(response).addCookie(Mockito.argThat((Cookie cookie) ->
                cookie.getName().equals("lang") && cookie.getValue().contains("\r\n")));

        // Check the response output for signs of HTTP Response Splitting
        String responseOutput = responseWriter.toString();
        assertTrue(responseOutput.contains("Set-Cookie: sessionId=abc123"),
                "Response should contain the injected Set-Cookie header.");
    }
}