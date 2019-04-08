joint.shapes.node = {};
joint.shapes.node.Base = joint.shapes.basic.Generic.extend(_.extend({}, joint.shapes.basic.PortsModelInterface, {
	markup : '<g class="rotatable"><g class="scalable"><rect class="brect"/><rect class="hrect"/></g><g class="inPorts"/><g class="outPorts"/></g>',
	portMarkup : '<g class="port port<%= id %>"><circle class="port-body"/><text class="port-label"/></g>',
	defaults: joint.util.deepSupplement({
		type: 'node.Base',
		size: { width: 130, height: 85 }, 
		inPorts : [],
		outPorts : [],
		attrs : {
			'.brect': {
				width : 130,
				height : 85
			},
			"." : {
				magnet : 1
			},
			".port-body" : {
				r : 8,
				magnet : !0
			},
			'.inPorts circle': { fill: '#FFF', magnet: 'passive', type: 'input' },
			'.outPorts circle': { fill: '#FFF', type: 'output'},
			'.outPorts text': {'ref-y':-18}
		}
	}, joint.shapes.basic.Generic.prototype.defaults),
	getPortAttrs : function(a, b, c, d, e) {
		var f = {}, g = "port" + b, h = d + ">." + g, i = h
		+ ">.port-label", j = h + ">.port-body";
		var y=e==='in'?0:85;
		f[i] = {
				text : a
		}, f[j] = {
				port : {
					id : a || _.uniqueId(e),
					type : e
				}
		}, f[h] = {
				ref : ".brect",
				"ref-x":(b + .5) * (1 / c),
				"ref-y" : y
		};
		return f;
	}
}));

