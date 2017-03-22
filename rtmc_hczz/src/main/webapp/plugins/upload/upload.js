//上传图片
//扩展方法
$.fn.extend({
	uploadImage:function(from,realImgUrl,showImgId){
		this.parent(".l-text").css({border:'none'});
		this.uploadify(
				{
					// 开启调试
					'debug' : false,
					// 是否自动上传
					'auto' : true,
					// 超时时间
					'successTimeout' : 99999,
					// 附带值
					'formData' : {
						'userid' : '用户id',
						'username' : '用户名',
						'rnd' : '加密密文'
					},
					// flash
					'swf' : "/plugins/upload/css/uploadify.swf",
					// 不执行默认的onSelect事件
					'overrideEvents' : [ 'onDialogClose' ],
					// 文件选择后的容器ID
					'queueID' : 'uploadfileQueue',
					// 服务器端脚本使用的文件对象的名称 $_FILES个['upload']
					'fileObjName' : 'upload',
					// 上传处理程序
					'uploader' : '/upload/uploadFile.do',
					// 浏览按钮的背景图片路径
					// 'buttonImage':'upbutton.gif',
					'buttonText' : '上传图片',
					// 浏览按钮的宽度
					'width' : '100',
					// 浏览按钮的高度
					'height' : '20',
					// expressInstall.swf文件的路径。
					// 'expressInstall':'expressInstall.swf',
					// 在浏览窗口底部的文件类型下拉菜单中显示的文本
					'fileTypeDesc' : '支持的格式：',
					// 允许上传的文件后缀
					'fileTypeExts' : '*.jpg;*.png;*.bmp;*.gif',
					// 上传文件的大小限制
					'fileSizeLimit' : '3MB',
					// 上传数量
					'queueSizeLimit' : 1,
					// 每次更新上载的文件的进展
					'onUploadProgress' : function(file, bytesUploaded, bytesTotal,
							totalBytesUploaded, totalBytesTotal) {
						// 有时候上传进度什么想自己个性化控制，可以利用这个方法
						// 使用方法见官方说明

					},
					// 选择上传文件后调用
					'onSelect' : function(file) {
					},
					// 返回一个错误，选择文件的时候触发
					'onSelectError' : function(file, errorCode, errorMsg) {
						switch (errorCode) {
						case -100:
							alert("上传的文件数量已经超出系统限制的"
									+ $('#uploadify').uploadify('settings',
											'queueSizeLimit') + "个文件！");
							break;
						case -110:
							alert("文件 ["
									+ file.name
									+ "] 大小超出系统限制的"
									+ $('#uploadify').uploadify('settings',
											'fileSizeLimit') + "大小！");
							break;
						case -120:
							alert("文件 [" + file.name + "] 大小异常！");
							break;
						case -130:
							alert("文件 [" + file.name + "] 类型不正确！");
							break;
						}
					},
					// 检测FLASH失败调用
					'onFallback' : function() {
						alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
					},
					// 上传到服务器，服务器返回相应信息到data里
					'onUploadSuccess' : function(file, datasource, response) {
						
						datasource = eval("(" + datasource + ")");
						if (datasource.status == 1) {
							form.getEditor(realImgUrl).setValue(datasource.msg);
							$("#"+showImgId).attr("src",datasource.msg);
						} else {
							form.getEditor(realImgUrl).setValue("");
							$("#"+showImgId).attr("src","/images/showbg.gif");
							alert("上传照片失败！");
						}
					}
				});
	}
});
