function cancel() {
    var confirm_ = confirm('确认？');
    var arr=[];//定义一个数组
    arr=orderList.checkedNames;
    if(arr.length>0){
        if(confirm_){
            $.ajax({
                ContentType:"application/json;charset=UTF-8",
                type:"post",
                url:"/index/cancelOrder",
                data:{"orderIdList":arr.join(",")},
                success:function (msg) {
                    if(msg=="success"){
                        location.reload() ;
                        alert("取消成功")
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