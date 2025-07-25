package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_11Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input is added to a cookie.
     * The test will pass if the vulnerability exists, i.e., the malicious input is not sanitized.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_11 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate adding a cookie to the response
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains unsanitized input
            assertTrue(cookie.getValue().contains("\r\n"), "Vulnerability exists: Input is not sanitized.");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);
    }
}