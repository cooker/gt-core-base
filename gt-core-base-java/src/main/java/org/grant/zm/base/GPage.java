package org.grant.zm.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ZoomGrant 2020/3/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPage<T> {

    public static Integer[] PAGE_LIMIT = new Integer[]{10, 25, 50, 100, 200};

    /**
     * 页数
     */
    Integer page;
    /**
     * 每页限制行
     */
    Integer pageLimit;
    /**
     * 当前页
     */
    Integer currentPage;
    /**
     * 总数
     */
    Integer total;

    List<T> datas;

    /**
     * 分页
     * @param datas
     * @param currentPage
     * @param pageLimit
     * @param <T>
     * @return
     */
    public static <T> GPage<T> page(List<T> datas, Integer currentPage, Integer pageLimit){
        Integer size = datas.size();
        List<T> temps = datas.stream().skip(currentPage * pageLimit)
                .limit(pageLimit).collect(Collectors.toList());
        GPage gPage = new GPage(getPage(size, pageLimit) , pageLimit, currentPage, size, temps);
        return gPage;
    }

    public static Integer getPage(Integer total, Integer pageLimit) {
        return (total % pageLimit) > 0 ? total / pageLimit +1 : total / pageLimit;
    }
}
