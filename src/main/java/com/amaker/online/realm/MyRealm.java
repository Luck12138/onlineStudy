package com.amaker.online.realm;


import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.storage.ThumbModel;
import com.amaker.online.common.web.SessionContext;
import com.amaker.online.common.web.auth.SessionUser;
import com.amaker.online.dao.AuthUserDao;
import com.amaker.online.model.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AuthUserDao authUserDao;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null)
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        // 获取当前登录用户
        SessionUser user = SessionContext.getAuthUser();
        if (user == null) {
            return null;
        }
        // 设置权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户权限并设置 以供shiro框架
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        AuthUser authUser = null;
        /**
         * 业务代码-start
         */
        try {
            AuthUser tmpAuthUser = new AuthUser();
            tmpAuthUser.setUsername(username);
            tmpAuthUser.setPassword(password);
            tmpAuthUser = authUserDao.getUser(tmpAuthUser);
            if(null != tmpAuthUser){
                authUser = new AuthUser();
                authUser.setId(tmpAuthUser.getId());
                authUser.setRealname(tmpAuthUser.getRealname());
                authUser.setUsername(tmpAuthUser.getUsername());
                authUser.setStatus(tmpAuthUser.getStatus());
                if(!StringUtils.isBlank(tmpAuthUser.getHeader())){
                    authUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader(),ThumbModel.THUMB_256));//设置头像
                }
            }else{
                throw new AuthenticationException("## user password is not correct! ");
            }
        } catch (Exception e) {
            throw new AuthenticationException("## user password is not correct! ");
        }
        //业务代码-end
        // 设置用户权限信息
		/*try {
			authUser.setPermissions(permissions);
		} catch (Exception e) {
			throw new AuthenticationException("## user permission setter exception! ");
		}*/
        // 创建授权用户
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser, password, getName());
        return info;
    }


}