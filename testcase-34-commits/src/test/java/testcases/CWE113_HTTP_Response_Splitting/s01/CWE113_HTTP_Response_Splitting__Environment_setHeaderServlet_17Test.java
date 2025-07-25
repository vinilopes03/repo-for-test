package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP header, leading to response splitting.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17();

        // Invoking the 'bad' method which is suspected to have the vulnerability
        servlet.bad(request, response);

        // Verifying if the setHeader method was called with unsanitized input
        Mockito.verify(response).setHeader(Mockito.eq("Location"),
                Mockito.contains(maliciousInput));

        // If the setHeader method is called with unsanitized input, the test should pass
        assertTrue(true, "The HTTP Response Splitting vulnerability exists as unsanitized input was used.");
    }
}