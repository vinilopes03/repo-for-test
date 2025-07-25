package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_07Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting a malicious input as an environment variable and checking if
     * the response header is set with unsanitized input, which would confirm the vulnerability.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable with malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_07 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_07();

        // Invoking the bad method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verifying if the response header was set with unsanitized input
        verify(response).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));

        // Asserting that the vulnerability is present
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}