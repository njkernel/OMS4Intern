package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbUser;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

public interface TbUserService {
    /**
     * @Description: 添加用户
     * @Param: [user]
     * @return: boolean
     * @Author: Lili Chen
     * @Date: 2019/1/13
     */
    boolean addUser(TbUser user);

    /**
     * @Description: 删除用户
     * @Param: [userId]
     * @return: boolean
     * @Author: Lili Chen
     * @Date: 2019/1/13
     */
    boolean deleteUser(Integer userId);


    /**
     * @Description: 更新用户
     * @Param: [user]
     * @return: boolean
     * @Author: Lili Chen
     * @Date: 2019/1/13
     */
    Integer updateUser(TbUser user);


    /**
     * @Description: 查看全部的用户
     * @Param:
     * @return:
     * @Author: Lili Chen
     * @Date: 2019/1/13
     */

    Map getListUser(Integer page, Integer size);


    /**
    * @Description: 验证用户名字
     * @Param: [userName]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    String validateName(String userName);

    /**
    * @Description: 根据用户id查看用户
    * @Param: [userId]
    * @return: cn.com.connext.oms.entity.TbUser
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    TbUser getUserById(Integer userId);

}
