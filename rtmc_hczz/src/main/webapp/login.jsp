<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>动态痕迹合成作战管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
	<!-- dialog -->
	<link type="text/css" rel="stylesheet" href="/plugins/ligerUi/css/ligerui-dialog.css" />
	<script type="text/javascript" src="/plugins/ligerUi/js/ligerUi.min.js"></script>

    <!-- validate -->
    <link rel="stylesheet" href="/plugins/jqueryValidation/css/validationEngine.jquery.css" />
    <script src="/plugins/jqueryValidation/js/jquery.validationEngine.js"></script> 
    <script src="/plugins/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script> 
    <script src="/plugins/jqueryValidation/js/jquery.validationEngine-addMethod.js"></script> 
    <link rel="stylesheet" type="text/css" href="/css/login.css">
<script type="text/javascript">
  $(function(){
     
	//session过期窗体处理
	if (window != top) {top.location.href = location.href; }
	
    var height=$(window).height()-$(".top").height()-$(".bottom").height();
    var width=$(window).width();
    $(".center").css({
    	height:height,
    	width:width
    });
    $(".login_box").css({
          left:($(window).width()-$(".login_box").width())*0.7
    });
     //获取验证
	 getRandom();
	 if($("#username").val().length==0){
	    	$("#username").focus();
	    }
	    
	    
	    //回车事件
	    document.onkeydown = function(e){
	        if(!e) e = window.event;//火狐中是 window.event
	        if((e.keyCode || e.which) == 13){
	            login();
	        }
	    };
	    
	    //验证表单
		$('#loginForm').validationEngine('attach', { 
	        promptPosition: 'centerRight', 
	        scroll: false ,
	        validationEventTriggers:"onChange",  
	        //校验只显示一个错误提示 
	        //maxErrorsPerField: 1,
	        //显示校验的记录数
	        showOneMessage: true,
	        //去掉提示边框和背景颜色
	        //addPromptClass: 'formError-noArrow formError-text',
	        custom_error_messages: {
	          '#username': {
	              'required': {
	                   'message': '* 账号不能为空!'
	               },
	              'length':{
	            	   'message': '* 账号长度为3-30位字符!'
	              }
	          },
	          '#pwd': {
	              'required': {
	                   'message': '* 密码不能为空!'
	               },
		           'length':{
		            	'message': '* 密码长度为5-20位字符!'
		           }
	          },
	          '#random': {
	              'required': {
	                   'message': '* 验证码不能为空!'
	               }
	          }
	       }
	    }); 
   
  });
  
  //获取验证码
  function getRandom(){
	  $.ajax({
			url:'/admin/getRandStr.do',
			type:'POST',
			dataType:'json',
			success:function(data){
				if(data.status.code=="10000"){
					$(".code_val").html(data.status.msg);
					//$("#code").val(data.status.msg);
				}
			}
		});
  }
  
  //登录
  function login(){
  	var flag=$("#loginForm").validationEngine('validate');
      if(!flag){
          return;
      }
     var manager = $.ligerDialog.waitting( "登录中，请稍后......" );
  	$.ajax({
  		url:'/admin/adminUsers/login.do',
  		type:'POST',
  		dataType:'json',
  		data:$("#loginForm").serialize(),
  		success:function(data){
  			manager.close();
  			if(data.status.code=="10000"){
  				window.location.href="/adminMain.html";
  			}else{
  				$.ligerDialog.alert(data.status.msg,"提示信息","warn");
  			}			
  		}
  	});
  }
</script>
  </head>
  
  <body>
        <!-- 顶部 -->
        <div class="top">
             <div class="login_top"></div>
        </div>
        <!-- 中间部 -->
        <div class="center">
             <form id="loginForm" class="login_box">
                  <div class="login_form_top"></div>
                  <div class="login_form_center">
                       <div class="username_label">
                            <input type="text" name="username" id="username" class="username validate[required,length[3,30]]"/>
                       </div>
                       <div class="password_label">
                            <input type="password" name="pwd" id="pwd" class="password validate[required,length[5,30]]"/>
                       </div>
                       <div class="validate_label">
                            <input type="text" name="random" id="random" class="code validate[required]" data-prompt-position="bottomRight"/>
                            <span class="code_val" onclick="getRandom()"></span>
                       </div>
                       <input type="button" class="login_btn" onclick="login()"/>
                  </div>
             </form>
        
        </div>
        <!-- 底部 -->
        <div class="bottom">
             <div class="login_bottom">Copyright © 2016-2050 成都高德唯斯科技股份有限公司</div>
        </div>
  </body>
</html>
