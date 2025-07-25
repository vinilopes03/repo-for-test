package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows HTTP response splitting through unsanitized input.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        // Simulate the bad method which is vulnerable to HTTP Response Splitting
        servlet.bad(request, response);

        // Assert
        // Verify that the response header was set with unsanitized input
        // This will pass if the vulnerability exists
        verify(response, times(1)).setHeader(eq("Location"), contains("\r\n"));
    }
}