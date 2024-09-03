package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.serviece.EmpVO;

public interface EmpMapper {
	// 전체조회
	public List<EmpVO> selectEmpAllList();
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	// 수정
	public int updateEmpInfo(@Param("empId") int empId, @Param("empVO") EmpVO empVO);
	// 삭제
	public int deleteEmpInfo(int empId);
}
