package kr.gainsys.mc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.gainsys.mc.vo.TestMybatisVo;

@Repository
@Mapper
public interface TestMybatisMapper {

	// for Mybatis

	List<TestMybatisVo> selectListAll();

}
