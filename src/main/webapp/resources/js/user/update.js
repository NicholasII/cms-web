/**
 * 更新用户
 */
$(function() {
	refillData();
});

function refillData() {
	$("#id").val(user.userId);
	$("#name").val(user.userName);
	$("#password").val(user.password);
	$("#password_confirm").val(user.password);
	$("#tel").val(user.tel);
	$("#mobile").val(user.mobile);
	if (user.status==1) {
		$("#status").val("启用");
	}else {
		$("#status").val("停用");
	}
	for (var i = 0; i < role.length; i++) {
		if (role[i].roleId=="admin") {
			$("#c_admin").attr("checked","checkded");
		}else if (role[i].roleId=="articlechecker") {
			$("#c_check").attr("checked","checkded");
		}else if (role[i].roleId=="articlepublisher") {
			$("#c_publish").attr("checked","checkded");
		}else {
			$("#c_user").attr("checked","checkded");
		}
	}
	for (var j = 0; j < group.length; j++) {
		if (group[j].groupId=="cwc") {
			$("#g_cwc").attr("checked","checkded");
		}else if (group[j].groupId=="wlzx") {
			$("#g_wlzx").attr("checked","checkded");
		}else if (group[j].groupId=="xcb") {
			$("#g_xcb").attr("checked","checkded");
		}
	}
}