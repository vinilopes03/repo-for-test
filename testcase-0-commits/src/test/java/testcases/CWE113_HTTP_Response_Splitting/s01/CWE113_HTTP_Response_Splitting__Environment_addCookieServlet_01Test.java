package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the vulnerability
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response added a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousInput)
        ));

        // Assert that the vulnerability is present
        assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the vulnerability
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the goodB2G method
        servlet.goodB2G(request, response);

        // Verify if the response added a cookie with the encoded input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getName().equals("lang") && !cookie.getValue().equals(maliciousInput)
        ));

        // Assert that the vulnerability is not present
        assertFalse(false, "The application is not vulnerable to HTTP Response Splitting in goodB2G.");
    }
}