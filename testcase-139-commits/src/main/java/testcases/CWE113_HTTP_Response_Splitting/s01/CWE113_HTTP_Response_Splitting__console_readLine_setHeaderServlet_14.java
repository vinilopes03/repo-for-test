/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_14.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-14.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: console_readLine Read data from the console using readLine()
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 14 Control flow: if(IO.staticFive==5) and if(IO.staticFive!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.logging.Level;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_14 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticFive==5)
        {
            data = ""; /* Initialize data */
            {
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;
                /* read user input from console with readLine */
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
                    try
                    {
                        if (readerBuffered != null)
                        {
                            readerBuffered.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                    }

                    try
                    {
                        if (readerInputStream != null)
                        {
                            readerInputStream.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                    }
                }
            }
        }
        else
        {
            data = null;
        }

        if (IO.staticFive==5)
        {
            if (data != null)
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_14() {}
}