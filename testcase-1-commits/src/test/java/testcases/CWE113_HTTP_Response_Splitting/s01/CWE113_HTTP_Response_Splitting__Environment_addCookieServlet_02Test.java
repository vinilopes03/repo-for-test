package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    void testCWE113_HTTPResponseSplitting() {
        // CWE-113: HTTP Response Splitting
        // Inject CRLF (%0d%0a) in input. If it's reflected in headers unescaped, vulnerability is present.
        String maliciousInput = "en-US%0d%0aSet-Cookie: evil=1";
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable to simulate untrusted input
        System.setProperty("ADD", maliciousInput);

        try {
            instance.bad(request, response);

            // Verify if the response.addCookie was called with a Cookie containing CRLF
            Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
                cookie.getValue().contains("\r\n") || cookie.getValue().contains("%0d%0a")
            ));

            // If the above verification passes, it means the vulnerability is present
            assertTrue(true, "Vulnerability present: CRLF was not sanitized from input.");
        } catch (Exception e) {
            fail("Vulnerability fixed or not triggered: Exception occurred or CRLF was sanitized.");
        } finally {
            // Clean up the environment variable
            System.clearProperty("ADD");
        }
    }
}