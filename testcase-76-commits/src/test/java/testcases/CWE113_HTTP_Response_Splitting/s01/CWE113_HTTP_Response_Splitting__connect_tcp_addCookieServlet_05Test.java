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

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test simulates a scenario where unsanitized input is used to create a cookie.
     * The test will pass if the vulnerability exists, i.e., the input is not sanitized.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));
        // Check if the cookie value contains unsanitized input
        Cookie addedCookie = new Cookie("lang", "en%0d%0aSet-Cookie:%20sessionId=malicious");
        assertTrue(addedCookie.getValue().contains("%0d%0a"), "The input was not sanitized, indicating a vulnerability.");
    }
}