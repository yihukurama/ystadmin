/**
 * @param data.limit
 *            每页条数
 * @param data.page
 *            第几页
 * @param data.uid
 *            用户id
 * @param data.successFunctionSuccess
 *            业务接口成功返回时执行的方法
 * @param data.successFunctionFalse
 *            业务接口执行失败时需要执行的方法
 * @param data.tips
 *            业务接口执行失败时前端需要给用户的提示 优先级低于执行方法
 * @param data.isNoExcue
 *            业务接口执行失败是否不做任何反应
 */
function httpPost(data) {
	var layer = layui.layer, $ = layui.jquery;

	var limit = data.limit ? data.limit : 15;
	var page = data.page ? data.page : 1;
	var uid = data.uid ? data.uid : "";
	var successFunctionSuccess = data.successFunctionSuccess;
	var successFunctionFalse = data.successFunctionFalse;
	var tips = data.tips;
	var noFalseExcue = data.isNoExcue ? false : data.isNoExcue;

	$.ajax({
		type : "POST",
		url : data.url,
		data : JSON.stringify({
			"data" : data.data,
			"limit" : limit,
			"page" : page,
			"uid" : uid
		}),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function successF(response) {
			var success = response.success;
			if (success) {
				successFunctionSuccess(response);
			} else {
				/**
				 * 1.前端自定义提示信息 优先级3 2.后端返回的提示信息 优先级 4 3.执行失败的方法 优先级2 4.不需要任何提示
				 * 优先级1
				 */
				if (noFalseExcue) {// 不需要任何提示

				} else {
					if (successFunctionFalse) {
						successFunctionFalse;
					} else {
						layer.open({
							title : '业务操作失败提示',
							content : tips || response.msg
						});
					}
				}
			}
		},
		error : function(data) {
			layer.open({
				title : '接口错误提示',
				content : "接口请求失败，状态码为" + data.status
			});
		}
	});
}
