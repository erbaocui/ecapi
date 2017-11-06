$.extend($.fn.validatebox.defaults.rules, {

    CheckSupplier:{
        validator:function(value, param){
            var result = true;
            var test=/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
            if(!test){
                result = false;
                $.fn.validatebox.defaults.rules.CheckSupplier.message = '请输入手机号码';
                return result;
            }
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/supplier/exist.do",
                    dataType:"json",
                    data:{"mobile":value},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckSupplier.message ="供应商手机号已存在";
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