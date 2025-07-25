package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_11Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the malicious input is not sanitized
     * and is directly added to the cookie, allowing for HTTP response splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_11 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the static method to return true to trigger the bad path
        IO ioMock = mock(IO.class);
        when(ioMock.staticReturnsTrue()).thenReturn(true);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input, indicating a vulnerability
        verify(response, times(1)).addCookie(argThat(cookie -> {
            String cookieValue = cookie.getValue();
            return cookieValue.equals(maliciousInput);
        }));
    }
}