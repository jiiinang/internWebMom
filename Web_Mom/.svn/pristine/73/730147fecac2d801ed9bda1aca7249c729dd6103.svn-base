<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table</title>
<link rel="stylesheet" href="${ctx}/resource/css/main.css">
</head>
<body>
	<c:import url="/resource/layout/left.jsp" />
	<div id="wrapper">
		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner" id = "body">
				<h1 class="major">프로젝트</h1>
				<form method="post">
					<div align="right">
						<button id="excelDownBtn" type="submit" onclick="fnExcelReport('myTable');">엑셀다운로드</button>
						<button id="udpBtn" type="submit">수정하기</button>
						<select id = "selectYear">
							<option>2020</option>
							<option>2021</option>
							<option>2022</option>
						</select>
					</div>
					<div style="width: 1400px; overflow: auto;">
						<table id = "myTable">
							<thead>
								<tr>
									<th rowspan="2" width="30px">파트</th>
									<th rowspan="2" width="30px">이름</th>
									<th rowspan="2" width="100px">프로젝트ID</th>
									<th rowspan="2" width="100px">프로젝트</th>
									<th rowspan="2" width="100px">특이사항</th>
									<th rowspan="2" width="10px">계획<br>실적</th>
									<th colspan="12" width="240px" id = "year">2020</th>
								</tr>
								<tr>
									<th width="20px">1</th>
									<th width="20px">2</th>
									<th width="20px">3</th>
									<th width="20px">4</th>
									<th width="20px">5</th>
									<th width="20px">6</th>
									<th width="20px">7</th>
									<th width="20px">8</th>
									<th width="20px">9</th>
									<th width="20px">10</th>
									<th width="20px">11</th>
									<th width="20px">12</th>
								</tr>
							</thead>
							<tbody id="tableBody" align="center">

							</tbody>
						</table>
					</div>
				</form>
			</div>
		</section>
	</div>
<script>
console.log("${ctx}");
var tableBody = document.getElementById("tableBody");
var selectYear = document.getElementById("selectYear");
var body = document.getElementById("body");
var myTable = document.getElementById("myTable");
var data = [];

window.onload = function(){
	var udpBtn = document.getElementById("udpBtn");
	document.addEventListener('keydown', function(event) {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		    sendData();
		    location.reload();
		  };
		  
	});
	
	myTable.addEventListener("click",function(){
		event.stopPropagation(); 
	});
	
	body.addEventListener("click",bodyClick);
	
	selectYear.addEventListener("change",yearChange);
	
	udpBtn.addEventListener("click", function() { // "수정하기" Click
		event.stopPropagation();
		sendData();
	});
	
	getData();
}
var bodyClick = function(){
	var inp = tableBody.querySelectorAll("input");
	for(let i = 0; i < inp.length; i++){
		if(inp[i].style.display != "none"){
			inp[i].setAttribute("style", "display:none");
			var divTag = inp[i].parentNode;
			var aTag = document.createElement("a");
			aTag.setAttribute("style", "color:yellow");
			var text = document.createTextNode(Number(inp[i].value).toFixed(1));
			if(isNaN(text)) text = document.createTextNode(inp[i].value);
			if(text.nodeValue==0) text = document.createTextNode("X");
			divTag.appendChild(aTag);
			aTag.appendChild(text);
		}
	}
}

var yearChange = function(){ // 날짜 선택 이벤트
	while(tableBody.firstChild){
		tableBody.removeChild(tableBody.firstChild);
	}
	createTable(data);
	
	var year = document.getElementById("year");
	while(year.firstChild) year.removeChild(year.firstChild);
	var newYear = document.createTextNode(selectYear.value);
	year.appendChild(newYear);
}

