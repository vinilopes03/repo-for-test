package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

/**
 * This test class verifies the presence of an HTTP Response Splitting vulnerability
 * in the CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_05 class.
 * The test will pass if the vulnerability is present, i.e., if unsanitized input
 * is used in the HTTP header, allowing for response splitting.
 */
public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_05Test {

    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_05();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Simulate the behavior of setting a header with unsanitized input
        doAnswer(invocation -> {
            String headerValue = invocation.getArgument(1);
            // Check if the header value contains a newline character, indicating a vulnerability
            assertTrue(headerValue.contains("\n"), "The input is not properly sanitized, allowing HTTP Response Splitting.");
            return null;
        }).when(response).setHeader(anyString(), anyString());

        // Act
        servlet.bad(request, response);
    }
}