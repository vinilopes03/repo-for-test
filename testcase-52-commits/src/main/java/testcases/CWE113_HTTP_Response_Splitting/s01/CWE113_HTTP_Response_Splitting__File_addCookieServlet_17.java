/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_addCookieServlet_17.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-17.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 17 Control flow: for loops
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_17 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method stub for bad case
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method stub for goodG2B case
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method stub for goodB2G case
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}

public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    data = ""; // Initialize data
    {
        File file = new File("C:\\data.txt");
        FileInputStream streamFileInput = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;

        try {
            streamFileInput = new FileInputStream(file);
            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        }
        catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
        finally {
            try { if (readerBuffered != null) readerBuffered.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO); }
            try { if (readerInputStream != null) readerInputStream.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO); }
            try { if (streamFileInput != null) streamFileInput.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO); }
        }
    }

    for (int j = 0; j < 1; j++) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }
}

private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    // FIX: Use a hardcoded string
    data = "foo";

    for (int j = 0; j < 1; j++) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }
}

private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    data = ""; // Initialize data
    {
        File file = new File("C:\\data.txt");
        FileInputStream streamFileInput = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;

        try {
            streamFileInput = new FileInputStream(file);
            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        }
        catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
        finally {
            try { if (readerBuffered != null) readerBuffered.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO); }
            try { if (readerInputStream != null) readerInputStream.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO); }
            try { if (streamFileInput != null) streamFileInput.close(); }
            catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO); }
        }
    }

    for (int k = 0; k < 1; k++) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            response.addCookie(cookieSink);
        }
    }
}

public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    goodG2B(request, response);
    goodB2G(request, response);
}

public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    mainFromParent(args);
}