/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"接口日志查询",
        toolbar:"#tb",
        url:getContextPath()+"/actionlog/list.do",
        method:"post",
        columns:[[
            {field:'id',title:'',width:0,hidden:true},
            {field:'actionTime',title:'请求时间',width:"12%", formatter:commonFormatter.time},
            {field:'loginName',title:'登录名',width:"8%"},
            {field:'actionUrl',title:'请求url',width:"15%"},
            {field:'token',title:'token',width:"17%"},

            {field: 'status', title: '状态', width:"3%",
                formatter: commonFormatter.status
            },
            {field:'requestParam',title:'请求参数',width:"20%"},
            {field:'responseParam',title:'返回参数',width:"20%"},
            {field:'_opation',title:"操作",width:"4%",align:'center',
                formatter:function(value,row,index){
                    return '<a href="#" class="easyui-linkbutton" onclick="showloginfo(\''+index+'\')">详情</a>';
                }
            },
            {field:'executeTime',title:'',width:0,hidden:true},
            {field:'loginId',title:'',width:0,hidden:true},
            {field:'errorStack',title:'',width:0,hidden:true}

        ]],
        singleSelect: true,//单选
        checkOnSelect:false,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "loginName": $("#qryLoginName").val(),
            "beginTime":$("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "token": $("#qryToken").val(),
            "status":$("#qryStatus").val()
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this).datagrid('appendRow', { loginName: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'loginName', colspan:5 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            }
        }
    });

    $('#dg').datagrid('getPager').pagination({
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50],
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    //搜索按钮
    $('#searchUser').click(function(){
        $("#dg").datagrid('load', {
            "loginName": $("#qryLoginName").val(),
            "beginTime":$("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "token": $("#qryToken").val(),
            "status":$("#qryStatus").val()
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
    });







});

//信息详情
function showloginfo(index){

    var row = $('#dg').datagrid('getData').rows[index];
    $('#fm').form('load', row);
    //$("#loginName").val(row.loginName);
    $("#actionTime").val(commonFormatter.time( row.actionTime));
    $("#dlg").dialog({title: "日志信息",modal:true});
    $("#dlg").dialog("open");
}





