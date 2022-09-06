package top.calvinhaynes.utils;

/**
 * 使用@RestController或@ResponseBody时，可直接返回该对象，
 * Spring Boot默认使用Jackson会自动将该对象转换为json字符串
 * 如{"code": 0,"msg": "","data": [{}, {}]}
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
public class JsonResult {

    private int status;

    /**
     * Instantiates a new Json result.
     */
    public JsonResult() {
    }

    /**
     * Instantiates a new Json result.
     *
     * @param status the status
     */
    public JsonResult(int status) {
        this.status = status;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                '}';
    }
}

