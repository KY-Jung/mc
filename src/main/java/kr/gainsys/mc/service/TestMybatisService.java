package kr.gainsys.mc.service;

import java.util.List;

import kr.gainsys.mc.mapper.TestMybatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gainsys.mc.vo.TestMybatisVo;

@Service
public class TestMybatisService {

	// for Mybatis

	@Autowired
	public TestMybatisMapper testMybatisMapper;
	
	public List<TestMybatisVo> selectListAll() {
		return testMybatisMapper.selectListAll();
	}
}
