$.extend($.fn.validatebox.defaults.rules, {

    CheckCar:{
        validator:function(value, param){
            var result = true;
            var test= /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/.test(value);
            if(!test){
                result = false;
                $.fn.validatebox.defaults.rules.CheckCar.message = '请输入车辆号牌';
                return result;
            }
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/car/exist.do",
                    dataType:"json",
                    data:{"plateNumber":value},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckCar.message ="车牌已存在";
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