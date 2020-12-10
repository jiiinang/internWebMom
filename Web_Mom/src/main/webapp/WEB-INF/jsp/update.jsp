<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/aljjabaegi.grid.css" />
<script type="text/javascript" src="${ctx}/resource/js/aljjabaegi.grid.min-5.0.0.js" charset="utf-8"></script>
</head>
<body>
	<div id="wrapper">
		<section id="main" class="wrapper">
		<div id="grid"></div>
		</section>
	</div>
<script>
window.onload = function(){
	setGrid();
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
	ajax("${ctx}/getSpec.do", "", function(returnData) {
		data = returnData;
		console.log(data);
	}, "json");
}
var setGrid = function(){
	var gridOption = {
			  id : 'grid',
			  url : '${ctx}/getSpec.do',
			  autoSearch : true,
			  multiSelection : true,
			  searchType : 'single',
			  colN : ['프로젝트ID', '멤버ID', '계획특이사항', '실적특이사항'],
			  colM : [
			    {id:'projId',align:'center'},
			    {id:'memberCd',align:'left'},
			    {id:'projSpecPlan',align:'right'},
			    {id:'projSpecPerf', align:'right'},
			  ],
			  rows:false,
			  cud : {
			      udt :{
			          url : "${ctx}/updSpec.do",
			      },
			      del : {
			          url : "${ctx}/delSpec.do"
			      }
		 	  }
	}
	var gird = AG(gridOption);
}
</script>
</body>
</html>