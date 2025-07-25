package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        switch (6)
        {
        case 6:
            data = System.getenv("ADD"); // POTENTIAL FLAW: Read data from an environment variable
            break;
        default:
            data = null; // INCIDENTAL: Dead Code
            break;
        }

        switch (7)
        {
        case 7:
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            }
            break;
        default:
            IO.writeLine("Benign, fixed string"); // INCIDENTAL: Dead Code
            break;
        }
    }
    
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added in subsequent commits
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added in subsequent commits
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added in subsequent commits
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added in subsequent commits
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