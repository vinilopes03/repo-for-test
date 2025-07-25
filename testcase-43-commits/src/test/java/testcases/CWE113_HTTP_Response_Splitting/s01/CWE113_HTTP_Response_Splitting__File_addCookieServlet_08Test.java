package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_08Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input is read from a file
     * and directly used to create a cookie without proper encoding or sanitization.
     * The test will pass if the vulnerability is present, i.e., the malicious input
     * is not sanitized and is directly added to the cookie.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_08();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the response to capture the added cookie
        doNothing().when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains unsanitized input
            String cookieValue = cookie.getValue();
            return cookieValue.contains("\r\n");
        }));
    }
}