var getTimeStamp = function () {      //나중에 최종수정 시간 기능넣을때 사용하면 좋을 듯 - 엑셀다운로드할때 파일명으로 사용
    var leadingZeros = function (n, digits) {      //나중에 최종수정 시간 기능넣을때 사용하면 좋을 듯 - 엑셀다운로드할때 파일명으로 사용
        var zero = '';
        n = n.toString();

        if (n.length < digits) {
          for (i = 0; i < digits - n.length; i++)
            zero += '0';
        }
        return zero + n;
    }
    
    var date = new Date();

    var dateResult = leadingZeros(date.getFullYear(), 4) + '-' +
      		leadingZeros(date.getMonth() + 1, 2) + '-' +
      		leadingZeros(date.getDate(), 2) + ' ' +
      		leadingZeros(date.getHours(), 2) + ':' +
      		leadingZeros(date.getMinutes(), 2) + ':' +
      		leadingZeros(date.getSeconds(), 2);
    return dateResult;
}

var fnExcelReport = function(id) { //엑셀 다운로드 함수

	var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
	tab_text = tab_text + '<head><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
	tab_text = tab_text + '<xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
	tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';
	tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
	tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';
	tab_text = tab_text + "<table border='1px'>";
	
	var tableVal = document.getElementById(id);
	
	tab_text = tab_text + tableVal.innerHTML;
	tab_text = tab_text + '</table></body></html>';
	var data_type = 'data:application/vnd.ms-excel';
	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");
	var fileName = "TI플랫폼_MOM" + getTimeStamp() + '.xls';
	//Explorer 환경에서 다운로드
	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
		if (window.navigator.msSaveBlob) {
			var blob = new Blob([ tab_text ], {
				type : "application/csv;charset=utf-8;"
			});
			navigator.msSaveBlob(blob, fileName);
		}
	} else {
		var blob2 = new Blob([ tab_text ], {
			type : "application/csv;charset=utf-8;"
		});
		var filename = fileName;
		var elem = window.document.createElement('a');
		elem.href = window.URL.createObjectURL(blob2);
		elem.download = filename;
		document.body.appendChild(elem);
		elem.click();
		document.body.removeChild(elem);
	}
}

var chkDuplication = function(data, curIdx){ // 프로젝트 중복체크
	var cnt = 0;
	for(let i = 0; i < curIdx; i++) {
		if (data[curIdx].memberCd == data[i].memberCd
				&& data[curIdx].projId == data[i].projId) {
			cnt = 1;
			break;
		}
	}
	return cnt;
}

var createTableInfo = function(data, selected, firstTr, secondTr){ // 테이블 기본정보 (파트, 이름, 프로젝트, 특이사항)
	var tdPartName = document.createElement("td");
	var tdUserName = document.createElement("td");
	var tdProjId = document.createElement("td");
	var partName = document.createTextNode(data.partName);
	var UserName = document.createTextNode(data.userName);
	var projId = document.createTextNode(data.projId);
	
	var specPlanId = data.memberCd + "_" + data.projId + "_SPECPLAN";
	var specPerfId = data.memberCd + "_" + data.projId + "_SPECPERF";
	
	tdPartName.setAttribute("rowspan", "2");
	firstTr.appendChild(tdPartName);
	tdPartName.appendChild(partName);
	
	tdUserName.setAttribute("rowspan", "2");
	firstTr.appendChild(tdUserName);
	tdUserName.appendChild(UserName);
	
	tdProjId.setAttribute("rowspan", "2");
	firstTr.appendChild(tdProjId);
	tdProjId.appendChild(projId);
	
	if (selected.memberCd == data.memberCd) {
		tdPartName.removeChild(tdPartName.firstChild);
		tdUserName.removeChild(tdUserName.firstChild);
	}

	var tdProjName = document.createElement("td");
	var projName = document.createTextNode(data.projName);
	tdProjName.setAttribute("rowspan", "2");
	firstTr.appendChild(tdProjName);
	tdProjName.appendChild(projName);

	var tdProjSpecPlan = document.createElement("td");
	var aProjSpecPlan = document.createElement("a");
	var projSpecPlan = document.createTextNode(data.projSpecPlan);
	firstTr.appendChild(tdProjSpecPlan);
	tdProjSpecPlan.appendChild(aProjSpecPlan);
	aProjSpecPlan.appendChild(projSpecPlan);
	aProjSpecPlan.setAttribute("id",specPlanId);
	aProjSpecPlan.setAttribute("style","color: #000000;");

	var tdProjSpecPerf = document.createElement("td");
	var aProjSpecPerf = document.createElement("a");
	var projSpecPerf = document.createTextNode(data.projSpecPerf);
	secondTr.appendChild(tdProjSpecPerf);
	tdProjSpecPerf.appendChild(aProjSpecPerf);
	aProjSpecPerf.appendChild(projSpecPerf);
	aProjSpecPerf.setAttribute("id",specPerfId);
	aProjSpecPerf.setAttribute("style","color: #000000;");

	var tdPlan = document.createElement("td");
	var plan = document.createTextNode("계획");
	firstTr.appendChild(tdPlan);
	tdPlan.appendChild(plan);

	var tdPerf = document.createElement("td");
	var perf = document.createTextNode("실적");
	secondTr.appendChild(tdPerf);
	tdPerf.appendChild(perf);
	
	clickEventSpecUpd(specPlanId);
	clickEventSpecUpd(specPerfId);
}

