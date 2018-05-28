$(function() {
	$("button[id*=addCar-]").each(function(){
		var gid = $(this).attr("id").split("-")[1] ;
		$(this).on("click",function(){
			// console.log("*** gid = " + gid) ;
			$.post("pages/front/center/shopcar/shopcar_add.action",{
				"gid" : gid
			},function(data){
				operateAlert(data.flag == true,"购物车添加成功！","购物车添加失败！") ;
			},"json") ; 
		}) ;
	}) ; 
}) 