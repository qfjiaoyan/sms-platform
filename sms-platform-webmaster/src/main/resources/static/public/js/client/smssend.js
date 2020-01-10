$(function () {
    $.get("../sys/client/all", function (r) {
        vm.sites = r.sites;
    });
});
var vm = new Vue({
    el:'#dtapp',
    data:{
        showList: true,
        title: null,
        sites: [],
        sms:{}
    },
    methods:{
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.sms = {parentName:null,parentId:0,type:1,orderNum:0};
        },
        saveOrUpdate: function (event) {
            var url = vm.sms.id == null ? "../sys/sms/save" : "../sys/sms/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.sms),
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