var drawTr = function(data, selected) { // 테이블 행렬 추가
	var firstTr = document.createElement("tr");
	var secondTr = document.createElement("tr");
	tableBody.appendChild(firstTr);
	tableBody.appendChild(secondTr);
	
	createTableInfo(data, selected, firstTr, secondTr);
	drawEmpty(data, firstTr, secondTr);
}

var drawEmpty = function(data, firstTr, secondTr){
	for (let j = 1; j <= 12; j++) {
		var tdPlanF = document.createElement("td");
		var tdPerfF = document.createElement("td");
		
		var divPlan = document.createElement("div");
		var divPerf = document.createElement("div");
		
		var planFTag = document.createElement("a");
		var perfFTag = document.createElement("a");
		
		var insPlan = "";
		var insPerf = "";
		
		if(j < 10){
			insPlan = data.memberCd + "_"
					+ data.projId + "_"
					+ (selectYear.value +"-0" + j + "-01") + "_PLnone";
			insPerf = data.memberCd + "_"
					+ data.projId + "_"
					+ (selectYear.value +"-0" + j + "-01") + "_PEnone";
		} else if(j >= 10){
			insPlan = data.memberCd + "_"
					+ data.projId + "_"
					+ (selectYear.value +"-" + j + "-01") + "_PLnone";
			insPerf = data.memberCd + "_"
					+ data.projId + "_"
					+ (selectYear.value +"-" + j + "-01") + "_PEnone";
		}

		divPlan.setAttribute("id", insPlan+"div");
		planFTag.setAttribute("id", insPlan);
		planF = document.createTextNode("　 ");

		divPerf.setAttribute("id", insPerf+"div");
		perfFTag.setAttribute("id", insPerf);
		perfF = document.createTextNode("　 ");

		firstTr.appendChild(tdPlanF);
		tdPlanF.appendChild(divPlan);
		divPlan.appendChild(planFTag);
		planFTag.appendChild(planF);

		secondTr.appendChild(tdPerfF);
		tdPerfF.appendChild(divPerf);
		divPerf.appendChild(perfFTag);
		perfFTag.appendChild(perfF);
	}
}

