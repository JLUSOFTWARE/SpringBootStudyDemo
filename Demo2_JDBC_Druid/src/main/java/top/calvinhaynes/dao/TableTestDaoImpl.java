package top.calvinhaynes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.calvinhaynes.pojo.TableTest;
import top.calvinhaynes.utils.JsonResult;

import java.util.List;
import java.util.Map;


/**
 * dao接口的实现类
 *
 * @author CalvinHaynes
 * @date 2021 /09/07
 */
@Repository
public class TableTestDaoImpl implements TableTestDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 构造器注入JDBC
     *
     * @param jdbcTemplate jdbc模板
     */
    @Autowired
    public TableTestDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加
     *
     * @param tableTest 表测试
     * @return int
     */
    @Override
    public int add(TableTest tableTest) {
        return jdbcTemplate.update("insert into spring_boot_test.table_test(id, name, level, create_time, delete_flag, extension) values(?, ?, ?, ?, ?, ?)", tableTest.getId(), tableTest.getName(), tableTest.getLevel(), tableTest.getCreateTime(), tableTest.getDeleteFlag(), tableTest.getExtension());
    }

    /**
     * 更新
     *
     * @param tableTest 表测试
     * @return int
     */
    @Override
    public int update(TableTest tableTest) {
        return jdbcTemplate.update("update spring_boot_test.table_test set name=? ,level=? ,create_time=? ,delete_flag=? ,extension=? where id=?", tableTest.getName(), tableTest.getLevel(), tableTest.getCreateTime(), tableTest.getDeleteFlag(), tableTest.getExtension(), tableTest.getId());
    }

    /**
     * 删除
     *
     * @param id id
     * @return int
     */
    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from spring_boot_test.table_test where id=?", id);
    }

    /**
     * 根据ID查询
     *
     * @param id id
     * @return {@link Object}
     */
    @Override
    public Object findTableById(int id) {
        List<TableTest> tableTest = jdbcTemplate.query("select * from spring_boot_test.table_test where id=?", new Object[]{id}, new BeanPropertyRowMapper(TableTest.class));
        if (tableTest != null && tableTest.size() > 0) {
            TableTest table = tableTest.get(0);
            return table;
        } else {
            return new JsonResult(0);
        }


    }
}
