function autocom(path){
    var parkList = new Array();
    $.ajax({
        type: "POST",
        url: path+"/parkCon/queryPark",
        dataType: "json",
        async: false,   //同步
        success: function(data){
            if(data && data.code == 1 && data.data){
                $.each(data.data,function (k,v) {
                    parkList.push({
                        value : v.id,
                        label : v.name
                    });
                });
            }else{
                ZENG.msgbox.show("停车场列表获取失败", 5, 1000);
            }
        }
    });

    $("#park").autocomplete({
        selectFirst : true,
        highlight : true,
        minLength : 0,
        source : parkList,
        scrollHeight: 350,
        select : function(event, ui) {
            $(this).val(ui.item.label);
            $("#parkId").val(ui.item.value);
            return false;
        },
        change:function () {
            if(!$("#park").val()){
                $("#parkId").val("");
            }
        }
    }).focus(function() {
        $(this).autocomplete("search", "");
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        return $("<li>").append(item.label).appendTo(ul);
    };
}