package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06 extends AbstractTestCaseServlet
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
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature only
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature only
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature only
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature only
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature only
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}