var grid,

//table	columns
columns = [
       	{ display: '主键ID', hide:true,		name: 'id', 			align: 'left',		width: "5%"},
       	{ display: '出库单号', 			name: 'code', 			align: 'center',		width: "12%" },
       	{ display: '状态', 			name: 'statusName', 			align: 'center',		width: "6%",
       		render:function(rowdata){
   				if(rowdata.status==0){
   				return "<font class=\"daiconfirm\">"+rowdata.statusName+"</font>";
   				}else if(rowdata.status==1){
   					return "<font class=\"passconfirm\">"+rowdata.statusName+"</font>";
   				}else if(rowdata.status==2){
   					return "<font class=\"notconfirm\">"+rowdata.statusName+"</font>";
   				}
   				return "";
   			}
       	},
     	{ display: '总金额', 		name: 'totalMoney', 			align: 'center',		width: "6%" },
       	{ display: '总数量', 		name: 'totalNumber', 			align: 'center',		width: "6%" },	
       	{ display: '备注', 		name: 'description', 			align: 'center',		width: "16%" },
       	{ display: '出库时间', 		name: 'outTime', 			align: 'center',		width: "8%" },
       	{ display: '创建人', 		name: 'createUserName', 			align: 'center',	width: "5%" },
       	{ display: '创建时间', 		name: 'createTime', 			align: 'center',	width: "10%" },
       	{ display: '修改人', 		name: 'updateUserName', 			align: 'center',	width: "5%" },
    	{ display: '修改时间', 		name: 'updateTime', 			align: 'center',	width: "10%" },
    	{ display: '审核意见', 		name: 'approveView', 			align: 'center',	width: "13%" },
    	{ display: '审核人', 		name: 'approveUserName', 			align: 'center',	width: "5%" },
    	{ display: '审核时间', 		name: 'approveTime', 			align: 'center',	width: "10%" },
  ],
//所有页面都要获取这两个变量
menuId = $.fsh.requestParam("menuId"),//菜单id 
_title="";

$(function (){	
	//$.fsh.isLogout();
	//设置路径
	_title = "功能管理|物品出库维护";
	$.fsh.createTitleAndFuncs(_title, menuId);
	//创建数据列表
	dataUrl = baseUrl + "goodsOutStore/getGoodsOutStoreCoufirmByPageList.do";
	grid = $.fsh.createTable(columns, dataUrl, $.fsh.options.page(), "orderNum", true);
	//排序跳转至第一页
	$("div.l-grid-hd-cell-outner").click(function(){
		if(grid.options.page!=1) grid.changePage("first");
	});
});
//刷新数据
function reflushData(){
grid.loadData();
}

//确认物品出库信息
function confirmGoodsOutStore(){
	var _flag = $.fsh.noteForNotSelected("请选中数据后，再操作！");
	if(_flag){
			var url="/manager/gw_goods_out_store/gw_goods_out_store_coufirm.html?id="+_flag.id+'&code='+_flag.code+'&status='+_flag.status
			parent.f_addTab("goodsOutStoreConfirm","确认物品入库信息",url);	
	}
}

//查阅物品出库信息
function consultGoodsOutStore(){
	var _flag = $.fsh.noteForNotSelected("请选中数据后，再操作！");
	if(_flag){
			var url="/manager/gw_goods_out_store/gw_goods_out_store_consult.html?id="+_flag.id+'&code='+_flag.code+'&status='+_flag.status+'&temp=1';
			parent.f_addTab("goodsOutStoreConsult","查阅物品出库信息",url);	
	}
}

//根据条件查询物品出库数据
function getGoodsOutStoreByCondition(){
	var form = new liger.get("form2");
 	var data= form.getData();
 	var code = data.code;	 
 	var outTime = data.outTime;
 	var status = data.status;
	dataUrl = baseUrl+"goodsOutStore/getGoodsOutStoreByPageList.do?code="+code+'&outTime='+outTime+'&status='+status
	grid = $.fsh.createTable(columns, dataUrl, $.fsh.options.page(), "", true);	
}