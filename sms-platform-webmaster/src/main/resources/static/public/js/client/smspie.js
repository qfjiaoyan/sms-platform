var vm = new Vue({
    el: '#dtapp',
    data: {
        sites: []
    },
    methods: {
        reload: function (event) {
            //1,初始化echarts对象
            var myEcharts = echarts.init(document.getElementById("pie"));
            //2, 指定图表的配置项和数据
            var option = {
                title: {
                    text: '成功率统计',
                    subtext: '真实有效',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: []
                },
                series: [
                    {
                        name: '成功率统计',
                        type: 'pie',
                        radius: '75%',
                        center: ['60%', '60%'],
                        data: [],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                startTime: $("#start").val() == '' ? null : Date.parse($("#start").val()),
                endTime: $("#end").val() == '' ? null : Date.parse($("#end").val()),
                clientID: $("#clientID").val()
            };
            $.ajax({
                url: '/sys/echarts/pie',
                data: temp,
                dataType: 'json',
                success: function (r) {
                    //console.log(r);
                    //给option赋值
                    option.legend.data = r.legendData;
                    option.series[0].data = r.seriesData;
                    // 使用刚指定的配置项和数据显示图表。
                    myEcharts.setOption(option);
                }
            });
        }
    },
    created: function () {//在模板渲染成html前调用，即通常初始化某些属性值，然后再渲染成视图
        //console.log(11)
        //一般可以在created函数中调用ajax获取页面初始化所需的数据
        $.get("../sys/client/all", function(r){
            vm.sites = r.sites;
        });
    }
});