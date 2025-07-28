package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    void testCWE113_HTTPResponseSplitting() {
        // CWE-113: HTTP Response Splitting
        // Inject CRLF (%0d%0a) in input. If it's reflected in headers unescaped, vulnerability is present.
        String maliciousInput = "en-US%0d%0aSet-Cookie: evil=1";
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        try {
            String result = instance.setResponseHeader(maliciousInput);

            boolean isVulnerable = result.contains("\r\n") || result.contains("%0d%0a");
            assertTrue(isVulnerable, "Vulnerability present: CRLF was not sanitized from input.");
        } catch (Exception e) {
            fail("Vulnerability fixed or not triggered: Exception occurred or CRLF was sanitized.");
        }
    }
}