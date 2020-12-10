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
			<div class="inner">
				<h1 class="major">프로젝트 작성</h1>
				<form id="sendData" action="">
					<table>
						<tr>
							<th width="150px">파트</th>
							<td><select id="findPart">
							</select></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><select id="findName">
							</select></td>
						</tr>
						<tr>
							<th>프로젝트</th>
							<td><select id="projName">
							</select></td>
						</tr>
						<tr>
							<th>계획 특이사항</th>
							<td><input id="projSpecPlan" style="width: 400px;" /></td>
						</tr>

						<tr>
							<th>실적 특이사항</th>
							<td><input id="projSpecPerf" style="width: 400px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="reset" value="다시작성" /> <input type="button"
							value="제출하기" id="submitBtn" type="submit" />
					</div>
				</form>
			</div>
		</section>
	</div>
<script>
var submitBtn = document.getElementById("submitBtn");
var projName = document.getElementById("projName");
var projSpecPlan = document.getElementById("projSpecPlan");
var projSpecPerf = document.getElementById("projSpecPerf");
var findPart = document.getElementById("findPart");
var findName = document.getElementById("findName");
var projDate = document.getElementById("projDate");

window.onload = function(){
	getMember();
	getProjectList();
}

var addPart = function(list) {
	var size = list.length;
	for (var i = 0; i < size; i++) {
		if (i == 0) {
			var str = document.createElement("option");
			str.setAttribute("value", list[i].partCode);
			var text = document.createTextNode(list[i].partName);
			str.appendChild(text);
			findPart.appendChild(str);
		} else if (list[i].partCode != list[i - 1].partCode && i > 0) {
			var str = document.createElement("option");
			str.setAttribute("value", list[i].partCode);
			var text = document.createTextNode(list[i].partName);
			str.appendChild(text);
			findPart.appendChild(str);
		}
	}
}

var addProjectList = function(list) {
	var size = list.length;
	for (let i = 0; i < size; i++) {
		var str = document.createElement("option");
		str.setAttribute("value", list[i].projectCode);
		var text = document.createTextNode("["+list[i].projectCode+"] " + list[i].projectName);
		str.appendChild(text);
		projName.appendChild(str);
	}
}

var defaultOption = function(list) {
	var size = list.length;
	for (var i = 0; i < size; i++) {
		if (list[i].partCode == findPart.value) {
			var count = 0;
			for (var j = 0; j < i; j++) {
				if (list[i].memberCd == list[j].memberCd) {
					count++;
					break;
				}
			}
			if (count == 0) {
				var str = document.createElement("option");
				str.setAttribute("value", list[i].memberCd);
				var text = document.createTextNode(list[i].userName);
				str.appendChild(text);
				findName.appendChild(str);
			}
		}
	}
}

var nameChaneEv = function() {
	findName.addEventListener("change", function() {
		while (rm.firstChild)
			rm.removeChild(rm.firstChild);
	})
}

var addChangeEv = function(list) {
	findPart.addEventListener("change", function() {
		while (findName.firstChild) {
			findName.removeChild(findName.firstChild);
		}
		for (var i = 0; i < list.length; i++) {
			if (list[i].partCode == findPart.value) {
				if (i == 0) {
					var str = document.createElement("option");
					str.setAttribute("value", list[i].memberCd);
					var text = document.createTextNode(list[i].userName);
					str.appendChild(text);
					findName.appendChild(str);
				} else if (i > 0) {
					var count = 0;
					for (var j = 0; j < i; j++) {
						if (list[i].memberCd == list[j].memberCd) {
							count++;
							break;
						}
					}
					if (count == 0) {
						var str = document.createElement("option");
						str.setAttribute("value", list[i].memberCd);
						var text = document.createTextNode(list[i].userName);
						str.appendChild(text);
						findName.appendChild(str);
					}
				}
			}
		}
	})
}

submitBtn.addEventListener("click", function() {

	var conf = confirm("제출하시겠습니까?");
	if (conf) {
		var pjNm = projName.options[projName.selectedIndex].text;
		pjNm = pjNm.substr(15);
		var sendData = {
			"memberCd" : findName.value,
			"projName" : pjNm,
			"projSpecPlan" : projSpecPlan.value,
			"projSpecPerf" : projSpecPerf.value,
			"projId" : projName.value
		}
		console.log(sendData);
		ajax("${ctx}/insertProject.do", sendData, function(returnData) {}, "json")
		location.href = "${ctx}/";
	}

})

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
var getMember = function(){
	ajax("${ctx}/getMember.do", "",
			function(returnData) {
				addPart(returnData);
				defaultOption(returnData);
				addChangeEv(returnData);
			}, "json")
}

var getProjectList = function(){
	ajax("${ctx}/getProjectList.do", "",
			function(returnData) {
				addProjectList(returnData);
			}, "json")
}
</script>
</body>
</html>