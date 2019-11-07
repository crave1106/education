/**
 * 
 * @param {*} ele 图片上传按钮
 * @param {*} url 图片上传地址
 */
function upImg(ele, url){
    layui.use('upload', function () {
        var $ = layui.jquery
            , upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#' + ele
            , url: url
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#' + ele).attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                // var demoText = $('#demoText');
                // demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                // demoText.find('.demo-reload').on('click', function () {
                //     uploadInst.upload();
                // });
            }
        })
    })
}

// 修改必填项提示
function changeRequiredTip(){
    layui.use('form', function () {
        var $ = layui.jquery,
            form = layui.form;
        form.verify({
            required: function (value, item) { 
                if(!value){
                    var text = $(item).parent('.diy-required').siblings('.layui-form-label').text();
                    if (text.substr(-1, 1) == '：' || text.substr(-1, 1) == ':') {
                        text = text.substr(0, (text.length - 1));
                    }
                    return text+'不能为空';
                }
            }
        })
    })
}

function diyEasyQuery(ele,callback) {
    layui.use(['form', 'jquery'], function () {
        var $ = layui.jquery;
        
        $('#'+ ele).on('keypress', function (e) {
            if (e.keyCode == 13) {
                callback()
            }
        })
        $('.diy-clear-focus').on('focus',function (){
            if ($('#multSearchForm').hasClass('active')) {
                $('#showMultSearch').trigger('click')
            }
        })

        $('.diy-searct-icon').click(function (){
            if ($('#multSearchForm').hasClass('active')) {
                $('#showMultSearch').trigger('click')
            }
            callback();
        })
    })
}

/**
 * 
 * 简易查询日期快捷查询
 * @param  $ 传入jquery 
 * @param  callback 返回函数，数据处理
 */

function diyQueryDay(callback){
    layui.use(['jquery', 'laydate'], function () {
        var $ = layui.jquery;
        $('.diy-query-day').on('click', 'a', function () {
            if ($('#multSearchForm').hasClass('active')) {
                $('#showMultSearch').trigger('click')
            }
            var type = parseFloat($(this).attr('data-type'));
            var dates = DateUtil.getDateRange(type);
            var obj = {
                startTime: dates.startDate,
                endTime: dates.endDate
            }
            $('.diy-query-day a').removeClass('active');
            $(this).addClass('active');
            return callback(dates)
        })
    })
    
}

/**
 * 
 * @param {*} ele 日期范围id
 * @param {*} value 日期初始化范围值
 * @param {*} callback 返回函数
 */
function dateRange(ele,value,callback){
    layui.use(['form', 'laydate'],function (){
        var laydate = layui.laydate;
        laydate.render({
            elem: "#" + ele,
            value: value,
            isInitValue: true,
            range: '~',
            type: 'date',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                callback(value, date, endDate)
            }
        })
    })
}

// 高级搜索展现
function showMultSearch() {
    layui.use(['form', 'jquery'], function () {
        var $ = layui.jquery;
            form = layui.form;
        $('#showMultSearch').click(function (){
            if ($('.multSearchForm').hasClass('active')){
                $('.multSearchForm').removeClass('active');
                $('.multSearchBtn-wrap i').removeClass('layui-icon-up');
                $('[clear]').val('');
                
            }else{
                $('.diy-query-day a').removeClass('active');
                form.render('select');
                $('.multSearchBtn-wrap i').addClass('layui-icon-up');
                $('.multSearchForm').addClass('active');
                $('[clear1]').val('');
                form.render('select');
            }
        })
    })
}

// 高级查询清除
function clearForm(){
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        $('[clear],[clear1]').val('');
    })
}

/**
 * 
 * @param {*} ele 表单参数
 * @param {*} param 表单配置参数
 * @param {*} callback 返回函数
 */
function tableList(ele, param, callback){
    layui.use(['table'], function () {
        var $ = layui.jquery,
            table = layui.table;
        if (!param.where){
            param.where = {}
        }
        if (!param.page) {
            param.page = false
        }
        if (!param.totalRow){
            param.totalRow = false
        }
        if (!param.request){
            param.request = {
                pageName: 'pageNo' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        }
        if (!param.parseData){
            param.parseData = function (res){
                if (res.code == 1) {
                    return {
                        "code": '0',
                        "count": res.data.total,
                        "data": res.data.list
                    }
                }
            }
        }
        table.render({
            elem: '#' + ele
            , url: param.url //数据接口
            , method: 'post'
            , where: param.where
            , height: 'full-' + ($('.layui-header').height() + $('.pub-head').height()) + '-20'
            , contentType: "application/x-www-form-urlencoded;charset=UTF-8"
            , page: param.page
            , request: param.request
            , totalRow: param.totalRow
            , cols: [param.cols]
            , parseData: function (res) {
                return param.parseData(res)
            }
            , done: function (res, curr, count) {
                if(callback){
                    callback(res, curr, count);
                }
                
            }
        });
    })
}

// 关闭自定义模态框弹出窗
function closeModel(){
    layui.use(['jquery'], function () {
        var $ = layui.jquery;

        $('.pub-model .model,.pub-model .close').click(function () {
            $('.pub-model').css('display', 'none');
        })
        $('.pub-model .img').click(function (e) {
            e.stopPropagation();
            e.cancelBubble = true;
        })
    
    })
    
}