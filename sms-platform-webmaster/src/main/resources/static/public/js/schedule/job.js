$(function () {
    var option = {
        url: '../schedule/job/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped: true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {
                field: 'menuId',
                title: '序号',
                width: 40,
                formatter: function (value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox: true},
            {title: '任务ID', field: 'jobId'},
            {title: 'bean名称', field: 'beanName'},
            {title: '方法名称', field: 'methodName'},
            {title: '参数', field: 'params'},
            {title: 'cron表达式 ', field: 'cronExpression'},
            {title: '备注 ', field: 'remark'},
            {
                title: '状态', field: 'status', formatter: function (value, row, index) {
                    return value === 0 ?
                        '<span class="label label-success">正常</span>' :
                        '<span class="label label-danger">暂停</span>';
                }
            },
            {title: '创建时间', field: 'createTime'}
        ]
    };
    $('#table').bootstrapTable(option);
});

var vm = new Vue({
    el: '#dtapp',
    data: {
        showList: true,
        title: null,
        scheduleJob: {}
    },
    methods: {
        del: function () {

            doTask('jobId', '删除', 'job/del');
        },
        resume: function () {

            doTask('jobId', '恢复', 'job/resume');
        },
        pause: function () {

            doTask('jobId', '暂停', 'job/pause');
        },
        runOnce: function () {
            doTask('jobId', '立即执行', 'job/run');
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.scheduleJob = {};
        },
        update: function () {
            var id = 'jobId';
            var jobId = getSelectedRow()[id];
            if (jobId == null) {
                return;
            }

            $.get("../schedule/job/info/" + jobId, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.scheduleJob = r.scheduleJob;
            });
        },
        saveOrUpdate: function () {
            var url = vm.scheduleJob.jobId == null ? "../schedule/job/save" : "../schedule/job/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.scheduleJob),
                success: function (r) {
                    if (r.code === 0) {
                        layer.alert('操作成功', function (index) {
                            layer.close(index);
                            vm.reload();
                        });
                    } else {
                        layer.alert(r.msg);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            $("#table").bootstrapTable('refresh');
        }
    }
});