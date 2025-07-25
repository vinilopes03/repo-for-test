package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_12Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability is present, i.e., if unsanitized input
     * is added directly to the cookie, allowing for potential HTTP response splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_12 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_12();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of IO.staticReturnsTrueOrFalse() to ensure the bad path is taken
        IO ioMock = mock(IO.class);
        when(ioMock.staticReturnsTrueOrFalse()).thenReturn(true);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized data
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains unsanitized input that could lead to HTTP response splitting
            String value = cookie.getValue();
            return value.contains("\r\n") || value.contains("\n");
        }));
    }
}