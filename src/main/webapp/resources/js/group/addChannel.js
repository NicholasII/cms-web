/**
 * 为分组添加栏目
 */
var addChannelUrl = ctx + "/group/bindChannel";
var deleteChannelUrl = ctx + "/group/deleteChannel";
$(function() {
	var treeObj = $("#channelTree").myZtree({
			check:{
				enable:true,
				chkboxType:{ "Y" : "p", "N" : "ps" },
				chkStyle : "checkbox"
			},
			callback:{
				beforeCheck:function(treeId,treeNode){
					if (!treeNode.checked) {
						var ps = treeObj.getCheckParentNodes(treeNode,false);
						ps.push(treeNode);
						addGroupChannel(ps);
					}else {
						var cs = new Array();
						treeObj.getCheckChildNodes(treeNode,true,cs);
						cs.push(treeNode);
						deleteGroupChannel(cs);
					}
				},
				onCheck:function(event,treeId,treeNode){
					
				}
			}
	},zNodes);
	
	initTree();
	function initTree() {
		for (var i = 0; i < checkedTrees.length; i++) {
			var node = checkedTrees[i];
			var n = treeObj.getNodeByParam("id", node.id, null);
			treeObj.checkNode(n, true, true);
		}
	}
});
function addGroupChannel(ps) {
	var array = new Array();
	for (var i = 0; i < ps.length; i++) {
		var node = ps[i];
		if (node.name != "网站系统栏目") {
			var data = {};
			data.channelid = node.id;
			data.channelpid = node.pid;
			data.channelname = node.name;
			data.groupid = group.groupId;
			array.push(data);
		}
	}
	var jsonString = JSON.stringify(array);
	var send = {};
	send.list = jsonString;
	commonAjax(addChannelUrl, "post", send, addStatus)
}
function deleteGroupChannel(cs) {
	var array = new Array();
	for (var i = 0; i < cs.length; i++) {
		var node = cs[i];
		if (node.name != "网站系统栏目") {
			var data = {};
			data.channelid = node.id;
			data.channelpid = node.pid;
			data.channelname = node.name;
			data.groupid = group.groupId;
			array.push(data);
		}
	}
	var jsonString = JSON.stringify(array);
	var send = {};
	send.data = jsonString;
	commonAjax(deleteChannelUrl, "post", send, deleteStatus)

}

function addStatus(data){
	var status = eval("("+data+")");
	if (status.success) {
		alert("绑定成功")
	}else {
		alert("绑定失败");
	}
	
}
function deleteStatus(data) {
	var status = eval("("+data+")");
	if (status.success) {
		alert("解除绑定成功")
	}else {
		alert("解除绑定失败");
	}
}