$(function(){
    var url = location.search;
    $.ajax({
        url:"/pushPmApi?mobile=15881677315",
        type:"post",
        data:{
            "nowPages":"2",
            "countPages":"4"
        },
        success:function (data) {
            alert(data.status);
            var arry = [];
            arry = data.data;
            var html = "";
            for(var i=0;i<arry.length;i++){
                html += "<div class=\"tr clearfix border-bottom-none\" >"
                html += "<div class='td w10'>";
                html += arry[i].did;
                html += "</div>";
                html += "<div class='td w10'>";
                html += arry[i].msg_TPL_ID;
                html += "</div>";
                html += "<div class='td w15'>";
                html += arry[i].msisdn;
                html += "</div>";
                html += "<div class='td w40'>";
                html += arry[i].msg_CONTENT;
                html += "</div>";
                html += "<div class='td w15'>";
                html += arry[i].record_TIME;
                html += "</div>";
                html += "<div class='td w10'>";
                html += arry[i].result;
                html += "</div>";
                html += "</div>";
            }
            $("#table-body").html(html);
        },
        error: function(msg){
            alert("ajax连接异常："+msg);
        }
    })
})