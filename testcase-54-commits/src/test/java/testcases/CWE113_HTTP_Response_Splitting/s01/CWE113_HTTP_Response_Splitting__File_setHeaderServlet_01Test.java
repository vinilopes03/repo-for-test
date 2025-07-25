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
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method in CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01
     * is vulnerable to HTTP Response Splitting when unsanitized input is used in HTTP headers.
     * The test will pass if the vulnerability is present, i.e., the header is set with unsanitized input.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en%0D%0ASet-Cookie:%20sessionId=abc123";

        // Mock the behavior of reading from a file
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_01 spyServlet = Mockito.spy(servlet);
        doReturn(maliciousInput).when(spyServlet).readDataFromFile();

        // Act
        spyServlet.bad(request, response);

        // Assert
        // Verify that the header is set with unsanitized input, indicating the vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}