var drawPlanPerf = function(data, selected, cnt){ // DB에 Project가 존재하고 Plan/Perf가 있을 때
	var dateArr = data.projDate.split("-");
	
	var planId = data.memberCd + "_" + data.projId + "_" + data.projDate + "_PL";
	var perfId = data.memberCd + "_" + data.projId + "_" + data.projDate + "_PE";
	
	if(dateArr[0] == selectYear.value){
		for(let j = 1; j <= 12; j++){
			if(dateArr[1] == j){
				var setDate = dateArr[0] + "-" + dateArr[1] + "-01";
				
				var findPlanId = data.memberCd + "_" + data.projId + "_" + setDate + "_PLnone";
				var aTagPlan = document.getElementById(findPlanId);
				
				if(aTagPlan && data.projPlan !== 0) {
					aTagPlan.parentNode.setAttribute("id",planId+"div");
					aTagPlan.removeChild(aTagPlan.firstChild);
					aTagPlan.setAttribute("style", "color:red");
					aTagPlan.setAttribute("id", planId);
					aTagPlan.parentNode.setAttribute("value", data.projPlan);
					var planF = document.createTextNode(data.projPlan);
					aTagPlan.append(planF);
				}
				
				var findPerfId = data.memberCd + "_" + data.projId + "_" + setDate + "_PEnone";
				var aTagPerf = document.getElementById(findPerfId);
				
				if(aTagPerf && data.projPerf !== 0) {
					aTagPerf.parentNode.setAttribute("id",perfId+"div");
					aTagPerf.removeChild(aTagPerf.firstChild);
					aTagPerf.setAttribute("style", "color:blue");
					aTagPerf.setAttribute("id", perfId);
					aTagPerf.setAttribute("value", data.projPerf);
					var perfF = document.createTextNode(data.projPerf);
					aTagPerf.append(perfF);
				}
			}
		}
	}
}

var createSumTr = function(data){ // 합계 행 추가
	var sumFristTrId = data.memberCd + "_FirstSum";
	var sumSecondTrId = data.memberCd + "_SecondSum";
	
	var sumFristTr = document.createElement("tr");
	var sumFristTd = document.createElement("th");
	var sumFirstStr = document.createTextNode("계획 총계");
	
	sumFristTd.setAttribute("colspan", "6");
	sumFristTd.setAttribute("style", "background-color:#FFCCE5");
	sumFristTr.setAttribute("id", sumFristTrId);
	
	tableBody.appendChild(sumFristTr);
	sumFristTr.appendChild(sumFristTd);
	sumFristTd.appendChild(sumFirstStr);
	
	var sumSecTr = document.createElement("tr");
	var sumSecTd = document.createElement("th");
	var sumSecStr = document.createTextNode("실적 총계");
	
	sumSecTd.setAttribute("colspan", "6");
	sumSecTd.setAttribute("style", "background-color:#CCE5FF");
	sumSecTr.setAttribute("id", sumSecondTrId);
	
	tableBody.appendChild(sumSecTr);
	sumSecTr.appendChild(sumSecTd);
	sumSecTd.appendChild(sumSecStr);
}

var createSumTd = function(data, sumFristTrId, sumSecTrId){ // 합계 열 추가
	var sumFirstTr = document.getElementById(sumFristTrId);
	var sumSecondTr = document.getElementById(sumSecTrId);
	
	var arrATag = tableBody.querySelectorAll("a");
	var list = [];
	
	for(let i = 0; i < arrATag.length; i++){
		var chk = arrATag[i].getAttribute("id").indexOf("none");
		if(chk === -1){
			var searchId = arrATag[i].getAttribute("id");
			var split = searchId.split("_");
			if(split[0] === data.memberCd){
				list.push(searchId);
			}
		}
	}
	
	drawSum(list, sumFirstTr, sumSecondTr);
}

