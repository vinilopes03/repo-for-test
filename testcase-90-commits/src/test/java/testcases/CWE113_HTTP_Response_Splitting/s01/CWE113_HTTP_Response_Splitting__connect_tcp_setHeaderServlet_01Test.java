package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01Test {

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method is vulnerable to HTTP Response Splitting
     * by checking if it allows unsanitized input (e.g., newline characters) to be included
     * in the HTTP header, which could lead to header injection.
     */
    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that includes newline characters
        String maliciousInput = "en\nContent-Length: 0\n\n";

        // Mock the behavior of the response to capture the header set
        doAnswer(invocation -> {
            String headerName = invocation.getArgument(0);
            String headerValue = invocation.getArgument(1);
            // Assert that the header value contains the malicious input
            assertTrue(headerValue.contains(maliciousInput), "Vulnerability exists: HTTP Response Splitting possible");
            return null;
        }).when(response).setHeader(anyString(), anyString());

        // Act
        servlet.bad(request, response);

        // Verify that setHeader was called, indicating the vulnerability was triggered
        verify(response, times(1)).setHeader(anyString(), anyString());
    }
}