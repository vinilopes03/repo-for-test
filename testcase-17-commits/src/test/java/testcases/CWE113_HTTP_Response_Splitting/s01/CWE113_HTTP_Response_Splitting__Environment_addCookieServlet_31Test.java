package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_31Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and the malicious input is directly added to the cookie.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_31 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_31();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input, indicating the vulnerability
        verify(response, times(1)).addCookie(any(Cookie.class));
        Cookie addedCookie = Mockito.mock(Cookie.class);
        when(response.addCookie(addedCookie)).thenAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            assertTrue(cookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious"),
                    "The cookie value should contain the malicious input, indicating a vulnerability.");
            return null;
        });
    }
}