var drawSum = function(list, sumFirstTr, sumSecondTr){ // 총합 구하기
	for(let i = 1; i <= 12; i++){
		var sumPlan = 0;
		var sumPerf = 0;
		
		for(let j = 0; j < list.length; j++){
			var split = list[j].split("_");
			var splitDate = split[2].split("-");
			if(splitDate[1] == i){
				var getP = document.getElementById(list[j]);
				if(list[j].indexOf("PL") !== -1){
					sumPlan += Number(getP.firstChild.nodeValue);
				}
				if(list[j].indexOf("PE") !== -1){
					sumPerf += Number(getP.firstChild.nodeValue);
				}
			}
		}
		
		sumPlan = sumPlan.toFixed(1);
		sumPerf = sumPerf.toFixed(1);
		
		if(sumPlan==0) sumPlan = 0;
		if(sumPerf==0) sumPerf = 0;
		
		var planTd = document.createElement("th");
		var planNode = document.createTextNode(sumPlan);
		
		sumFirstTr.appendChild(planTd);
		planTd.appendChild(planNode);
		
		var perfTd = document.createElement("th");
		var perfNode = document.createTextNode(sumPerf);
		
		sumSecondTr.appendChild(perfTd);
		perfTd.appendChild(perfNode);
		
		planTd.setAttribute("style","color:white; background-color : rgba(0, 0, 0, 0.15);");
		perfTd.setAttribute("style","color:white; background-color : rgba(0, 0, 0, 0.15);");
		
		if(sumPlan > 0){
			planTd.setAttribute("style","color:red; background-color : rgba(0, 0, 0, 0.15);");
		}
		if (sumPerf > 0){
			var split = list[0].split("_");
			var sumPlanId = split[0] + "_" + selectYear.value + "_" + i;
			if(i < 10) sumPlanId = split[0] + "_" + selectYear.value + "_0" + i;
			
			perfTd.setAttribute("style","color:blue; background-color : rgba(0, 0, 0, 0.15);");
			perfTd.setAttribute("value", sumPerf);
			perfTd.setAttribute("id", sumPlanId);
		}
	}
}

var clickEventUpd = function(sendId) { // Plan/Perf Click 이벤트
	var aTag = document.getElementById(sendId);
	aTag.addEventListener("click", function() {
		event.stopPropagation();
		var divTag = document.getElementById(sendId + "div");
		if(divTag){
			var str = aTag.innerText;
			if(divTag.firstChild){
				divTag.removeChild(divTag.firstChild);
			}
			
			var inp = document.createElement("input");
			inp.setAttribute("style", "width:20px; height:20px;");
			inp.setAttribute("id", sendId);
			inp.setAttribute("value",str);
			
			divTag.appendChild(inp);
			divTag.setAttribute("style", "background-color : rgba(0, 0, 0, 0.15);");
			event.stopPropagation();
		}
	})
}

var clickEventSpecUpd = function(sendId) { // Plan/Perf Click 이벤트
	var aTag = document.getElementById(sendId);
	var divTag = aTag.parentNode;
	divTag.addEventListener("click",function(){
		var str = aTag.innerText;
		if(aTag){
			aTag.remove();
		}
		var inp = document.createElement("input");
		inp.setAttribute("style", "width:100%; height:20px;");
		inp.setAttribute("id", sendId);
		inp.setAttribute("value",str);
		
		divTag.appendChild(inp);
		divTag.setAttribute("style", "background-color : rgba(0, 0, 0, 0.15);");
	},{once: true})
}

var addClickEvent = function(){ // clickEvent 연결
	var list = tableBody.querySelectorAll("div");
	var noneList = [];
	var size = list.length;
	var sendId
	for(let i = 0; i < size; i++){
		sendId = list[i].firstChild.getAttribute("id"); // aTag의 Id
		clickEventUpd(sendId);
	}
}

