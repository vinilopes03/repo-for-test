/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_addCookieServlet_03.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-03.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 03 Control flow: if(5==5) and if(5!=5)
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

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_03 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (5==5)
        {
            data = ""; /* Initialize data */
            {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = null;
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;
                try
                {
                    /* read string from file into data */
                    streamFileInput = new FileInputStream(file);
                    readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);
                    data = readerBuffered.readLine();
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                }
                finally
                {
                    /* Close stream reading objects */
                    try { if (readerBuffered != null) readerBuffered.close(); } catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO); }
                    try { if (readerInputStream != null) readerInputStream.close(); } catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO); }
                    try { if (streamFileInput != null) streamFileInput.close(); } catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO); }
                }
            }
        }
        else
        {
            data = null;
        }

        if (5==5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw
            }
        }
    }

    /* goodG2B1() - use goodsource and badsink by changing first 5==5 to 5!=5 */
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (5!=5)
        {
            data = null;
        }
        else
        {
            data = "foo"; // Hardcoded string
        }

        if (5==5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation to follow
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}