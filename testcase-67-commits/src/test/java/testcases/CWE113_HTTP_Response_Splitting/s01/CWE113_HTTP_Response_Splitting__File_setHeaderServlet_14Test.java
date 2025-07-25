package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_14Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP response header.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_14 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_14();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could exploit HTTP Response Splitting
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";

        // Mock the static variable to ensure the control flow enters the vulnerable path
        IO.staticFive = 5;

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the setHeader method was called with unsanitized input
        verify(response, times(1)).setHeader(eq("Location"), contains(maliciousInput));
    }
}