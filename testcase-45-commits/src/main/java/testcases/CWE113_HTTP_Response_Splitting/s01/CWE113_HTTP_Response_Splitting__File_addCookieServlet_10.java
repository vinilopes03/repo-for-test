package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_10 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = ""; // Initialize data
        
        File file = new File("C:\\data.txt");
        try (FileInputStream streamFileInput = new FileInputStream(file);
             InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
             BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
            
            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = "foo"; // FIX: Use a hardcoded string

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }

    // Placeholders for other good methods
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