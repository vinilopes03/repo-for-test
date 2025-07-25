package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., the input is not sanitized.
     * It uses a mock HttpServletResponse to capture the header set by the servlet.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate environment variable with malicious input
        String maliciousInput = "en-US%0D%0AContent-Length%3A%200%0D%0A%0D%0A<script>alert('xss')</script>";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header was set with unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}