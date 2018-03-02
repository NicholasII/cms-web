/**
 * 首页图片
 */
var upload_file_url = ctx + "/system/indexPic/uploadImg"


$(function () {
	var xsize,ysize;
	var jcrop_api,boundx,boundy;
	var x,y,w,h;
	$("#file_upload").uploadify({
		'auto':false,
		'multi' : false,
		'swf' : ctx + '/resources/lib/uploadify/uploadify.swf',
		'uploader' : upload_file_url,
		'method' : 'post',
		'fileObjName' : 'indexPic',
		'fileTypeExts':"*.jpg;*.gif;*.png",
		'onUploadSuccess' : function(file, data, response) {
			var result = eval("("+data+")");
			var status = result.state;
			if (status=="success") {
				var indexPicDto = result.indexPicDto;
				addImage(indexPicDto);
			}else {
				var msg = result.message;
				alert(msg);
			}	
        }
	});
	
	$("#uploadFile").click(function() {
		$("#file_upload").uploadify("upload","*");
	})
	
	function addImage(indexPic){
		var filename  = indexPic.newName; 
		var indexPicWidth = indexPic.indexPicWidth;
		var indexPicHeight = indexPic.indexPicHeight;
		xsize = indexPicWidth;
		ysize = indexPicHeight;
		var url = ctx + "/resources/upload/indexPic/temp/"+filename; 
		
		var cropView = $("<div id='cropview' style='width:"+indexPicWidth+"px;height:"+indexPicHeight+"px;overflow:hidden;margin-bottom:5px;'><img id='cropimg' src='"+url+"' class='jcrop-preview'/></div>");
		$("#indexPicView").append(cropView);	
		
		$("#indexPicView").append("<div id='preview'><img id='preimg' src='"+url+"'/></div><input type='button' value='确定选择' id='confirmSelect'/>");
		$("#preimg").Jcrop({
			aspectRatio:indexPicWidth/indexPicHeight,
			onChange: updatePreview,
		    onSelect: updatePreview,
			setSelect:[0,0,indexPicWidth,indexPicHeight]
		},function(){
		      // Use the API to get the real image size
		      var bounds = this.getBounds();
		      boundx = bounds[0];
		      boundy = bounds[1];
		      // Store the API in the jcrop_api variable
		      jcrop_api = this;

		      // Move the preview into the jcrop container for css positioning
		      
		    });
		$("#confirmSelect").click(confirmSelect);
		
	}
	
	function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        var rx = xsize / c.w;
        var ry = ysize / c.h;
        x = c.x;
        y = c.y;
        w = c.w;
        h = c.h;
        $("#cropimg").css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };
    
    
    function confirmSelect() {
		alert("x:"+x+"y:"+y+"w:"+w+"h:"+h);
	}
})
	