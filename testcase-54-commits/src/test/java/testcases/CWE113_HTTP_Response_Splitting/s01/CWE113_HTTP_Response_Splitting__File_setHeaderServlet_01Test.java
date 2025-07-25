package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability
     * in the 'bad' method of CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01.
     * It simulates a scenario where malicious input containing CRLF characters is read
     * from a file and directly used in the HTTP response header without proper sanitization.
     * The test passes if the vulnerability is present, i.e., the header is set with unsanitized input.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate reading malicious input from a file
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";
        doAnswer(invocation -> {
            // Simulate setting a header with unsanitized input
            String headerValue = invocation.getArgument(1);
            assertTrue(headerValue.contains("%0D%0A"), "The header value should contain CRLF characters indicating a vulnerability.");
            return null;
        }).when(response).setHeader(eq("Location"), anyString());

        // Act
        servlet.bad(request, response);

        // Assert
        verify(response, times(1)).setHeader(eq("Location"), contains(maliciousInput));
    }
}