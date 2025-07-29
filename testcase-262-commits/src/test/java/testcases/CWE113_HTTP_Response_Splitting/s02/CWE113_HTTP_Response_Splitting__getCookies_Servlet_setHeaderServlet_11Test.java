package testcases.CWE113_HTTP_Response_Splitting.s02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_11Test {

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        // CWE-113: HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aSet-Cookie:evil=1";
        CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_11 instance = new CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Cookie cookie = new Cookie("lang", maliciousInput);
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        try {
            instance.bad(request, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred");
        }

        // Verify that the header was set with potentially malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
        
        // Check if the response header contains CRLF
        boolean isVulnerable = response.getHeader("Location").contains("\r") || response.getHeader("Location").contains("\n");
        assertTrue(isVulnerable, "Vulnerability fixed or input sanitized: CRLF not propagated to header");
    }
}