var sendData = function(){ // 데이터 송신
	var inpDataList = tableBody.querySelectorAll("input");
	var size = inpDataList.length;
	
	var sendUpdate = [];
	var sendInsert = [];
	var sendSpecUpd = [];
	
	for (let i = 0; i < size; i++) {
		var spl = inpDataList[i].getAttribute("id").split("_");
		var updData = Number(inpDataList[i].value).toFixed(1);
		if(isNaN(updData)) updData = inpDataList[i].value;
		
		var date = spl[2].split("-");
		var getPerfId = spl[0] + "_" + date[0] + "_" + date[1]; // 총 합 Id
		var getPerfValue = 0;
		var getPerfSum = document.getElementById(getPerfId);
		if(getPerfSum){
			getPerfValue = getPerfSum.getAttribute("value");
		}
		
		if (spl[2] === "SPECPLAN") {
			var tmp = {
				"memberCd" : spl[0],
				"projId" : spl[1],
				"projSpecPlan" : updData
			};
			sendSpecUpd.push(tmp);
		}
		
		if (spl[2] === "SPECPERF") {
			var tmp = {
				"memberCd" : spl[0],
				"projId" : spl[1],
				"projSpecPerf" : updData
			};
			sendSpecUpd.push(tmp);
		}
		
		if(spl[3]){
			if (spl[3] === "PE") {
				var existPlanValue = inpDataList[i].parentNode.getAttribute("value");
				var tmp = {
					"memberCd" : spl[0],
					"projId" : spl[1],
					"projDate" : spl[2],
					"projPerf" : updData
				};
				if(Number(getPerfValue) + Number(updData) - Number(existPlanValue) > 1){
					alert("실적 총계는 1을 넘길 수 없습니다.");
				} else{
					sendUpdate.push(tmp);
				}
			}
			
			if (spl[3] === "PL") {
				var tmp = {
					"memberCd" : spl[0],
					"projId" : spl[1],
					"projDate" : spl[2],
					"projPlan" : updData
				};
				sendUpdate.push(tmp);
			}
			
			if (spl[3].indexOf("PEnone") !== -1) {
				var tmp = {
					"memberCd" : spl[0],
					"projId" : spl[1],
					"projDate" : spl[2],
					"projPerf" : updData
				};
				if(Number(getPerfValue) + Number(updData) > 1){
					alert("실적 총계는 1을 넘길 수 없습니다.");
				} else{
					sendInsert.push(tmp);
				}
			}
			
			if (spl[3].indexOf("PLnone") !== -1) {
				var tmp = {
					"memberCd" : spl[0],
					"projId" : spl[1],
					"projDate" : spl[2],
					"projPlan" : updData
				};
				sendInsert.push(tmp);
			}
		}
		
	}
	if(sendUpdate.length > 0) ajax("${ctx}/updateProject.do", sendUpdate,function(){}, "json");
	if(sendInsert.length > 0) ajax("${ctx}/insertData.do", sendInsert,function(){}, "json");
	if(sendSpecUpd.length > 0) ajax("${ctx}/updSpecPlanPerf.do", sendSpecUpd,function(){}, "json");
}

var createTable = function(data) { // 테이블 생성
	var size = data.length;
	var selected = "";
	
	for(var i = 0; i < size; i++){
		var cnt = chkDuplication(data, i);
		
		if(cnt === 0){
			drawTr(data[i], selected);
		}
		
		if(i < size-1 && (data[i].memberCd != data[i+1].memberCd) || i == size-1){
			createSumTr(data[i]);
		}
		//insertBefore
		selected = data[i];
	}
	
	for(var i = 0; i < size; i++) {
		var cnt = chkDuplication(data, i);
		
		if(data[i].projDate != null) { // 계획 실적이 DB에 존재하는 경우
			drawPlanPerf(data[i], selected, cnt);
		}
		
		if(i < size-1 && (data[i].memberCd != data[i+1].memberCd) || i == size-1){
			var sumFristTrId = data[i].memberCd + "_FirstSum";
			var sumSecondTrId = data[i].memberCd + "_SecondSum";
			createSumTd(data[i], sumFristTrId, sumSecondTrId);
		}
	}
	addClickEvent();
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
	// xhr.processData = false;
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
	//console.log(data);
	xhr.send(data);
}

var getData = function(){
	ajax("${ctx}/project.do", "", function(returnData) {
		data = returnData;
		createTable(data);
	}, "json");
}
</script>
</body>
</html>