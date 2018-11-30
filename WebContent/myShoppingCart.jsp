<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctxPath" value="${pageContext.request.contextPath}"
	scope="session"></c:set>
<c:if test="${user==null }">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="author" content="M_Adnan" />
<!-- Document Title -->
<title>我的购物车</title>
<link rel="shortcut icon" href="${ctxPath }/static/images/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="${ctxPath }/static/images/favicon.ico"
	type="image/x-icon">
<link rel="stylesheet" type="text/css"
	href="${ctxPath }/static/rs-plugin/css/settings.css" media="screen" />
<link rel="stylesheet" href="${ctxPath }/static/css/ionicons.min.css">
<link rel="stylesheet" href="${ctxPath }/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctxPath }/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctxPath }/static/css/main.css">
<link rel="stylesheet" href="${ctxPath }/static/css/style.css">
<link rel="stylesheet" href="${ctxPath }/static/css/responsive.css">

<link
	href="https://fonts.googleapis.com/css?family=Lato:100i,300,400,700,900"
	rel="stylesheet">

<script src="${ctxPath }/static/js/vendors/modernizr.js"></script>
<script src="${ctxPath }/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var ShoppingCart;
$.ajax({
	type:"post",
	url:"MyShoppingCartServlet",
	dataType:"JSON",
	success:function(resMsg){
		ShoppingCart = resMsg;
		if(resMsg.code==0){
			alert(resMsg.msg);
		}else{
			$.each(resMsg,function(){
				var totalPrice = parseInt(this.quantity)*parseFloat(this.price);
				$("tbody:first").append(add(this,totalPrice));
			});
		}
	}
});

var totalPrice=0;
function buyChoice(data){
	$.each(ShoppingCart,function(){
		if(this.goods_id==data.value){
			if(data.checked==true){
				totalPrice = totalPrice+this.price*this.quantity
			}else{
				totalPrice = totalPrice-this.price*this.quantity
			}
		}
	});
	$("#allPrice").text(totalPrice);
}


function add(cart,totalPrice){
	return returnValue = "<tr><td><input type='checkbox' value='"+cart.goods_id+"' onchange='buyChoice(this)'>选择</td> <td><div class='media'><div class='media-left'> <a href='#.'> <img class='img-responsive' src='"+cart.photo_url+"' alt='"+cart.goods_name+"' >"+
	" </a> </div><div class='media-body'>"+cart.goods_name+"</div></div></td><td class='text-center padding-top-60'>￥"+cart.price+
	"</td><td class='text-center'><div class='quinty padding-top-20'>"+cart.quantity+"</div></td><td class='text-center padding-top-60' >"+totalPrice+"</td>"+
	"<td class='text-center padding-top-60'><button id='add"+cart.goods_id+"' onclick='addOne("+cart.goods_id+","+cart.user_id+")'>+1</button>&nbsp;&nbsp;<button id='plus"+cart.goods_id+"' onclick='plusOne("+cart.goods_id+","+cart.user_id+")'>&nbsp;-1&nbsp;</button>&nbsp;&nbsp;&nbsp;"+
	"<button onclick='deleteIt("+cart.goods_id+","+cart.user_id+")'>&nbsp;删除&nbsp;</button></td></tr>";
}

function addOne(goodsIdValue,userIdValue){
	$.ajax({
		data:{goodsId:goodsIdValue,userId:userIdValue},
		type:"post",
		url:"QuantityAddOneServlet",
		dataType:"JSON",
		success:function(resMsg){
			$("tbody:first").empty();
			$.each(resMsg,function(){
				var totalPrice = parseInt(this.quantity)*parseFloat(this.price);
				$("tbody:first").append(add(this,totalPrice));
			});
		}
	});
}

function plusOne(goodsIdValue,userIdValue){
	$.ajax({
		data:{goodsId:goodsIdValue,userId:userIdValue},
		type:"post",
		url:"QuantityPlusOneServlet",
		dataType:"JSON",
		success:function(resMsg){
			$("tbody:first").empty();
			$.each(resMsg,function(){
				var totalPrice = this.quantity*this.price;
				$("tbody:first").append(add(this,totalPrice));
			});
		}
	});
}


