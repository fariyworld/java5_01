<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>数据抓取统计分析</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<select name="GrabObj">
		<option value="1">蔬菜</option>
		<option value="2">水果</option>
		<option value="3">肉禽蛋</option>
		<option value="4">水产</option>
		<option value="5">粮油</option>
	</select>
	<a href="javascript: grab()">抓取价格数据</a>
	<br />
	<br />
	<input type="text" id="phoneNumber" />
	<button>查询归属地</button>
	<br />
	<br />
	<a href="javascript: testIp()">测试ip</a>
	<br />
	<br />

	<input type="text" value="" id="foodName" />
	<input type="button" value="生成年度报表" flag="year" />
	<input type="button" value="生成月度报表" flag="month" />
	<br />
	<br />
	<div id="main" style="width: 900px; height: 400px;"></div>

	<a href="javascript: exportImg();" style="display: none" id="exportImg">导入图表并生成PDF</a>


	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	
		var chart = echarts.init(document.getElementById('main'));
		var data1 ;
		var data2 ;
		//抓取数据函数
		function grab() {
			var grabObjVal = $("select[name=GrabObj]").val();
			var type = $("option[value=" + grabObjVal + "]").html();
			var grabUrl = "http://www.xinfadi.com.cn/marketanalysis/" + grabObjVal + "/list/1.shtml";
			console.log(grabUrl); //拿到抓取地址,发送ajax请求
	
			var url = "Food/grabData.action";
			var data = {
				"grabUrl" : grabUrl,
				"type" : type
			};
	
			$.post(url, data, function(data) {
				console.log(data.result);
			}, "json");
		}
	
		//生成报表
		$(":button").click(function() {
	
			var foodName = $("#foodName").val();
			if (foodName) {
	
				console.log(foodName);
	
				/*
				年度还是月度  ajax返回平均值集合
				*/
				var type = $(this).attr("flag");
				var url = "Food/getFoodListByType.action";
	
				if (type === 'year') {
	
					var data = {
						"foodName" : foodName,
						"type" : type
					};
					$.post(url, data, function(data) {
						alert(data.result);
	
						var yearChartsMap = data.yearChartMap;
						var chartDatas = new Array();
						var chartData = new Array();
						for (var i = 0; i <= 1; i++) {
	
							var chartMap = yearChartsMap[i + 2016];
	
							for (var j = 1; j <= 12; j++) {
	
								chartData[j - 1] = chartMap[j];
							}
							chartDatas[i] = chartData;
						}
						chartDatas[2] = foodName;
						console.log(chartDatas);
						createLineChart(chartDatas)
	
					}, "json");
	
				} else {
					var date = prompt("请输入你要生成报表的年月(xxxx-xx)", "");
					if (date) {
						var regEx = /((?!0000)[0-9]{4})-(0[1-9]|1[0-2])$/;
						if (regEx.test(date)) {
							console.log(date);
							var data = {
								"foodName" : foodName,
								"type" : type,
								"date" : date
							};
	
							$.post(url, data, function(data) {
								alert(data.result);
							}, "json");
	
						} else {
							alert("请按照格式输入正确的查询日期");
						}
					} else {
						alert("请按照格式输入正确的查询日期");
					}
				}
	
			}
		});
	
	
		//生成报表函数
		function createLineChart(data) {
			
			data1 = data[0];
			data2 = data[1];
	
			var title = data[2] + " 2016-2017 年度价格折线图";
	
			//指定图标的配置和数据
			var option = {
				title : {
					text : title,
					x: 'center'
				},
				legend : {
					data : [ '2016', '2017' ],
					y: 30
				},
				xAxis : {
					data : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ]
				},
				yAxis : {},
				series : [ {
					name : '2016',
					type : 'line',
					data : data1
				}, {
					name : '2017',
					type : 'line',
					data : data2
				} ]
			};
			//使用制定的配置项和数据显示图表
			chart.setOption(option);
			$("#exportImg").show();
		}
	
		//生成PDF
		function exportImg() {
			
			var imgdata = chart.getDataURL();
			
			var url = "Food/exportChartToPDF.action";
			
			$.post(url,{"imgdata":imgdata},function(data){
				alert(data.result);
			},"json");
			
		}
		
		//给老板发送邮件
		
		
		//测试拦截ip
		function testIp(){
			
			var url = "Food/testIp.action";
			
			$.post(url,function(data){
				console.log(data.result);
			},"json");
		}
		
		//查询归属地
		$("button").click(function(){
		
			var phoneNumber = $("#phoneNumber").val();
			
			if(phoneNumber){
			
				var url = "Food/queryNumber.action";
				
				var data = {"phoneNumber":phoneNumber};
				
				$.post(url,data,function(data){
				
					alert(data.result);
					
				},"json");
			
			}else{
			
				alert("手机号不能为空");
			}
		});

	</script>
</body>
</html>
