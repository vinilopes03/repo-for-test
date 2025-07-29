public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    // ... (bad method and good methods here)

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}