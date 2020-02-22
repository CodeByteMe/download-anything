package top.cyblogs.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @param <T>
 * @author CY
 */
@Data
@Accessors(chain = true)
public class PageResponse<T> {

    /**
     * 总量
     */
    private Long total;

    /**
     * 当前页
     */
    private List<T> rows;
}
