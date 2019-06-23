package TestNGtest.dataProdriver;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlTest {
    @Test
    public void test(){
        //声明 MySQL 数据库的驱动
        String mysqlDriver = "com.mysql.jdbc.Driver";
        //声明数据库IP 地址/端口号和数据库名称
        String url = "jdbc:mysql://127.0.0.1:3306/testData";
        //声明数据库的账号，密码
        String user = "root";
        String password = "quhanqing3113";
        //声明存储测试数据的 List 对象
        List<Object[]> records = new ArrayList<Object[]>();
        try {
            //设定驱动
            Class.forName(mysqlDriver);
            //声明连接数据库的链接对象，使用数据库服务器地址、用户名、密码作为参数
            Connection conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed())
                System.out.println("连接数据库成功");
            //创建 statement 对象
            Statement statement = conn.createStatement();
            //构造sql 查询语句
            String sql = "select * from user";
            //声明 ResultSet 对象，用来存储 sql 语句执行后返回的结果集
            ResultSet rs = statement.executeQuery(sql);
            //声明一个 ResultSetMetaData 对象
            ResultSetMetaData rsMetaData = rs.getMetaData();
            //调用ResultSetMetaData对象的 getColumnCount 方法获取结果集的列数
            int cols = rsMetaData.getColumnCount();
            //使用 next 方法遍历结果集中的所有数据
            while(rs.next()) {
                //声明一个数组，数组的大小使用结果集的列数声明
                String fields[] = new String[cols];
                int col = 0;
                //遍历所有数据行中的所有列数据，并储存在数组中
                for(int colIdex = 0; colIdex < cols; colIdex++) {
                    fields[col] = rs.getString(colIdex + 1);
                    col++;
                }
                //将数组存储到 records 中
                records.add(fields);
//                System.out.println(rs.getString(1) );
            }
            //关闭结果集对象
            rs.close();
            //关闭数据库连接
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }


        //定义方法返回值，Object[][],将 records  list 转换为一个 Object 的二维数组
        Object[][] results = new Object[records.size()][];
        //设置二维数组每行的值
        for(int i = 0; i<records.size();i++) {
            results[i] = records.get(i);
            System.out.println(results[i][0]);
            System.out.println(results[i][1]);
        }
    }
}
