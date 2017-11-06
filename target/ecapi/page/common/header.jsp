
<meta charset="UTF-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/icon.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/demo/demo.css" />
<%--<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/plugins/uploadfiy/uploadify.css" />--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/plugins/jquery.combobox.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-rule.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-method.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorUser.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCustomer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorSupplier.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorDriver.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorRole.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/datagrid.js"></script>
<%--
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
--%>

</head>


<script type="text/javascript">
    function getContextPath() {
        // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
        var currentPath = window.document.location.href;
        // 获取主机地址之后的目录，如： /ssm/index.jsp
        var pathName = window.document.location.pathname;
        var pos = currentPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8080
        var localhostPath = currentPath.substring(0, pos);
        // 获取带"/"的项目名，如：/ssm
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
       /* alert("currentPath:" + currentPath + "\n"
        + "pathName:" + pathName + "\n"
        + "localhostPath:" + localhostPath + "\n"
        + "projectName:" + projectName + "\n"
        + "contextPath:"+ localhostPath + projectName);*/
        return(localhostPath + projectName);
    }

    function localhostPath() {
        // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
        var currentPath = window.document.location.href;
        // 获取主机地址之后的目录，如： /ssm/index.jsp
        var pathName = window.document.location.pathname;
        var pos = currentPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8080
        var localhostPath = currentPath.substring(0, pos);
        // 获取带"/"的项目名，如：/ssm
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        /* alert("currentPath:" + currentPath + "\n"
         + "pathName:" + pathName + "\n"
         + "localhostPath:" + localhostPath + "\n"
         + "projectName:" + projectName + "\n"
         + "contextPath:"+ localhostPath + projectName);*/
        return(localhostPath);
    }
    
    var commonFormatter=
    {
        dateTime: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
           // return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute ;
        },
        time: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var milliSecond = date.getMilliseconds();

            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            if(milliSecond>10&&milliSecond<100){
                milliSecond= '0' + milliSecond
            }
            milliSecond =  milliSecond < 10 ? ('00' + milliSecond) : milliSecond;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second+':' + milliSecond;

        },
        status: function (value, rec, index) {
            if (value == 0) return decodeURI('%E6%88%90%E5%8A%9F');//成功
            if (value == 1) return decodeURI('%E5%A4%B1%E8%B4%A5');//失败
            return decodeURI('');

        }

    }

</script>
