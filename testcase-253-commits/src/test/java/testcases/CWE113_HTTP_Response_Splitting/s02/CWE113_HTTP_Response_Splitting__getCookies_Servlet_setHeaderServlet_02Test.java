package testcases.CWE113_HTTP_Response_Splitting.s02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_02Test {

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        // CWE-113: HTTP Response Splitting (robust check, argument capture)
        String maliciousInput = "en-US%0d%0aSet-Cookie:evil=1";
        CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_02 instance = new CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_02();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Cookie cookie = new Cookie("lang", maliciousInput);
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        try {
            instance.bad(request, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred");
        }

        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).setHeader(eq("Location"), headerCaptor.capture());
        String headerValue = headerCaptor.getValue();

        boolean isVulnerable = headerValue.contains("\r") || headerValue.contains("\n") || headerValue.contains("%0d%0a");
        assertTrue(isVulnerable, "Vulnerability fixed or input sanitized: CRLF not propagated to header value");
    }
}