// global params
var ___idx = 10000;
angular
    .module('app')
    .constant(
    'GG',
    {
        /**
         * the base path
         */
        BASE: '',
        states: {
            home: 'app.home',
            topic: 'app.topic',
            login: 'login'
        },
        impl: "实施人员",
        admin: "超级管理员",
        role_fun: [
            /** 超级管理员权限 开始 */
			{
			    active: false,
			    text: '控件管理',
			    iconCls: 'glyphicon glyphicon glyphicon-move icon-drawer  text-muted',
			    funcs: [{
			        id: '0-0',
			        text: '控件定义',
			        sref: 'app.widget.define'
			    }]
			},
			{
                active: false,
                text: '系统管理',
                iconCls: 'glyphicon glyphicon-stats  icon-wrench text-warning',
                funcs: [{
                    id: '1-0',
                    text: '机构管理',
                    sref: 'app.hospital.org'
                },{
                    id: '1-1',
                    text: '用户管理',
                    sref: 'app.system.user'
                },{
                    id: '1-2',
                    text: '角色管理',
                    sref: 'app.system.role'
                },{
                    id: '1-3',
                    text: '企业域管理',
                    sref: 'app.system.domain'
                }]
            },
            {
                active: false,
                text: '统计分析',
                iconCls: 'glyphicon glyphicon-stats  icon-envelope text-danger',
                funcs: [{
                    id: '2-0',
                    text: '日志查询',
                    sref: 'app.log.query'
                },{
                    id: '2-1',
                    text: '访问统计',
                    sref: 'app.access.statistic'
                }]
            },
            {
                active: false,
                text: '发现管理',
                iconCls: 'icon-compass text-info-lter',
                funcs: [{
                    id: '3-0',
                    text: '栏目管理',
                    sref: 'app.column'
                },{
                    id: '3-1',
                    text: '文章管理',
                    sref: 'app.article'
                }]
            },
            {
                active: false,
                text: '指标体系',
                iconCls: 'icon-pin text-info',
                funcs: [{
                    id: '4-0',
                    text: '标准指标体系',
                    sref: 'app.standard.target'
                },{
                    id: '4-1',
                    text: '指标计算模板',
                    sref: 'app.standard.template'
                },
                {
                	id: '4-2',
                    text: '插件管理',
                    sref: 'app.standard.plugin'
                }]
            },
            {
                active: false,
                text: '应用管理',
                iconCls: 'glyphicon glyphicon glyphicon-th-list icon text-primary-dker',
                funcs: [{
                    id: '5-0',
                    text: '应用定义',
                    sref: 'app.application.define'
                }, {
                    id: '5-1',
                    text: '应用分类',
                    sref: 'app.application.catagory'
                }]
            },
            /** 超级管理员权限 结束 */
            
            /** 实施人员权限 开始 */
            {
                active: false,
                text: '视图管理',
                iconCls: 'glyphicon glyphicon-th-large icon text-success',
                funcs: [{
                    id: '6-0',
                    text: '视图定义',
                    sref: 'app.view.define'
                }, {
                    id: '6-1',
                    text: '数据定义',
                    sref: 'app.target.define'
                }, {
                    id: '6-2',
                    text: '数据分类',
                    sref: 'app.target.catagory'
                }, {
                    id: '6-3',
                    text: '字典管理',
                    sref: 'app.hospital.dictionary'
                }]
            },
            {
                active: false,
                text: '指标管理',
                iconCls: 'icon-pin text-info',
                funcs: [{
                    id: '7-0',
                    text: '指标体系',
                    sref: 'app.hospital.target'
                }, {
                    id: '7-1',
                    text: '数据模板',
                    sref: 'app.hospital.tpl'
                }]
            },
            {
                active: false,
                text: '绩效管理',
                iconCls: 'icon-target text-danger',
                funcs: [{
                    id: '8-0',
                    text: '绩效模板管理',
                    sref: 'app.achievements'
                },{
                    id: '8-1',
                    text: '绩效分配',
                    sref: 'app.achievementsrelation'
                },{
                    id: '8-2',
                    text: '考核周期',
                    sref: 'app.achievementcycle'
                },{
                    id: '8-3',
                    text: '绩效维护',
                    sref: 'app.maintain'
                }]
            },
            {
                active: false,
                text: '通知管理',
                iconCls: 'glyphicon glyphicon-stats  icon-bell text-danger',
                funcs: [{
                    id: '9-0',
                    text: '异常定义',
                    sref: 'app.notice.exception'
                }, {
                    id: '9-1',
                    text: '通知查询',
                    sref: 'app.notice.query'
                }, {
                    id: '9-2',
                    text: '周报管理',
                    sref: 'app.notice.report'
                }
//                , {
//                    id: '3-2',
//                    text: '通知报告',
//                    sref: 'app.notice.report'
//                }
                ]
            },
            {
                active: false,
                text: '同行对比',
                iconCls: 'glyphicon glyphicon-stats  icon-flag text-muted',
                funcs: [{
                    id: '10-0',
                    text: '同行对比',
                    sref: 'app.hospital.compare'
                }]
            },
            {
                active: false,
                text: '元数据管理',
                iconCls: 'glyphicon glyphicon-stats   icon-note text-info',
                funcs: [{
                    id: '11-0',
                    text: '元数据定义',
                    sref: 'app.metadata.define'
                } 
                /*,{
                    id: '7-1',
                    text: '数据查询',
                    sref: 'app.metadata.testdata'
                }, {
                    id: '7-2',
                    text: '数据维护',
                    sref: 'app.metadata.maintenance'
                }*/
                ]
            },
            {
                active: false,
                text: '系统管理',
                iconCls: 'glyphicon glyphicon-stats  icon-wrench text-warning',
                funcs: [{
                    id: '12-0',
                    text: '人员管理',
                    sref: 'app.hospital.user'
                }, {
                    id: '12-1',
                    text: '角色管理',
                    sref: 'app.hospital.role'
                },
                /*{
                    id: '12-2',
                    text: '指标权限',
                    sref: 'app.hospital.permission'
                }, */
                {
                    id: '12-2',
                    text: '组织角色管理',
                    sref: 'app.hospital.roledept'
                }, {
                    id: '12-3',
                    text: '应用管理',
                    sref: 'app.hospital.plugin'
                }
                //, {
                //    id: '12-4',
                //    text: '部门管理',
                //    sref: 'app.hospital.dept'
                //}, {
                //    id: '12-5',
                //    text: '通讯录管理',
                //    sref: 'app.hospital.contact'
                //}
                ]
            },
            {
                active: false,
                text: '实施工具集',
                iconCls: 'icon-target text-danger',
                funcs: [{
                    id: '13-0',
                    text: 'DRUID查询',
                    sref: 'app.metadata.testdata'
                },{
                    id: '13-1',
                    text: '数据维护',
                    sref: 'app.metadata.maintenance'
                }]
            },
            /*{
                active: false,
                text: '周报管理',
                iconCls: 'fa fa-file-text-o text-success',
                funcs: [{
                    id: '13-0',
                    text: '周报模板管理',
                    sref: 'app.report.tpl'
                },{
                    id: '13-1',
                    text: '周报分配',
                    sref: 'app.report.send'
                }]
            },*/
            {
                active: false,
                text: '业务组织管理',
                iconCls: 'fa fa-file-text-o text-success',
                funcs: [{
                    id: '14-0',
                    text: '分类管理',
                    sref: 'app.organization.cat'
                },{
                    id: '14-1',
                    text: '组织信息管理',
                    sref: 'app.organization.info'
                }]
            }],
        colors: [{
            backgroundColor: '#FFFFFF'
        }, {
            backgroundColor: '#F4F4EC'
        }, {
            backgroundColor: '#999999'
        }, {
            backgroundColor: '#000000'
        }, {
            backgroundColor: '#5cc3cf'
        }, {
            backgroundColor: '#867CC5'
        }, {
            backgroundColor: '#FFB400'
        }, {
            backgroundColor: '#E0567A'
        }, {
            backgroundColor: '#F47A53'
        }, {
            backgroundColor: '#73B648'
        }],
        idx: function () {
            return ++___idx;
        }
    });