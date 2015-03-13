package com.infotop.account.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.infotop.account.mapper.RoleMapper;
import com.infotop.account.mapper.UserMapper;
import com.infotop.account.model.Permission;
import com.infotop.account.model.Role;
import com.infotop.account.model.User;







public class AuthenticationRealm extends AuthorizingRealm {

   // private static final Logger log = LoggerFactory.getLogger(AuthenticationRealm.class);
    
	@Autowired
	private UserService userService;
    
    @Resource
    private RoleMapper roleMapper;
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
//        char[] password = upToken.getPassword();
//        log.debug(String.valueOf(password));
        // Null username is invalid
        if (username == null) {
            throw new AccountException("用户名不能为空!");
        }
        SimpleAuthenticationInfo info = null;
       try{
       String password =  userMapper.getPassword(username);
       if (password == null) {
           throw new UnknownAccountException("No account found for user [" + username + "]");
       }
//       if(password.length>2){
//    	   throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
//       }
       info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
       }  catch (RuntimeException e) {
           final String message = "There was a SQL error while authenticating user [" + username + "]";
           throw new AuthenticationException(message, e);
       } 
        
           return info;
    }



	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		  if (principals == null) {
	            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
	        }

	        String username = (String) getAvailablePrincipal(principals);
	        Set<String> roleNames = getRoleNameByUserName(username);
	        Set<String> permissions = new HashSet<String>();
	        for(String roleName : roleNames){
	        	Role role = roleMapper.getRole(roleName);
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
		User user = userMapper.getUser(username);
		for(Role role:user.getRoles()){
			roless.add(role.getName());
			//log.debug(role.getName());
		}
		
		return roless;
		
	}
	

}
