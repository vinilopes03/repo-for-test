package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and can cause HTTP response splitting.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('xss')</script>";
        when(request.getParameter("lang")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response header is set with the malicious input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}