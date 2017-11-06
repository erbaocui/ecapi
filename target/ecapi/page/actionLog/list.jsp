<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="list.js"></script>

</head>
<body style="margin:1px;">
<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>

            <tr>

                <td>&nbsp;token：&nbsp;<input type="text" id="qryToken" size="40"/><td>
                <td>&nbsp;登录名：&nbsp;<input type="text" id="qryLoginName" size="20"/><td>
                <td>&nbsp;开始时间：&nbsp;<input type="text" id="qryBeginTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><td>
                <td>&nbsp;结束时间：&nbsp;<input type="text" id="qryEndTime" size="20"  class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus" name="qryStatus" >
                        <option value="-1">全部</option>
                        <option value="0">成功</option>
                        <option value="1">失败</option>
                    </select>
                <td>
                <td><a id="searchUser" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>

            </tr>
        </tabel>
    </div>


</div>

<div id="dlg" class="easyui-dialog"
     style="width: 800px;height:700px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id">
        <table cellspacing="8px">
            <tr>
                <td>token：</td>
                <td><input type="text" id="token"  name="token"size="50" disabled="disabled"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="loginName" name="loginName" size="50" disabled="disabled"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>用户id：</td>
                <td><input type="text" id="loginId" name="loginId" size="50" disabled="disabled"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>请求时间：</td>
                <td><input type="text" id="actionTime"  name="actionTime" size="50" disabled="disabled"/>&nbsp;
                </td>
            </tr>
            <tr >
                <td>请求URL：</td>
                <td><input type="text" id="actionUrl"  name="actionUrl" size="50" disabled="disabled"/>&nbsp;

                </td>
            </tr>
            <tr >
                <td>执行时间：</td>
                <td><input type="text" id="executeTime"  name="executeTime" size="50" disabled="disabled"/>&nbsp;

                </td>
            </tr>
            <tr>
                <td>状&nbsp;&nbsp;&nbsp;态：</td>
                <td>
                    <select id="status" name="status" disabled="disabled">
                        <option value="-1"  selected="selected" >全部</option>
                        <option value="0" >成功</option>
                        <option value="1">失败</option>
                    </select>
                </td>
            </tr>
            <tr>
            <tr>

                <td>请求参数：</td>
                <td><textarea id="requestParam" name="requestParam" rows="4" cols="90" disabled="disabled"></textarea>&nbsp;</td>
            </tr>
            <tr>

                <td>返回参数：</td>
                <td><textarea id="responseParam" name="responseParam" rows="4" cols="90" disabled="disabled"></textarea>&nbsp;</td>
            </tr>



                <td>错误堆栈信息：</td><td></td>

            </tr>

            <tr>


                <td colspan="2"><textarea id="errorStack" name="errorStack"  rows="10" cols="110"disabled="disabled"></textarea>&nbsp;</td>
            </tr>
        </table>
    </form>
</div>





<div id="dlg-buttons">
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>