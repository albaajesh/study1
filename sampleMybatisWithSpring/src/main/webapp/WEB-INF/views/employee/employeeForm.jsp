<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
var employee_form_inputform_id = 'employee_form_inputForm';

$ .parser.onComplete = function() {
		parent. $ .messager.progress('close');
		$('#'+employee_form_inputform_id).form(
				{
					onSubmit : function() {
						parent. $ .messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
						});
						var isValid = $(this).form('validate');
						if (!isValid) {
							parent. $ .messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						parent. $ .messager.progress('close');
						result = $ .parseJSON(result);
						if (result.success) {
							parent. $ .modalDialog.openner_dataGrid
									.datagrid('reload');
							parent. $ .modalDialog.openner_dataGrid.datagrid(
									'uncheckAll').datagrid('unselectAll')
									.datagrid('clearSelections');
							parent. $ .modalDialog.handler.dialog('close');
							$ .messager.show({ // show error message
								title : '提示',
								msg : result.message
							});
						} else {
							$ .messager.alert('错误', result.message, 'error');
						}
					}
				});

	} ;
</script>

<form:form id="employee_form_inputForm" name="employee_form_inputForm" action="${ctx}/employee/${action}"
		 modelAttribute="employee" method="post" class="form-horizontal">
	<input type="hidden" name="id" id="id" value="${ employee.id}" />
	<table class="content" style="width: 100%;">
	 		<tr>
			<td class="biao_bt3">Name</td>
			<td><input type="text" name="name" id="name" value="${ employee.name }" class="easyui-validatebox" data-options="missingMessage:'name不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3">age</td>
			<td><input type="text" name="age" id="age" value="${ employee.age }" class="easyui-validatebox" data-options="missingMessage:'age不能为空.',required:true"   />	</td>
		</tr>
	  		<tr>
			<td class="biao_bt3">Department</td>
			<td><input type="text" name="department" id="department" value="${ employee.department }" class="easyui-validatebox" data-options="missingMessage:'department不能为空.',required:true"   />	</td>
		</tr>
	  		
	   	</table>
</form:form>
	