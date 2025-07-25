package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_03 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 == 5) { // Always true
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // INCIDENTAL: Dead code
        }

        if (5 == 5) { // Always true
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 != 5) {
            data = null; // INCIDENTAL: Dead code
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (5 == 5) { // Always true
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 == 5) { // Always true
            data = "foo"; // FIX: Use a hardcoded string
        } else {
            data = null; // INCIDENTAL: Dead code
        }

        if (5 == 5) { // Always true
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods...
}