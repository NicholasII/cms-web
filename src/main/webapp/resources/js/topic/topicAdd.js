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
	$("#file_upload").uploadify({
		'auto':false,
		'swf' : '/cms-web/resources/lib/uploadify/uploadify.swf',
		'uploader' : '/cms-web/topic/upload',
		'method' : 'post',
		'fileObjName' : 'attach',
		'fileTypeExts':"*.jpg;*.gif;*.png;*.doc;*.docx;*.txt;*.xls;*.xlsx;*.rar;*.zip;*.pdf;*.flv;*.swf",
		'onUploadSuccess' : function(file, data, response) {
			var result = eval("("+data+")");
			if (result.status) {  
				createAttachNode(result.attach);
			}
        }
	});
	
	function createAttachNode(attach) {
		var rows = $("<tr><input name='attachments' type='hidden' value="+ attach.id +"></tr>");
		var fileImg;
		var imgUrl = ctx + "/resources/upload/thumbnail/" + attach.newname;
		var indexImg,channelImg;	
		
		if (attach.isimg==1) {
			fileImg=$("<td><img src="+imgUrl+"></td>");
			indexImg = $("<td><input onclick='setIndexPic(this)' type='checkbox' value="+ attach.id +"></td>");
			channelImg = $("<td><input id='channelpicid' type='radio' name='channelpicid' value="+ attach.id +"></td>");
		}else {
			fileImg = $("<td>普通附件类型</td>");
			indexImg = $("<td>普通附件类型</td>");
			channelImg = $("<td>普通附件类型</td>");
		}
		
		rows.append(fileImg);
		var fileName = $("<td>"+attach.newname+"</td>");
		rows.append(fileName);
		var fileSize = $("<td>"+attach.size+"</td>");
		rows.append(fileSize);
		rows.append(indexImg);
		rows.append(channelImg);

		var attachment = $("<td><input onclick='setIsAttachment(this)' type='checkbox' value="+ attach.id +" checked='checked'></td>");
		rows.append(attachment);
		var operate = $("<td><a href='#' class='a_button'>删除附件</a>&nbsp;&nbsp;<a href='#' class='a_button'>插入到文章</a></td>");
		rows.append(operate);
		$("#attach_info").append(rows);
									
	}
	
	$("#uploadFile").click(function() {
		$("#file_upload").uploadify("upload","*");
	})	
	
});


$("input.isIndexImg").click(function(){
	var id = $(this).val();
	var checked = $(this).attr("checked");
	alert(id);
	alert(checked);
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

function setIndexPic(element) {
	var id = element.value;
	var checked = element.checked;
	var url = ctx + "/topic/updateAttach/" + id;
	var data = {};
	if (checked) {
	data.isindexpic = 1;
	}else {
		data.isindexpic = 0;
	}
	commonAjax(url,"post",data,"updateState");
}

function setIsAttachment(element){
	var id = element.value;
	var checked = element.checked;
	var url = ctx + "/topic/updateAttach/" + id;
	var data = {};
	if (checked) {
		data.isattach = 1;
	}else {
		data.isattach = 0;
	}
	commonAjax(url,"post",data,"updateState");
}
function updateState(data) {
	if (data.status=="success") {
		alert("更新成功！");
	}else if (data.status=="fail") {
		alert("更新失败！");
	}else {
		alert("其他！");
	}
	
}