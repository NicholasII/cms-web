/**
 * 栏目管理
 */
//删除栏目
var id,pid;
function deleteChannel(id,pid){
    this.id = id;
    this.pid = pid;
    
    var response = confirm("删除是不可逆操作，确定要删除吗？");  
    if(response = true){
        deleteConfirm();
    }
}
function deleteConfirm(){
    var url = ctx + "/channel/delete/"+ id+"/"+pid;
    formSubmit(url,[]);
}
function confirm1(id,message, callback) {
    $('#'+id).modal({
        closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
        position: ["20%","50%"],
        overlayId: 'confirm-overlay',
        containerId: 'confirm-container', 
        onShow: function (dialog) {
            var modal = this;

            $('.message', dialog.data[0]).append(message);

            $('.yes', dialog.data[0]).click(function () {

                if ($.isFunction(callback)) {
                    callback.apply();
                }

                modal.close();
            });
        }
 })
}
