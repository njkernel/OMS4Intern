package cn.com.connext.oms.web.Controller;
import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbPermission;
import cn.com.connext.oms.entity.TbRole;
import cn.com.connext.oms.service.TbRolePermissionService;
import cn.com.connext.oms.service.TbRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @created with IDEA
 * @author: Stephanie
 * @version: 1.0.0
 * @date: 2019/1/14
 **/
@RestController
public class TbRoleController {
    @Autowired
    private TbRoleService tbRoleService;
    @Autowired
    private TbRolePermissionService tbRolePermissionService;

    /**
     * 服务端分页查询
     * @param pageSize
     * @param currentPage
     * @param roleName
     * @return
     */
    @ApiOperation(value = "角色列表数据接口")
    @RequestMapping(value = "rolePage", method = RequestMethod.GET)
    public BaseResult page(
            Integer pageSize, Integer currentPage,String roleName
    ){
        try {

            PageInfo<TbRole> rolePageInfo = tbRoleService.page(pageSize, currentPage, roleName);

            return BaseResult.success("成功",rolePageInfo );
        } catch (Exception e) {
            return BaseResult.fail();
        }
    }

    /**
     * 获取角色
     * @return
     */

    @GetMapping("/getRole")
    @ApiOperation(value = "获取角色")
    public BaseResult getAllRole(){
        try {
            List<TbRole> allRole = tbRoleService.addRole();
            return BaseResult.success("success",allRole);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("获取角色失败");
        }
    }
        /**
     * 增加角色
     * @param tbRole
     * @return
     */
    @ApiOperation(value = "增加角色")
    @GetMapping("/addRole")
    public BaseResult addRole(TbRole tbRole) {
        try {
            boolean add = tbRoleService.addRole(tbRole);
             return BaseResult.success("success", add);

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("增加角色失败");
        }

    }

    /**
     * 删除角色以及对应权限
     * @param roleId
     * @return
     */
    @ApiOperation(value = "删除角色")
    @GetMapping("/roleDeletById")
    public BaseResult roleDeletById(@RequestParam("roleId") int roleId){
        try {
            tbRoleService.roleDeletById(roleId);
            return BaseResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  BaseResult.fail("删除角色失败");
        }

    }

    /**
     * 获取当前角色权限接口
     * @param roleId
     * @return
     */
    @ApiOperation(value = "获取当前角色权限接口")
    @GetMapping(value = "/rolePermission")
    public BaseResult getPermissionsByRole(Integer roleId){
        try {
            List<TbPermission> getPermissionsByRole = tbRolePermissionService.getPermissionsByRole(roleId);
            return BaseResult.success("success",getPermissionsByRole);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("获取当前角色权限接口失败");
        }

    }


    /**
     * 更新角色接口并授权
     * @param roleId
     * @param roleName
     * @param permissionId
     * @return
     */

    @PostMapping("/roleUpdate")
    @ApiOperation(value = "更新角色接口并授权")
    public BaseResult roleUpdate(Integer roleId, String roleName, String permissionId){
        try {
            return BaseResult.success("success",tbRoleService.roleUpdate(roleId,roleName,permissionId));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("更新角色接口并授权失败");
        }

    }


//    /**
//     * 通过角色id获取角色名字
//     */
//    @GetMapping("/getIdRole")
//    @ApiOperation(value = "通过角色id获取角色名字")
//    public BaseResult fingRoleNameById(String roleId){
//        try {
//           TbRole tbRole = tbRoleService.fingRoleNameById(Integer.valueOf(roleId));
//            return BaseResult.success("success",tbRole.getRoleId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return BaseResult.fail("通过角色id获取角色名字失败");
//        }
//    }
//    /**
//     * 通过角色名字获取角色id
//     */
//    @GetMapping("/getNameId")
//    @ApiOperation(value ="通过角色名字获取角色id")
//    public BaseResult findIdByRoleName(Integer roleName){
//        try {
//            TbRole tbRole  = tbRoleService.findIdByRoleName(roleName);
//            return BaseResult.success("success",tbRole.getRoleName());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return BaseResult.fail("通过角色名字获取角色id失败");
//        }
//    }

}

