/**
 * 添加文章脚本
 */
$(document).ready(function() {
	var treeObj = $("#channelTree").myZtree({
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
				rootPid : "-1"
			}
		},
		callback : {
			beforeClick : beforeClick,
			onClick : onClick
		}
	}, havingTree);
	$("#keyword").keywordinput({
		autocomplete:{
			enable:true,
			source: ctx + "/topic/keyword/allkey"
		}
	});	
});
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择文章栏目...");
	return check;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("channelTree");
	nodes = zTree.getSelectedNodes();
	if (nodes.length > 1) {
		alert("只能选择一个栏目");
		return;
	}
	var channelName = nodes[0].name;
	var channelId = nodes[0].id;
	var nameObj = $("#channelname");
	nameObj.attr("value", channelName);
	var idObj = $("#channelid");
	idObj.attr("value", channelId);

}

function showMenu() {
	var cityObj = $("#channelname");
	var cityOffset = $("#channelname").offset();
	$("#menuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
