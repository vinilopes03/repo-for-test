package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It sets up a mock environment variable with malicious input and checks if the
     * response header is set without sanitization, indicating the vulnerability.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable with malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('xss')</script>";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the response header is set with the malicious input, indicating the vulnerability
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains(maliciousInput));
    }
}