//Create a custom view for that element that displays an HTML div above it.
//-------------------------------------------------------------------------
joint.shapes.node.BaseView = joint.dia.ElementView.extend(joint.shapes.basic.PortsViewInterface).extend({
	template: [
	           '<div class="wf-node">',
	           '<div class="head">',
	           '<span class="iconfont icon-xingzhuang1"></span> <span class="title"></span>',
	           '<div class="tools">',
	           '<span class="setting"></span>',
	           '</div>',
	           '</div>',
	           '<div class="body">',
	           '<span class="state"></span>',
	           '<span class="name"></span>',
	           '<span class="desc"></span>',
	           '</div>',
	           '</div>'
	           ].join(''),

	           initialize: function() {
	        	   _.bindAll(this, 'updateBox');
	        	   joint.dia.ElementView.prototype.initialize.apply(this, arguments);

	        	   this.$box = $(_.template(this.template)());

	        	   //TODO
	        	   /*console.log(this.model);*/

	        	   if(this.model.attributes.inPorts.length==1){
	        		   this.$box.find('.head').append('<span class="in"></span>');
	        	   }
	        	   if(this.model.attributes.outPorts.length==1){
	        		   this.$box.find('.body').append('<span class="out"></span>');
	        	   }else if(this.model.attributes.outPorts.length==2){
	        		   this.$box.find('.body').append('<span class="out out1"><span class="text">yes</span></span><span class="out out2"><span class="text">no</span></span>');
	        	   }

	        	   //TODO初始化事件

	        	   // Update the box position whenever the underlying model changes.
	        	   this.model.on('change', this.updateBox, this);
	        	   // Remove the box when the model gets removed from the graph.
	        	   this.model.on('remove', this.removeBox, this);

	        	   this.updateBox();
	           },
	           render: function() {
	        	   joint.dia.ElementView.prototype.render.apply(this, arguments);
	        	   this.paper.$el.append(this.$box);
	        	   this.updateBox();
	        	   return this;
	           },
	           updateBox: function() {
	        	   // Set the position and dimension of the box so that it covers the JointJS element.
	        	   var bbox = this.model.getBBox();
	        	   // Example of updating the HTML with a data stored in the cell model.

	        	   //TODO更新界面
	        	   this.$box.addClass(this.model.get('class'));
	        	   this.$box.find('.state').text(this.model.get('data')['state']);
	        	   this.$box.find('.name').text(this.model.get('data')['name']);
	        	   this.$box.find('.desc').text(this.model.get('data')['desc']);
	        	   this.$box.find('.iconfont').attr('class',this.model.get('icon'));
	        	   if(this.model.get('data')['title']!=null){
	        		   this.$box.find('.title').text(this.model.get('data')['title']);
	        	   }else{
	        		   this.$box.find('.title').text(this.model.get('title'));
	        	   }

	        	   this.$box.css({ width: bbox.width, height: bbox.height, left: bbox.x, top: bbox.y, transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)' });
	           },
	           removeBox: function(evt) {
	        	   this.$box.remove();
	           }
});

joint.shapes.node.Trigger=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Trigger',
		class:'trigger',
		inPorts : [],
		outPorts : [''],
		icon:'iconfont icon-kaishirenwu',
		title:'开始'
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.TriggerView = joint.shapes.node.BaseView;

joint.shapes.node.Delay=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Delay',
		class:'delay',
		inPorts : [''],
		outPorts : [''],
		icon:'iconfont icon-iconfontshalou',
		title:'延迟'
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.DelayView = joint.shapes.node.BaseView;

joint.shapes.node.Execute=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Execute',
		class:'execute',
		inPorts : [''],
		outPorts : [''],
		icon:'iconfont icon-chilun',
		title:'执行'
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.ExecuteView = joint.shapes.node.BaseView;

joint.shapes.node.Filter=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Filter',
		class:'filter',
		inPorts : [''],
		outPorts : [''],
		icon:'iconfont icon-filter',
		title:'过滤',
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.FilterView = joint.shapes.node.BaseView;

joint.shapes.node.Decide=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Decide',
		class:'decide',
		inPorts : [''],
		outPorts : ['yes','no'],
		icon:'iconfont icon-lingxing',
		title:'判断',
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.DecideView = joint.shapes.node.BaseView;

//推荐系统
joint.shapes.node.Recommend=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Recommend',
		class:'recommend',
		inPorts : [''],
		outPorts : [''],
		icon:'iconfont icon-tuijian',
		title:'推荐系统',
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.RecommendView = joint.shapes.node.BaseView;

//SPSS
joint.shapes.node.Spss=joint.shapes.node.Base.extend({
	defaults : joint.util.deepSupplement({
		type: 'node.Spss',
		class:'spss',
		inPorts : [''],
		outPorts : [''],
		icon:'iconfont icon-cmd',
		title:'SPSS',
	}, joint.shapes.node.Base.prototype.defaults)
}),joint.shapes.node.SpssView = joint.shapes.node.BaseView;

Array.prototype.insert = function (index, item) { 
	this.splice(index, 0, item); 
};

function in_array(search,array){
	for(var i in array){
		if(array[i]==search){
			return true;
		}
	}
	return false;
}

$(function(){
	var paperEl=$('#paper');
	var graph = new joint.dia.Graph;
	var paper = new joint.dia.Paper({
		el: paperEl,
		width: paperEl.width(),
		height: paperEl.height()-3,
		model: graph,
		gridSize: 1,
		defaultLink: new joint.dia.Link({
			router: { name: 'manhattan' },
			connector: { name: 'rounded' },
			attrs: {
				'.connection': {
					stroke: '#333333',
					'stroke-width': 3
				},
				'.marker-target': {
					fill: '#333333',
					d: 'M 10 0 L 0 5 L 10 10 z'
				}
			}
		})
	});

//	loadData();


	paper.on('cell:pointerclick', function(cellView, evt, x ,y) {
		selectNode(cellView);
	});

	paper.on('cell:pointerdblclick', function(cellView, evt, x ,y) { 
		selectNode(cellView);
		wf.editNode();
	});

	function selectNode(cellView){
		$.each(graph.getElements(),function(i,n){
			paper.findViewByModel(n).unhighlight();
			paper.findViewByModel(n).$box.removeClass('highlighted');
		});
		if(cellView.$box){
			cellView.highlight();
			cellView.$box.addClass('highlighted');
		}
	}
	paper.on('blank:pointerclick', function(cellView, evt, x ,y) {
		$.each(graph.getElements(),function(i,n){
			paper.findViewByModel(n).unhighlight();
			paper.findViewByModel(n).$box.removeClass('highlighted');
		});
	});

//	添加一个节点
	function addNode(type,data){
		type=type.toLowerCase();
		if(in_array(type,Array('trigger','execute','filter','delay','decide','recommend','spss'))){
			var node;
			if(type=='trigger'){
				node=new joint.shapes.node.Trigger({data:data});
			}else if(type=='execute'){
				node=new joint.shapes.node.Execute({data:data});
			}else if(type=='delay'){
				node=new joint.shapes.node.Delay({data:data});
			}else if(type=='decide'){
				node=new joint.shapes.node.Decide({data:data});
			}else if(type=='filter'){
				node=new joint.shapes.node.Filter({data:data});
			}else if(type=='recommend'){
				console.log(1234);
				node=new joint.shapes.node.Recommend({data:data});
			}else if(type=='spss'){
				console.log(123);
				node=new joint.shapes.node.Spss({data:data});
			}
			if(node){
				/*var latestNode=getSelected();
		    if(latestNode==null){
		        latestNode=graph.getElements()[graph.getElements().length-1];
		    }
        	if(latestNode!=null){
	        	var pos=latestNode.position();
	        	node.position(pos.x+50,pos.y+50);
        	}*/
				var selectedNode=getSelected();
				if(selectedNode!=null){
					var pos=selectedNode.position();
					node.position(20+pos.x,20+pos.y);
				}else{
					var offsetCount=wf.graph.getElements().length+1;
					node.position(20*offsetCount,20*offsetCount);
				}
				graph.addCell(node);
				//node.toFront();
			}
			return node;
		}
		return null;
	}

//	双击编辑节点，需要被重写
	function editNode(){
		//编辑完成后可以把所有想存储的数据放在data里面，
		/*node.set('data',{
        state:'sfsdf',
        name:'shulei',
        desc:'sdfdsfssdfds'
    })；*/
	}

//	更新节点数据
	function updateNode(node,data){
		node.set('data',data);
	}

//	删除选中的节点
	function deleteSelectedNode(){
		$.each(getSelections(),function(i,n){
			n.remove();
		});
	}

//	加载界面，传入json string
	function loadData(data){
		if(data!=null&&data!=''){
			graph.fromJSON(JSON.parse(data))
		}
	}

//	获取选中的多个节点
	function getSelections(){
		var nodes=[];
		$.each(graph.getElements(),function(i,n){
			if(paper.findViewByModel(n).$el.attr('class').indexOf('highlighted')>-1){
				nodes.push(n);
			}
		});
		return nodes;
	}

//	获取选中的单个节点
	function getSelected(){
		var nodes=getSelections();
		if(nodes.length>0){
			return nodes[0];
		}
		return null;
	}

//	获取流程数据json
	function getGraphData(){
		return JSON.stringify(graph);
	}

	function updateNode(node,data){
		node.set('data',data);
		paper.findViewByModel(node).updateBox();
	}

	function getLatestNode(){
		var selectedNode=getSelected();
		if(selectedNode!=null){
			return selectedNode;
		}
		return graph.getElements()[0];
	}

	function getStartNode(){
		var node1=null;
		$.each(graph.getElements(),function(i,n){
			if(n.attributes.type=='node.Trigger'){
				node1=n;
				return;
			}
		});
		return node1;
	}

//	获得执行节点
	function getSendNode(){
		var node1=null;
		$.each(graph.getElements(),function(i,n){
			if(n.attributes.type=='node.Execute'){
				node1=n;
				return;
			}
		});
		return node1;
	}
//	获得全部节点
	function getAllNode(){
		var nodeList=[];
		$.each(graph.getElements(),function(i,n){
			nodeList.push(n);
		});
		return nodeList;
	}
	function getPrevNode(node){
		//paper.findViewByModel(node)
		if(node==null){
			node=getSelected();
		}
		var links=graph.getLinks();
		for (var i=0;i<links.length;i++) {
			var link=links[i];
			if(link.attributes.target.id==node.id){
				return graph.getCell(link.attributes.source.id);
			}
		}
		return null;
	}

	function getNextNodes(node){
		var nodeList=[];
		if(node==null){
			return null;
		}else{
			var links=graph.getLinks();
			for (var i=0;i<links.length;i++) {
				var link=links[i];
				if(link.attributes.source.id==node.id){
					nodeList.push(graph.getCell(link.attributes.target.id));
				}
			}
			return nodeList;
		}
		return null;
	}

	wf={
			addNode:addNode,
			editNode:editNode,
			updateNode:updateNode,
			deleteSelectedNode:deleteSelectedNode,
			loadData:loadData,
			getSelections:getSelections,
			getSelected:getSelected,
			getGraphData:getGraphData,
			updateNode:updateNode,
			getPrevNode:getPrevNode,
			getNextNodes:getNextNodes,
			getStartNode:getStartNode,
			getSendNode:getSendNode,
			getAllNode:getAllNode,
			paper:paper,
			graph:graph
	};
});
