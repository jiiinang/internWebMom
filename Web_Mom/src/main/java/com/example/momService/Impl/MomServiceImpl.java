package com.example.momService.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cmmn.PaginationInfo;
import com.example.mapper.MomMapper;
import com.example.momService.MomService;
import com.example.vo.MemberVO;
import com.example.vo.ProjectListVO;
import com.example.vo.ProjectSpecVO;
import com.example.vo.ProjectVO;

@Service("momService")
public class MomServiceImpl implements MomService {

	@Autowired
	MomMapper momMapper;

	@Override
	public List<ProjectVO> getProject() throws Exception {
		return momMapper.getProject();
	}

	@Override
	public List<ProjectVO> myProjectList(Map<String, Object> inpData) throws Exception {
		return momMapper.myProjectList(inpData);
	}
	
	@Override
	public List<ProjectListVO> getProjectList() throws Exception {
		return momMapper.getProjectList();
	}

	@Override
	public void updateProject(Map<String, Object> inpData[]) throws Exception {
		int size = inpData.length;
		for (int i = 0; i < size; i++) {
			int chk = momMapper.chkProj(inpData[i]);
			System.out.println("chk == " + chk);
			System.out.println(inpData[i]);
			if (inpData[i].containsKey("projPerf") && chk > 0) {
				momMapper.updateProjectPerf(inpData[i]);
			}
			if (inpData[i].containsKey("projPlan") && chk > 0) {
				momMapper.updateProjectPlan(inpData[i]);
			}
		}

	}

	@Override
	public List<MemberVO> getMember() throws Exception {
		return momMapper.getMember();
	}

	@Override
	public void insertProject(Map<String, Object> inpData) throws Exception {
		int chk = momMapper.chkDuplication(inpData);
		if(chk != 1) {
			Map<String, Object> inpProj = new HashMap<String, Object>();
			inpProj.put("projId", inpData.get("projId"));
			inpProj.put("projName", inpData.get("projName"));
			System.out.println("mom_project : " + inpProj);
			momMapper.insertMomProject(inpProj);
		}

		Map<String, Object> inpPs = new HashMap<String, Object>();
		inpPs.put("projId", inpData.get("projId"));
		inpPs.put("memberCd", inpData.get("memberCd"));
		inpPs.put("projSpecPlan", inpData.get("projSpecPlan"));
		inpPs.put("projSpecPerf", inpData.get("projSpecPerf"));
		System.out.println("mom_ps_mpng : " + inpPs);
		momMapper.insertPs(inpPs);

	}

	@Override
	public void insertData(Map<String, Object> inpData[]) throws Exception {
		int size = inpData.length;
		
		for(int i=0; i<size; i++) {
			String date = (String) inpData[i].get("projDate");
			inpData[i].put("projDate", date.substring(0,7)+"%");
			int chk = momMapper.chkProj(inpData[i]);
			
			if(chk == 1) {
				if(inpData[i].containsKey("projPerf")) {
					momMapper.updateProjectPerf(inpData[i]);
				}
				if(inpData[i].containsKey("projPlan")) {
					momMapper.updateProjectPlan(inpData[i]);
				}
			}
			
			if(chk == 0) {
				inpData[i].put("projDate", date);
				if(inpData[i].containsKey("projPerf")) {
					momMapper.insertProjectPerf(inpData[i]);
				}
				if(inpData[i].containsKey("projPlan")) {
					momMapper.insertProjectPlan(inpData[i]);
				}
			}
		}
	}

	@Override
	public List<ProjectSpecVO> getSpec(PaginationInfo page) throws Exception {
		return momMapper.getSpec(page);
	}

	@Override
	public void updSpec(Map<String, Object> inpData) throws Exception {
		momMapper.updSpec(inpData);
	}

	@Override
	public void deleteProject(Map<String, Object>[] inpData) throws Exception {
		int size = inpData.length;
		for(int i = 0; i < size; i++) {
			momMapper.deleteProject(inpData[i]);
		}
	}

	@Override
	public int getSpecCount() throws Exception {
		return momMapper.getSpecCount();
	}

	@Override
	public void updSpecPlanPerf(Map<String, Object> inpData[]) throws Exception {
		int size = inpData.length;
		for(int i = 0; i < size; i++) {
			if(inpData[i].containsKey("projSpecPlan")) momMapper.updSpecPlan(inpData[i]);
			if(inpData[i].containsKey("projSpecPerf")) momMapper.updSpecPerf(inpData[i]);
		}
	}
	
}
