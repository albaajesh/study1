package com.infotop.account.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;



import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springside.modules.utils.Encodes;



import com.infotop.account.model.Permission;
import com.infotop.account.model.Role;
import com.infotop.account.model.User;











public class AuthenticationRealm extends AuthorizingRealm {

   // private static final Logger log = LoggerFactory.getLogger(AuthenticationRealm.class);
    
	protected AccountService accountService;
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        User user = accountService.findUserByName(upToken.getUsername());
       // String password =  userService.getPassword(upToken.getUsername());
        ShiroUser shiroUser = null;
        if(user!=null){
        	shiroUser = new ShiroUser(user.getId(), user.getLoginName(),
					user.getName(), user.getRegisterDate(), user.getUserType());
        	byte[] salt = Encodes.decodeHex(user.getSalt());
        	return new SimpleAuthenticationInfo(shiroUser,user.getPassword(),ByteSource.Util.bytes(salt),getName());
        }else{
        	return null;
        }
       /* String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空!");
        }
        SimpleAuthenticationInfo info = null;
       try{
       String password =  userService.getPassword(username);
       if (password == null) {
           throw new UnknownAccountException("No account found for user [" + username + "]");
       }

       info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
       }  catch (RuntimeException e) {
           final String message = "There was a SQL error while authenticating user [" + username + "]";
           throw new AuthenticationException(message, e);
       } 
        
           return info;*/
    }

    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = accountService.findUserByName(shiroUser.loginName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Role role : user.getRoleList()) {
			// 基于Role的权限信息
			info.addRole(role.getName());
			// 基于Permission的权限信息
			info.addStringPermissions(accountService.getPermissionByRoleId(role.getId()));
		}
		return info;
	}

	/*@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		  if (principals == null) {
	            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
	        }

	        String username = (String) getAvailablePrincipal(principals);
	        Set<String> roleNames = getRoleNameByUserName(username);
	        Set<String> permissions = new HashSet<String>();
	        for(String roleName : roleNames){
	        	Role role = accountService.findRoleByName(roleName);
	        	for(Permission permission :role.getPermissions()){
	        		permissions.add(permission.getModule()+":"+permission.getPrivilege());
	        	}
	        	
	        }
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	        info.setStringPermissions(permissions);
	        return info;
	}
	
	public Set<String> getRoleNameByUserName(String username){
		Set<String> roless = new HashSet<String>();
		User user = accountService.findUserByName(username);
		for(Role role:user.getRoles()){
			roless.add(role.getName());
			//log.debug(role.getName());
		}
		
		return roless;
		
	}*/
	
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				accountService.HASH_ALGORITHM);
		matcher.setHashIterations(accountService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}
	
	

	

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}






	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String loginName;
		public String name;
		public int roleId;
		public String registerDate;
		public int userType;

		public ShiroUser(Long id, String loginName, String name, String registerDate,
				int userType) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.registerDate = registerDate;
			this.userType = userType;
		}

		public String getName() {
			return name;
		}

		public Long getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		

		public String getRegisterDate() {
			return registerDate;
		}


		public int getUserType() {
			return userType;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, "loginName");
		}

		/**
		 * 重载equals,只比较loginName
		 */
		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj, "loginName");
		}
	}
}