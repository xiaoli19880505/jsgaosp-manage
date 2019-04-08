/**
 * <div h-color="#fffff" _link="189888">
 * <h-normal name="值1" _val="{}" _realtime="6" >{{title}}
 *      总收入<h-val/>万元
 *      <h-tb/>
 *      环比<h-hb/>%
 * </h-normal>
 * <h-nav1 _val="" >
 
 * </h-nav1>
 * <h-echart name="值2" _val="" _opt="{}" _realtime="">
 * 		
 * </h-echart>
 * <h-grid name="值2" _header="" _val="" _realtime>
 *     
 * </h-grid>
 * 
 * <h-liqud name="值2" _val="" _opt="" _realtime>
 * 		
 * </h-liqud>
 * 
 * _val={
 * 	type:'druid',
 *  src:'',
 *  query:{
 * 		time:''//昨日、本月、今年
 * 		//dept:''//ALL,妇科，男科等
 * }
 * }
 * 
 * 
 * 
 */
//内置属性 _nav _realtime _color
angular.module('app').directive('hb', ['$compile', '$timeout', '$q',
	function($compile, $parse, $timeout, $q) {
		//druid数据源url
		var dsUrl = "";
		//内置属性
		var NAV = "_nav"; //导航
		var REAL_TIME = "_realtime"; //实时
		var COLOR = "_color"; //颜色
		var VALUE = "val";

		var startWith = function(w, src) {
			var reg = new RegExp("^" + w);
			return reg.test(src);
		}
		var toJSON = function(str) {
			return (new Function("return " + str))();
		};
		var build = function() {

		}
		return {
			restrict: 'E',
			compile: function(element, attributes) {
				attributes[NAV] && {
					//注册 click事件 $state参数
				}
				attributes[REAL_TIME] && {
					//$interval
				}
				for (var key in attributes) {
					if (startWith(VALUE, key)) {
						//计算值
					}
				}
				return function(scope, el, attr) {
					var cp = function(val) {
						//替换 html
						//替换 option
						//$timeout(function() {
						//var toJSON = function(str) {
						//	return (new Function("return " + str))();
						//};
						//echarts.init(el[0]).setOption(toJSON(option));
						//}, 0);
					};
					if (expsJSON) {
						for (var key in expsJSON) {
							var reg = new RegExp("('|\"){{[^}]*" + key + "[^{]*}}('|\")", "gi");
							var exp = expsJSON[key];
							switch (exp.way) {
								case 'manual':
									$timeout(function() {
										var toJSON = function(str) {
											return (new Function("return " + str))();
										};
										echarts.init(el[0]).setOption(toJSON(option));
										//echarts.init(el[0]).setOption(option);
									}, 0);
									//									cp(exp.value);
									break;
								case 'script':
									exp.value.then(function(data) {
										cp(data);
									});
									break;
								case 'url':
									exp.value.then(function(data) {
										cp(data);
									});
									break;
								default:
									break;
							}
						}
					} else {
						cp(opt);
					}
				};
			}
		}
	}
]);