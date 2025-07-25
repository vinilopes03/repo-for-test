package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_03 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code, won't run but ensures data is initialized
        }

        if (5 == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW: Input not verified
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 != 5) {
            data = null; // Dead code, won't run
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (5 == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW: Input not verified
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5 == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code, won't run but ensures data is initialized
        }

        if (5 == 5) {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}