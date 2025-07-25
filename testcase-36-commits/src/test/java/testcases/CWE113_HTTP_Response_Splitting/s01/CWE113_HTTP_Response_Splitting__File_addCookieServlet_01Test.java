package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_01Test {

    /**
     * Test method to verify HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists (i.e., unsanitized input is added to the cookie).
     * It will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testBad() {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_01();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        try {
            servlet.bad(request, response);
        } catch (Throwable throwable) {
            fail("Exception thrown during test execution: " + throwable.getMessage());
        }

        // Assert
        // Verify if a cookie with unsanitized input is added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains unsanitized input
            return cookie.getValue().contains("\r\n");
        }));
    }
}