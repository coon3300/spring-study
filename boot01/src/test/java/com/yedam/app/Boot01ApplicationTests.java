package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.serviece.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	
	@Autowired // 필드 주입
	private EmpMapper empMapper;
//	@Test
	void empList() {
		
//		assertEquals(empMapper, null);
//		assertNotNull(empMapper);
		// 전체 조회 : 입력,X -> 출력, 1건 이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
//		System.out.println("list : " + list);
//		list.forEach(ele ->{
//			System.out.println("ele : " + ele);
//			
//		});
	}
	
//	@Test
	void empInfo() {
		// 단건 조회 : 입력, 사원번호(100) -> 출력, 사원이름(King)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
//		System.out.println("findVO.getLastName() : " + findVO.getLastName());
	}
	
//	@Test
	void empInsert() {
		// 사원 등록 : 입력, 사원이름, 이메일, 업무
		//		  -> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdHong123@gamil.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
		
	}
	
//	@Test
	void empInsertFull() throws ParseException {
		// 사원 등록 : 입력, 사원이름, 이메일, 입사일자, 업무, 급여
		//		  -> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Han");
		empVO.setEmail("jhHan123@gamil.com");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(new Date());
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
		
	}
	
//	@Test
	void empDelete() {
		int result = empMapper.deleteEmpInfo(207);
		assertEquals(result, 1);
		
		result = empMapper.deleteEmpInfo(208);
		assertEquals(result, 1);
	}

//	@Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmail("kjHong@naver.com");
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		
		assertEquals(result, 1);
	}
	
	@Test
	void empUpdateDynamic() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(208);
		empVO.setLastName("Kim");
		empVO.setJobId("MK_REP");
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(),empVO);
		
		assertEquals(result, 1);
	}	
	
}
