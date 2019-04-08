angular.module('app').directive('uiCheckbox', [function(){
    // Runs during compile
    return {
        scope: {
        	items:'=',
        	value:'='
        },
        restrict: 'AE',
        template: '<input type="text" class="form-control ui-checkbox-input" readOnly value="{{value}}">\
				   <div class="ui-checkbox-ul ng-hide">\
						<div class="ui-checkbox-li text-ellipsis" ng-repeat="item in items">\
							<label class="checkbox i-checks">\
				            	<input type="checkbox" data-val="{{item}}" ng-click="updateSelection();"><i></i> {{item}}\
				          	</label>\
				        </div>\
				   </div>',
        link: function($scope, iElm, iAttrs, controller) {
        	iElm.addClass('ui-checkbox');
        	
        	iElm.find('.ui-checkbox-input').click(function(){
        		if($(this).next().hasClass('ng-hide')){
        			$('.ui-checkbox-ul').addClass('ng-hide');
        			$(this).next().removeClass('ng-hide');
        		}else{
        			$(this).next().addClass('ng-hide');
        		}
        	});
        	
        	$scope.updateSelection=function(){
        		$scope.value='';
        		iElm.find('input[type=checkbox]').each(function(i,n){
        			if($(n).prop('checked')){
        				if($scope.value!=''){
        					$scope.value+=',';
        				}
        				$scope.value+=$(n).data('val');
        			}
        		});
        	}
        }
    };
}]);

$(function(){
	$(document).click(function(){
		if($(event.target).parents('.ui-checkbox').length==0){
			$('.ui-checkbox-ul').addClass('ng-hide');
		}
	});
})

