<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/front/center/shopcar/shopcar_list.js"></script>
<%
	String orders_add_url = request.getAttribute("basePath") + "/pages/front/center/orders/orders_add_pre.action" ;
%>
<body class="back">
	<div class="contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="2" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;我的购物车</strong>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center"><strong>商品图片</strong></th>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>商品单价</strong></th>
									<th class="text-center"><strong>购买数量</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allShopcars}" var="shopcar">
									<tr id="goods-${shopcar.gid}"> 
										<td class="text-center">
											<input type="checkbox" id="gid" name="gid" value="${shopcar.gid}">
										</td>
										<td class="text-center"><img src="upload/goods/${allGoodses[shopcar.gid].photo}" style="width:30px;"></td>
										<td class="text-center">${allGoodses[shopcar.gid].name}</td>
										<td class="text-center"><span id="price-${shopcar.gid}">${allGoodses[shopcar.gid].price}</span></td>
										<td class="text-center">
											<button class="btn btn-primary" id="sub-${shopcar.gid}">-</button>
											<input type="text" id="amount-${shopcar.gid}" name="amount-${shopcar.gid}" class="shopcar-form-control" size="4" maxlength="4" value="${shopcar.amount}">
											<button class="btn btn-primary" id="add-${shopcar.gid}">+</button> 
										</td>
										<td class="text-center"><button class="btn btn-primary" id="updateBtn-${shopcar.gid}">修改</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-right">
							总价￥<span id="allPrice" class="text-danger h2">78.9</span>
						</div>
						<div>
							<button class="btn btn-primary" id="editBtn"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改数量</button>
							<button class="btn btn-danger" id="rmBtn"><span class="glyphicon glyphicon-remove"></span>&nbsp;移出购物车</button>
							<a class="btn btn-success" href="<%=orders_add_url%>" id="addBtn"><span class="glyphicon glyphicon-file"></span>&nbsp;下单</a>
						</div>
					</div>
					<div class="panel-footer" style="height:80px;">
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
