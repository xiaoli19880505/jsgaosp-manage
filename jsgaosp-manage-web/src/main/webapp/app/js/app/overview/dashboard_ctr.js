app.controller('dashboardController',
		function($scope, $timeout, $http, $state, $modal, $stateParams,GG){

	$scope.dataVal1;
	$scope.dataVal2;
	
	$scope.createCpuSirq = function(){
		$http.post(GG.BASE+'/dashboard/getCpuSirq').success(function(data){
			var option = {
				backgroundColor:'#FFF',
				title : {
					show:false
				},
				toolbox: {
					show : false
				},
				calculable : false,
				tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series : [
			        {
			            name:'Agent失效率',
			            type:'gauge',
			            detail : {formatter:'{value}%'},
			            data:[{value: data.sirq, name: '失效率'}]
			        }
			    ]
			};
			var myChart = echarts.init(document.getElementById("cpuSirq"),'macarons').setOption(option);
			var myChart1 = echarts.init(document.getElementById("cpuSirq1"),'macarons').setOption(option);
			var myChart2 = echarts.init(document.getElementById("cpuSirq2"),'macarons').setOption(option);
			var myChart3 = echarts.init(document.getElementById("cpuSirq3"),'macarons').setOption(option);
			
			setInterval(function (){
				 option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
				 $scope.dataVal1 = 100;
				 $scope.dataVal2 = 200;
				 myChart.setOption(option, true);
				 myChart1.setOption(option, true);
				 myChart2.setOption(option, true);
				 myChart3.setOption(option, true);
			},2000);
		});
	}
	
	$scope.createChart1=function(){
		$http.post(GG.BASE+'/dashboard/getCpuInfo',{type:$scope.responseChart.type}).success(function(data){
			var option = {
					backgroundColor:'#FFF',
					title : {
						show:false
					},
					tooltip : {
						trigger: 'axis'
					},
					legend: {
						data:['异常数量','告警数量','监控次数']
					},
					toolbox: {
						show : false
					},
					calculable : false,
					xAxis : [
				         {
				        	 type : 'category',
				        	 boundaryGap : false,
				        	 data:data.dataTimeList
				         }
					],
					yAxis : [
						{
		                	  type : 'value',
		                	  axisLabel : {
		                		  formatter: '{value}'
		                	  }
						}
		           　],
		          　　series : [
                        {
                        	name:'异常数量',
                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        	type:'line',
                        	data:data.dataValue1
                        },
                        {
                        	name:'告警数量',
                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        	type:'line',
                        	data:data.dataVelue2
                        },
                        {
                        	name:'监控次数',
                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        	type:'line',
                        	data:data.dataVelue3
                        }
		           ]
			};
			echarts.init(document.getElementById("chart1"),'macarons').setOption(option);
		});

	}
	$scope.selectType=function(type){
		$scope.responseChart.type=type;
		$scope.createChart1();
	}

	$scope.createCpuSirq();
	
	$scope.responseChart={};
	$scope.selectType(7);
	
});