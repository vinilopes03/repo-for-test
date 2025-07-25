package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This JUnit 5 test class is designed to verify the presence of an HTTP Response Splitting vulnerability
 * in the CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 class.
 * The test specifically checks if unsanitized input from an environment variable can be used to manipulate
 * HTTP headers by injecting newline characters.
 */
public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04Test {

    @Test
    public void testHttpResponseSplittingVulnerability() {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        try {
            // Act
            servlet.bad(request, response);

            // Assert
            // Verify that the cookie was added with the malicious input, indicating a vulnerability
            Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
                String cookieValue = cookie.getValue();
                return cookieValue.contains("\r\n") || cookieValue.contains("\n");
            }));

            // If the above verification passes, it means the vulnerability exists
            assertTrue(true, "The HTTP Response Splitting vulnerability exists.");
        } catch (Throwable e) {
            // If an exception is thrown, the test should fail
            fail("An exception occurred during the test: " + e.getMessage());
        } finally {
            // Clean up the environment variable
            System.clearProperty("ADD");
        }
    }
}