function deleteIt(goodsIdValue,userIdValue){
	$.ajax({
		data:{goodsId:goodsIdValue,userId:userIdValue},
		type:"post",
		url:"DeleteGoodsServlet",
		dataType:"JSON",
		success:function(resMsg){
			$("tbody:first").empty();
			$.each(resMsg,function(){
				var totalPrice = this.quantity*this.price;
				$("tbody:first").append(add(this,totalPrice));
			});
		}
	});
}


function search(){
	var messageValue = $("#search").val();
	$.ajax({
		data:{message:messageValue},
		type:"post",
		url:"SearchGoodsServlet",
		dataType:"JSON",
		success:function(resMsg){
			$("tbody:first").empty();
			if(resMsg.code==0){
				alert(resMsg.msg)
			}else{
				$.each(resMsg,function(){
					var totalPrice = parseInt(this.quantity)*parseFloat(this.price);
					$("tbody:first").append(add(this,totalPrice));
				});
				total(resMsg);
			}
		}
	});
}

var buyGoodsId = new Array();
var i=0;
function buyGoods(){
	$("input[type='checkbox']:checked").each(function(){
		buyGoodsId[i]=this.value;
		i++;
	});
	if(i!=0){
		$.ajax({
			data:{length:i,goodsIdArray:buyGoodsId},
			traditional:true,
			type:"post",
			url:"SubmitOrderSevlet",
			dataType:"JSON",
			success:function(resMsg){
				if(resMsg.code==1){
					window.location.href="order.jsp";
				}else{
					alert(resMsg.msg);
				}
			}
		});
	}else{
		alert("您没有选择任何商品");
	}
}
</script>
</head>
<body>
	<header>
	<div class="container">
		<div class="logo">
			<a href="index.html"><img
				src="${ctxPath }/static/images/logo.png" alt=""></a>
		</div>
		<div class="search-cate">
			<div class="btn-group bootstrap-select">
				<span class="filter-option pull-left">搜索</span>&nbsp; <span
					class="bs-caret"><span class="caret"></span></span>
				</button>
				<div class="dropdown-menu open" role="combobox">
					<ul class="dropdown-menu inner" role="listbox"
						aria-expanded="false">
						<li data-original-index="0" class="selected"><a tabindex="0"
							class="" data-tokens="null" role="option" aria-disabled="false"
							aria-selected="true"></a></li>
						<li data-original-index="2"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> TV &amp;
									Video</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="3"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Camera,
									Photo &amp; Video</span><span
								class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="4"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Cell Phones
									&amp; Accessories</span><span
								class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="5"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Headphones</span><span
								class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="6"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Video Games</span><span
								class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="7"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Bluetooth
									&amp; Wireless </span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="8"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Gaming
									Console</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="9"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Computers
									&amp; Tablets</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
						<li data-original-index="10"><a tabindex="0" class=""
							data-tokens="null" role="option" aria-disabled="false"
							aria-selected="false"><span class="text"> Monitors </span><span
								class="glyphicon glyphicon-ok check-mark"></span></a></li>
					</ul>
				</div>
				<select class="selectpicker" tabindex="-98">
				</select>
			</div>
			<input id="search" placeholder="搜索购物车商品">
			<button class="submit" onclick="search()">
				<i class="icon-magnifier"></i>
			</button>
		</div>

		<ul class="nav navbar-right cart-pop">
			<li class="dropdown"><a href="myShoppingCart.jsp" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><span class="itm-cont">${fn:length(mycart)}</span>
					<i class="flaticon-shopping-bag"></i> <strong>我的购物车</strong> <br>
					<span>共${fn:length(mycart)}件</span></a></li>
		</ul>
	</div>

	<form action="" method="post">
	<section class="shopping-cart padding-bottom-60">
	<div class="container" style="margin-top: 30px">
		<table class="table">
			<thead>
				<tr>
					<th class="text-center">选择</th>
					<th class="text-center">商品</th>
					<th class="text-center">价格</th>
					<th class="text-center">数量</th>
					<th class="text-center">总价</th>
					<th>&nbsp; 操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>

		<div class="promo">
			<!-- Grand total -->
			<div class="g-totel">
				<h5>
					共计: <span id="allPrice">0</span>
				</h5>
			</div>
		</div>

		<!-- Button -->
		<div class="pro-btn">
			<a href="goods.jsp" class="btn-round btn-light">继续购物</a> <input type="button" onclick="buyGoods()"
				class="btn-round" value="提交订单"></input>
		</div>
	</div>
	</section>
		</form>
</body>
</html>