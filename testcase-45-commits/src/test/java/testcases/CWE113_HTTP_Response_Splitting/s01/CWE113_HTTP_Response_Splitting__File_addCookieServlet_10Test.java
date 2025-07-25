package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_10Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by providing a malicious input that includes a CRLF sequence ('\r\n'),
     * which should not be allowed in a cookie value. If the vulnerability exists, the test
     * will pass by confirming that the malicious input is added as a cookie without sanitization.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of reading from a file
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        when(request.getParameter("data")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input
        verify(response, times(1)).addCookie(any(Cookie.class));
        Cookie addedCookie = new Cookie("lang", maliciousInput);
        assertTrue(addedCookie.getValue().contains("\r\n"), "The input was not sanitized, indicating a vulnerability.");
    }
}