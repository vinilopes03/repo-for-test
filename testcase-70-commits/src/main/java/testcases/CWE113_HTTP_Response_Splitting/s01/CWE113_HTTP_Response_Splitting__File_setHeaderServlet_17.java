/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-17.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 17 Control flow: for loops
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; // Initialize data
        {
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                // POTENTIAL FLAW: Read data from a file
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }

        for (int j = 0; j < 1; j++) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // FIX: Use a hardcoded string

        for (int j = 0; j < 1; j++) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; // Initialize data
        {
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                // POTENTIAL FLAW: Read data from a file
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }

        for (int k = 0; k < 1; k++) {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}