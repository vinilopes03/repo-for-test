package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_09 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = ""; // Initialize data
        File file = new File("C:\\data.txt");
        try (BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(java.util.logging.Level.WARNING, "Error with stream reading", exceptIO);
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }

    // Other methods
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}