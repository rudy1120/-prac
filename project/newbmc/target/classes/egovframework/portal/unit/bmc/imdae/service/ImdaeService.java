package egovframework.portal.unit.bmc.imdae.service;

import java.util.List;

import egovframework.portal.unit.bmc.imdae.vo.ImdaeJiguVO;
import egovframework.portal.unit.bmc.imdae.vo.ImdaeWatingInfoVO;

public interface ImdaeService {
	
	List<ImdaeJiguVO> getList(ImdaeJiguVO imdaeJiguVO);

	List<ImdaeWatingInfoVO> getWatingList(ImdaeWatingInfoVO imdaeWatingInfoVO);

	List<ImdaeJiguVO> getHappyList(ImdaeJiguVO imdaeJiguVO);
	
}
