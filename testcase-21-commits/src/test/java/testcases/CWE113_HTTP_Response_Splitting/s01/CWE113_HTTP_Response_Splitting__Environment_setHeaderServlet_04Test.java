package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern
     * and checking if the response header is set without proper sanitization.
     * The test will pass if the vulnerability exists (i.e., the header is set with unsanitized input).
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable with malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200";
        System.setProperty("ADD", maliciousInput);

        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04();

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header is set with the unsanitized malicious input
        verify(response, times(1)).setHeader("Location", "/author.jsp?lang=" + maliciousInput);
    }
}