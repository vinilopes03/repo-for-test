/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_06.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-06.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: console_readLine Read data from the console using readLine()
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 06 Control flow: if(PRIVATE_STATIC_FINAL_FIVE==5) and if(PRIVATE_STATIC_FINAL_FIVE!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "";
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            InputStreamReader readerInputStream = new InputStreamReader(System.in, "UTF-8");
            BufferedReader readerBuffered = new BufferedReader(readerInputStream);
            try
            {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from the console
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // BAD SINK
        }
    }
    
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // FIX: Use a hardcoded string
        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // GOOD SINK
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}