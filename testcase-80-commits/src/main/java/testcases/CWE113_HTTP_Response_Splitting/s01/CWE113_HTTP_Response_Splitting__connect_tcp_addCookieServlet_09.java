package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_09 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            // Placeholder for bad source
            data = "";
        }
        else
        {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Placeholder for bad sink
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_FALSE)
        {
            data = null;
        }
        else
        {
            data = "foo";
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Placeholder for bad sink
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            data = "foo";
        }
        else
        {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Placeholder for bad sink
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            data = "";
        }
        else
        {
            data = null;
        }

        if (IO.STATIC_FINAL_FALSE)
        {
            IO.writeLine("Benign, fixed string");
        }
        else
        {
            if (data != null)
            {
                // Placeholder for good sink
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            data = "";
        }
        else
        {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Placeholder for good sink
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

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}