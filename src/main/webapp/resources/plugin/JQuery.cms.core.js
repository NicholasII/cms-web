(function($){
	$.fn.extend({
		"commonulli":function(options){
			options=$.extend({
				selected:"navSelected",
				titleTagName:"h3"
			},options||{});
			var titleNode = $(this).find("ul>"+options.titleTagName);
			var selectNode = $(this).find("ul."+options.selected+">"+options.titleTagName);
			titleNode.css("cursor,pointer");
			titleNode.nextAll().css("display","none");
			selectNode.css("display","block");
			titleNode.click(function(){
				var isCheck = $(this).parent().hasClass(options.selected);
				if(isCheck){
					$(this).parent().removeClass(options.selected);
					$(this).nextAll().slideUp();
				}else{
					$(this).parent().addClass(options.selected);
					$(this).nextAll().slideDown();
				}
			});
			return this;
		},
		"myZtree":function(options,zNodes){
			var setting = $.extend({
				view: {
					dblClickExpand: false,
					selectedMulti: false
				}
			},options||{});
			var zTreeObj = $.fn.zTree.init($(this), setting, zNodes);
		}
	});
})(jQuery)