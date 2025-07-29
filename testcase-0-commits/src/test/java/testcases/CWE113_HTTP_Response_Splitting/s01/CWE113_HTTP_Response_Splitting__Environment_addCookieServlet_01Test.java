package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        // Test for CWE-113: HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aSet-Cookie:evil=1";
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        HttpServletResponse response = mock(HttpServletResponse.class);
        Cookie cookie = new Cookie("lang", maliciousInput);
        when(response.getHeader("Set-Cookie")).thenReturn("lang=" + maliciousInput);

        try {
            instance.bad(null, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred");
        }

        verify(response).addCookie(cookie);
        String header = response.getHeader("Set-Cookie");
        boolean isVulnerable = header != null && (header.contains("\r\n") || header.contains("%0d%0a"));
        assertTrue(isVulnerable, "Vulnerability fixed or not triggered: CRLF was sanitized or not reflected");
    }
}