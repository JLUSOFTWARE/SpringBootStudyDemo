package top.calvinhaynes.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTest {
    private int id;
    private String name;
    private int level;
    private String createTime;
    private int deleteFlag;
    private String extension;

}
