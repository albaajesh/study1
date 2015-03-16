<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div data-options="fit:true" class="easyui-panel">
	<div class="easyui-layout" data-options="fit:true">
		<%-- <div
			data-options="region:'north',border:false,title:'Employee',iconCls:'icon-find'"
			style="height: 65px;overflow: hidden;">
			<form id="employee_list_searchForm" method="post"
				style="width:100%;overflow:hidden;">
				<table class="search_table" style="width: 100%;">
					<tr>
						    						<th><spring:message code="pageurlinfo_element" /></th>
						<td><input type="text" name="search_EQ_element"
							value="${ param.search_EQ_element}"
							id="search_EQ_element" /></td>   						<th><spring:message code="pageurlinfo_attribute" /></th>
						<td><input type="text" name="search_EQ_attribute"
							value="${ param.search_EQ_attribute}"
							id="search_EQ_attribute" /></td>        						<th style="width:20%;">&nbsp;<a href="javascript:void(0);"
							id="pageurlinfo_list_searchBtn">查询</a>&nbsp;<a
							href="javascript:void(0);" id="pageurlinfo_list_clearBtn">清空</a></th>
					</tr>
				</table>
			</form>
		</div> --%>
		<div data-options="region:'center',border:false">
			<table id="employee_list_dg" style="display: none;"></table>
		</div>
		<div id="employee_list_toolbar" style="display: none;">
				<a href="javascript:updateForm(employee_list_create_url,'employee_form_inputForm',employee_list_datagrid,{title:'新增信息'});" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:false">添加</a> 			
		  	  <a href="javascript:deleteBatch(employee_list_delete_url,employee_list_datagrid);" class="easyui-linkbutton"  data-options="iconCls:'icon-remove',plain:false">删除</a>
			</div> 
	</div>
</div>
<script type="text/javascript">
	//列表DataGrid
	var employee_list_datagrid;
	//列表DataGrid ID
	var employee_list_datagrid_id = 'employee_list_dg';
	//列表查询表单ID
	var employee_list_searchform_id = 'employee_list_searchForm';
	//列表toolbar ID
	var employee_list_toolbar_id = 'employee_list_toolbar';
	//操作链接
	var employee_list_create_url =  '${ctx}/employee/create';
	var employee_list_update_url =  '${ctx}/employee/update/';
	var employee_list_delete_url =  '${ctx}/employee/delete';
	var employee_list_view_url =  '${ctx}/employee/view/';
	var employee_list_datagrid_load_url = '${ctx}/employee/findList';
	
	//定义相关的操作按钮
	function employee_list_actionFormatter(value,row,index){
		console.log(row);
		var str = '';	
		str += formatString(
				'<img onclick="updateForm(\'{0}\',\'employee_form_inputForm\',employee_list_datagrid,{title:\'编辑信息\'});" src="{1}" title="编辑"/>',
				employee_list_update_url + row.id,
				'${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/application_form_edit.png');
		str += '&nbsp;';
		str += formatString('<img onclick="deleteOne(\'{0}\',\'{1}\',employee_list_datagrid);" src="{2}" title="删除"/>',
		                    row.id,employee_list_delete_url,'${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/application_form_delete.png');
		str += '&nbsp;';
		str += formatString(
				'<img onclick="view(\'{0}\',\'{1}\');" src="${ctx}/static/js/plugins/jquery-easyui-1.3.4/themes/icons/view.png" title="查看"/>',
				employee_list_view_url + row.id);
		str += '&nbsp;';
		return str;
	}
	
	//DataGrid字段设置
	var employee_list_datagrid_columns = [ [
	                    		{field : 'id',title : '编号',width : 150,checkbox : true,align:'center'},
	    	          					{field : 'name',title : 'EmployeeName',width : 150,align:'center'},
			          					{field : 'age',title : 'Age',width : 150,align:'center'},
			          					{field : 'department',title : 'Department',width : 150,align:'center'},
			          	                {field : 'action',title : '操作',width : 80,align : 'center',formatter : employee_list_actionFormatter} 
	                    		] ];
	/** 初始化DataGrid,加载数据 **/		
	function employee_list_loadDataGrid(){		 
		employee_list_datagrid = $('#'+employee_list_datagrid_id).datagrid({
			url : employee_list_datagrid_load_url,
			fit : true,
			border : false,
			fitColumns : true,
			singleSelect : false,
			striped : true,
			pagination : true,
			rownumbers : true,
			idField : 'id',
			pageSize : 15,
			pageList : [ 5, 10,15, 20, 30, 40, 50 ],
			columns : employee_list_datagrid_columns,
			toolbar:'#'+employee_list_toolbar_id,
			onLoadSuccess : function() {	
				$(this).datagrid('tooltip');
				$('#'+employee_list_searchform_id+' table').show();
				$('#'+employee_list_datagrid_id).show();
				$('#'+employee_list_toolbar_id).show();
				parent. $ .messager.progress('close');
			}
		});
	}
	$ .parser.onComplete = function() {
		//加载DataGrid数据
		employee_list_loadDataGrid();	
		//绑定按钮事件
		bindSearchBtn('employee_list_searchBtn','employee_list_clearBtn','employee_list_searchForm',employee_list_datagrid);
	};
</script>


