package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is directly added to a cookie without encoding.
     * 
     * The test simulates a scenario where the environment variable "ADD" contains
     * malicious input that could lead to HTTP Response Splitting if not properly
     * sanitized.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with unsanitized input
        verify(response, times(1)).addCookie(argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousInput)
        ));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}