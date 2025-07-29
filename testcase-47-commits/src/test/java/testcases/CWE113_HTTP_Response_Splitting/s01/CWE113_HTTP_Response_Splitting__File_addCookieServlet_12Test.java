package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_12Test {

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        // CWE-113: HTTP Response Splitting (robust check, argument capture)
        String maliciousInput = "en-US%0d%0aSet-Cookie:evil=1";
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_12 instance = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_12();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Simulate the behavior of the bad method
        when(request.getParameter("lang")).thenReturn(maliciousInput);

        try {
            instance.bad(request, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred");
        }

        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response).addCookie(cookieCaptor.capture());
        Cookie added = cookieCaptor.getValue();

        boolean isVulnerable = added.getValue().contains("\r") || added.getValue().contains("\n") || added.getValue().contains("%0d%0a");
        assertTrue(isVulnerable, "Vulnerability fixed or input sanitized: CRLF not propagated to cookie value");
    }
}