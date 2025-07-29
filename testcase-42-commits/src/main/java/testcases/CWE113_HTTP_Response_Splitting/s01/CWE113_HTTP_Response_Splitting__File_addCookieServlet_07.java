/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_addCookieServlet_07.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-07.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
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

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_07 extends AbstractTestCaseServlet
{
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateFive == 5)
        {
            data = ""; /* Initialize data */
            {
                File file = new File("C:\\data.txt");
                try (FileInputStream streamFileInput = new FileInputStream(file);
                     InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                     BufferedReader readerBuffered = new BufferedReader(readerInputStream))
                {
                    /* POTENTIAL FLAW: Read data from a file */
                    data = readerBuffered.readLine();
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                }
            }
        }
        else
        {
            data = null;
        }

        if (privateFive == 5)
        {
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
        // Method implementation will be added in later commits
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}