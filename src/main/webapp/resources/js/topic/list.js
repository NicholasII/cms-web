/**
 * 文章列表
 */
var channel_url = ctx + "/channel/allsubchannels";
var topiclist_url = ctx + "/topic/list";
$(function() {
	commonAjax(channel_url,"post",null,"getSubChannel");
});

function deleteTopic(topicid) {
		$("#dialog-confirm").dialog({
			resizable : false,
			height : "auto",
			width : 400,
			modal : true,
			buttons : {
				"删除" : function() {
					$(this).dialog("close");
					var url = ctx + "/topic/deleteTopic/"+topicid;
					formSubmit(url,[]);
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			}
		});
		
	}
function searchTopic(){
	var title = $("#topictitle").val();
	var channelid = $("#search").val();
	var keyword = $("#topickey").val();
	var requestData = new Array();
	var channelData = new Object();
	if (channelid!=-1) {
		channelData.name = "channel";
		channelData.value = channelid;
	}
	requestData.push(channelData);
	var titleData = new Object();
	if (title!=null) {
		titleData.name = "title";
		titleData.value = title;
	}
	requestData.push(titleData);
	var keyData = new Object();
	if (keyword!=null) {
		keyData.name = "keyword";
		keyData.value = keyword;
	}
	requestData.push(keyData);
	formSubmit(topiclist_url,requestData);
}

function getSubChannel(data) {
	data = data.allSubChannel;
	for (var int = 0; int < data.length; int++) {
		var channel = data[int];
		var option = $("<option value='"+channel.id+"'>"+channel.name+"</option>")
		$("#search").append(option);
	}
}