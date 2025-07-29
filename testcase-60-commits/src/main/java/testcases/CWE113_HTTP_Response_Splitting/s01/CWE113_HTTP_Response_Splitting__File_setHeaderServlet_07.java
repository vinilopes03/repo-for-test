/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_setHeaderServlet_07.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-07.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 07 Control flow: if(privateFive==5) and if(privateFive!=5)
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

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_07 extends AbstractTestCaseServlet
{
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateFive == 5)
        {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")))
            {
                data = readerBuffered.readLine(); // Read data from file
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }
        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // Vulnerable to HTTP Response Splitting
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // Use hardcoded string
        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // Safe
        }
    }
}