/**
 * 添加用户
 */
// 只能输入英文
jQuery.validator.addMethod("english", function(value, element) {
    var chrnum = /^([a-zA-Z]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入字母");
//只能输入英文
jQuery.validator.addMethod("pass2", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入字母和数字的组合");

$("#adduser").validate({
	rules : {
		id : {
			required : true,
			english : true
		},
		name : {
			required : true,
		},
		origin_password:{
			required : true,
		},
		password : {
			required : true,
			pass2:true,
			maxlength : 16,
			minlength : 6,
		},
		password_confirm : {
			required : true,
			equalTo : "#password",
			maxlength : 16,
			minlength : 6,
			pass2:true,
		},
		tel : {
			required : true,
			maxlength : 11,
			minlength : 11,
			number : true
		},
		mobile : {
			required : true,
			maxlength : 11,
			minlength : 11,
			number : true
		},
		role : {
			required : true
		},
		group : {
			required : true
		}
	},
	messages : {
		password_confirm : {
			equalTo : "两次输入密码不同"
		},
		role : {
			required : "至少选择一个角色"
		},
		group : {
			required : "至少选择一个分组"
		}
	}
});
