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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.algomedica.entity.State;
import com.algomedica.exception.ORMException;

/**
 * @author SQureshi
 *
 */
@Repository
public class StateDaoImpl extends AbstractDao<Long, State> implements StateDao {

	/* (non-Javadoc)
	 * @see com.algomedica.dao.StateDao#getAllStates()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllStates() {
		List<State> states = null;
		try {
			Criteria criteria = createEntityCriteria();
			states = criteria.list();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return states;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.StateDao#getStateByName(java.lang.String)
	 */
	@Override
	public State getStateByName(String name) {
		State state = null;
		try {
			 Criteria criteria = createEntityCriteria();
		        criteria.add(Restrictions.eq("stateName",name).ignoreCase());
		        state = (State) criteria.uniqueResult();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return state;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.StateDao#getStateById(java.lang.Long)
	 */
	@Override
	public State getStateById(Long id) {
		State state = null;
		try {
			state = getByKey(id);
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return state;
	}

}