angular.module('app').directive('uiFilter', ['$http', '$state', 'GG', 'LoadSonDataTableService', 'fieldService', 'emailService', function($http, $state, GG, LoadSonDataTableService, fieldService, emailService) {
    return {
        scope: false,
        templateUrl: 'tpl/flowchart/newfilter.html',
        restrict: 'AE', // E = Element, A = Attribute, C = Class, M
        // = Comment
        link: function($scope, iElm, iAttrs, controller) {
        	
            $scope.filter = conditionFilter;
            
            //初始化用户属性
            $scope.initUser_properties=function(){
            	LoadSonDataTableService.get().then(function(data) {
            		$scope.filter.user_properties = data.data;
            	});
            }
            
            //初始化邮件模板
            $scope.initEmail_templates=function(){
            	emailService.getModelByType("EMAIL")
            	.then(function(data) {
            		if (data.result) {
            			data.dataList = [{
            				msgId: 'ALL',
            				messageName: '任何消息'
            			}].concat(data.dataList);
            			$scope.filter.email_templates = data.dataList;
            		}
            	});
            }
            
            //选择模式
            $scope.selectMode = function(mode) {
                $scope.filter.mode=mode;
                $scope.filter.mode_name=$scope.getModeName(mode);
            }
            
            $scope.getModeName = function(mode) {
            	if(mode=='all'){
            		return '并且';
            	}else if(mode=='any'){
            		return '或者';
            	}
            }
            
            //初始化模式
            $scope.selectMode(iAttrs.uiFilter||"all");
            //初始化增加一个过滤条件
            $scope.filter.conditions=[];
            $scope.filter.conditions.push({
				type:'Attribute',
				
			});
            
            $scope.initUser_properties();
            $scope.initEmail_templates();

            //选择类型
            $scope.selectType = function(cdIndex) {
                $scope.filter.conditions[cdIndex].refine = false;
                var type = $scope.filter.conditions[cdIndex].type;
                if (type == 'Attribute') {
                    //默认选中第一个
                	if($scope.filter.user_properties.length>0){
                		$scope.initUser_properties();
	                    $scope.filter.conditions[cdIndex].user_property = $scope.filter.user_properties[0].tableId;
	                    $scope.filter.conditions[cdIndex].tableCode=$scope.filter.user_properties[0].code;
	                    $scope.selectUserProperty($scope.filter.conditions[cdIndex].user_property ,cdIndex);
                	}
                } else if (type == 'Email') {
                	$scope.initEmail_templates();
                    $scope.filter.conditions[cdIndex].email_template = $scope.filter.email_templates[0].msgId;
                }
            }
            //选择用户属性
            $scope.selectUserProperty = function(tableId,cdIndex) {
            	var table=$scope.getTable(tableId);
            	if(table!=null){
            		$scope.filter.conditions[cdIndex].tableCode=table.code;
            		if(table.code!="Orders"){
            			 $scope.filter.conditions[cdIndex].refine = false;
            		}
                    fieldService.query(tableId, "SD").then(function(data) {
                        $scope.filter.conditions[cdIndex].user_fields = data;
                        if($scope.filter.conditions[cdIndex].user_fields.length>0){
    	                    $scope.filter.conditions[cdIndex].user_field = $scope.filter.conditions[cdIndex].user_fields[0].fieldId;
    	                    $scope.filter.conditions[cdIndex].fieldCode = $scope.filter.conditions[cdIndex].user_fields[0].code;
                        }
                    });
            	}
            }
            
            //根据tableId获得table
            $scope.getTable=function(tableId){
            	for (var i = 0; i < $scope.filter.user_properties.length; i++) {
					if(tableId== $scope.filter.user_properties[i].tableId){
						return $scope.filter.user_properties[i];
					}
				}
            	return null;
            }
            
            //选择userfield
            $scope.selectUserField = function(fieldId,cdIndex) {
            	var user_fieldObj= $scope.getField(fieldId,cdIndex);
            	if(user_fieldObj!=null){
                	var type=user_fieldObj.type;
                	$scope.filter.conditions[cdIndex].user_field=user_fieldObj.fieldId;
                	$scope.filter.conditions[cdIndex].fieldCode=user_fieldObj.code;
                	if(type==1){
                		$scope.filter.conditions[cdIndex].operator_type='字符串';
                	}else if(type==2||type==4){
                		$scope.filter.conditions[cdIndex].operator_type='数值';
                	}else if(type==3){
                		$scope.filter.conditions[cdIndex].operator_type='时间';
                	}else if(type==5){
                		$scope.filter.conditions[cdIndex].operator_type='枚举';
                		if(user_fieldObj.enumValue&&user_fieldObj.enumValue!=""){
                			$scope.filter.conditions[cdIndex].operator_enums=user_fieldObj.enumValue.split(/[，,]/);
                		}
                	}
            	}
            }
            
            //根据fieldId获得field
            $scope.getField=function(fieldId,cdIndex){
            	for (var i = 0; i <  $scope.filter.conditions[cdIndex].user_fields.length; i++) {
					if(fieldId==$scope.filter.conditions[cdIndex].user_fields[i].fieldId){
						return $scope.filter.conditions[cdIndex].user_fields[i];
					}
				}
            	return null;
            }
            
            //切换操作选项
            $scope.selectOperator=function(cdIndex){
            	if($scope.filter.conditions[cdIndex].operator=='in'||$scope.filter.conditions[cdIndex].operator=='notin'){
            		$scope.filter.conditions[cdIndex].operator_value='';
            	}
            }

            //增加条件
            $scope.addCondition = function(event) {
            	var event=event || window.event;
            	var obj = event.srcElement ? event.srcElement:event.target; 
				var type=$(obj).attr('type');
				if(type){
					if(type=='all'||type=='any'){
						
					}else{
						$scope.filter.conditions.push({
							type:type
						});
					}
				}
            }

            //删除条件
            $scope.removeCondition = function(cdIndex) {
				 $scope.filter.conditions.splice(cdIndex,1);
            }

            //refineCondition
            $scope.refineCondition = function(cdIndex) {
                $scope.filter.conditions[cdIndex].refine = true;
            }

            //cancelRefineCondition
            $scope.cancelRefineCondition = function(cdIndex) {
                $scope.filter.conditions[cdIndex].refine = false;
            }
            
        }
    };
}]);

angular.module('app').directive('uiConditionadd', ['$http', '$state', 'GG', 'LoadSonDataTableService', 'fieldService', 'emailService', function($http, $state, GG, LoadSonDataTableService, fieldService, emailService) {
    return {
        scope: false,
        templateUrl: 'tpl/flowchart/addcondbtn.html',
        restrict: 'AE',
        link: function($scope, iElm, iAttrs, controller) {
			
        }
    };
}]);

