package com.datadrizzle.connection.translators;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datadrizzle.ui.model.MutualFundCompany;
import com.datadrizzle.vo.MutualFundCompanyVO;

@Service
public class StockTranslator {

	@Autowired
	DozerBeanMapper mapper;

	public MutualFundCompany mutualFundVO2UIModel(MutualFundCompanyVO companyVo) {
		return mapper.map(companyVo, MutualFundCompany.class);
	}
	
	public List<MutualFundCompany> mutualFundVOList2UIModelList(List<MutualFundCompanyVO> companyVOList) {
		return map(companyVOList, MutualFundCompany.class);
	}

	public <T, U> List<U> map(final List<T> source, final Class<U> destType) {
		final List<U> dest = new ArrayList<>();
		
		for (T element : source) {
			dest.add(mapper.map(element, destType));
		}
		
		return dest;
	}

}
