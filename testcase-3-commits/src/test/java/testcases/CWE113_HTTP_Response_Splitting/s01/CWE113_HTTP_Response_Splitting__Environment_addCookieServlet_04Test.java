package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04Test {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate the vulnerability
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Call the vulnerable method
        servlet.bad(request, response);

        // Verify that the vulnerability is triggered by checking if the response contains the malicious cookie
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousInput)
        ));

        // If the above verification passes, it means the vulnerability is present and correctly triggered
        assertTrue(true);
    }
}