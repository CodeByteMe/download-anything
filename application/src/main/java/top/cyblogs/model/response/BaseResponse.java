package top.cyblogs.model.response;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 返回值结果
 *
 * @author CY
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseResponse<T> {

    /**
     * 成功标志
     */
    private boolean flag;

    /**
     * 状态码
     */
    private HttpStatus status;

    /**
     * 消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 携带数据和信息
     *
     * @param data    响应数据
     * @param message 操作结果信息
     * @return 携带数据和信息
     */
    public static <T> BaseResponse<T> ok(String message, T data) {
        return new BaseResponse<>(true, HttpStatus.OK, message, data);
    }

    /**
     * 仅携带信息
     *
     * @param message 操作结果信息
     * @return 携带信息
     */
    public static <T> BaseResponse<T> ok(String message) {
        return ok(message, null);
    }

    /**
     * 仅携带数据
     *
     * @param data 响应数据
     * @param <T>  响应类型
     * @return 携带数据
     */
    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(true, HttpStatus.OK, "操作完成", data);
    }
}
