package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05Test {

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        // CWE-113: HTTP Response Splitting (robust check, argument capture)
        String maliciousInput = "en-US%0d%0aSet-Cookie:evil=1";
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05 instance = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Mock the environment variable
        try {
            System.setProperty("ADD", maliciousInput);
            instance.bad(request, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred");
        } finally {
            System.clearProperty("ADD");
        }

        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).setHeader(headerCaptor.capture(), valueCaptor.capture());

        String headerValue = valueCaptor.getValue();
        boolean isVulnerable = headerValue.contains("\r") || headerValue.contains("\n") || headerValue.contains("%0d%0a");
        assertTrue(isVulnerable, "Vulnerability fixed or input sanitized: CRLF not propagated to header value");
    }
}