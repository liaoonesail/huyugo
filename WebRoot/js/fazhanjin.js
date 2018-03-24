
$(function () {
    $.ajaxSetup({
        async: false
    });
    $(".fazhanjin").click(function () {
        $(".fazhanjin").css({"background-color": "#1E9FFF"});
        $(this).css({"background-color":"yellow"});
        $("#fazhanhead").attr("value",$(this).attr("myturn"));
    });

    $(".jiameng").click(function () {

    });
});
function yanzheng2(){
    var payMoney=0;
    var title=$("#fazhanhead").attr("value");
    var account=$("#account").html();
    var yue=$("#yue").val();
    if(yue==null||yue==""){
    yue=0;
    $("#yue").val(yue);
    }
    if(title==1) payMoney=100;
    else if(title==2) payMoney=500;
    else if(title==3) payMoney=1000;
    else if(title==4) payMoney=5000;
    else if(title==5) payMoney=10000;
    else if(title==6) payMoney=50000;
    else if(title==7) payMoney=100000;

    if(title==0){
        alert("请选择要加盟的金额")
        return false;
    }else{
        if(account-yue<0){
            alert("余额不足")
            return false;
        }
        if(yue-payMoney>0){
            alert("输入余额超过加盟金额")
            return false;
        }
        var x=false;
        $("#title").val(title);
        $.post(
            "../fazhanOrder/istrue.do",
            {"title":title},
            function (data) {
               if(data==false){
                   alert("总加盟金额超过10万");
               }
               x=data;
            },
            "json"
        );
        return x;
    }
}