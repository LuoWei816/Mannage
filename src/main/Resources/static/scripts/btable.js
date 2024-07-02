$(function() {
    load();
    //年-月-日格式
    $('#startTime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    })

    $('#endTime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    })
    //模态框提交
    $('#submitModal').on('click', function() {
        submitModal();
    });
    $('#myModal').on('show.bs.modal', function (){
        $('#select-params').val('');
        $('#select-params').selectpicker('refresh');
    });
    //模态框清空
    $('#myModal').on('hidden.bs.modal', function (e) {
        $('#import-form')[0].reset();
    }).modal('hide');
    //多选框赋值至隐藏输入框
    $('#select-params').change(function(){
        $("input[name=selectParamsArrays]").val($("#select-params").val())
    })
    $('#select-params').selectpicker('refresh');
})


function queryParamsSet() {
    var params = {
        mobile:$("#mobile").val(),
        did:$("#did").val(),
        msg_tpl_id:$("#tplId").val(),
        startTime:$("#startTime").val(),
        endTime:$("#endTime").val()
    }
    $("input[name=queryParams]").val(JSON.stringify(params));
}

function submitModal() {
    if($("input[name=messageCount]").val()==0){
       $('#emptyNotice').modal("show");
    }else{
        var messageForm = $('#import-form').serialize();
        $.ajax({
            type:"GET",
            url:"/fileImport",
            data:messageForm,
            success:function (response) {
                    alert(response.result);
            },
            error:function (xhr, status, error) {

            },
        })
    }
    $('#myModal').modal('hide');
}



function mySearch(){
    $('#myTable').bootstrapTable('refresh');
}

function responseHandler(res) {
    queryParamsSet();
    $("input[name=messageCount]").val(res.total);
    return {
        "rows": res.data, // 具体每一个bean的列表
        "total": res.total // 总共有多少条返回数据
    }
}
function load() {
    var rows = 5;
    $('#myTable').bootstrapTable({
        url : "/pushPmApi", // 请求的后台URL（*）
        method : 'GET', // 请求方式：get/post（*）
        showRefresh : true, // 是否显示刷新按钮
        showToggle : true, // 是否显示详细视图和列表视图的切换按钮
        showColumns : false, // 是否显示列操作按钮
        detailView : true, // 是否显示详细视图
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        // queryParamsType : "limit",
        // 设置为limit则会发送符合RESTFull格式的参数
        singleSelect : true, // 设置为true将禁止多选
        clickToSelect : true, // 是否启用点击选中行
        // contentType : "application/x-www-form-urlencoded",
        // 发送到服务器的数据编码类型
        pageSize: rows,
        pageNumber : 1, // 如果设置了分布，首页页码
        search : true, // 是否显示搜索框
        toolbar:'#toolbar',
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams : function(params) {
            return {
                // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order, //排位命令（desc，asc）
                mobile: $("#mobile").val(),
                did:$("#did").val(),
                msg_tpl_id:$("#tplId").val(),
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val()
            };
        },
        // 请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
        // queryParamsType = 'limit' ,返回参数必须包含
        // limit, offset, search, sort, order 否则, 需要包含:
        // pageSize, pageNumber, searchText, sortName,
        // sortOrder.
        // 返回false将会终止请求
        columns : [ {
            title : '电话号码',
            field : 'msisdn',
            align : 'left',
            valign : 'center',
            width : '5%',
          /*  formatter : function(value, row, index) {
                return index + 1;
            }*/

        }, {
            title : '模板ID',
            field : 'did',
            align : 'left',
            valign : 'center',
            width : '5%'

        }, {
            title : '端口',
            field : 'msg_PORT',
            align : 'left',
            valign : 'center',
            width : '10%',
          /*  cellStyle : function(value, row, index) {
                return {
                    css : {
                        "word-wrap" : "break-word",
                        "word-break" : "normal"
                    }
                };
            }*/

        }, {
            title : '短信内容',
            field : 'msg_CONTENT',
            align : 'left',
            valign : 'center',
            width : '20%'

        }, {
            title : '记录时间',
            field : 'record_TIME',
            align : 'left',
            valign : 'center',
            width : '20%'

        }, {
            title : '发送结果',
            field : 'result',
            align : 'left',
            valign : 'center',
            width : '20%'

        }],
        responseHandler: responseHandler,
    });
}