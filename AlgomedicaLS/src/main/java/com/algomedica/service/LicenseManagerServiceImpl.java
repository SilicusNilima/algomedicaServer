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
package com.algomedica.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algomedica.dao.CustomerDao;
import com.algomedica.dao.LicenseDao;
import com.algomedica.entity.Customer;
import com.algomedica.entity.LicenseDetail;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.DuplicateFieldException;
import com.algomedica.exception.KeyGenerationException;
import com.algomedica.form.LicenseForm;
import com.algomedica.license.gen.LicenseContent;
import com.algomedica.license.gen.LicenseManager;
import com.algomedica.util.ApplicationConstant;
import com.algomedica.util.ErrorCodesConstants;

@Repository
public class LicenseManagerServiceImpl implements LicenseManagerService {
	private static final Logger LOGGER = Logger.getLogger(LicenseManagerServiceImpl.class);
	@Autowired
	LicenseDao licenseDao;

	@Autowired
	CustomerDao customerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.service.LicenseManagerService#generateLicense(com.
	 * algomedica.form.LicenseForm, long)
	 */
	@Override
	public Customer generateLicense(LicenseForm licenseForm, long userId) {
		LicenseDetail detail = new LicenseDetail();
		Customer customer;

		try {
			if (licenseForm != null && StringUtils.isNotEmpty(licenseForm.getLsmacAddress())
					&& StringUtils.isNotBlank(licenseForm.getLsType()) && licenseForm.getLsExipryDays() != 0) {
				if (licenseDao
						.getLicenceByMacAddress(licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:")) != null) {
					LOGGER.error(ErrorCodesConstants.DUPLICATE_MAC_ADDRESS);
					throw new ApplicationException(ErrorCodesConstants.DUPLICATE_MAC_ADDRESS_CODE,
							ErrorCodesConstants.DUPLICATE_MAC_ADDRESS);
				}
				customer = customerDao.getCustomerById(licenseForm.getCustomerId());
				if (customer == null) {
					throw new DuplicateFieldException(ErrorCodesConstants.NO_RECORD_EXIST_CODE,
							ErrorCodesConstants.NO_RECORD_EXIST);
				}
				// generate license function calling area

				detail.setLsExipryDate(calculateExpiryDate(licenseForm.getLsExipryDays()));
				detail.setLsExpiryDays(BigDecimal.valueOf(licenseForm.getLsExipryDays()));
				LicenseManager lm = new LicenseManager();
				LicenseContent lc = new LicenseContent(customer.getOrgName(),
						licenseForm.getLsmacAddress().replace(":", ""), licenseForm.getLsType(),
						getTime(detail.getLsExipryDate().getTime()), licenseForm.getLsCategory());
				detail = lm.generateLicenseKey(lc, detail);
				// code merging is pending
				detail.setCreatedBy(userId);
				detail.setLsCost(BigDecimal.valueOf(Double.parseDouble(licenseForm.getLsCost())));

				detail.setLsLicenseType(licenseForm.getLsType());
				detail.setLsCategory(licenseForm.getLsCategory());
				String macAddress = licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:");
				detail.setLsMacAddress(macAddress);
				detail.setLsModelName(licenseForm.getLsmodelName());
				detail.setLsModelNumber(licenseForm.getLsmodelNumber());
				detail.setLsOpsEmail(licenseForm.getLsOpsEmail());
				detail.setLsOpsName(licenseForm.getLsOpsName());
				detail.setLsOpsPhone(licenseForm.getLsOpsPhone());
				detail.setOrganization(customer);
				detail.setLsStatus(ApplicationConstant.STATUC_ACTIVE);
				customer.getLicenseDetails().add(detail);
				customerDao.updateCustomer(customer);
			} else {
				throw new ApplicationException(ErrorCodesConstants.INVALID_INPUT_CODE,
						ErrorCodesConstants.INVALID_INPUT);
			}
		} catch (KeyGenerationException e) {
			LOGGER.error("During generating License exception occured ");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, e);
		} catch (ApplicationException e) {
			LOGGER.error("During generating License exception occured ");
			throw new ApplicationException(e.getCode(), e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("During generating License Exception occured ");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, e);
		}
		return customer;
	}

	private String getTime(long lsExipryDays) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(lsExipryDays); // Now use today date.
		return sdf.format(c.getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.algomedica.service.LicenseManagerService#updateLicense(com.algomedica
	 * .form.LicenseForm, long)
	 */
	@Override
	public Customer updateLicense(LicenseForm licenseForm, long userId) {
		Customer customer;
		try {
			if (licenseForm != null && StringUtils.isNotEmpty(licenseForm.getLsmacAddress())
					&& StringUtils.isNotBlank(licenseForm.getLsType()) && licenseForm.getLsExipryDays() != 0) {
				LicenseDetail detail = licenseDao.getLicenseById(licenseForm.getId());
				if (detail == null) {

					throw new ApplicationException(ErrorCodesConstants.INVALID_LICENSE_ID_CODE,
							ErrorCodesConstants.INVALID_LICENSE_ID);
				}

				if (!detail.getLsMacAddress().equals(licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:"))) {

					if (licenseDao
							.getLicenceByMacAddress(licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:")) != null) {
						LOGGER.error(ErrorCodesConstants.DUPLICATE_MAC_ADDRESS);
						throw new ApplicationException(ErrorCodesConstants.DUPLICATE_MAC_ADDRESS_CODE,
								ErrorCodesConstants.DUPLICATE_MAC_ADDRESS);
					}
					
					
				}

				customer = detail.getOrganization();
				if (checkForRegeneration(licenseForm, detail)) {
					LicenseDetail licenseDetail = (LicenseDetail) detail.clone();
					licenseDetail.setId(null);

					licenseDetail.setLsLicenseType(licenseForm.getLsType());
					String macAddress = licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:");
					licenseDetail.setLsMacAddress(macAddress);

					licenseDetail.setLsCategory(licenseForm.getLsCategory());

					licenseDetail.setLsExipryDate(
							calculateExpiryDate(detail.getLsExipryDate(), licenseForm.getLsExipryDays()));
					licenseDetail.setLsExpiryDays(BigDecimal.valueOf(licenseForm.getLsExipryDays()));

					// generate license function calling area
					LicenseManager lm = new LicenseManager();
					LicenseContent lc = new LicenseContent(detail.getOrganization().getOrgName(),
							licenseDetail.getLsMacAddress().replace(":", ""), licenseDetail.getLsLicenseType(),
							getTime(licenseDetail.getLsExipryDate().getTime()), licenseForm.getLsCategory());
					licenseDetail = lm.generateLicenseKey(lc, licenseDetail);
					licenseDetail.setLsCost(BigDecimal.valueOf(Double.parseDouble(licenseForm.getLsCost())));
					licenseDetail.setLsCost(BigDecimal.valueOf(Double.parseDouble(licenseForm.getLsCost())));
					licenseDetail.setLsCategory(licenseForm.getLsCategory());
					licenseDetail.setLsMacAddress(macAddress);
					licenseDetail.setLsModelName(licenseForm.getLsmodelName());
					licenseDetail.setLsModelNumber(licenseForm.getLsmodelNumber());
					licenseDetail.setLsOpsEmail(licenseForm.getLsOpsEmail());
					licenseDetail.setLsOpsName(licenseForm.getLsOpsName());
					licenseDetail.setLsOpsPhone(licenseForm.getLsOpsPhone());
					licenseDetail.setLsStatus(ApplicationConstant.STATUC_ACTIVE);
					licenseDetail.setLsreason(licenseForm.getLsregenerationReason());

					// code merging is pending
					detail.setLsStatus(ApplicationConstant.STATUC_INACTIVE);
					detail.setUpdatedBy(userId);
					detail.setLsCost(BigDecimal.valueOf(Double.parseDouble(licenseForm.getLsCost())));
					detail.setLsModelName(licenseForm.getLsmodelName());
					detail.setLsModelNumber(licenseForm.getLsmodelNumber());
					detail.setLsOpsEmail(licenseForm.getLsOpsEmail());
					detail.setLsOpsName(licenseForm.getLsOpsName());
					detail.setLsOpsPhone(licenseForm.getLsOpsPhone());
					detail.setLsreason(licenseForm.getLsregenerationReason());
					customer.getLicenseDetails().add(licenseDetail);
					customerDao.updateCustomer(customer);

				} else {
					detail.setUpdatedBy(userId);
					detail.setLsCost(BigDecimal.valueOf(Double.parseDouble(licenseForm.getLsCost())));
					String macAddress = licenseForm.getLsmacAddress().replaceAll("..(?!$)", "$0:");
					detail.setLsCategory(licenseForm.getLsCategory());
					detail.setLsMacAddress(macAddress);
					detail.setLsModelName(licenseForm.getLsmodelName());
					detail.setLsModelNumber(licenseForm.getLsmodelNumber());
					detail.setLsOpsEmail(licenseForm.getLsOpsEmail());
					detail.setLsOpsName(licenseForm.getLsOpsName());
					detail.setLsOpsPhone(licenseForm.getLsOpsPhone());
					detail.setLsStatus(ApplicationConstant.STATUC_ACTIVE);
					detail.setLsreason(licenseForm.getLsregenerationReason());
					licenseDao.updateLicense(detail);
				}
			} else {
				throw new ApplicationException(ErrorCodesConstants.INVALID_INPUT_CODE,
						ErrorCodesConstants.INVALID_INPUT);
			}
		} catch (ApplicationException e) {
			LOGGER.error("During updating License exception occured " + e.getMessage());
			throw new ApplicationException(e.getCode(), e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("During updating License Exception occured ");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, e);
		}
		return customer;
	}

	/**
	 * @param oldExpiryDate
	 * @param lsExipryDays
	 * @return
	 * @throws ParseException
	 */
	private Date calculateExpiryDate(Date oldExpiryDate, long lsExipryDays) throws ParseException {
		Calendar c = Calendar.getInstance(); // starts with today's date and

		c.setTime(oldExpiryDate);
		c.add(Calendar.DAY_OF_YEAR, Integer.valueOf(String.valueOf(lsExipryDays)));
		DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = c.getTime();
		date = outputFormatter.parse(outputFormatter.format(date.getTime()));
		return date;
	}

	/**
	 * @param oldExpiryDate
	 * @param lsExipryDays
	 * @return
	 * @throws ParseException
	 */
	private Date calculateExpiryDate(long lsExipryDays) throws ParseException {
		Calendar c = Calendar.getInstance(); // starts with today's date and
		c.add(Calendar.DAY_OF_YEAR, Integer.valueOf(String.valueOf(lsExipryDays)));
		DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = c.getTime();
		date = outputFormatter.parse(outputFormatter.format(date.getTime()));
		return date;
	}

	/**
	 * @param licenseForm
	 * @param licenseDetail
	 * @return boolean
	 */
	private boolean checkForRegeneration(LicenseForm licenseForm, LicenseDetail licenseDetail) {

		if (!licenseForm.getLsType().equals(licenseDetail.getLsLicenseType())
				|| !licenseForm.getLsmacAddress().equals(licenseDetail.getLsMacAddress().replace(":", ""))
				|| !licenseForm.getLsCategory().equals(licenseDetail.getLsCategory())
				|| !licenseDetail.getLsExpiryDays().equals(new BigDecimal(licenseForm.getLsExipryDays()))) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.algomedica.service.LicenseManagerService#getLicenseDetail(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> getLicenseDetail(String id) {
		Map<String, Object> map;
		if (StringUtils.isEmpty(id)) {
			throw new ApplicationException(ErrorCodesConstants.INVALID_LICENSE_ID_CODE,
					ErrorCodesConstants.INVALID_LICENSE_ID);
		}
		LicenseDetail licenseDetail = licenseDao.getLicenseById(Long.parseLong(id));
		if (licenseDetail == null || licenseDetail.getBlobkey() == null) {

			throw new ApplicationException(ErrorCodesConstants.INVALID_LICENSE_ID_CODE,
					ErrorCodesConstants.INVALID_LICENSE_ID);
		}
		// release the blob and free up memory. (since JDBC 4.0)
		try {
			java.sql.Blob blob = licenseDetail.getBlobkey();
			int blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);

			blobAsBytes = zipBytes(licenseDetail.getLsMacAddress() + ".key", blobAsBytes);
			map = new HashMap<>();
			map.put("data", blobAsBytes);
			map.put("filename", licenseDetail.getLsMacAddress());

			blob.free();
		} catch (SQLException | IOException e) {
			LOGGER.error("During getting License detail exception occured ");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, e);
		}

		return map;
	}

	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename);
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}
}
