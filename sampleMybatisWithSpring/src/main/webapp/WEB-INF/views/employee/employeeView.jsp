<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="three">
	<div class="contenttable">
		<h3>
			Employee
		</h3>
		<div class="contenttable1">
			<table class="content" style="width: 99%;" >
				 				<tr>
					<td class="biao_bt3">Name</td>
					<td>${ employee.name }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3">Age</td>
					<td>${ employee.age }</td>
				</tr>
				  				<tr>
					<td class="biao_bt3">Department</td>
					<td>${ employee.department }</td>
				</tr>
				  			
				   			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$ .parser.onComplete = function() {
	parent.$ .messager.progress('close');
};
</script>



