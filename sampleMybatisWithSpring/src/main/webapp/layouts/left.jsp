<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>

.menuItem li a{
    margin-left: 10px; padding-left: 10px;text-align:left;
}
	</style>
<div class="easyui-accordion"
	data-options="fit:true,iconCls:'icon-ok',border:false">
	

	<div class="menuItem" title='系统设置' data-options="iconCls:'icon-cog'"
		style="padding: 10px;">
		<ul>
	<%-- 	 <shiro:hasPermission name="menu:user"> --%>
			<li><a data-options="plain:true" href="javascript:void(0)"
				class="user easyui-linkbutton"
				onclick="indexTabsAddTab('href',{title:'用户管理',url:'${ctx}/account/user',iconCls:'icon-user_gray'})">
					<img
					src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/user_gray.png" />&nbsp;<!-- 用户管理 -->User
			</a></li>
			<%-- </shiro:hasPermission> --%>
			<li><a data-options="plain:true" href="javascript:void(0)"
				class="user easyui-linkbutton"
				onclick="indexTabsAddTab('href',{title:'Employee',url:'${ctx}/employee',iconCls:'icon-user_gray'})">
					<img
					src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/user_gray.png" />&nbsp;<!-- 用户管理 -->Employee
			</a></li>
			<li><a data-options="plain:true" href="javascript:void(0)"
				class="user easyui-linkbutton"
				onclick="indexTabsAddTab('href',{title:'角色管理',url:'${ctx}/role',iconCls:'icon-group_key'})">
					<img
					src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/group_key.png" />&nbsp;角色管理
			</a></li>
			<li><a data-options="plain:true" href="javascript:void(0)"
				class="user easyui-linkbutton"
				onclick="indexTabsAddTab('href',{title:'权限管理',url:'${ctx}/permission',iconCls:'icon-key'})">
					<img src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/key.png" />&nbsp;权限管理
			</a></li>
		</ul>
		
	</div>
	
</div>



