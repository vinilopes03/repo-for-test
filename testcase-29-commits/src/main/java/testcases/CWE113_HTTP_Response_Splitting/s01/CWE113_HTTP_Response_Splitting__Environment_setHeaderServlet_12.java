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
            // POTENTIAL FLAW: Read data from an environment variable
            data = System.getenv("ADD");
        }
        else
        {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if(IO.staticReturnsTrueOrFalse())
        {
            if (data != null)
            {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
        else
        {
            // Placeholder for other processing
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