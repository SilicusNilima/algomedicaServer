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

import com.algomedica.entity.City;
import com.algomedica.exception.ORMException;

/**
 * @author SQureshi
 *
 */
@Repository
public class CityDaoImpl extends AbstractDao<Long, City> implements CityDao {
	private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.dao.CityDao#getAllCities()
	 */
	@Override
	public List<City> getAllCities() {
		List<City> cities = null;
		try {
			Criteria criteria = createEntityCriteria();
			cities = criteria.list();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return cities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.dao.CityDao#getCityById(java.lang.Long)
	 */
	@Override
	public City getCityById(Long id) {
		City city = null;
		LOGGER.info("Get city by id = " + id);
		try {
			city = getByKey(id);
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return city;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.dao.CityDao#getCityByName(java.lang.String)
	 */
	@Override
	public City getCityByName(String name) {
		City city = null;
		LOGGER.info("Get city by name = " + name);
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("cityName", name).ignoreCase());
			city = (City) criteria.uniqueResult();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return city;
	}

}
