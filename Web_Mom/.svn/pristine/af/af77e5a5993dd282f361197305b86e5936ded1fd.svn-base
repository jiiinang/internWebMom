<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Project</title>
<link rel="stylesheet" href="${ctx}/resource/css/main.css">
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>
<body>
	<c:import url="/resource/layout/left.jsp" />
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">내 프로젝트 현황</h1>
				<div align="right">
					<button id = "summary" style="display: none">전체 프로젝트</button>
					<button id = "all">세부 프로젝트</button>
				</div>
				<br>
				
				<div>
					<table id="sum">
						<tbody id = "sm">
	
						</tbody>
					</table>
					
				</div>
				<table id="part" style="display: none">
					<tbody align="center" id = "rm">
						
					</tbody>
				</table>
				
			</div>
		</section>
	</div>

<script type="text/javascript">
var rm = document.getElementById("rm");
var sm = document.getElementById("sm");
google.load("visualization", "1", {
	packages : [ "corechart" ]
});

var summaryBtn = document.getElementById("summary");
var allBtn = document.getElementById("all");
var sum = document.getElementById("sum");
var part = document.getElementById("part");

summaryBtn.addEventListener("click",function(){
	sum.setAttribute("style","display:block");
	part.setAttribute("style","display:none");
	allBtn.setAttribute("style","display:block");
	summaryBtn.setAttribute("style","display:none");
})
allBtn.addEventListener("click",function(){
	sum.setAttribute("style","display:none");
	part.setAttribute("style","display:block");
	allBtn.setAttribute("style","display:none");
	summaryBtn.setAttribute("style","display:block");
})

function drawChartSum(list) {
	function drawChart() {
		var listSum = [ [ "계획", "총합" ] ];

		var size = list.length;
		var perf = 0;
		console.log(size);
		var selectProj = "";
		selectProj = list[0].projId;
		for (var i = 0; i < size; i++) {
			if(selectProj != list[i].projId){
				var tmp = [ list[i-1].projName, perf ];
				listSum.push(tmp);
				selectProj = list[i].projId;
				perf = 0;
			}
			if(selectProj == list[i].projId){
				perf += list[i].projPerf;
			}
			if(i == size-1){
				var tmp = [ list[i].projName, perf ];
				listSum.push(tmp);
			}
		}
		var data = google.visualization.arrayToDataTable(listSum);
		var options = {
			title : "전체 실적"
		};
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		var div = document.createElement("div");

		div.setAttribute("id","sumOfAll");
		div.setAttribute("style","height:400px");
		sm.appendChild(tr);
		tr.appendChild(td);
		td.appendChild(div);
		
		var chart = new google.visualization.PieChart(document
				.getElementById("sumOfAll"));
		chart.draw(data, options);
	}
	google.setOnLoadCallback(drawChart);

}

function drawChartSumBar(list) {
	
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	var div = document.createElement("div");

	div.setAttribute("id","sumBar");
	sm.appendChild(tr);
	tr.appendChild(td);
	td.appendChild(div);
	
	var plan = 0;
	var perf = 0;
	
	for(let i = 0; i < list.length; i++){
		plan += list[i].projPlan;
		perf += list[i].projPerf;
	}
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ "Title", "계획", {
					role : 'annotation'
				}, "실적", {
					role : 'annotation'
				} ], [ "", plan, plan //성공데이터
				, perf, perf ] //실패데이터
		]);

		var barChartOption = {
			bars : 'vertical',
			height : 260,
			width : '100%',
			legend : {
				position : "top"
			},
			isStacked : false,
			tooltip : {
				textStyle : {
					fontSize : 12
				},
				showColorCode : true
			}

		};

		var chart = new google.visualization.BarChart(document
				.getElementById("sumBar"));
		chart.draw(data, barChartOption);
		window.addEventListener('resize', function() {
			chart.draw(data, barChartOption);
		}, false);
	}
	google.charts.setOnLoadCallback(drawChart);
}

