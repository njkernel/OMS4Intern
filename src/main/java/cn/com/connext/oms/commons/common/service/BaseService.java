package cn.com.connext.oms.commons.common.service;

import com.github.pagehelper.PageInfo;

/**
 * <p>Title: baseService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/1 22:22
 */
public interface BaseService<T> {
    int insert(T t, String createBy);

    int delete(T t);

    int update(T t, String updateBy);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);
}
