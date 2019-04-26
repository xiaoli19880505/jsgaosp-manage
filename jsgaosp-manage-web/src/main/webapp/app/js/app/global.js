//global params
angular.module('app').constant('GG', {
	/**
	 * the base path
	 */
	BASE : '',
	APIHOST:'http://localhost:9000',
	timer:'',
	states : { 
		home : 'app.home',
		login : 'login'
	},

	user:'USER',//普通用户
	sysadmin:'ADMIN',//系统管理员
	allPerm :'0-0,1-0,1-1,1-2,2-0,2-1,2-2,2-3,2-4,3-0,3-1,3-2,3-3,3-4,3-5,3-6,3-7,3-8,4-0,4-1,4-2,4-3,4-4,5-0,5-1,6-0,6-1,6-2,6-3,6-4,7-0,7-1,7-2,7-3,8-0,8-1,8-2',
	/**
	 * 普通用户角色
	 */
	user_role_fun : [{
		active : false,
		text : '我的仪表盘',
		id : '0-0',
		iconCls : 'fa fa-dashboard',
		sref : 'app.overview'
	}, {
		active: false,
	    text: '资产管理',
	    iconCls: 'glyphicon glyphicon glyphicon-move icon-drawer  text-muted',
	    sref : '#',
	    funcs: [{
	        id: '1-0',
	        text: '硬件设备管理',            
	        iconCls :'fa fa-caret-right',
	        sref: 'app.device.mgmt'
	    }, {
	        id: '1-1',
	        text: '系统设置',            
	        iconCls :'fa fa-caret-right',
	        sref: 'app.widget.define'
	    }, {
	        id: '1-2',
	        text: '工具',            
	        iconCls :'fa fa-caret-right',
	        sref: 'app.widget.define'
	    }]
	},{
        active: false,
        text: '视图展示',
        iconCls: 'glyphicon glyphicon glyphicon-th-list icon text-primary-dker',
        sref : '#',
        funcs: [{
            id: '2-0',
            text: '我的仪表盘',
            iconCls :'fa fa-caret-right',
            sref: 'app.application.catagory'
        }, {
            id: '2-1',
            text: '状态统计',
            iconCls :'fa fa-caret-right',
            sref: 'app.application.define'
        }, {
            id: '2-2',
            text: '苹果树',
            iconCls :'fa fa-caret-right',
            sref: 'app.application.define'
        }, {
            id: '2-3',
            text: '检测视图',
            iconCls :'fa fa-caret-right',
            sref: 'app.application.define'
        }, {
            id: '2-4',
            text: 'Viso视图',
            iconCls :'fa fa-caret-right',
            sref: 'app.application.define'
        }]
    },{
        active: false,
        text: '统计报表',
        iconCls: 'glyphicon glyphicon-stats   icon-note text-info',
        sref : '#',
        funcs: [{
            id: '3-0',
            text: '时事报告',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-1',
            text: '流量统计',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-2',
            text: 'TOPN报告',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-3',
            text: '故障报告',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-4',
            text: '统计报告',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-5',
            text: '操作日志',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-6',
            text: '巡检报告',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-7',
            text: '服务器',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }, {
            id: '3-8',
            text: '网络设备',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }]
    },{
        active: false,
        text: '告警管理',
        iconCls: 'glyphicon glyphicon-th-large icon text-success',
        sref : '#',
        funcs: [{
            id: '4-0',
            text: '告警设置',
            iconCls :'fa fa-caret-right',
            sref: 'app.view.define'
        }, {
            id: '4-1',
            text: '告警阈值设置',
            iconCls :'fa fa-caret-right',
            sref: 'app.target.catagory'
        }, {
            id: '4-2',
            text: '告警日志查询',
            iconCls :'fa fa-caret-right',
            sref: 'app.target.define'
        }, {
            id: '4-3',
            text: '告警亚索视图',
            iconCls :'fa fa-caret-right',
            sref: 'app.target.dictionary'
        }, {
            id: '4-4',
            text: '告警故障查询',
            iconCls :'fa fa-caret-right',
            sref: 'app.metadata.define'
        }]
    },{
        active: false,
        text: '应用管理',
        iconCls: 'glyphicon glyphicon-stats  icon-envelope text-danger',
        sref : '#',
        funcs: [{
			id : '5-0',
			text : '应用申报',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.app_applicant'
		},{
			id : '5-1',
			text : '应用审批',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.app_approve'
		},{
            id : '5-2',
            text : '应用管理',
            iconCls :'fa fa-caret-right',
            sref : 'app.system.app_manage'
        },{
            id : '5-3',
            text : '应用版本',
            iconCls :'fa fa-caret-right',
            sref : 'app.system.app_version'
        },{
				id : '5-4',
				text : '应用系统申报',
				iconCls :'fa fa-caret-right',
				sref : 'app.system.sys_applicant'
			},{
				id : '5-5',
				text : '应用系统审批',
				iconCls :'fa fa-caret-right',
				sref : 'app.system.sys_approve'
			}]
    },{
		active : false,
		text : '业务监控',
		iconCls : 'fa fa-database',
		sref : '#',
		funcs : [{
			active:false,
			id: '6-0',
			text:'业务管理',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '6-1',
			text:'业务列表',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '6-2',
			text:'业务方块',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '6-3',
			text:'业务分析',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '6-4',
			text:'告警设置',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		}]
	},{
		active : false,
		text : '配置管理',
		iconCls : 'fa fa-database',
		sref : '#',
		funcs : [{
			active:false,
			id: '7-0',
			text:'配置监控',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '7-1',
			text:'统计报表',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '7-2',
			text:'操作查询',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		},{
			active:false,
			id: '7-3',
			text:'设置',
			iconCls :'fa fa-caret-right',
			sref:'app.APIkey.accessKey'
		}]
	}],
	/**
	 * 系统管理员角色菜单
	 */
	sysadmin_role_fun : [{
		active : false,
		text : '系统管理',
		iconCls : 'fa fa-cog',
		sref : '#',
		funcs : [ {
			id : '8-0',
			text : '组织管理',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.org'
		}, {
			id : '8-1',
			text : '账号管理',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.user'
		}, {
			id : '8-2',
			text : '角色管理',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.role'
		} , {
			id : '8-3',
			text : '行政区划管理',
			iconCls :'fa fa-caret-right',
			sref : 'app.system.area'
		}, {
            id : '8-4',
            text : '系统参数',
            iconCls :'fa fa-caret-right',
            sref : 'app.system.sysargs'
        },
		// 	{
		// 	id : '8-5',
		// 	text : '测试版块',
		// 	iconCls :'fa fa-caret-right',
		// 	sref : 'app.system.test'
		// },
			{
            id : '8-6',
            text : '数据字典',
            iconCls :'fa fa-caret-right',
            sref : 'app.system.codesort'
        }]
	}],
	colors : [ {
		backgroundColor : '#FFFFFF'
	}, {
		backgroundColor : '#F4F4EC'
	},{
        backgroundColor : '#999999'
    }, {
		backgroundColor : '#000000'
	}, {
		backgroundColor : '#5cc3cf'
	}, {
		backgroundColor : '#867CC5'
	}, {
		backgroundColor : '#FFB400'
	}, {
		backgroundColor : '#E0567A'
	}, {
		backgroundColor : '#F47A53'
	}, {
		backgroundColor : '#73B648'
	} ],
	idx : function() {
		return ++___idx;
	}
});