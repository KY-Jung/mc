package kr.gainsys.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gainsys.mc.mapper.IdMapper;
import kr.gainsys.mc.vo.IdVo;

@Service
public class IdService {

	@Autowired
	public IdMapper idMapper;
	
	public List<IdVo> selectIdList() {
		return idMapper.selectIdList();		
	}
}
