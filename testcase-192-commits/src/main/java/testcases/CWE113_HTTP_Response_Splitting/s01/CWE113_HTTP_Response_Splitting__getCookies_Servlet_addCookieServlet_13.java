/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_13.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-13.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: getCookies_Servlet Read data from the first cookie using getCookies()
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 13 Control flow: if(IO.STATIC_FINAL_FIVE==5) and if(IO.STATIC_FINAL_FIVE!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_13 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_FIVE != 5)
        {
            data = null; // This code will never run
        }
        else
        {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods...
}