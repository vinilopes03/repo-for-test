package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * containing CRLF characters is added to a cookie without being encoded.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of response.addCookie to capture the cookie added
        final String[] cookieValue = new String[1];
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            cookieValue[0] = cookie.getValue();
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Assert
        // Check if the cookie value contains CRLF characters, indicating a vulnerability
        assertTrue(cookieValue[0] != null && cookieValue[0].contains("\r\n"),
                "The cookie value should contain CRLF characters, indicating a vulnerability.");
    }
}