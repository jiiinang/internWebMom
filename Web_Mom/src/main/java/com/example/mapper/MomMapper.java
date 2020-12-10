package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.cmmn.PaginationInfo;
import com.example.vo.MemberVO;
import com.example.vo.ProjectListVO;
import com.example.vo.ProjectSpecVO;
import com.example.vo.ProjectVO;

@Mapper
public interface MomMapper {
	public int chkProj(Map<String, Object> inpData) throws Exception;
	public int chkDuplication(Map<String, Object> inpData) throws Exception;
	
	public List<ProjectVO> getProject() throws Exception;
	public List<ProjectVO> myProjectList(Map<String, Object> inpData) throws Exception;
	public List<MemberVO> getMember() throws Exception;
	public List<ProjectListVO> getProjectList() throws Exception;
	public List<ProjectSpecVO> getSpec(PaginationInfo page) throws Exception;
	public int getSpecCount() throws Exception;
	public void updSpec(Map<String, Object> inpData) throws Exception;
	
	public void updSpecPerf(Map<String, Object> inpData) throws Exception;
	public void updSpecPlan(Map<String, Object> inpData) throws Exception;
	
	public void updateProjectPlan(Map<String, Object> inpData) throws Exception;
	public void updateProjectPerf(Map<String, Object> inpData) throws Exception;
	
	public void deleteProject(Map<String, Object> inpData) throws Exception;
	
	public void insertProjectPlan(Map<String, Object> inpData) throws Exception;
	public void insertProjectPerf(Map<String, Object> inpData) throws Exception;
	
	public void insertMomProject(Map<String, Object> inpData) throws Exception;
	
	public void insertPs(Map<String, Object> inpData) throws Exception;
}