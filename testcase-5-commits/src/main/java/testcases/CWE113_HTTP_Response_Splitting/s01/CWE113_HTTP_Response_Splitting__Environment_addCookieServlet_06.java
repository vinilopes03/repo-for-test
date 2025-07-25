// Commit 3
package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE != 5)
        {
            data = null;
        }
        else
        {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = "foo"; // FIX: Use a hardcoded string
        }
        else
        {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method stub
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method stub
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method stub
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}