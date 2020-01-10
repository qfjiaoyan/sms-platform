$(function(){
    var option = {
        url: '../sys/message/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {
                field: 'id',
                title: '序号',
                width: 40,
                formatter: function(value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox:true},
            { title: 'id', field: 'id',sortable:true},
            { title: '敏感词', field: 'dirtyword'}
        ]};
    $('#table').bootstrapTable(option);
});
var vm = new Vue({
    el:'#dtapp',
    data:{
        showList: true,
        title: null,
        message:{}
    },
    methods:{
        del: function(){
            var rows = getSelectedRows();
            if(rows == null){
                return ;
            }
            var id = 'id';
            //提示确认框
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function(index, layero){
                var ids = new Array();
                //遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function(i, row) {
                    ids[i] = row[id];
                });

                $.ajax({
                    type: "POST",
                    url: "/sys/message/del",
                    data: JSON.stringify(ids),
                    success : function(r) {
                        if(r.code === 0){
                            layer.alert('删除成功');
                            $('#table').bootstrapTable('refresh');
                        }else{
                            layer.alert(r.msg);
                        }
                    },
                    error : function() {
                        layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                    }
                });
            });
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.message = {parentName:null,parentId:0,type:1,orderNum:0};
        },
        update: function (event) {
            var id = 'id';
            var id = getSelectedRow()[id];
            if(id == null){
                return ;
            }

            $.get("../sys/message/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.message = r.message;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.message.id == null ? "../sys/message/save" : "../sys/message/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.message),
                success: function(r){
                    if(r.code === 0){
                        layer.alert('操作成功', function(index){
                            layer.close(index);
                            vm.reload();
                        });
                    }else{
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
