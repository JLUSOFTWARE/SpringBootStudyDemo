package top.calvinhaynes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.calvinhaynes.dao.TableTestDao;
import top.calvinhaynes.pojo.TableTest;


/**
 * 表测试服务Service层的接口实现类
 * The type Table test service.
 *
 * @author CalvinHaynes
 * @date 2021 /09/07
 */
@Service
public class TableTestServiceImpl implements TableTestService{

    private final TableTestDao tableTestDao;

    /**
     * Instantiates a new Table test service.
     *
     * @param tableTestDao the table test dao
     */
    @Autowired
    public TableTestServiceImpl(TableTestDao tableTestDao) {
        this.tableTestDao = tableTestDao;
    }

    @Override
    public int add(TableTest tableTest) {
        return tableTestDao.add(tableTest);
    }

    @Override
    public int update(TableTest tableTest) {
        return tableTestDao.update(tableTest);
    }

    @Override
    public int delete(int id) {
        return tableTestDao.delete(id);
    }

    @Override
    public Object findTableById(int id) {
        return tableTestDao.findTableById(id);
    }
}
