/**
 * login
 */
$(document).ready(function(){
	$("#btn").click(function(){
		$.ajax({
			type:"POST",
			url:"login?"+$("#loginform").serialize(),
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") window.location.href="index.html";
				else{
					alert("登录失败，账号或密码错误！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	})
})