function paging(page) {
    $("#tbody").html("");
    $.ajax({
        ContentType:"application/json;charset=UTF-8",
        type:"post",
        url:"/getAllRefundIndex",
        data:{"page":page,"size":4},
        success:function (msg) {
            var refundList=msg["refundList"];
            var page=msg["page"];
            var pageCount=msg["pageCount"];
            $("#page").html(page);
            $("#pageCount").html(pageCount);
            for(var refund in refundList){
                var a=refundList[refund].returnId;
                if(a==null){
                    var a="无";
                }
                document.getElementById("tbody").innerHTML+="<tr>" +
                    "<td>"+refundList[refund].refundId+"</td>" +
                    "<td><input type='checkbox' name='' id=''></td>" +
                    "<td>"+refundList[refund].refundCode+"</td>" +
                    "<td>"+refundList[refund].refundPrice+"</td>" +
                    "<td>"+refundList[refund].refundState+"</td>" +
                    "<td>"+a+"</td>" +
                    "<td>"+refundList[refund].orderId+"</td>" +
                    "<td>"+refundList[refund].createtd+"</td>" +
                    "<td>"+refundList[refund].updated+"</td>" +
                    "<td>"+refundList[refund].modifiedUser+"</td>" +
                    "</tr>";
            }

        }
    })

}
function mySearch() {
    var select=$("#select").val();
    var mySelect=$("#mySelect").val();
    if(select=="--请选择--"||select==""||mySelect==null||mySelect==""){
        alert("选择条件不可为空");
    }
    else if(select=="refundState"&&mySelect!="退款成功"&&mySelect!="待退款"){
        alert("退款单的状态为‘退款refundState成功’或者‘待退款’");
    }
    else{
        $("#frm").submit();
    }

}
function refund() {
    var confirm_ = confirm('确认？');
    var arr=[];//定义一个数组
    var a=$('input[name="checkid"]:checked');
    $('input[name="checkid"]:checked').each(function(){
        arr.push($(this).val());
    });
    if(arr.length>0){
        if(confirm_){
            $.ajax({
                ContentType:"application/json;charset=UTF-8",
                type:"post",
                url:"/index/refund",
                data:{"refundIdList":arr.join(",")},
                success:function (msg) {
                    if(msg=="success"){
                        alert("退款成功")
                        location.reload() ;
                    }else {
                        alert("退款失败");
                    }
                }
            })
        }
    }else {
        alert("你还没进行选择");
    }
}

function queryRefund() {
    var confirm_ = confirm('确认？');
    var select=$("#select").val();
    var mySelect=$("#mySelect").val();
    if(confirm_){
        if(select==null||select==""){
            alert("搜索的必要条件不可为空");
        }else if(mySelect==null||mySelect==""){
            alert("搜索的必要条件不可为空");
        }else{
            $.ajax({
                ContentType:"application/json;charset=UTF-8",
                type:"post",
                url:"",
                data:{"selectFirst":select,"selectSecond":mySelect},
                success:function (msg) {
                    if(msg=="success"){
                        alert("退款成功")
                        window.location.href="/getRefund";
                    }else {
                        alert("退款失败");
                    }
                }
            })
        }


    }

    /*function detailRefund() {
        var arr=[];//定义一个数组
        var b=$("input[type='checkbox']:checked").length
        var a=$('input[name="checkid"]:checked').val();
        if(b==1){
            $("#iframe").attr("src","/refundDetail?refundId="+a);
        }else{

        }

    }*/
}