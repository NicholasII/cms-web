<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function list(){
		var url = ctx + "/channel/page";
		var data = [];
		var data1 = {};
		data1.name = "pid";
		data1.value = "${pid}";
		var data2 = {};
		data2.name = "pname";
		data2.value = "${pname}";
		data.push(data1);
		data.push(data2);
		formSubmit(url, data);
	}
	
</script>
<span> 
	<a href="javascript:list()" class="a_link">子栏目列表</a> 
	<span class="a_link">当前栏目:<span>${pname}[${pid}]</span></span>
</span>