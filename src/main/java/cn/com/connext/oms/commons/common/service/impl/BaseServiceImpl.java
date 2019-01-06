/*
package cn.com.connext.oms.commons.common.service.impl;

import cn.com.connext.oms.commons.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;


*/
/**
 * <p>Title: BaseServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/1 22:24
 *//*

@Service
@Transactional(readOnly = true)
public class BaseServiceImpl<T, D extends MyMapper<T>> implements BaseService<T> {
    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t, String createBy) {
        */
/*t.setCreateBy(createBy);
        t.setCreateDate(new Date());
        t.setUpdateBy(createBy);
        t.setUpdateDate(new Date());*//*

        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t, String updateBy) {
        */
/*t.setUpdateBy(updateBy);
        t.setUpdateDate(new Date());*//*

        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
        return pageInfo;
    }
}
*/