var drawChartPart = function(list) {
	var size = list.length;
	for(let i = 0; i < size; i++){
		function drawChart() {
			var eleId = list[i].projId + "_" + list[i].projDate;
			var data = new google.visualization.arrayToDataTable(
					[
							[ '', '', {
								role : 'annotation'
							}, {
								role : 'style'
							} ], // 컬러 설정 추가 {role : 'style'}
							[ '계획', list[i].projPlan, list[i].projPlan,
									'#E93E3E' ],
							[ '실적', list[i].projPerf, list[i].projPerf,
									'#3051D7' ]
					]);
			var options = {
				title : "[ " +list[i].projDate+ " ] \n", // 제목
				height : 260,
				width : '100%',
				bar : {
					groupWidth : '30%' // 그래프 너비 설정 %
				},
				legend : {
					position : 'none' // 항목 표시 여부 (현재 설정은 안함)
				}
			};
		
			
			if(i == 0 || (i > 0 && list[i].projId != list[i-1].projId)){
				var tdpj = document.createElement("tr");
				var thpj = document.createElement("th");
				thpj.setAttribute("style","width:2080px; background-color:#EAECFA");
				var textnode = document.createTextNode(list[i].projName);
				
				rm.appendChild(tdpj);
				tdpj.appendChild(thpj);
				thpj.appendChild(textnode);
				
				var divPlan = document.createElement("div");
				var strPlan = document.createTextNode("계획 특이사항 : "+list[i].projSpecPlan);
				var divPerf = document.createElement("div");
				var strPerf = document.createTextNode("실적 특이사항 : "+list[i].projSpecPerf);
				var brDiv = document.createElement("div");
				var br = document.createElement("br");
				
				thpj.appendChild(br);
				thpj.appendChild(divPlan);
				divPlan.appendChild(strPlan);
				thpj.appendChild(divPerf);
				divPerf.appendChild(strPerf);
				thpj.appendChild(brDiv);
			}
			
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			var div = document.createElement("div");

			div.setAttribute("id",eleId);
			rm.appendChild(tr);
			tr.appendChild(td);
			td.appendChild(div);
			
			var chart = new google.visualization.ColumnChart(document.getElementById(eleId));
			chart.draw(data, options);
		}
		google.setOnLoadCallback(drawChart);
		
	}
	
}

var ajax = function(url, params, func, type) {
	const xhr = new XMLHttpRequest();
	var contentType = '';
	if (!!type) {
		if (type == 'json') {
			contentType = 'application/json'; //javascript object notation json
		} else if (type == 'file') {
			contentType = 'multipart/form-data';
		}
	} else {
		contentType = 'application/x-www-form-urlencoded';
	}
	var data = '';
	if (!!params && params != '') {
		if (type == 'json') {
			data = JSON.stringify(params);
		} else if (type == 'form' || type == 'file' || !!type) {
			data = this.jsonToForm(params); //form 데이터의 경우 serialize해야함
		}
	}
	xhr.open('POST', url, true);
	//		    xhr.processData = false;
	xhr.responseType = 'json';
	xhr.setRequestHeader('Content-type', contentType
			+ '; charset=UTF-8;');
	xhr.onload = function(e) {
		console.log(this);
		if (this.status == 200 && this.readyState == 4) {
			func((this.responseType == 'json') ? this.response : JSON
					.parse(this.response));
		} else if (this.status == 404 && this.readyState == 4) {
			alert(aljjaMsgObj.ajax404Err);
		} else if (this.status == 500 && this.readyState == 4) {
			alert(aljjaMsgObj.ajax500Err);
		} else {
			if (this.readyState == 4) {
				alert(aljjaMsgObj.ajaxDataErr);
			}
		}
	};
	console.log(data);
	xhr.send(data);
}

var param = {
		"inpData" : "NS20200803001"
}
ajax("${ctx}/myProjectList.do", param, function(returnData) {
	var size = returnData.length;
	drawChartPart(returnData);
	drawChartSum(returnData);
	drawChartSumBar(returnData);
}, "json")
</script>

</body>
</html>