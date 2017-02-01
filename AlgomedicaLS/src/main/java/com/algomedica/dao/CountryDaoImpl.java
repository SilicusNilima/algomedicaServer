/*************************************************************************
 * 
 * Algomedica CONFIDENTIAL
 * __________________
 * 
 *  [2017] Algomedica Pvt. Ltd. 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Algomedica Pvt. Ltd. The intellectual and technical 
 * concepts contained herein are proprietary to Algomedica Pvt. Ltd.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Algomedica Pvt. Ltd.
 * 
 */
package com.algomedica.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.algomedica.entity.Country;
import com.algomedica.exception.ORMException;

/**
 * @author SQureshi
 *
 */
@Repository
public class CountryDaoImpl extends AbstractDao<Long, Country> implements CountryDao {
	private static final Logger LOGGER = Logger.getLogger(CountryDaoImpl.class);	
	/* (non-Javadoc)
	 * @see com.algomedica.dao.CountryDao#getAllCountries()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getAllCountries() {
		List<Country> counties = null;
		try {
			Criteria criteria = createEntityCriteria();
			counties = criteria.list();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return counties;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CountryDao#getCountryById(java.lang.Long)
	 */
	@Override
	public Country getCountryById(Long id) {
		Country county = null;
		LOGGER.info("Get country by id = "+id);
		try {
			county = getByKey(id);
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return county;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CountryDao#getCountryByName(java.lang.String)
	 */
	@Override
	public Country getCountryByName(String name) {
		Country county = null;
		LOGGER.info("Get country by name = "+name);
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("countryName", name).ignoreCase());
			county = (Country) criteria.uniqueResult();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return county;
	}

}
