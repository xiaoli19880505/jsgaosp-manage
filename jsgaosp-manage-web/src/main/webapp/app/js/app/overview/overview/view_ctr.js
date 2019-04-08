app.controller('overviewModelController',
		function($scope, $timeout, $http, $state, $modal, $stateParams, overviewService){
	//一页显示个数，已写死在插件里，并不能修改。
	$scope.pageSize = 10;
	//当前页数
	$scope.currentPage = 1;
	$scope.compaignsName=[]
	$scope.loadCompaign = function(){
		$scope.compaigns = {};
		overviewService.get($scope.currentPage, $scope.pageSize)
			.then(function(data){
				$scope.totalItems = data.totalItems;
                $scope.compaigns = data.data;
                angular.forEach(data.data,function(item){
                	$scope.compaignsName.push(item.name);
                })
                console.log($scope.compaignsName);
                $scope.createChart1();
			})
	}
	$scope.loadCompaign();
	
	/**
	 * 初始化前七天
	 */
	$scope.dateArray = [];
	$scope.getBeforeDate = function(){
		var myDate = new Date(); //获取今天日期
		myDate.setDate(myDate.getDate() - 7);
		var dateTemp; 
		var flag = 1; 
		for (var i = 0; i < 7; i++) {
		    dateTemp = (myDate.getMonth()+1)+"."+myDate.getDate();
		    $scope.dateArray.push(dateTemp);
		    myDate.setDate(myDate.getDate() + flag);
		}
	}
	$scope.getBeforeDate();
	
	/**
	 * 选中变色
	 */
	$scope.selectCompaigns = function(compaign){
		angular.forEach($scope.compaigns,function(item){
			item.selected = false;
		})
		$scope.compaign = compaign;
		$scope.compaign.selected = true;
	}
	/**
	 * 第一个柱状图开始
	 */
	$scope.createChart1=function(){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			        formatter:function(params){
			        	var str = "";
			        	for(var i=0;i<params.length;i++){
			        		str += params[i].seriesName + ' : '+  (params[i].value*1000/10) +'%<br/>';//此处先*100然后除以10是因为js的乘法运算有bug,这样可以一定程度预防此bug
			        																				  //此bug为 0.321 * 100 = 32.10000000001;
			        	}
			            return str;
			        }
			    },
			    legend: {
			        data:$scope.compaignsName
			    },
			    toolbox: {
			        show : false,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line' , 'bar']},//','stack', 'tiled'
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : $scope.dateArray
			            
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            data : [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1],
			            axisLabel: {
			                  show: true,
			                  interval: 'auto',
			                  formatter:function(value){
			                	  return value *1000 / 10 + "%";
			                  }
			                }
			        }
			    ],
			    series : [
			        {
			            name:'测试数据',
			            type:'bar',
			            data:[0.320, 0.332, 0.301, 0.334, 0.390, 0.330, 0.320]
			        },
			        {
			            name:'xxxxx',
			            type:'bar',
			            data:[0.120, 0.132, 0.101, 0.134, 0.90, 0.230, 0.210]
			        },
			        {
			            name:'dddd',
			            type:'bar',
			            data:[0.220, 0.182, 0.191, 0.234, 0.290, 0.330, 0.310]
			        },
			        {
			            name:'5555',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.410]
			        },
			        {
			            name:'666',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.410]
			        }
			        ,
			        {
			            name:'7777',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.410]
			        }
			    ]
			};
			var chart1=echarts.init(document.getElementById("chart1"));
			chart1.setOption(option);
	}
	
	
	/**
	 * 第二个柱状图开始
	 */
	$scope.createChart2=function(){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			        formatter:function(params){
			        	var str = "";
			        	for(var i=0;i<params.length;i++){
			        		str += params[i].seriesName + ' : '+  (params[i].value*1000/10) +'%<br/>';//此处先*100然后除以10是因为js的乘法运算有bug,这样可以一定程度预防此bug
			        																				  //此bug为 0.321 * 100 = 32.10000000001;
			        	}
			            return str;
			        }
			    },
			    legend: {
			    	data:['爆款商品推荐','商品打折','优惠券','体育用品','服饰']
			    },
			    toolbox: {
			        show : false,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line' , 'bar']},//','stack', 'tiled'
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : $scope.dateArray
			            
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            data : [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1],
			            axisLabel: {
			                  show: true,
			                  interval: 'auto',
			                  formatter:function(value){
			                	  return value *1000 / 10 + "%";
			                  }
			                }
			        }
			    ],
			    series : [
			        {
			            name:'爆款商品推荐',
			            type:'bar',
			            data:[0.320, 0.332, 0.301, 0.334, 0.390, 0.330, 0.320]
			        },
			        {
			            name:'商品打折',
			            type:'bar',
			            data:[0.120, 0.132, 0.101, 0.134, 0.30, 0.230, 0.210]
			        },
			        {
			            name:'优惠券',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.310]
			        },
			        {
			            name:'体育用品',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.410]
			        },
			        {
			            name:'服饰',
			            type:'bar',
			            data:[0.150, 0.232, 0.201, 0.154, 0.190, 0.330, 0.360]
			        }			       
			    ]
			};
			var chart2=echarts.init(document.getElementById("chart2"));
			chart2.setOption(option);
	}
	$scope.createChart2();
	/**
	 * 第三个柱状图开始
	 */
	$scope.createChart3=function(){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			        formatter:function(params){
			        	var str = "";
			        	for(var i=0;i<params.length;i++){
			        		str += params[i].seriesName + ' : '+  params[i].value +'<br/>';
			        	}
			            return str;
			        }
			    },
			    legend: {
			    	data:['浏览','考虑','成交','完成']
			    },
			    toolbox: {
			        show : false,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line' , 'bar']},//','stack', 'tiled'
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : $scope.dateArray
			            
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'浏览',
			            type:'bar',
			            data:[560, 842, 811, 824, 850, 860, 880]
			        },
			        {
			            name:'考虑',
			            type:'bar',
			            data:[400, 632, 601, 734, 700, 730, 710]
			        },
			        {
			            name:'成交',
			            type:'bar',
			            data:[335, 582, 491, 534, 490, 530, 410]
			        },
			        {
			            name:'完成',
			            type:'bar',
			            data:[210, 232, 201, 254, 290, 330, 210]
			        }			       
			    ]
			};
			var chart3=echarts.init(document.getElementById("chart3"));
			chart3.setOption(option);
	}
	$scope.createChart3();
	
	/**
	 * 第四个柱状图开始
	 */
	$scope.createChart4=function(){
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			        formatter:function(params){
			        	var str = "";
			        	for(var i=0;i<params.length;i++){
			        		str += params[i].seriesName + ' : '+  params[i].value +'<br/>';
			        	}
			            return str;
			        }
			    },
			    legend: {
			    	data:['爆款商品推荐','商品打折','优惠券','体育用品','服饰']
			    },
			    toolbox: {
			        show : false,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line' , 'bar']},//','stack', 'tiled'
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : $scope.dateArray
			            
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            data : [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1],
			            axisLabel: {
			                  show: true
			                }
			        }
			    ],
			    series : [
			        {
			            name:'爆款商品推荐',
			            type:'bar',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'商品打折',
			            type:'bar',
			            data:[120, 132, 101, 134, 30, 230, 210]
			        },
			        {
			            name:'优惠券',
			            type:'bar',
			            data:[150, 232, 201, 154, 190, 330, 310]
			        },
			        {
			            name:'体育用品',
			            type:'bar',
			            data:[150, 232, 201, 154, 190, 330, 410]
			        },
			        {
			            name:'服饰',
			            type:'bar',
			            data:[150, 232, 201, 154, 190, 330, 360]
			        }			       
			    ]
			};
			var chart4=echarts.init(document.getElementById("chart4"));
			chart4.setOption(option);
	}
	$scope.createChart4();
});