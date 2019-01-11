package cn.com.connext.oms.config.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import tk.mybatis.mapper.entity.Example;


public class MyRealm extends AuthorizingRealm {
	


	/**
	 * 执行授权逻辑
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*String username=(String) SecurityUtils.getSubject().getPrincipal();
		//给资源进行授权，SimpleAuthorizationInfo是AuthorizationInfo的一个实现类
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

		Set<String> roles=new HashSet<String>();
		//根据用户名去查他的角色
		Example example=new Example(Member.class);
		example.createCriteria().andEqualTo("username",username);
		Member member = memberMapper.selectOneByExample(example);
		String roleid = member.getRoleid();
		Role role = roleDao.selectByPrimaryKey(roleid);

		//根据角色去查他的权限
		List<Permission> permissionsByUserName = permissionDao.getPermissionsByRole(role.getRolename());
		//根据用户名去查他的权限
		for(Permission permission:permissionsByUserName) {
			info.addStringPermission(permission.getName());
		}
		//给资源添加授权字符串
		info.setRoles(roles);
		return info;*/
		return null;
	}

	/**
	 *   执行认证逻辑
	 *   1、检查提交的进行认证的令牌信息
	 *   2、根据令牌信息从数据源(通常为数据库)中获取用户信息
	 *   3、对用户信息进行匹配验证。
	 *   4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
	 *   5、验证失败则抛出AuthenticationException异常信息。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*System.out.println("token.getPrincipal:" + token.getPrincipal());
		System.out.println("token.getCredentials:" + token.getCredentials());
		String userName = token.getPrincipal().toString();
		Example example=new Example()

		Member member = memberService.findByName(userName);

		if (member != null) {
			// 用户存在，判断密码
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(member.getUsername(), member.getPassword(), getName());
			return authcInfo;
		} else {
			//用户名不存在，shiro底层会抛出UnKnowAccountException
			return null;
		}*/
		return null;
	}
	
}
