package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_13Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test simulates a scenario where unsanitized input is read from a file and added to a cookie.
     * The test passes if the vulnerability exists, i.e., the input is not sanitized and directly added to the cookie.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_13 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_13();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie is added with unsanitized data
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));
        Cookie addedCookie = Mockito.mock(Cookie.class);
        when(response.addCookie(addedCookie)).thenAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains unsanitized input (e.g., newline character)
            assertTrue(cookie.getValue().contains("\n"), "The cookie value should contain unsanitized input.");
            return null;
        });
    }
}