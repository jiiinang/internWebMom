package com.example.momService;

import java.util.List;
import java.util.Map;

import com.example.cmmn.PaginationInfo;
import com.example.vo.MemberVO;
import com.example.vo.ProjectListVO;
import com.example.vo.ProjectSpecVO;
import com.example.vo.ProjectVO;

public interface MomService {
	public List<ProjectVO> getProject() throws Exception;
	public List<ProjectVO> myProjectList(Map<String, Object> inpData) throws Exception;
	public List<MemberVO> getMember() throws Exception;
	public List<ProjectListVO> getProjectList() throws Exception;
	public List<ProjectSpecVO> getSpec(PaginationInfo page) throws Exception;
	public int getSpecCount() throws Exception;
	public void updSpec(Map<String, Object> inpData) throws Exception;
	public void updSpecPlanPerf(Map<String, Object> inpData[]) throws Exception;
	public void updateProject(Map<String, Object> inpData[]) throws Exception;
	public void insertProject(Map<String, Object> inpData) throws Exception;
	public void deleteProject(Map<String, Object> inpData[]) throws Exception;
	public void insertData(Map<String, Object> inpData[]) throws Exception;
}