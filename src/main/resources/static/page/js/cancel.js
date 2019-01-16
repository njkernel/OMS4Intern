function cancel() {
    var confirm_ = confirm('确认？');
    var arr=[];//定义一个数组
    var a=$('input[name="checkid"]:checked');
    a.each(function(){
        arr.push($(this).val());
    });
    if(arr.length>0){
        if(confirm_){
            $.ajax({
                ContentType:"application/json;charset=UTF-8",
                type:"post",
                url:"/cancelOrder",
                data:{"refundIdList":arr.join(",")},
                success:function (msg) {
                    if(msg=="success"){
                        alert("取消成功")
                        location.reload() ;
                    }else {
                        alert("取消失败");
                    }
                }
            })
        }
    }else {
        alert("你还没进行选择");
    }


}