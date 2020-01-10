$(function () {
    var option = {
        url: 'log/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped: true,     //设置为true会有隔行变色效果
        columns: [
            {
                field: 'logId',
                title: '序号',
                width: 40,
                formatter: function (value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox: true},
            {title: '日志ID', field: 'logId'},
            {title: '任务ID', field: 'jobId'},
            {title: 'bean名称', field: 'beanName'},
            {title: '方法名称', field: 'methodName'},
            {title: '参数', field: 'params'},
            {
                title: '状态', field: 'status', formatter: function (value, row, index) {
                    return value === 0 ?
                        '<span class="label label-success">成功</span>' :
                        '<span class="label label-danger pointer" onclick="vm.showError(' + row.logId + ')">失败</span>';
                }
            },
            {title: '耗时(单位：毫秒)', field: 'times'},
            {title: '执行时间', field: 'createTime'}
        ]
    };
    $('#table').bootstrapTable(option);
});

var vm = new Vue({
    el: '#dtapp',
    methods: {
        del: function () {

            doTask('logId', '删除', 'log/del');
        }
    }
});