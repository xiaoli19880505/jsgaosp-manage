/**
 * 控件指令帮助类
 */
angular.module('app').service('options', [

	function() {
		return {
			line: {},
			bar: {
				tooltip: {
					trigger: 'axis'
				},
				calculable: true,
				xAxis: [{
						splitLine: {
							show: false
						},
						show: false,
						type: 'value',
						boundaryGap: [0, 0.01],
						axisLabel: {
							textStyle: {
								fontSize: 1
							}
						}
					},

				],
				grid: {
					borderWidth: 0,
					x: 60,
					y: 10,
					y2: 25,
					x2: 25
				},
				yAxis: [{
					splitLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					type: 'category',
					data: []
				}],
				series: [{
					name: '',
					itemStyle: {
						normal: {
							color: function(params) {
								// build a color map as your need.
								var colorList = ['#74B648', '#E0567A',
									'#FFB400', '#867CC5'
								];
								return colorList[params.dataIndex]
							},
							label: {
								show: true
							}
						}
					},
					type: 'bar',
					data: []
				}]
			},
			pie: {}
		}
	}
]);