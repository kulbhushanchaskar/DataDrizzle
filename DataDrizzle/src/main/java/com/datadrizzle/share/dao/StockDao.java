/*package com.datadrizzle.share.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.datadrizzle.vo.MutualFundCompanyVO;

@Repository
public class StockDao implements IStockDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<MutualFundCompanyVO> getAllMutualFundCompanies() {
		List<MutualFundCompanyVO> mCompanies = entityManager.createQuery("SELECT M FROM MutualFundCompany M").getResultList();
		return mCompanies;
	}

}
*/