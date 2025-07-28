package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    void testCWE113_HTTPResponseSplitting() {
        // Test for CWE-113: HTTP Response Splitting
        // Injects CR/LF characters to check if they are included in response headers
        // Test passes if CR/LF is not sanitized (vulnerable)
        // Test fails if CR/LF is sanitized or removed (fixed)
        String maliciousInput = "value\r\nSet-Cookie: malicious=1";
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        instance.bad(null, null); // Trigger the vulnerability by calling the bad method
        String header = instance.setResponseHeader(maliciousInput); // Adjust method name
        assertTrue(header.contains("\r\n"), 
            "Vulnerability present: CR/LF characters not sanitized in response header");
        assertFalse(header.contains("\r\n").equals(""), 
            "Vulnerability fixed: CR/LF characters sanitized or removed");
    }
}