package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.AbstractTestCaseServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_12 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                // POTENTIAL FLAW: Read data using an outbound TCP connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = "foo"; // Use a hardcoded string
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method stub
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method stub
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}