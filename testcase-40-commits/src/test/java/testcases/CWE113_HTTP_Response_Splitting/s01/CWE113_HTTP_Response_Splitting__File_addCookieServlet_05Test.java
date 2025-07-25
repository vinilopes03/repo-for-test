package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_05Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * is added to the cookie, allowing for potential HTTP response splitting.
     * The test will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mocking the behavior of response.addCookie to capture the cookie added
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains unsanitized input
            assertTrue(cookie.getValue().contains("\r\n"), "Vulnerability not present: Input is sanitized or encoded.");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Creating a temporary file with malicious input
        File tempFile = File.createTempFile("data", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("malicious\r\ninput");
        }

        // Replacing the file path in the main class to use the temporary file
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_05 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_05() {
            @Override
            public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
                String data;
                if (privateTrue) {
                    data = ""; /* Initialize data */
                    {
                        File file = tempFile; // Use the temporary file
                        FileInputStream streamFileInput = null;
                        InputStreamReader readerInputStream = null;
                        BufferedReader readerBuffered = null;
                        try {
                            /* read string from file into data */
                            streamFileInput = new FileInputStream(file);
                            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                            readerBuffered = new BufferedReader(readerInputStream);
                            /* POTENTIAL FLAW: Read data from a file */
                            /* This will be reading the first "line" of the file, which
                             * could be very long if there are little or no newlines in the file */
                            data = readerBuffered.readLine();
                        } catch (IOException exceptIO) {
                            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                        } finally {
                            /* Close stream reading objects */
                            try {
                                if (readerBuffered != null) {
                                    readerBuffered.close();
                                }
                            } catch (IOException exceptIO) {
                                IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                            }

                            try {
                                if (readerInputStream != null) {
                                    readerInputStream.close();
                                }
                            } catch (IOException exceptIO) {
                                IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                            }

                            try {
                                if (streamFileInput != null) {
                                    streamFileInput.close();
                                }
                            } catch (IOException exceptIO) {
                                IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                            }
                        }
                    }
                } else {
                    /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
                     * but ensure data is inititialized before the Sink to avoid compiler errors */
                    data = null;
                }

                if (privateTrue) {
                    if (data != null) {
                        Cookie cookieSink = new Cookie("lang", data);
                        /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                        response.addCookie(cookieSink);
                    }
                }
            }
        };

        // Execute the bad method to trigger the vulnerability
        servlet.bad(request, response);

        // Clean up the temporary file
        tempFile.delete();
    }
}