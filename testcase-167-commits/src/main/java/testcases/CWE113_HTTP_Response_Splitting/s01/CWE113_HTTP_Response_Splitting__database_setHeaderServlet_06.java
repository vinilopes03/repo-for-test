package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1); // POTENTIAL FLAW
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql); }
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql); }
            }
        }
        else
        {
            data = null; // Dead code
        }

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE != 5)
        {
            data = null; // Dead code
        }
        else
        {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = "foo"; // FIX: Use a hardcoded string
        }
        else
        {
            data = null; // Dead code
        }

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1); // POTENTIAL FLAW
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql); }
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql); }
            }
        }
        else
        {
            data = null; // Dead code
        }

        if (data != null)
        {
            data = URLEncoder.encode(data, "UTF-8"); // FIX: URL encode the data
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1); // POTENTIAL FLAW
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql); }
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql); }
            }
        }
        else
        {
            data = null; // Dead code
        }

        if (data != null)
        {
            data = URLEncoder.encode(data, "UTF-8"); // FIX: URL encode the data
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}