package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains a newline character, indicating a vulnerability
            return cookie.getValue().contains("\n");
        }));
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.good(request, response);

        // Assert
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains a newline character, indicating a vulnerability
            return cookie.getValue().contains("\n");
        }));
    }
}