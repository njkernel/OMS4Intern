$(document).ready(function(){
    function checkAll(){
    if ($(':checkbox').is(':checked')) {
        if($("input:checked").length === 1){
            //选中一个，启用相应按钮
           $("#return").attr('disabled',false);
            $("#exchange").attr('disabled',false);
            $("#MyAbnormalModel").attr('disabled',false);
            $("#outstock").attr('disabled',false);
            }
        else {
            $("#return").attr('disabled',true);
            $("#exchange").attr('disabled',true);
            $("#MyAbnormalModel").attr('disabled',true);
            $("#outstock").attr('disabled',true);
      }
    }
      else {
          $("#exchange").attr('disabled',true);
          $("#return").attr('disabled',true);
          $("#MyAbnormalModel").attr('disabled',true);
          $("#outstock").attr('disabled',true);
      }
   }
   $(document).on('click',"input[type='checkbox']",function(){
       checkAll(this);
   });
   // $("input[type='checkbox']").click(function(){
   //     checkAll(this);
    //    var flag = $(this).prop("checked"); //先记录下点击后应该的状态
    //    $("input[type='checkbox']").prop("checked", false);
    //    $(this).prop("checked", flag);
    //    });
    window.onload=checkAll;
});