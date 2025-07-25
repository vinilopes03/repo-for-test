package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17Test {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws Exception {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.clearProperty("ADD");
    }

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test sets an environment variable with malicious input and checks if it
     * is directly used in the HTTP header without sanitization.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with malicious input
        System.setProperty("ADD", "en-US%0d%0aContent-Length:%200");

        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17();

        // Invoke the vulnerable method
        servlet.bad(request, response);

        // Verify that the response contains the unsanitized input
        String headerValue = responseWriter.toString();
        assertTrue(headerValue.contains("en-US\r\nContent-Length: 0"),
                "The response header should contain unsanitized input, indicating a vulnerability.");
    }
}