/**
 * 更新文章
 */
$(function() {
	if (topic != null || topic != undefined) {
		var id = topic.id;
		$("#id").val(id);
		var title = topic.title;
		$("#title").val(title);
		var channelname = topic.channelname;
		$("#channelname").val(channelname);
		var channelid = topic.channelid;
		$("#channelid").val(channelid);
		var status = topic.status;
		if (status == 1) {
			$("#publish").attr("checked", "checked");
		}
		if (status == 0) {
			$("#unpublish").attr("checked", "checked");
		}
		var recommend = topic.recommend;
		if (recommend == 1) {
			$("#recommend").attr("checked", "checked");
		}
		if (recommend == 0) {
			$("#unrecommend").attr("checked", "checked");
		}
		var createdate = topic.createdate;
		var date = new Date(createdate);
		var year = date.getFullYear();
        var month = date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
        var day = date.getDate()<10 ? '0'+(date.getDate()) : date.getDate();
		date = month + "/" + day + "/" + year;
		$("#createdate").val(date);
		var content = topic.content;
		$("#content").val(content);
		var summary = topic.summary;
		$("#summary").val(summary);
		var keywords = topic.keyward;
		addKeyword(keywords);
		var channelpicid  = topic.channelpicid;
		addAttachs(channelpicid);
	}
});

function addKeyword(keywords) {
	var array = keywords.split("|");
	for (var i = 0; i < array.length; i++) {
		var key = array[i];
		var node = createKeywordNode(key);
		$("#keyward-wrap").append(node);
	}
}
function createKeywordNode(value){
	return '<div class="keyward-in"><span>'+value+'</span><a href="#" class="keyward-shut">x</a><input type="hidden" name="keyward" value="'+value+'"></div>';
}
function addAttachs(channelpicid) {
	if (attachments!=null) {
		for (var int = 0; int < attachments.length; int++) {
			var attach = attachments[int];
			createAttachNode(attach,channelpicid);
		}
	}
}
function createAttachNode(attach,channelpicid) {
	var rows = $("<tr><input name='attachments' type='hidden' value="+ attach.id +"></tr>");
	var fileImg;
	var imgUrl = ctx + "/resources/upload/thumbnail/" + attach.newname;
	var indexImg,channelImg,attachment;
	if (attach.isimg==1) {
		fileImg=$("<td><img src="+imgUrl+"></td>");
		if (attach.isindexpic==1) {
			indexImg = $("<td><input onclick='setIndexPic(this)' type='checkbox' value="+ attach.id +" checked='checked'></td>");
		}else {
			indexImg = $("<td><input onclick='setIndexPic(this)' type='checkbox' value="+ attach.id +"></td>");
		}
		if (channelpicid==attach.id) {
			channelImg = $("<td><input type='radio' name='channelpicid' value="+ attach.id +" checked='checked'></td>");
		}else {
			channelImg = $("<td><input type='radio' name='channelpicid' value="+ attach.id +"></td>");
		}	
	}else {
		fileImg = $("<td>普通附件类型</td>");
		indexImg = $("<td>普通附件类型</td>");
		channelImg = $("<td>普通附件类型</td>");
	}
	if (attach.isattach) {
		attachment = $("<td><input onclick='setIsAttachment(this)' type='checkbox' value="+ attach.id +" checked='checked'></td>");
	}else {
		attachment = $("<td><input onclick='setIsAttachment(this)' type='checkbox' value="+ attach.id +"></td>");
	}
	rows.append(fileImg);
	var fileName = $("<td>"+attach.newname+"</td>");
	rows.append(fileName);
	var fileSize = $("<td>"+attach.size+"</td>");
	rows.append(fileSize);
	rows.append(indexImg);
	rows.append(channelImg);
	rows.append(attachment);
	var operate = $("<td><a href='#' class='a_button'>删除附件</a>&nbsp;&nbsp;<a href='#' class='a_button'>插入到文章</a></td>");
	rows.append(operate);
	$("#attach_info").append(rows);
								
}