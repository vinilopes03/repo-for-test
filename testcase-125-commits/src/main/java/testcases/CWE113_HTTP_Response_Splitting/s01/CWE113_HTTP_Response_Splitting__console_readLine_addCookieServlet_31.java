/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__console_readLine_addCookieServlet_31.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-31.tmpl.java
*/
/*
 * @description
 * CWE: 113 HTTP Response Splitting
 * BadSource: console_readLine Read data from the console using readLine()
 * GoodSource: A hardcoded string
 * Sinks: addCookieServlet
 *    GoodSink: URLEncode input
 *    BadSink : querystring to addCookie()
 * Flow Variant: 31 Data flow: make a copy of data within the same method
 *
 * */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__console_readLine_addCookieServlet_31 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String dataCopy;
        {
            String data = ""; /* Initialize data */
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;

            /* Read user input from console with readLine */
            try
            {
                readerInputStream = new InputStreamReader(System.in, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                /* POTENTIAL FLAW: Read data from the console using readLine */
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                // Closing resources (Not closing System.in)
                try { if (readerBuffered != null) readerBuffered.close(); } catch (IOException e) {}
                try { if (readerInputStream != null) readerInputStream.close(); } catch (IOException e) {}
            }
            dataCopy = data;
        }
        {
            String data = dataCopy;
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Placeholder for good method implementation
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}