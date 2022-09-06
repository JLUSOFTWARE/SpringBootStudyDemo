package top.calvinhaynes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.calvinhaynes.pojo.TableTest;
import top.calvinhaynes.service.TableTestService;
import top.calvinhaynes.utils.JsonResult;


/**
 * 表测试控制器
 *
 * @author CalvinHaynes
 * @date 2021 /09/06
 */
@RestController
@RequestMapping("/table")
public class TableTestController {

    private final TableTestService tableTestService;

    /**
     * 构造器注入service
     *
     * @param tableTestService 表测试服务
     */
    @Autowired
    public TableTestController(TableTestService tableTestService) {
        this.tableTestService = tableTestService;
    }


    /**
     * 新增表格信息接口
     *
     * @param id         the id
     * @param name       the name
     * @param level      the level
     * @param createTime the create time
     * @param deleteFlag the delete flag
     * @param extension  the extension
     * @return the json result
     */
    @PostMapping("/add")
    public JsonResult postTable(@RequestParam(value = "id") int id,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "level") int level,
                                @RequestParam(value = "create_time") String createTime,
                                @RequestParam(value = "delete_flag") int deleteFlag,
                                @RequestParam(value = "extension") String extension) {
        TableTest tableTest = new TableTest();
        tableTest.setId(id);
        tableTest.setName(name);
        tableTest.setLevel(level);
        tableTest.setCreateTime(createTime);
        tableTest.setDeleteFlag(deleteFlag);
        tableTest.setExtension(extension);

        int status = tableTestService.add(tableTest);

        if (status == 1) {
            return new JsonResult(status);
        } else {
            return new JsonResult(0);
        }
    }

    /**
     * 更新表格信息接口
     *
     * @param id         the id
     * @param name       the name
     * @param level      the level
     * @param createTime 创建时间
     * @param deleteFlag 删除标记
     * @param extension  the extension
     * @return the json result
     */
    @PutMapping("update/{id}")
    public JsonResult updateTable(@PathVariable("id") int id,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "level") int level,
                                  @RequestParam(value = "create_time") String createTime,
                                  @RequestParam(value = "delete_flag") int deleteFlag,
                                  @RequestParam(value = "extension") String extension) {

        TableTest tableTest = new TableTest();
        tableTest.setName(name);
        tableTest.setLevel(level);
        tableTest.setCreateTime(createTime);
        tableTest.setDeleteFlag(deleteFlag);
        tableTest.setExtension(extension);
        tableTest.setId(id);

        int status = tableTestService.update(tableTest);

        if (status == 1) {
            return new JsonResult(status);
        } else {
            return new JsonResult(0);
        }
    }

    /**
     * 删除表格信息接口
     *
     * @param id the id
     * @return the json result
     */
    @DeleteMapping("delete/{id}")
    public JsonResult deleteTableById(@PathVariable("id") int id) {
        int status = tableTestService.delete(id);

        if (status == 1) {
            return new JsonResult(status);
        } else {
            return new JsonResult(0);
        }
    }

    /**
     * 查询表格信息接口
     *
     * @param id the id
     * @return the table by id
     */
    @GetMapping("query/{id}")
    public Object getTableById(@PathVariable("id") int id) {
        return tableTestService.findTableById(id);
    }
}
