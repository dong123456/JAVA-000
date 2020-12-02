import java.sql.*;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;   //需要这个jar包 mysql-connector-java-5.1.49.jar

import java.util.*;

public class InsertMysqlTest {

    public static Connection getConnection() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/shop"; //换成要连接的数据库信息
        String user = "root";
        String password = "";

        Connection conn = null;

        try {
            conn = (Connection) DriverManager.getConnection ( url, user, password );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void insertTest1() {

        Long startTime = System.currentTimeMillis();
        try {
            Connection conn = getConnection();
            if (!conn.isClosed ()) {
                System.out.println ( "数据库连接成功：" );

                Statement stmt = conn.createStatement();
                int i = 1;
                while (i<=1000000) {
                    String orderNo = UUID.randomUUID().toString().replaceAll("-","");

                    String sql = "insert into orders(order_no, user_id, order_amount, shipping_amount, pay_amount)  " +
                            "values ('"+ orderNo  +"', 1, 100, 0, 100)";


                    stmt.executeUpdate(sql);
                    i++;

                }
                Long endTime = System.currentTimeMillis();

                Long timeElapse = endTime - startTime;
                System.out.println ( "数据更新完成,耗时 " + timeElapse );

                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void insertTest2() {

        Long startTime = System.currentTimeMillis();
        try {
            Connection conn = getConnection();
            if (!conn.isClosed ()) {
                System.out.println ( "数据库连接成功：" );

                Statement stmt = conn.createStatement();
                int i = 1;

                String sqlTop = "insert into orders(order_no, user_id, order_amount, shipping_amount, pay_amount) values ";

                StringBuilder sql = new StringBuilder();
                while (i<=1000000) {
                    String orderNo = UUID.randomUUID().toString().replaceAll("-","");

                    if (i % 10000 == 0) {

                        sql.append("('"+ orderNo  +"', 1, 100, 0, 100)");
                        System.out.println ( i );
                        stmt.executeUpdate(sqlTop + sql.toString());
                        sql.setLength(0);

                    } else {
                        sql.append("('"+ orderNo  +"', 1, 100, 0, 100),");
                    }
                /*    sql.append("insert into orders(order_no, user_id, order_amount, shipping_amount, pay_amount)  " +
                            "values");


                            "insert into orders(order_no, user_id, order_amount, shipping_amount, pay_amount)  " +
                            "values ('"+ orderNo  +"', 1, 100, 0, 100)";*/
                    i++;

                }
                Long endTime = System.currentTimeMillis();

                Long timeElapse = endTime - startTime;
                System.out.println ( "数据更新完成,耗时 " + timeElapse );

                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        /*
         *  全部单条插入，共花费213秒
         */

        //insertTest1();

        /*
         * 每1w条插入一次，共花费18秒
         */
        insertTest2();
    }
}