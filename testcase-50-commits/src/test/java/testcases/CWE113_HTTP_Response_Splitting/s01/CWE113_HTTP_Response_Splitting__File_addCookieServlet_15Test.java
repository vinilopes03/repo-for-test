package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This JUnit 5 test class verifies the presence of an HTTP Response Splitting vulnerability
 * in the `bad` method of the `CWE113_HTTP_Response_Splitting__File_addCookieServlet_15` class.
 * The test will pass if the vulnerability is present (i.e., unsanitized input is added to the cookie),
 * and fail if the input is properly sanitized or encoded.
 */
public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_15Test {

    @Test
    public void testBadMethodForHttpResponseSplitting() {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_15 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_15();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        try {
            Mockito.when(response.getWriter()).thenReturn(printWriter);
        } catch (Exception e) {
            fail("Failed to mock response writer: " + e.getMessage());
        }

        // Act
        try {
            servlet.bad(request, response);
        } catch (Throwable throwable) {
            fail("Exception thrown during test execution: " + throwable.getMessage());
        }

        // Assert
        // Check if the response contains a cookie with unsanitized input
        Cookie[] cookies = response.getCookies();
        boolean vulnerabilityExists = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lang".equals(cookie.getName()) && cookie.getValue().contains("\r\n")) {
                    vulnerabilityExists = true;
                    break;
                }
            }
        }

        // The test should pass if the vulnerability exists
        assertTrue(vulnerabilityExists, "The HTTP Response Splitting vulnerability should exist.");
    }
}