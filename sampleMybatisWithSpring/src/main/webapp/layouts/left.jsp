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
			<li><a data-options="plain:true" href="javascript:void(0)"
				class="user easyui-linkbutton"
				onclick="indexTabsAddTab('href',{title:'用户管理',url:'${ctx}/account/user',iconCls:'icon-user_gray'})">
					<img
					src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/user_gray.png" />&nbsp;<!-- 用户管理 -->User
			</a></li>
			
		</ul>
		
	</div>
	
</div>