var conditionFilter = {
    types: [{
        name: '用户属性',
        value: 'Attribute'
    }, {
        name: '消息',
        value: 'Email'
    }, {
        name: '事件',
        value: 'Event'
    }, {
        name: '浏览页面',
        value: 'Page'
    }],
    email_templates1: [{
        "name": "—any email—",
        "desc": "for any campaign or newsletter"
    }, {
        "name": "10 Reasons to Subscribe.",
        "desc": "Campaign: --SAMPLE-- Get People to Subscribe"
    }, {
        "name": "Become an expert...",
        "desc": "Campaign: --SAMPLE-- Educate New Signups"
    }, {
        "name": "Example email",
        "desc": "Campaign: test"
    }, {
        "name": "Let's agree to stay friends",
        "desc": "Campaign: --SAMPLE-- Re-engage Inactive Users"
    }, {
        "name": "Welcome to MyApp",
        "desc": "Campaign: --SAMPLE-- Educate New Signups"
    }, {
        "name": "What happens now?",
        "desc": "Campaign: --SAMPLE-- Re-engage Inactive Users"
    }],
    email_operators: [{
        "value": "sent_email",
        "name": "\n      被发送\n    "
    }, {
        "value": "delivered_email",
        "name": "\n      被接收\n    "
    }, {
        "value": "opened_email",
        "name": "\n      被打开\n    "
    }, {
        "value": "clicked_email",
        "name": "\n      被点击\n    "
    }, /*{
        "value": "converted_email",
        "name": "\n      converted\n    "
    }, */{
        "value": "bounced_email",
        "name": "\n      被退回\n    "
    }, {
        "value": "failed_email",
        "name": "\n      发送失败\n    "
    }, {
        "value": "spammed_email",
        "name": "\n      被标记为垃圾邮件\n    "
    }, {
        "value": "unsubscribed_email",
        "name": "\n      导致退订\n    "
    }],
    email_inverses: [{
        name: '曾经',
        value: 'false'
    }, {
        name: '未曾',
        value: 'true'
    }],
    event_list: [{
        "value": "signUp",
        "name": "用户注册",
        "fields": []
    }, {
        "value": "updateProfile",
        "name": "更新个人资料",
        "fields": []
    }, {
        "value": "viewItem",
        "name": "浏览商品",
        "fields": []
    }, {
        "value": "updateCart",
        "name": "更新购物车",
        "fields": []
    }, {
        "value": "purchase",
        "name": "购买商品",
        "fields": []
    }, {
        "value": "orderShipped",
        "name": "发货",
        "fields": []
    }, {
        "value": "subscribe",
        "name": "订阅",
        "fields": []
    }, {
        "value": "unsubscribe",
        "name": "取消订阅",
        "fields": []
    }, {
        "value": "emailOpen",
        "name": "打开邮件",
        "fields": []
    }, {
        "value": "emailClick",
        "name": "点击邮件",
        "fields": []
    }, {
        "value": "pushOpen",
        "name": "打开推送消息",
        "fields": []
    }],
    event_inverses: [{
        "value": "false",
        "name": "\n      被触发\n    "
    }, {
        "value": "true",
        "name": "\n      未被触发\n    "
    }],
    operators: [{
        name: '等于',
        value: 'eq',
        type:'数值,字符串'
    }, {
        name: '不等于',
        value: '!eq',
        type:'数值,字符串'
    }, {
        name: '大于',
        value: 'gt',
        type:'数值'
    }, {
        name: '小于',
        value: 'lt',
        type:'数值'
    }, {
        name: '存在',
        value: 'exists',
        type:'数值,字符串,时间'
    }, {
        name: '不存在',
        value: '!exists',
        type:'数值,字符串,时间'
    },{
        name: '包含',
        value: 'contains',
        type:'字符串'
    }, {
        name: '不包含',
        value: '!contains',
        type:'字符串'
    },{
        name: '时间晚于',
        value: 'timestamp_gt',
        type:'时间'
    }, {
        name: '时间早于',
        value: 'timestamp_lt',
        type:'时间'
    }, {
    	name: '属于',
    	value: 'in',
    	type:'枚举'
    }, {
    	name: '不属于',
    	value: 'notin',
    	type:'枚举'
    }],
    timestamp_units: [/*{
        name: 'from now',
        value: 'from now'
    }, */{
        name: '以前',
        value: 'ago'
    },{
        name: '之后',
        value: 'after'
    }],
    periods: [{
        "value": "ever",
        "name": "曾经"
    }, {
        "value": "recent",
        "name": "在过去"
    }],
    time_type: [{
        "value": "most",
        "name": "最多"
    }, {
        "value": "least",
        "name": "至少"
    }],
    
    has_inverses: [{
    	"value": "has",
    	"name": "有"
    }, {
    	"value": "hasnot",
    	"name": "没有"
    }],
    any_urls: [{
        "value": "true",
        "name": "\n      任何网址\n    "
    }, {
        "value": "false",
        "name": "\n      网址匹配\n    "
    }],
    page_inverses: [{
        "value": "false",
        "name": "\n      被浏览\n    "
    }, {
        "value": "true",
        "name": "\n      未被浏览\n    "
    }],
    condition_types:[{
        label: '分组',
        options: [{
            name: '全部',
            value: 'all'
        }, {
            name: '任意',
            value: 'any'
        }]
    },{
        label: '类型',
        options: [{
            name: '用户属性',
            value: 'Attribute'
        }, {
            name: '邮件',
            value: 'Email'
        }, {
            name: '事件',
            value: 'Event'
        }, {
            name: '浏览页面',
            value: 'Page'
        }]
    }],
    modes:[{
        name: '全部',
        value: 'all'
    }, {
        name: '任意',
        value: 'any'
    }],
    conditions: []
};
