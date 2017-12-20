/**
 * 通用js工具包
 */
/**
 * 模拟表单提交
 * @param {} url
 * @param {} data
 * data范式：data[{name:n1,value:v1},...,{name:nn,value:vn}]
 */
function formSubmit(url,data){
    var form = $("<form></form>").attr("action", url).attr("method", "post");
    for(var i=0;i<data.length;i++){
        form.append($("<input></input>").attr("type", "hidden").attr("name",data[i].name).attr("value", data[i].value));
    }
    form.appendTo('body').submit().remove();
}