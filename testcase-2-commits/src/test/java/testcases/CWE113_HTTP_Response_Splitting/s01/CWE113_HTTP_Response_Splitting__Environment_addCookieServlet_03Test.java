package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set an environment variable that simulates a malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response added a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set an environment variable that simulates a malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03();

        // Call the good methods
        servlet.goodG2B1(request, response);
        servlet.goodG2B2(request, response);
        servlet.goodB2G1(request, response);
        servlet.goodB2G2(request, response);

        // Verify that the response did not add a cookie with the malicious input
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}