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
import org.springframework.transaction.annotation.Transactional;

import com.algomedica.entity.Customer;
import com.algomedica.entity.LicenseDetail;
import com.algomedica.exception.ORMException;

/**
 * @author KLele
 *
 */
@Transactional
@Repository
public class LicenseDaoImpl extends AbstractDao<Long, LicenseDetail> implements LicenseDao{

	
	@Override
	public LicenseDetail getLicenceByMacAddress(String macAddress) {

		LicenseDetail licenseDetail = null;
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("lsMacAddress", macAddress));
			criteria.add(Restrictions.eq("lsStatus",(byte) 1 ));
			licenseDetail = (LicenseDetail) criteria.uniqueResult();
		} catch (Exception ex) {
			System.out.println(ex);
			throw new ORMException(ex);
		}
		return licenseDetail != null ? licenseDetail : null;

	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.LicenseDao#saveLicense(com.algomedica.entity.LicenseDetail)
	 */
	@Override
	public void saveLicense(LicenseDetail licenseDetail) {
		try{
			merge(licenseDetail);
		}catch (Exception e) {
			throw new ORMException(e);
		}
		
	}
	
	
	@Override
	public void updateLicense(LicenseDetail licenseDetail) {
		try{
			update(licenseDetail);
		}catch (Exception e) {
			throw new ORMException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.LicenseDao#getLicenseById(long)
	 */
	@Override
	public LicenseDetail getLicenseById(long id) {
		LicenseDetail licenseDetail = null;
		try{
			//licenseDetail=getByKey(id);
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("id",id));
			licenseDetail = (LicenseDetail) criteria.uniqueResult();
		}catch (Exception e) {
			throw new ORMException(e);
		}
		return licenseDetail != null ? licenseDetail : null;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.LicenseDao#getLicenseDetailsByCustomerId(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LicenseDetail> getLicenseDetailsByCustomerId(Customer customer) {
		
			List<LicenseDetail> licenseDetails = null;
			try {
				System.out.println(customer);
				 Criteria criteria = createEntityCriteria();
			        criteria.add(Restrictions.eq("customer",customer));
			        licenseDetails = criteria.list();
			} catch (Exception ex) {
				System.out.println(ex);  
				throw new ORMException(ex);
			}
			return licenseDetails;
		}
	}
	


