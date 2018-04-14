/**
 * managecourse.js
 */
function showCourses(data){
	$("#list tr:gt(0)").remove();
	//$("#table1 tr:eq(0):not(:eq(0))").remove();
	$.each(data,function(n,v) {
		var newRow = "<tr><td>"+v["id"]+
		             "</td><td>"+v["name"]+
		             "</td><td>"+v["school"]+
		             "</td><td>"+v["teacher"]+
		             "</td><td>"+v["post"]+
		             "</td></tr>";
		$("#list tr:last").after(newRow);
	})
}

$(document).ready(function(){
	$("#btn_list").click(function(){
		$.ajax({
			type:"POST",
			url:"managecourse?opt=list",
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") showCourses(data["courses"]);
				else{
					alert("查询失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	});
	
	$("#btn_add").click(function(){
		var param="id="+$("#txt_id").val()+
		          "&name="+$("#txt_name").val()+
		          "&introduction="+$("#txt_intro").val();
		//alert(param);
		$.ajax({
			type:"POST",
			url:"managecourse?opt=add&"+param,
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") showCourses(data["courses"]);
				else{
					alert("增加失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	})
	
	$("#btn_delete").click(function(){
		var param="id="+$("#txt_id").val()+
		          "&name="+$("#txt_name").val()+
		          "&introduction="+$("#txt_intro").val();
		//alert(param);
		$.ajax({
			type:"POST",
			url:"managecourse?opt=delete&"+param,
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") showCourses(data["courses"]);
				else{
					alert("删除失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	})
})