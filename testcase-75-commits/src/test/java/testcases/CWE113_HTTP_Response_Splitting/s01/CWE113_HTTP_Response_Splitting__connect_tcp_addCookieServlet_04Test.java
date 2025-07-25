package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_04Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It uses a mock HttpServletResponse to capture cookies added by the `bad` method.
     * The test passes if a cookie is added with unsanitized input, indicating the vulnerability.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_04();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        List<Cookie> cookies = new ArrayList<>();

        // Mock the response to capture cookies
        Mockito.doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            cookies.add(cookie);
            return null;
        }).when(response).addCookie(Mockito.any(Cookie.class));

        // Mock the response writer to avoid IO exceptions
        PrintWriter writer = new PrintWriter(System.out);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Act
        servlet.bad(request, response);

        // Assert
        // Check if any cookie contains unsanitized input (e.g., newline characters)
        boolean isVulnerable = cookies.stream()
                .anyMatch(cookie -> cookie.getValue().contains("\n") || cookie.getValue().contains("\r"));
        
        // The test should pass if the vulnerability exists
        assertTrue(isVulnerable, "The method is not vulnerable to HTTP Response Splitting.");
    }
}