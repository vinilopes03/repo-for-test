// ... (header and imports remain unchanged)

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // ... (bad method implementation remains unchanged)
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // ... (goodG2B1 implementation remains unchanged)
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // ... (goodG2B2 implementation remains unchanged)
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")))
            {
                // POTENTIAL FLAW: Read data using an outbound tcp connection
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }
        else
        {
            data = null; // Dead Code
        }

        if (PRIVATE_STATIC_FINAL_FIVE != 5)
        {
            IO.writeLine("Benign, fixed string");
        }
        else
        {
            if (data != null)
            {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")))
            {
                // POTENTIAL FLAW: Read data using an outbound tcp connection
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        }
        else
        {
            data = null; // Dead Code
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}