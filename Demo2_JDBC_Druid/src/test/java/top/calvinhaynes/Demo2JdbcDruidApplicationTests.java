package top.calvinhaynes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试类
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
@SpringBootTest
class Demo2JdbcDruidApplicationTests {

    //注入数据源
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        //查看默认的数据源
        System.out.println(dataSource.getClass());
        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //关闭连接
        connection.close();
    }

}
