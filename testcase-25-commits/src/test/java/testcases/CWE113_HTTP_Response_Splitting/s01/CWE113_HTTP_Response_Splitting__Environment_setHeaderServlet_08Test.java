package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern that includes
     * a CRLF sequence. The test checks if this input is directly used in the HTTP header without
     * proper sanitization, which would confirm the vulnerability.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08();

        // Set the environment variable "ADD" to a malicious input
        String maliciousInput = "en-US%0D%0AContent-Length%3A%200%0D%0A%0D%0A";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response header is set with the malicious input, indicating a vulnerability
        verify(response, times(1)).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));
    }
}