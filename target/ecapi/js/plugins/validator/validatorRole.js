$.extend($.fn.validatebox.defaults.rules, {

    CheckRole:{
        validator:function(value, param){
            var result = true;
            var  test=/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/i.test(value);
            if(!test){
                result = false;
                $.fn.validatebox.defaults.rules.CheckRole.message = '角色key不合法（字母开头，允许4-16字节，允许字母数字下划线）'
                return result;
            }
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/role/exist.do",
                    dataType:"json",
                    data:{"kv":value},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckRole.message ="角色已存在";
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