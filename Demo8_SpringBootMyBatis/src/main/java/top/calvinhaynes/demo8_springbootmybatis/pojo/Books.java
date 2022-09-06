package top.calvinhaynes.demo8_springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 书
 *
 * @author CalvinHaynes
 * @date 2021 /09/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("books")
public class Books {
    private String bookId;
    private String bookName;
    private int bookCounts;
    private String detail;

}
