/**
 * 
 */

$(document).ready(function(){
	bind_org();
})

function bind_org(){
	$("#btn_org_add").click(function(){
		$.ajax({
			type:"POST",
			url:"manageorganization?opt=add",
			data:new FormData($("#org_add_info")[0]),
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
	$("#btn_org_delete").click(function(){
		$.ajax({
			type:"POST",
			url:"manageorganization?opt=delete",
			data:new FormData($("#org_delete_info")[0]),
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
	function showOrgs(data){
		$("#orglist tr:gt(0)").remove();
		//$("#table1 tr:eq(0):not(:eq(0))").remove();
		$.each(data,function(n,v) {
			var newRow = "<tr><td>"+v["id"]+
			             "</td><td>"+v["name"]+
			             "</td><td>"+v["type"]+
			             "</td><td>"+v["introduction"]+
			             "</td></tr>";
			$("#orglist tr:last").after(newRow);
		})
	}
	$("#btn_org_list").click(function(){
		$.ajax({
			type:"POST",
			url:"manageorganization?opt=list",
			data:{},
			dataType:"json",
			cache:false,
			success:function(data){
				if(data["status"] == "OK") showOrgs(data["organizations"]);
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