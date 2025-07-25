package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_12 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if(IO.staticReturnsTrueOrFalse())
        {
            data = ""; // Initialize data
            // Read data using an outbound tcp connection
            {
                Socket socket = null;
                BufferedReader readerBuffered = null;
                InputStreamReader readerInputStream = null;
                try
                {
                    // Read data using an outbound tcp connection
                    socket = new Socket("host.example.org", 39544);
                    // Read input from socket
                    readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);
                    // POTENTIAL FLAW: Read data using an outbound tcp connection
                    data = readerBuffered.readLine();
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                }
                finally
                {
                    // Clean up stream reading objects
                    try
                    {
                        if (readerBuffered != null)
                        {
                            readerBuffered.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                    }

                    try
                    {
                        if (readerInputStream != null)
                        {
                            readerInputStream.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                    }

                    // Clean up socket objects
                    try
                    {
                        if (socket != null)
                        {
                            socket.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing Socket", exceptIO);
                    }
                }
            }
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
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
        else
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method remains unimplemented for now
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method remains unimplemented for now
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method remains unimplemented for now
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}