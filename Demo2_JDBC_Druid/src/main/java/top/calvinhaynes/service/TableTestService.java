package top.calvinhaynes.service;

import top.calvinhaynes.pojo.TableTest;


/**
 * 表测试服务接口Service层
 * The interface Table test service.
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
public interface TableTestService {

    /**
     * Add int.
     *
     * @param tableTest the table test
     * @return the int
     */
    int add(TableTest tableTest);

    /**
     * Update int.
     *
     * @param tableTest the table test
     * @return the int
     */
    int update(TableTest tableTest);

    /**
     * Delete int.
     *
     * @param id the id
     * @return the int
     */
    int delete(int id);

    /**
     * Find table by id object.
     *
     * @param id the id
     * @return the object
     */
    Object findTableById(int id);
}
