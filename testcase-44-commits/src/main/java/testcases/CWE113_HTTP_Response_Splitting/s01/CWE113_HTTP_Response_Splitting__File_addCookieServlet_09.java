package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_09 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implemented in previous commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implemented in previous commit
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implemented in previous commit
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = ""; // Initialize data
        File file = new File("C:\\data.txt");
        try (BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(java.util.logging.Level.WARNING, "Error with stream reading", exceptIO);
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            response.addCookie(cookieSink);
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = ""; // Initialize data
        File file = new File("C:\\data.txt");
        try (BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(java.util.logging.Level.WARNING, "Error with stream reading", exceptIO);
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            response.addCookie(cookieSink);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}