package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_10 extends AbstractTestCaseServlet {
    
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticTrue) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            try {
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine(); // POTENTIAL FLAW
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) readerBuffered.close();
                    if (readerInputStream != null) readerInputStream.close();
                    if (streamFileInput != null) streamFileInput.close();
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing stream", exceptIO);
                }
            }
        } else {
            data = null;
        }

        if (IO.staticTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFalse) {
            data = null;
        } else {
            data = "foo";
        }

        if (IO.staticTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticTrue) {
            data = "foo";
        } else {
            data = null;
        }

        if (IO.staticTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G1
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G2
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}