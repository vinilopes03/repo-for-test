package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.AbstractTestCaseServlet;
import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // ... (same as previous commit)
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // ... (same as previous commit)
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = "";
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                // POTENTIAL FLAW: Read data using an outbound TCP connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = "";
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                // POTENTIAL FLAW: Read data using an outbound TCP connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = java.net.URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        } else {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = java.net.URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}