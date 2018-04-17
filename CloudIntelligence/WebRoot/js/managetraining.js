/**
 * 
 */

$(document).ready(function(){
	bind_training();
})

function bind_training(){
	$("#btn_training_add").click(function(){
		$.ajax({
			type:"POST",
			url:"managetraining?opt=add",
			data:new FormData($("#training_add_info")[0]),
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
	})
	$("#btn_training_delete").click(function(){
		$.ajax({
			type:"POST",
			url:"managetraining?opt=delete",
			data:new FormData($("#training_delete_info")[0]),
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
	function showTrainings(data){
		$("#traininglist tr:gt(0)").remove();
		//$("#table1 tr:eq(0):not(:eq(0))").remove();
		$.each(data,function(n,v) {
			var newRow = "<tr><td>"+v["id"]+
			             "</td><td>"+v["name"]+
			             "</td><td>"+v["content"]+
			             "</td><td>"+v["course"]+
			             "</td><td>"+v["releasedate"]+
			             "</td><td>"+v["deadline"]+
			             "</td></tr>";
			$("#traininglist tr:last").after(newRow);
		})
	}
	$("#btn_training_list").click(function(){
		$.ajax({
			type:"POST",
			url:"managetraining?opt=list",
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") showTrainings(data["trainings"]);
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