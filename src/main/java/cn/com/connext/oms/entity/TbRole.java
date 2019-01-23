package cn.com.connext.oms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "tb_role")
public class TbRole implements Serializable {

    private static final long serialVersionUID = -5732352282251058142L;
    /**
     * id
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 创建时间
     */
    private Date created;

}