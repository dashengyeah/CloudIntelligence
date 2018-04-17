/**
 * 
 */

function course(){
	//alert("course!");
	$("#item-org").css({"display":"none"});
	$("#item-training").css({"display":"none"});
	$("#item-course").css({"display":"block"});
}

function org(){
	$("#item-course").css({"display":"none"});
	$("#item-training").css({"display":"none"});
	$("#item-org").css({"display":"block"});
}

function training(){
	$("#item-org").css({"display":"none"});
	$("#item-course").css({"display":"none"});
	$("#item-training").css({"display":"block"});
}

$(document).ready(function(){
	bind_course();
})

function bind_course(){
	$("#btn_course_add").click(function(){
		alert("新增！");
		$.ajax({
			type:"POST",
			url:"managecourse?opt=add",
			data:new FormData($("#course_add_info")[0]),
            async: false,
            cache: false,
            contentType: false,
            processData: false,
			success:function(data){
				if(data["status"] == "OK") alert("新增成功！");
				else{
					alert("新增失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	});
	$("#btn_course_delete").click(function(){
		$.ajax({
			type:"POST",
			url:"managecourse?opt=delete",
			data:new FormData($("#course_delete_info")[0]),
            async: false,
            cache: false,
            contentType: false,
            processData: false,
			success:function(data){
				if(data["status"] == "OK") alert("删除成功！");
				else{
					alert("删除失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	});
	
	function showCourses(data){
		$("#courselist tr:gt(0)").remove();
		//$("#table1 tr:eq(0):not(:eq(0))").remove();
		$.each(data,function(n,v) {
			var newRow = "<tr><td>"+v["id"]+
			             "</td><td>"+v["name"]+
			             "</td><td>"+v["school"]+
			             "</td><td>"+v["teacher"]+
			             "</td></tr>";
			$("#courselist tr:last").after(newRow);
		})
	}
	$("#btn_course_list").click(function(){
		alert("show course");
		$.ajax({
			type:"POST",
			url:"managecourse?opt=list",
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK"){
					alert(data);
					showCourses(data["courses"]);
				}
				else{
					alert("查询失败！");
				}
			},
			error:function(xhr){
				alert("error: "+xhr.responseText);
			}
		})
	});
}