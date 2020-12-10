package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cmmn.Common;
import com.example.cmmn.PaginationInfo;
import com.example.momService.MomService;
import com.example.vo.MemberVO;
import com.example.vo.ProjectListVO;
import com.example.vo.ProjectSpecVO;
import com.example.vo.ProjectVO;
import com.example.vo.SearchVO;
import com.example.vo.TempSet;

@Controller
public class MomDataController {
	
	@Resource(name="momService")
	MomService momService;
	
	@ResponseBody
	@RequestMapping(value="/getMember.do")
	public String getMember() {
		List<MemberVO> memberList = null;
		try {
			memberList = momService.getMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.gson().toJson(memberList);
	}
	
	@ResponseBody
	@RequestMapping(value="/project.do")
	public String getProject() {
		List<ProjectVO> projList = null;
		try {
			projList = momService.getProject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.gson().toJson(projList);
	}
	
	@ResponseBody
	@RequestMapping(value="/myProjectList.do")
	public String myProjectList(@RequestBody String inpData) {
		List<ProjectVO> myProjList = null;
		Map<String, Object> inp = Common.jsonParser(inpData);
		try {
			myProjList = momService.myProjectList(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.gson().toJson(myProjList);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateProject.do")
	public void updateProject(@RequestBody String inpData) {
		Map<String, Object> inp[] = Common.jsonParserArr(inpData);
		try {
			momService.updateProject(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/insertProject.do")
	public void insertProject(@RequestBody String inpData) {
		System.out.println(inpData);
		
		Map<String, Object> inp = Common.jsonParser(inpData);
		try {
			momService.insertProject(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/insertData.do")
	public void insertData(@RequestBody String inpData) {
		Map<String, Object> inp[] = Common.jsonParserArr(inpData);
		try {
			momService.insertData(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updSpec.do")
	public void updSpec(@RequestBody String inpData) {
		System.out.println(inpData);
		
		Map<String, Object> inp = Common.jsonParser(inpData);
		try {
			momService.updSpec(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updSpecPlanPerf.do")
	public void updSpecPlanPerf(@RequestBody String inpData) {
		System.out.println(inpData);
		
		Map<String, Object> inp[] = Common.jsonParserArr(inpData);
		try {
			momService.updSpecPlanPerf(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delSpec.do")
	public void delSpec(@RequestBody String inpData) {
		System.out.println(inpData);
		
		Map<String, Object> inp[] = Common.jsonParserArr(inpData);
		try {
			momService.deleteProject(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getProjectList.do")
	public String getProjectList() {
		List<ProjectListVO> Data = null;
		try {
			Data = momService.getProjectList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.gson().toJson(Data);
	}
	
	@ResponseBody
	@RequestMapping(value="/getSpec.do")
	public String getSpec(@RequestBody SearchVO param) {
		
		TempSet set = new TempSet();
	      List<ProjectSpecVO> rows = null;
	      PaginationInfo page = new PaginationInfo();
	      int size = 0;
	      /*
	       * 1.total count 조회
	       * 2.page setting
	       * 3.데이터 조회
	       * 4.return*/
	      try {
	         try {
	            size = momService.getSpecCount();
	         }catch(Exception e) {
	            set.setResultCode(301);
	            set.setResultMsg("데이터 갯수를 조회하는데 실패하였습니다.");
	            throw e;
	         }
	         try {
	            page.setTotalRecordCount(size);
	            page.setCurrentPageNo(param.getPageIndex());
	            page.setRecordCountPerPage(param.getRecordCountPerPage());
	            page.setPageSize(param.getCountPerPage());
	            set.setFirstPageOnPageList(page.getFirstPageNoOnPageList());
	            set.setLastPageOnPageList(page.getLastPageNoOnPageList());
	            set.setTotalPage(page.getTotalPageCount());
	            param.setFirstIndex(page.getFirstRecordIndex());
	            param.setLastIndex(page.getLastRecordIndex());
	         }catch(Exception e) {
	            set.setResultCode(302);
	            set.setResultMsg("페이지 정보를 설정하는데 실패하였습니다.");
	            throw e;
	         }
	         try {
	            rows = momService.getSpec(page);
	            
	            if(rows.size()>0) {
	               set.setRows(rows);
	               set.setTotalCnt(size);
	               set.setResultMsg("데이터를 조회하는데 성공하였습니다.");
	            }else {
	               set.setResultMsg("데이터가 존재하지 않습니다.");
	            }
	         }catch(Exception e) {
	            set.setResultCode(300);
	            set.setResultMsg("데이터를 조회하는데 실패하였습니다.");
	            throw e;
	         }
	      }catch(Exception e) {
	    	  
	      }
	      System.out.println("param = " + param.toString());
		  System.out.println("set = " + set.toString());
		  System.out.println("page = " + page.toString());
	      return Common.gson().toJson(set);
	}
}
