package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is used in the HTTP header, leading to potential
     * HTTP Response Splitting.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0D%0AContent-Length:%200";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with unsanitized input
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains(maliciousInput));

        // Assert that the test passes, indicating the vulnerability exists
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}