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
     * This test verifies if the 'bad' method in the CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01
     * class is vulnerable to HTTP Response Splitting by checking if unsanitized input can be injected into the HTTP header.
     * The test will pass if the vulnerability exists (i.e., the input is not sanitized).
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that includes a CRLF sequence
        String maliciousInput = "en\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of the response to capture the header set
        doAnswer(invocation -> {
            String headerName = invocation.getArgument(0);
            String headerValue = invocation.getArgument(1);
            // Assert that the header value contains the malicious input, indicating a vulnerability
            assertTrue(headerValue.contains(maliciousInput), "The input was not sanitized, indicating a vulnerability.");
            return null;
        }).when(response).setHeader(anyString(), anyString());

        // Act
        servlet.bad(request, response);

        // Verify that setHeader was called, indicating that the test executed the path we intended
        verify(response, times(1)).setHeader(anyString(), anyString());
    }
}