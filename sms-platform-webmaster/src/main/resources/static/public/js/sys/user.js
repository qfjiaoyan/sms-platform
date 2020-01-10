$(function () {

    $("#table").bootstrapTable({
        url: "/sys/user/list",
        pagination: true,
        sidePagination: "server",
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        columns: [
            {checkbox: true},
            {field: 'id', title: '编号', sortable: true},
            {field: 'usercode', title: '用户名'},
            {
                field: 'password', title: '密码', formatter: function (v, r, index) {
                    return "******";
                }
            },
            {field: 'email', title: '邮箱'},
            {field: 'realName', title: '真实姓名'},
            {
                field: 'status', title: '状态', formatter: function (v, r, i) {
                    if (v == 0) {
                        return "无效";
                    } else {
                        return "有效";
                    }
                }
            },
            {field: 'clientid', title: '客户id'}
        ]
    });


});

var vm = new Vue({
    el: '#dtapp',
    data: {
        showList: true,
        title: '',
        sites: [],
        user: {}
    },
    methods: {
        del: function () {
            //getSelectedRows();：common.js中定义   功能获得用户选择的记录  返回的是数组
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
                    ids[i] = row[id];//得到选择的这一行的id
                });

                //ids  = [1,2,3];//json数组
                $.ajax({
                    type: "POST",
                    url: "/sys/user/del",
                    data: JSON.stringify(ids),//把json数组转json字符串
                    success: function (r) {
                        if (r.code === 0) {//成功

                            layer.alert('删除成功');
                            //刷新
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
            vm.user = {parentName: null, parentId: 0, type: 1, orderNum: 0};
            $.get("../sys/client/all", function (r) {
                vm.sites = r.sites;
            });
        },
        update: function (event) {
            var id = 'id';
            var userId = getSelectedRow()[id];
            if (userId == null) {
                return;
            }
            $.get("../sys/user/info/" + userId, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.user = r.user;
            });
            $.get("../sys/client/all", function (r) {
                vm.sites = r.sites;
            });
        },
        saveOrUpdate: function (event) {
            //有菜单编号时是修改，没有：新增

            var url = vm.user.id == null ? "../sys/user/save" : "../sys/user/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.user),//json字符串
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
