package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        switch (6)
        {
        case 6:
            data = System.getenv("ADD");
            break;
        default:
            data = null;
            break;
        }

        switch (7)
        {
        case 7:
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
            break;
        default:
            IO.writeLine("Benign, fixed string");
            break;
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature introduced
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature introduced
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature introduced
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature introduced
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature introduced
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}