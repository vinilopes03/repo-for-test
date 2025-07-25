package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern
     * and checking if the response header is set without proper sanitization.
     * The test will pass if the vulnerability exists (i.e., the header is set with unsanitized input).
     */
    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable with a malicious input pattern
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify if the response header is set with unsanitized input
        verify(response).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));
    }
}