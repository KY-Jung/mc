package kr.gainsys.mc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.gainsys.mc.vo.IdVo;

@Repository
@Mapper
public interface IdMapper {

	// for iBatis

	List<IdVo> selectIdList();

}
