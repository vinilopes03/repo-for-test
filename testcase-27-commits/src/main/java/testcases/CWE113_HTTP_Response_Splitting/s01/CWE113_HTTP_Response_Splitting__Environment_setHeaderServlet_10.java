/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-10.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 10 Control flow: if(IO.staticTrue) and if(IO.staticFalse)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + URLEncoder.encode(data, "UTF-8"));
            }
        }
    }

    /* goodG2B1() - use goodsource and badsink by changing first IO.staticTrue to IO.staticFalse */
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticFalse)
        {
            data = null;
        }
        else
        {
            data = "foo";
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodG2B2() - use goodsource and badsink by reversing statements in first if */
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            data = "foo";
        }
        else
        {
            data = null;
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodB2G1() - use badsource and goodsink by changing second IO.staticTrue to IO.staticFalse */
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (IO.staticFalse)
        {
            IO.writeLine("Benign, fixed string");
        }
        else
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodB2G2() - use badsource and goodsink by reversing statements in second if  */
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}