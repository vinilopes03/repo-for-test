import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    // bad method implementation remains unchanged

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsFalse()) {
            data = null; // Dead code, but ensures data is initialized
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (IO.staticReturnsTrue()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrue()) {
            data = "foo"; // FIX: Use a hardcoded string
        } else {
            data = null; // Dead code, but ensures data is initialized
        }

        if (IO.staticReturnsTrue()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }
    // Other methods remain unchanged for now
}