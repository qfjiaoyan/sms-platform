$(function () {
    var option = {
        url: '../sys/search/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        toolbar: '#toolbar',
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                rows: params.limit,   //页面大小
                start: params.offset,  //页码
                keyword: $("#keyword").val(),
                startTime: $("#start").val() == '' ? null : Date.parse($("#start").val()),
                endTime: $("#end").val() == '' ? null : Date.parse($("#end").val()),
                mobile: $("#mobile").val(),
                clientID: $("#clientID").val()
            };
            return temp;
        },
        striped: true,     //设置为true会有隔行变色效果
        columns: [
            {
                field: 'id',//field： json的key对应
                title: '序号',
                width: 40,
                formatter: function (value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            /*{field: 'totalRepTime', title: '请求次数'},*/
            {field: 'corpname', title: '客户名称'},
            {field: 'sendTimeStr', title: '发送时间'},
            {field: 'reportState', title: '状态', formatter: function (v, r, i) {
                    if (v == 0) {
                        return "成功";
                    } else if(v == 1) {
                        return "等待";
                    } else {
                        return "失败";
                    }
                }
            },
            {field: 'operatorId', title: '运营商', formatter: function (v, r, i) {
                    if (v == 0) {
                        return "全网";
                    } else if(v == 1) {
                        return "移动";
                    } else if(v == 2) {
                        return "联通";
                    } else {
                        return "电信";
                    }
                }
            },
            {field: 'errorCode', title: '错误码'},
            {field: 'srcNumber', title: '发送号'},
            {field: 'destMobile', title: '手机号'},
            {field: 'messageContent', title: '短信内容'}
        ]
    };
    $('#table').bootstrapTable(option);
});

function formatDate(time){
    var date=new Date();
    date.setTime(time);//处理long型时间
    return date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()
}
var vm = new Vue({
    el: '#dtapp',
    data: {
        sites: []
    },
    methods: {
        reload: function (event) {
            $("#table").bootstrapTable('refresh');
        }
    },
    created: function () {//
        console.log(11)
        $.get("../sys/client/all", function(r){
            vm.sites = r.sites;
        });
    }
});