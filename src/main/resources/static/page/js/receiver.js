
function edit() {
    $("#receiver_name").attr("disabled",false);
    $("#receiver_address").attr("disabled",false);
    $("#receiver_city").attr("disabled",false);
    $("#receiver_district").attr("disabled",false);
    $("#receiver_state").attr("disabled",false);
    $("#receiver_mobile").attr("disabled",false);

}
function not() {
    $("#receiver_name").attr("disabled",true);
    $("#receiver_address").attr("disabled",true);
    $("#receiver_city").attr("disabled",true);
    $("#receiver_district").attr("disabled",true);
    $("#receiver_state").attr("disabled",true);
    $("#receiver_mobile").attr("disabled",true);
}
function notEdit() {
    var receiver_id=$("#receiver_id").val();
    $("#receiver_name").attr("disabled",true);
    $("#receiver_address").attr("disabled",true);
    $("#receiver_city").attr("disabled",true);
    $("#receiver_district").attr("disabled",true);
    $("#receiver_state").attr("disabled",true);
    $("#receiver_mobile").attr("disabled",true);

    $.ajax({
        ContentType:"application/json;charset=UTF-8",
        type:"post",
        url:"/index/cancelEdit",
        data: {"receiverId":receiver_id},
        success:function (msg) {
            var receiver=msg["receiver"];
            $("#receiver_name").val(receiver.receiverName);
            $("#receiver_address").val(receiver.receiverAddress);
            $("#receiver_city").val(receiver.receiverCity);
            $("#receiver_district").val(receiver.receiverDistrict);
            $("#receiver_state").val(receiver.receiverState);
            $("#receiver_mobile").val(receiver.receiverMobile);

        }

    })

}


function editReceiver() {
    var receiverName=$("#receiver_name").val();
    var receiverAddress=$("#receiver_address").val();
    var receiverCity=$("#receiver_city").val();
    var receiverDistrict=$("#receiver_district").val();
    var receiverState=$("#receiver_state").val();
    var receiverMobile=$("#receiver_mobile").val();
    var receiverId=$("#receiver_id").val();
    var confirm_ = confirm('确认？');
    var re = /^1\d{10}$/;
    if(confirm_){
        if(!re.test(receiverMobile)){
            alert("请注意手机格式");
        }
        else if(receiverAddress==null||receiverAddress==""){
            alert("请注意详细地址不可为空");
        }
        else if(receiverCity==null||receiverAddress==""){
            alert("城市不可为空");
        }
        else if(receiverName==null||receiverMobile==""){
            alert("名字不可为空");
        }
        else if(receiverDistrict==null||receiverDistrict==""){
            alert("区不可为空");
        }
        else if(receiverState==null||receiverState==""){
            alert("省不可为空");
        }
        else{
            $.ajax({
                ContentType:"application/json;charset=UTF-8",
                type:"post",
                url:"/index/editReceiverInformation",
                data: {"receiverName" :receiverName,"receiverAddress":receiverAddress,"receiverCity":receiverCity,
                    "receiverDistrict":receiverDistrict,"receiverState":receiverState,"receiverMobile":receiverMobile,
                    "receiverId":receiverId},
                success:function (msg) {
                    if(msg=="success"){
                         not();
                        alert("编辑成功");
                    }else {
                        alert("编辑失败");
                    }
                }
            })
        }

    }
}


