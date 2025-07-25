package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_15 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        switch (6) {
        case 6:
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            break;
        default:
            data = null;
            break;
        }

        switch (7) {
        case 7:
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
            break;
        default:
            IO.writeLine("Benign, fixed string");
            break;
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        switch (5) {
        default:
            data = "foo"; // FIX: Use a hardcoded string
            break;
        }

        switch (7) {
        case 7:
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
            break;
        default:
            IO.writeLine("Benign, fixed string");
            break;
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        switch (6) {
        case 6:
            data = "foo"; // FIX: Use a hardcoded string
            break;
        default:
            data = null;
            break;
        }

        switch (7) {
        case 7:
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
            break;
        default:
            IO.writeLine("Benign, fixed string");
            break;
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        switch (6) {
        case 6:
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            break;
        default:
            data = null;
            break;
        }

        switch (8) {
        default:
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
            break;
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        switch (6) {
        case 6:
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            break;
        default:
            data = null;
            break;
        }

        switch (7) {
        case 7:
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
            break;
        default:
            IO.writeLine("Benign, fixed string");
            break;
        }
    }

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