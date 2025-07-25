package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            // Read data from an environment variable
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // This will never run
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Potential flaw: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for the good implementation
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}