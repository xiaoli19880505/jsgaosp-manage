'use strict';

/**
 * Config for the router
 */

angular
.module('app')
.run(
		[ '$rootScope', '$state', '$stateParams',
		  function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
		} ])
		.config(
				[
				 '$stateProvider',
				 '$urlRouterProvider',
				 function($stateProvider, $urlRouterProvider) {
					 $urlRouterProvider.otherwise('app/overview');
					 var urls = [
					     /**
					      * overview
					      * */
			             {
			            	 state : 'app.overview',
			            	 url : '/overview',
			            	 templateUrl : 'tpl/overview/dashboard.html',
			            	 deps : ['js/app/overview/dashboard_ctr.js']
			             },
			             /**
			              * login
			              */
			             {
			            	 state : 'login',
			            	 url : '/login',
			            	 templateUrl : 'tpl/login.html',
			            	 resolve : {
			            		 deps : ['$ocLazyLoad',function($ocLazyLoad) {
	            		        	 return $ocLazyLoad.load('ui.select').then(
            		        			 function() {
            		        				 return $ocLazyLoad
            		        				 .load([
            		        				        'js/app/login/login-ctrl.js',
            		        				        'js/app/login/login-serv.js' ]);
            		        			 });
	            		          }]
			            	 }
			             },
			             /**
			              * logout
			              */
			             {
			            	 state : 'logout',
			            	 url : '/logout',
			            	 templateUrl : 'tpl/login.html',
			            	 deps : [ 'js/app/login/logout.js' ]
			             },
			             {
			            	 state : 'app',
			            	 url : '/app',
			            	 templateUrl : 'tpl/app.html',
			            	 deps : [ 'js/app/app_ctrl.js',
			            	          'js/app/systemmgmt/systemuser/user-serv.js' ]
			             },
			             {
			            	 state : 'app.home',
			            	 url : '/home',
			            	 templateUrl : 'tpl/overview/dashboard.html',
			            	 deps : [
			            	         'js/app/overview/dashboard_ctr.js']
			             },
			             {
			            	 state : 'app.system',
			            	 url : '/sys',
			            	 template : '<div class="w-full scroll-y h-full" ui-view></div>'
			             },
			             {
			            	 state : 'app.system.role',
			            	 url : '/role',
			            	 templateUrl : 'tpl/systemmgmt/rolemgmt/role.html',
			            	 deps : [
			            	         'js/app/systemmgmt/rolemgmt/role_ctrl.js',
			            	         'js/app/systemmgmt/rolemgmt/role_serv.js',
			            	         'js/app/systemmgmt/rolemgmt/role_modal.js' ]
			             },
			             {
			            	 state : 'app.system.user',
			            	 url : '/user',
			            	 templateUrl : 'tpl/systemmgmt/usermgmt/user.html',
			            	 deps : [
			            	         'js/app/systemmgmt/systemuser/user-ctrl.js',
			            	         'js/app/systemmgmt/systemuser/user-serv.js',
								 	 'js/app/systemmgmt/commgmt/company_serv.js',
			            	         'js/app/systemmgmt/systemuser/user-modal.js' ]
			             },
			             {
			            	 state : 'app.system.company',
			            	 url : '/company',
			            	 templateUrl : 'tpl/systemmgmt/commgmt/company.html',
			            	 deps : [
			            	         'js/app/systemmgmt/commgmt/company_serv.js',
			            	         'js/app/systemmgmt/commgmt/company_ctrl.js' ]
			             },
						 {
							 state : 'app.system.area',
							 url : '/area',
							 templateUrl : 'tpl/systemmgmt/area/area.html',
							 deps : [
								 'js/app/systemmgmt/area/area_ctrl.js',
								 'js/app/systemmgmt/area/area_serv.js'

								 ]
						 },
						
						 /**系统参数**/
						 {
                             state : 'app.system.sysargs',
                             url : '/sysargs',
                             templateUrl : 'tpl/systemmgmt/sysargs/sysargs.html',
                             deps : [
                                 'js/app/systemmgmt/sysargs/sysargs-ctrl.js',
                                 'js/app/systemmgmt/sysargs/sysargs-serv.js',
                                 'js/app/systemmgmt/sysargs/sysargs-modal.js'

                             ]
						 },
						 /**数据字典管理 */
						 {
							state : 'app.system.codesort',
							url : '/codesort',
							templateUrl : 'tpl/systemmgmt/syscode/codesort.html',
							deps : [
								'js/app/systemmgmt/syscode/codesort-ctrl.js',
								'js/app/systemmgmt/syscode/codesort-serv.js',
								'js/app/systemmgmt/syscode/codesort-modal.js'

							]
						},
                         {
							 state : 'app.system.application',
							 url : '/application',
							 templateUrl : 'tpl/systemmgmt/application/application.html',
							 deps : [
								 'js/app/systemmgmt/application/application-ctrl.js',
								 'js/app/systemmgmt/application/application-serv.js',
								 'js/app/systemmgmt/application/application-modal.js'

								 ]
						 },
						 {
							 state : 'app.system.approve',
							 url : '/approve',
							 templateUrl : 'tpl/systemmgmt/approve/approve.html',
							 deps : [
								 'js/app/systemmgmt/approve/approve-ctrl.js',
								 'js/app/systemmgmt/approve/approve-serv.js',
								 'js/app/systemmgmt/approve/approve-modal.js'
								 
								 ]
						 },
						 {
							 state : 'app.system.sys_applicant',
							 url : '/sys_applicant',
							 templateUrl : 'tpl/applicationmgmt/sys_applicant/sys_applicant.html',
							 deps : [
									 'js/app/systemmgmt/application/sys_applicant/applicant-ctrl.js',
									 'js/app/systemmgmt/application/sys_applicant/applicant-serv.js',
									 'js/app/systemmgmt/application/sys_applicant/applicant-modal.js'


								 ]
						 },
			             /***设备管理***/
			             {
			            	 state : 'app.device',
			            	 url : '/device',
			            	 template : '<div class="w-full scroll-y h-full" ui-view></div>',
			            	 deps : []
			             },
			             {
			            	 state : 'app.device.mgmt',
			            	 url : '/devicemgmt',
			            	 templateUrl : 'tpl/deviceMgmt/device.html',
			            	 deps : ['js/app/deviceMgmt/device_ctrl.js',
			            		 	 'js/app/deviceMgmt/device_serv.js']
			             },
			             /***
			              * APIKey
			              */
			             {
			            	 state : 'app.APIkey',
			            	 url : '/APIkey',
			            	 template : '<div class="w-full scroll-y h-full" ui-view></div>',
			            	 deps : [
			            	         ]
			             },
			             {
			            	 state:'app.APIkey.accessKey',
			            	 url:'/accessKey',
			            	 templateUrl:'tpl/accessKey/AccessKey.html',
			            	 deps:[
			            	       'js/app/accessKey/AccessKey.js',
			            	       'js/app/accessKey/access_serv.js'
			            	       ]
			             },
			             /***
			              * 控件管理
			              */
			             {
			                 state: 'app.widget',
			                 url: '/widget',
			                 template: '<div class="w-full  h-full" ui-view></div>'
			             }, {
			                 state: 'app.widget.define',
			                 url: '/widgetdef',
			                 templateUrl: 'tpl/widget/define/widget.html',
			                 deps: [
			                        
					         ]
			             },
			             /***
			              * 应用管理
			              */
			             {
			                 state: 'app.application',
			                 url: '/application',
			                 template: '<div class="w-full  h-full" ui-view></div>'
			             }, {
			                 state: 'app.application.catagory',
			                 url: '/catagory',
			                 templateUrl: 'tpl/applicationmgmt/catagory/catagory.html',
			                 deps: [
			                 ]
			             }, {
			                 state: 'app.application.define',
			                 url: '/define',
			                 templateUrl: 'tpl/applicationmgmt/define/define.html',
			                 deps: [
				                     
					         ]
			             },
			             /***
			              * 愿数据管理
			              */
			             {
			                 state: 'app.metadata',
			                 url: '/metadata',
			                 template: '<div class="w-full  h-full" ui-view></div>'
			             }, {
			                 state: 'app.metadata.define',
			                 url: '/metadatadef',
			                 templateUrl: 'tpl/metadata/define/define.html',
			                 deps: [
			                     
			                 ]
			             },
			             /***
			              * 视图管理
			              */
			             {
			                 state: 'app.view',
			                 url: '/view',
			                 template: '<div ui-view class="w-full h-full"></div>'
			             }, {
			                 state: 'app.view.define',
			                 url: '/define',
			                 templateUrl: 'tpl/view/define/define.html',
			                 deps: [
			                     
			                 ]
			             },{
			                 state: 'app.target',
			                 url: '/target',
			                 template: '<div class="w-full  h-full" ui-view></div>'
			             }, {
			                 state: 'app.target.catagory',
			                 url: '/catagory',
			                 templateUrl: 'tpl/targetmgmt/catagory/catagory.html',
			                 deps: [
			                     
			                 ]
			             }, {
			                 state: 'app.target.define',
			                 url: '/define',
			                 templateUrl: 'tpl/targetmgmt/define/define.html',
			                 deps: [
				                      
					         ]
			             }, {
			                 state: 'app.target.dictionary',
			                 url: '/dictionary',
			                 templateUrl: 'tpl/dictionarymgmt/dictionary.html',
			                 deps: [
			                     
			                 ]
			             },
			             /***
			              * 统计分析
			              */
			             {
			                 state: 'app.log',
			                 url: '/log',
			                 template: '<div class="w-full h-full" ui-view></div>'
			             }, {
			                 state: 'app.log.query',
			                 url: '/query',
			                 templateUrl: 'tpl/log/log.html',
			                 deps: [
			                     
			                 ]
			             }, {
			                 state: 'app.access',
			                 url: '/access',
			                 template: '<div class="w-full h-full" ui-view></div>'
			             }, {
			                 state: 'app.access.statistic',
			                 url: '/statistic',
			                 templateUrl: 'tpl/log/statistic.html',
			                 deps: [
			                     
			                 ]
			             }
					 ];
					 angular.forEach(urls, function(o) {
						 var opt = {
								 url : o.url,
								 template : o.template,
								 templateUrl : o.templateUrl
						 };
						 if (o.resolve) {
							 opt.resolve = o.resolve;
						 }

						 if (o.deps && o.deps.length > 0
								 && !!!opt.resolve) {
							 opt.resolve = {
									 deps : [ 'uiLoad', function(uiLoad) {
										 return uiLoad.load(o.deps);
									 } ]
							 }
						 }
						 $stateProvider.state(o.state, opt);
					 });
				 } ]);