/*
 * The MIT License
 *
 * Copyright 2014 Glenn McGuire <glennmcguire9@gmail.com> <www.github.com/gmac>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package my.honeypot;

import static com.esotericsoftware.minlog.Log.warn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLInterface {

    public String url = "jdbc:mysql://192.168.0.2:3306/14TOC";
    public String user = "root";
    public String password = "";
    public Connection con = null;
    public ResultSet rs = null;
    public int Counter = 0;
    public int rownum = 0;

    public String[][] SQLSend(String state, int rows) {
        String[][] Ret = null;

        try {

            con = DriverManager.getConnection(url, user, password);
            rs = con.createStatement().executeQuery(state);

            rs.last();
            rownum = rs.getRow();
            rs.beforeFirst();

            Ret = new String[rows][rownum];

            while (rs.next()) {
                for (int i = 0; i < rows; i++) {
                    Ret[i][Counter] = rs.getString(i + 1);
                }
                Counter++;
            }

        } catch (SQLException sex) {
            warn("SQLSend", "Exception Caught executing SQL statement: \n                                       " + state);
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }

                Counter = 0;
                rownum = 0;

            } catch (SQLException ex) {
                warn("SQLSend", "Exception Caught closing connection");
            }
        }

        return Ret;
    }

    public void SQLUpdate(String state) {

        try {

            con = DriverManager.getConnection(url, user, password);
            con.createStatement().execute(state);

        } catch (SQLException ex) {
            warn("SQLUpdate", "Exception Caught executing SQL statement: \n                                       " + state);
        } finally {
            try {
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (Exception ex) {
                warn("SQLInterface", "Exception Caught closing connection");
            }
        }
    }

}
