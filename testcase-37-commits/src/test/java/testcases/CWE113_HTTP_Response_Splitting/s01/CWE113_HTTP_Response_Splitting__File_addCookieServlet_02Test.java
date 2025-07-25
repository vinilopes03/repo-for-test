package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_02Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows malicious input to be included in the HTTP response.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mock the response to capture the added cookies
        Mockito.doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Assert that the cookie value contains unsanitized input, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\n"), "Vulnerability exists: Input is not sanitized.");
            return null;
        }).when(response).addCookie(Mockito.any(Cookie.class));

        // Act
        servlet.bad(request, response);
    }
}