package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = ""; // Initialize data
            // Read data using an outbound tcp connection
        }
        else
        {
            data = null; // Ensure data is initialized
        }

        if(IO.staticReturnsTrue())
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsFalse())
        {
            data = null; // Dead code
        }
        else
        {
            data = "foo"; // Use a hardcoded string
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = "foo"; // Use a hardcoded string
        }
        else
        {
            data = null; // Dead code
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = ""; // Initialize data
            // Read data using an outbound tcp connection
        }
        else
        {
            data = null; // Dead code
        }

        if (IO.staticReturnsFalse())
        {
            IO.writeLine("Benign, fixed string"); // Dead code
        }
        else
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8"); // Use URLEncoder.encode
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = ""; // Initialize data
            // Read data using an outbound tcp connection
        }
        else
        {
            data = null; // Dead code
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8"); // Use URLEncoder.encode
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