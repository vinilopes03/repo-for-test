package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_07 extends AbstractTestCaseServlet {
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = "";
            Socket socket = null;
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;
            try {
                socket = new Socket("host.example.org", 39544);
                readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) readerBuffered.close();
                    if (readerInputStream != null) readerInputStream.close();
                    if (socket != null) socket.close();
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing resources", exceptIO);
                }
            }
        } else {
            data = null;
        }

        if (privateFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    // Method signatures for good methods
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}