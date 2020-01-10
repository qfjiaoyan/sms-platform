$(function () {
    var option = {
        url: '../sys/product/list',
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
            {title: '标题', field: 'name'},
            {title: '售后服务', field: 'service'}
        ]
    };
    $('#table').bootstrapTable(option);
});
var myue = UE.getEditor('myeditor');
var myue2 = UE.getEditor('myeditor2');
var vm = new Vue({
    el: '#dtapp',
    data: {
        showList: true,
        title: null,
        product: {}
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
                    url: "/sys/product/del",
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
            vm.product = {parentName: null, parentId: 0, type: 1, orderNum: 0};
        },
        update: function (event) {
            var id = 'id';
            var id = getSelectedRow()[id];
            if (id == null) {
                return;
            }

            $.get("../sys/product/info/" + id, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.product = r.product;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.product.id == null ? "../sys/product/save" : "../sys/product/update";
            //对编辑器的操作最好在编辑器ready之后再做
            myue.ready(function () {
                //获取html内容，返回: <p>hello</p>
                var html = myue.getContent();
                vm.product.description = html;
            });
            myue2.ready(function () {
                //获取html内容，返回: <p>hello</p>
                var html = myue2.getContent();
                vm.product.service = html;
            });
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.product),
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