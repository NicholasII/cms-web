/**
 * 分组管理的栏目数
 */
$(function(){
	var treeObj = $("#channelTree").myZtree({
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPid:"-1"
			}
		}
},havingTree);
});