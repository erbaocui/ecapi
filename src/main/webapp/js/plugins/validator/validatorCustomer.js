$.extend($.fn.validatebox.defaults.rules, {
    CheckCustomerPw:{
        validator: function (value, param) {
            return /^[a-zA-Z0-9]{5,15}$/i.test(value);
        },
        message:'密码不合法（允许6-16字节，允许字母数字）'
    },

    CheckCustomer:{
        validator:function(value, param){
            var result = true;
            var  test;
            if( $(param[0]).val()=='0'){
                test=/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
                if(!test){
                    result = false;
                    $.fn.validatebox.defaults.rules.CheckCustomer.message = '请输入手机号码';
                    return result;
                }

            }else{
               test = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value);
                if(!test){
                    result = false;
                    $.fn.validatebox.defaults.rules.CheckCustomer.message = '请输邮箱';
                    return result;
                }
            }

            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/customer/exist.do",
                    dataType:"json",
                    data:{"loginName":value,"type":$(param[0]).val()},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckCustomer.message ="客户已存在";
                            result = false;
                        }
                    }
                });
            }
           return result;


        },
        message:"{0}"
    }
});