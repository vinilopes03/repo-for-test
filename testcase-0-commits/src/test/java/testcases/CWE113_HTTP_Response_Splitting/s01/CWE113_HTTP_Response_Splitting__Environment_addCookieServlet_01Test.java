package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability
     * in the `bad` method of the `CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01` class.
     * It simulates an environment variable containing malicious input and checks if the input
     * is added to the response cookie without proper sanitization.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate environment variable with malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie with malicious input is added to the response
        verify(response, times(1)).addCookie(argThat(cookie -> 
            cookie.getValue().equals(maliciousInput)
        ));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}