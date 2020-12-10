<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Project</title>
</head>
<link rel="stylesheet" href="${ctx}/resource/css/main.css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<body>
	<c:import url="/resource/layout/left.jsp" />
	<div id="wrapper">
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">팀 프로젝트 현황</h1>
				<table>
					<tr>
						<td width = "20px"><select id="findPart"></select></td>
						<td align="left"><select id="findName"></select></td>
						<td align="right" width = "20px"><button id="findBtn">찾기</button></td>
					</tr>
				</table>
				<div align="right" id = "projSelects" style = "display:none">
				
				</div>
				
				<div id = "graph">
					<table id="part">
							<tbody align="center" id = "rm">
								
							</tbody>
					</table>
				</div>
			</div>
		</section>
	</div>
<script>
google.load("visualization", "1", {
	packages : [ "corechart" ]
});
var findPart = document.getElementById("findPart");
var findName = document.getElementById("findName");
var part = document.getElementById("part");
var rm = document.getElementById("rm");
var findBtn = document.getElementById("findBtn");
var projSelects = document.getElementById("projSelects");

var selectMember = "";
var selectProj = "";

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
	//    xhr.processData = false;
	xhr.responseType = 'json';
	xhr.setRequestHeader('Content-type', contentType + '; charset=UTF-8;');
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

var addPart = function(list){
	var size = list.length;
	for(var i = 0; i < size; i++){
		if(i==0){
			var str = document.createElement("option");
			str.setAttribute("value",list[i].partCode);
			var text = document.createTextNode(list[i].partName);
			str.appendChild(text);
			findPart.appendChild(str);
		}
		else if(list[i].partCode != list[i-1].partCode && i > 0){
			var str = document.createElement("option");
			str.setAttribute("value",list[i].partCode);
			var text = document.createTextNode(list[i].partName);
			str.appendChild(text);
			findPart.appendChild(str);
		}
	}
}

var param = {};
ajax("${ctx}/project.do", param, function(returnData) {
	//console.log(returnData);
	addPart(returnData);
	defaultOption(returnData);
	addChangeEv(returnData);
	addSearchEv(returnData);
}, "json")

var defaultOption = function(list){
	var size = list.length;
	for(var i = 0; i < size; i++){
		if(list[i].partCode == findPart.value){
			var count = 0;
			for(var j = 0; j < i; j++){
				if(list[i].memberCd == list[j].memberCd){
					count++;
					break;
				}
			}
			if(count==0){
				var str = document.createElement("option");
				str.setAttribute("value",list[i].memberCd);
				var text = document.createTextNode(list[i].userName);
				str.appendChild(text);
				findName.appendChild(str);
			}
		}
	}
}

var addChangeEv = function(list){
	findPart.addEventListener("change",function(){
		while(projSelects.firstChild) projSelects.removeChild(projSelects.firstChild);
		while(rm.firstChild) rm.removeChild(rm.firstChild);
		projSelects.setAttribute("style","display:none");
		while(findName.firstChild) findName.removeChild(findName.firstChild);
		for(var i = 0; i < list.length; i++){
			if(list[i].partCode == findPart.value){
				if(i==0){
					var str = document.createElement("option");
					str.setAttribute("value",list[i].memberCd);
					var text = document.createTextNode(list[i].userName);
					str.appendChild(text);
					findName.appendChild(str);
				}
				else if(i>0){
					var count = 0;
					for(var j = 0; j < i; j++){
						if(list[i].memberCd == list[j].memberCd){
							count++;
							break;
						}
					}
					if(count==0){
						var str = document.createElement("option");
						str.setAttribute("value",list[i].memberCd);
						var text = document.createTextNode(list[i].userName);
						str.appendChild(text);
						findName.appendChild(str);
					}
				}
			}
		}
	})
	nameChaneEv();
}

var drawChartPart = function(list) {
	var size = list.length;
	for(let i = 0; i < size; i++){
		if(selectMember == list[i].memberCd && projSelect.value == list[i].projId){
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
					
					rm.appendChild(tdpj);
					tdpj.appendChild(thpj);
					
					var divPlan = document.createElement("div");
					var strPlan = document.createTextNode("계획 특이사항 : "+list[i].projSpecPlan);
					var divPerf = document.createElement("div");
					var strPerf = document.createTextNode("실적 특이사항 : "+list[i].projSpecPerf);
					var brDiv = document.createElement("div");
					
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
	
}

var addSearchEv = function(list){
	findBtn.addEventListener("click", function(){
		projSelects.setAttribute("style","display:block");
		while(projSelects.firstChild) projSelects.removeChild(projSelects.firstChild);
		while(rm.firstChild) rm.removeChild(rm.firstChild);			
		var select = document.createElement("select");
		projSelects.appendChild(select);
		select.setAttribute("id","projSelect");
		for(var i = 0; i < list.length; i++){
			if(list[i].memberCd == findName.value){
				if(i == 0 || (i > 0 && list[i].projId != list[i-1].projId)){
					var option = document.createElement("option");
					option.setAttribute("value",list[i].projId);
					var str = document.createTextNode(list[i].projName);
					option.appendChild(str);
					select.appendChild(option);
				}
			}
		}
		projSelectEv(list);
	})
}

var nameChaneEv = function(){
	findName.addEventListener("change",function(){
		while(projSelects.firstChild) projSelects.removeChild(projSelects.firstChild);
		while(rm.firstChild) rm.removeChild(rm.firstChild);
		projSelects.setAttribute("style","display:none");
	})
}

var projSelectEv = function(list){
	var size = list.length;
	var projSelect = document.getElementById("projSelect");
	defaultProject(list);
	projSelect.addEventListener("change",function(){
		while(rm.firstChild) rm.removeChild(rm.firstChild);
		drawChartPart(list);
	})
}

var defaultProject = function(list){
	var size = list.length;
	for(var i = 0; i < size; i++){
		if(projSelect.value == list[i].projId && findName.value == list[i].memberCd){
			selectMember = list[i].memberCd;
			drawChartPart(list);
			break;
		}
	}
}
</script>
</body>
</html>