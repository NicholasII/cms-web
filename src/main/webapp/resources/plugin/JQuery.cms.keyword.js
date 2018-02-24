(function($){
	$.fn.keywordinput = function(opts) {
		opts = $.extend({
			msg:'请输入关键字，通过逗号或者回车确认',
			num:5,
			autocomplete:{
				enable:false,
				source: "#",
		        minLength: 2
			}
		},opts||{});
		init(this);
		$(this).keydown(inputKeyword);
		if (opts.autocomplete.enable) {
			$(this).autocomplete({
		    	source: opts.autocomplete.source,
		        minLength: opts.autocomplete.minLength
		    });
		}
		/**
		 * 通过事件委派处理
		 */
		$("#keyward-wrap").on("click","a.keyward-shut",function(event){
			$(this).parent(".keyward-in").remove();
			event.preventDefault();
		});
		
		
		function inputKeyword(event){
			var code = event.keyCode;
			if(code==13 || code==188){	
				var key = $(this).val();
				if (key!="" && key!=null) {
					if($(".keyward-in").length>=opts.num){
						alert("最多只能添加"+opts.num+"个关键字！");
						event.preventDefault();
						return;
					}
					var node = createKeywordNode(key);
					$("#keyward-wrap").append(node);
					$(this).val("");
					event.preventDefault();
				}else {
					alert("请输入内容！");
					event.preventDefault();
				}
				
			}
		}
		function init(element) {
			$(element).val(opts.msg);
			$(element).addClass("keyward-input");
			$(element).wrap("<div id='keyward-container'></div>").before("<div id='keyward-wrap'></div>");
			$(element).focus(function(){
				$(this).val("");
				$(this).css("border","none");
			})
			$(element).blur(function(){
				$(this).val(opts.msg);
			})
		}
		function createKeywordNode(value){
			return '<div class="keyward-in"><span>'+value+'</span><a href="#" class="keyward-shut">x</a><input type="hidden" name="keyward" value="'+value+'"></div>';
		}

	}
	
	
})(jQuery)