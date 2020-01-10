$(function () {
    var option = {
        url: '../system/role/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        toolbar: '#toolbar',
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                name: $("#role-name").val(),
                status: $("#role-status").val()
            };
            return temp;
        },
        striped: true,     //设置为true会有隔行变色效果
        columns: [
            {checkbox: true},
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
            {title: '用户ID', field: 'id'},
            {field: 'name', title: '名称'},
            {field: 'remark', title: '备注'},
            {
                title: '状态', field: 'status', formatter: function (value, row, index) {
                    return value === 1 ? '<span class="label label-success">有效</span>' : '<span class="label label-danger">无效</span>'
                }
            }
        ]
    };
    $('#table').bootstrapTable(option);
});
var ztree;

var vm = new Vue({
    el: '#dtapp',
    data: {
        showList: true,
        title: null,
        role: {}
    },
    methods: {
        del: function () {

            //var rows = getSelectedRows();
            /**
             * getSelections
             参数： undefined
             详情：
             返回选定的行，如果未选择任何记录，则返回一个空数组。
             */
            var rows = $("#table").bootstrapTable("getSelections")
            //[]
            if (rows == null || rows.length == 0) {
                alert('请选择您要删除的行');
                return;
            }
            var id = 'id';
            //提示确认框  layer huozhe sweetalert
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function (index, layero) {
                var ids = new Array();
                //遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function (i, row) {
                    console.log(row[id]);
                    ids.push(row[id]);
                });

                $.ajax({
                    type: "POST",
                    url: "../system/role/delete",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.status) {
                            layer.alert('删除成功');
                            $('#table').bootstrapTable('refresh');
                        } else {
                            layer.alert(r.message);
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
            vm.role = {};
        },
        update: function (event) {
            var id = 'id';
            var roleId = getSelectedRow()[id];//common.js
            if (roleId == null) {
                return;
            }
            //sys/menu/info/1
            $.get("../system/role/info/" + roleId, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.role = r.role;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.role.id == null ? "../system/role/add" : "../system/role/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.role),
                success: function (r) {
                    if (r.status) {
                        layer.alert('操作成功', function (index) {
                            layer.close(index);
                            vm.reload();
                        });
                    } else {
                        layer.alert(r.message);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            $("#table").bootstrapTable('refresh');
        },
        menuTree: function () {
            var id = 'id';
            var roleId = getSelectedRow()[id];//common.js
            if (roleId == null) {
                return;
            }
            vm.getMenu();
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var treeData = ztree.getCheckedNodes(true);
                    alert(treeData);
                    var menuIds = ""
                    for (var i = 0; i < treeData.length; i++) {
                        menuIds += "&menuIds=" + treeData[i].id
                    }
                    $.ajax({
                        method: "get",
                        url: "../system/role/assign_menu",
                        data: "roleId=" + roleId + menuIds,
                        success: function (r) {
                            if (r.status) {
                                layer.alert('分配成功');
                                layer.close(index);
                            } else {
                                layer.alert(r.message);
                            }
                        },
                        error: function () {
                            layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                        }
                    });
                }
            });
        },
        getMenu: function () {
            var roleId = getSelectedRow()["id"];//common.js
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentId",
                        rootPId: -1
                    },
                    key: {
                        url: "nourl"
                    }
                }, check: {
                    enable: true,
                    chkStyle: "checkbox",    //复选框
                    chkboxType: {
                        "Y": "ps",
                        "N": "ps"
                    }
                }
            };
            $.get("../system/role/role_menu/" + roleId, function (roleMenu) {
                $.get("../system/role/menu_tree",
                    function (r) {
                        //设置ztree的数据
                        ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                        ztree.expandAll(true);
                        for (var i = 0; i < roleMenu.length; i++) {
                            var node = ztree.getNodeByParam("id", roleMenu[i]);
                            node.checked = true;
                            ztree.updateNode(node);
                        }
                    });
            })

        }
    }
});