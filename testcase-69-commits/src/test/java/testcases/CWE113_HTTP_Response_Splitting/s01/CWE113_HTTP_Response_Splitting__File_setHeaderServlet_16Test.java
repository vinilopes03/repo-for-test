package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It uses a mock HttpServletResponse to capture the header set by the 'bad' method.
     * The test passes if the vulnerability is present, i.e., the malicious input is not sanitized.
     * The test fails if the input is properly sanitized or encoded.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0D%0AContent-Length:%200";

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header was set with the malicious input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}