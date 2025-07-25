package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It uses a mock HttpServletResponse to capture the header set by the 'bad' method.
     * The test passes if the malicious input is not sanitized, confirming the vulnerability.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('xss')</script>";
        doReturn(maliciousInput).when(response).getHeader("Location");

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the header contains unsanitized input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}