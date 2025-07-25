package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_12Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input containing CRLF characters is used
     * in the HTTP header, which could lead to HTTP Response Splitting attacks.
     * The test will PASS if the vulnerability exists, i.e., the malicious input is not sanitized.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_12 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_12();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Simulate the condition where the bad source is used
        Mockito.when(IO.staticReturnsTrueOrFalse()).thenReturn(true);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response header is set with unsanitized data
        // This will pass if the vulnerability exists (i.e., the input is not sanitized)
        verify(response).setHeader(eq("Location"), eq("/author.jsp?lang=" + "maliciousInput%0D%0ASet-Cookie:sessionId=malicious"));
    }
}