package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate the vulnerability
        System.setProperty("ADD", "value\r\nSet-Cookie: sessionId=12345");

        // Capture the cookies added to the response
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains a newline character, indicating a potential HTTP Response Splitting
            assertTrue(cookie.getValue().contains("\r\n"), "Cookie value should contain a newline character indicating HTTP Response Splitting");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Call the vulnerable method
        servlet.bad(request, response);

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}