public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    // ... (bad method here)

    /* goodG2B1() - use goodsource and badsink by changing first IO.staticReturnsTrue() to IO.staticReturnsFalse() */
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsFalse()) 
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            data = null;
        } 
        else 
        {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (IO.staticReturnsTrue()) 
        {
            if (data != null) 
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodG2B2() - use goodsource and badsink by reversing statements in first if */
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        if (IO.staticReturnsTrue()) 
        {
            /* FIX: Use a hardcoded string */
            data = "foo";
        } 
        else 
        {
            data = null; // Initialize to avoid compiler error
        }

        if (IO.staticReturnsTrue()) 
        {
            if (data != null) 
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }
}