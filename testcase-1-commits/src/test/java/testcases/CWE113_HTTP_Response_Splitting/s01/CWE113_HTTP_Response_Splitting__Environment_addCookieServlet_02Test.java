package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., the input is not sanitized
     * and allows malicious input to be included in the HTTP response.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response contains a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}