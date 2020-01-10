$(function () {
    var option = {
        url: '../sys/channel/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped: true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {
                field: 'id',
                title: '序号',
                width: 40,
                formatter: function (value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox: true},
            {title: 'id', field: 'id', sortable: true},
            {title: '通道名称', field: 'channelname'},
            {title: '通道类型', field: 'channeltype', formatter: function (v, r, i) {
                    if (v == 0) {
                        return "全网";
                    } else if (v == 1) {
                        return "移动";
                    } else if (v == 2) {
                        return "联通";
                    } else{
                        return "电信";
                    }
                }
            },
            {title: '账户接入号', field: 'spnumber'},
            {title: '协议类型', field: 'protocaltype', formatter: function (v, r, i) {
                    if (v == 1) {
                        return "cmpp";
                    } else if (v == 2) {
                        return "sgip";
                    } else {
                        return "smgp";
                    }
                }
            }
        ]
    };
    $('#table').bootstrapTable(option);
});

var vm = new Vue({
    el: '#dtapp',
    data: {
        showList: true,
        title: null,
        channel: {}
    },
    methods: {
        del: function () {
            var rows = getSelectedRows();
            if (rows == null) {
                return;
            }
            var id = 'id';
            //提示确认框
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function (index, layero) {
                var ids = new Array();
                //遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function (i, row) {
                    ids[i] = row[id];
                });

                $.ajax({
                    type: "POST",
                    url: "/sys/channel/del",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code === 0) {
                            layer.alert('删除成功');
                            $('#table').bootstrapTable('refresh');
                        } else {
                            layer.alert(r.msg);
                        }
                    },
                    error: function () {
                        layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                    }
                });
            });
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.channel = {parentName: null, parentId: 0, type: 1, orderNum: 0};
        },
        update: function (event) {
            var id = 'id';
            var id = getSelectedRow()[id];
            if (id == null) {
                return;
            }

            $.get("../sys/channel/info/" + id, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.channel = r.channel;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.channel.id == null ? "../sys/channel/save" : "../sys/channel/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.channel),
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