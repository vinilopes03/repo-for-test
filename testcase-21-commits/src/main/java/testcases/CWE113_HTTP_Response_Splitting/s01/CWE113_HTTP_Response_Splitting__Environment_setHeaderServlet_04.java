/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-04.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 04 Control flow: if(PRIVATE_STATIC_FINAL_TRUE) and if(PRIVATE_STATIC_FINAL_FALSE)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04 extends AbstractTestCaseServlet
{
    private static final boolean PRIVATE_STATIC_FINAL_TRUE = true;
    private static final boolean PRIVATE_STATIC_FINAL_FALSE = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FALSE)
        {
            data = null; // This code is not reachable
        }
        else
        {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (PRIVATE_STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        // Other good methods will be added in later commits
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}