package com.yedam.app;

import static org.junit.jupiter.api.Assertions.*;

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
	@Test
	void empList() {
//		assertEquals(empMapper, null);
//		assertNotNull(empMapper);
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}

}
