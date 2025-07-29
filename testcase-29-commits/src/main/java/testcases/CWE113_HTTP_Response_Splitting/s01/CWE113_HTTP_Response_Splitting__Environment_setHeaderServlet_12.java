/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-12.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 12 Control flow: if(IO.staticReturnsTrueOrFalse())
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if(IO.staticReturnsTrueOrFalse())
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = "foo"; // Default value for other branch
        }

        if(IO.staticReturnsTrueOrFalse())
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8"); // Fixed Sink
                response.setHeader("Location", "/author.jsp?lang=" + data); // Now a Good Sink
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if(IO.staticReturnsTrueOrFalse())
        {
            data = "foo"; // Good source
        }
        else
        {
            data = "foo";
        }

        if(IO.staticReturnsTrueOrFalse())
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Still a Bad Sink
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if(IO.staticReturnsTrueOrFalse())
        {
            data = System.getenv("ADD"); // Bad Source
        }
        else
        {
            data = System.getenv("ADD");
        }

        if(IO.staticReturnsTrueOrFalse())
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8"); // Good Sink
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