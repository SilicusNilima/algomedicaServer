/**
 * 
 */
package com.algomedica.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.algomedica.dao.CustomerDao;
import com.algomedica.dao.LicenseDao;
import com.algomedica.entity.Customer;
import com.algomedica.entity.LicenseDetail;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.ORMException;
import com.algomedica.form.LicenseForm;

/**
 * @author KLele
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LicenseManagerServiceImplTest {

	@Mock
	LicenseDao licenseDao;

	@Mock
	CustomerDao customerDao;

	@InjectMocks
	LicenseManagerServiceImpl licenseServiceImpl;
	
	@Spy
	static
	LicenseForm licenseForm;
	
	@Spy
	static Customer customer;
	

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(LicenseManagerServiceImplTest.class);
		licenseForm=getLicenseForm();
		customer=getCustomer();
	}

	/**
	 * @return
	 */
	private static Customer getCustomer() {
		Customer cust1= new Customer();
		cust1.setId(new Long(1));
		cust1.setOrgName("New hospital");
		cust1.setOrgPersonName("first customer");
		cust1.setOrgPersonPhone("123654897");
		cust1.setOrgPhone("12633544");
		cust1.setLicenseDetails(new ArrayList<>());
		return cust1;
	}
	
	private static LicenseDetail getLicenseDetails(){
		LicenseDetail ld=new LicenseDetail();
		ld.setLsCategory("Medium");
		ld.setLsMacAddress("Aa:dd:cC:bB:eE:fF");
		ld.setLsLicenseType("Perptual");
		ld.setLsExpiryDays(new BigDecimal("2"));
		ld.setLsExipryDate(new Date());
		ld.setOrganization(customer);
		return  ld;
	}

	/**
	 * @return
	 */
	private static LicenseForm getLicenseForm() {
		LicenseForm licenseForm= new LicenseForm();
		licenseForm.setCustomerId(new Long(1));
		licenseForm.setLsmacAddress("qq:ww:rr:rr:tt:yy:YY");
		licenseForm.setLsExipryDays(20);
		licenseForm.setLsType("T");
		licenseForm.setLsmodelName("Lenovo");
		licenseForm.setLsmodelNumber("zp45");
		licenseForm.setLsCost("150");
		licenseForm.setLsCategory("SMall");
		return licenseForm;
	}
	@Test
	public void generateLicenseSuccess(){
		Mockito.when(licenseDao.getLicenceByMacAddress(licenseForm.getLsmacAddress())).thenReturn(null);
		Mockito.when(customerDao.getCustomerById(new Long(1))).thenReturn(customer);
		licenseServiceImpl.generateLicense(licenseForm,1);
		org.junit.Assert.assertNotSame(customer.getLicenseDetails().size(), 0);
				
	}
	@Test(expected=ApplicationException.class)
	public void generateLicenseFailDueToDupMac(){
		Mockito.when(licenseDao.getLicenceByMacAddress(licenseForm.getLsmacAddress())).thenReturn(new LicenseDetail());
		licenseServiceImpl.generateLicense(licenseForm,1);
	}
	@Test(expected=ApplicationException.class)
	public void generateLicenseFailDueToPesistingChanges(){
		Mockito.when(licenseDao.getLicenceByMacAddress(licenseForm.getLsmacAddress())).thenReturn(null);
		Mockito.when(customerDao.getCustomerById(new Long(1))).thenReturn(customer);
		Mockito.doThrow(new ORMException()).when(customerDao).updateCustomer(customer);
		licenseServiceImpl.generateLicense(licenseForm,1);
	}
	@Test
	public void updateLicenseSuccess(){
		Mockito.when(licenseDao.getLicenseById(licenseForm.getId())).thenReturn(getLicenseDetails());
		int actulSize = customer.getLicenseDetails().size();
		licenseServiceImpl.updateLicense(licenseForm,1);
		int newSize = customer.getLicenseDetails().size();
		org.junit.Assert.assertNotSame(actulSize,newSize);
	}

	@Test(expected=ApplicationException.class)
	public void updateLicenseFailDueToInvalidLs(){
		licenseServiceImpl.updateLicense(licenseForm,1);
	}

}
