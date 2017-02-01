/**
 * 
 */
package com.algomedica.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Any;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.algomedica.dao.CityDao;
import com.algomedica.dao.CountryDao;
import com.algomedica.dao.CustomerDao;
import com.algomedica.dao.StateDao;
import com.algomedica.entity.Address;
import com.algomedica.entity.Customer;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.ORMException;
import com.algomedica.form.CustomerForm;

/**
 * @author SQureshi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	CustomerDao dao;
	@Mock
	CityDao cityDao;
	@Mock
	StateDao stateDao;
	@Mock
	CountryDao countryDao;

	@InjectMocks
	CustomerServiceImpl customerService;

	@Spy
	private static List<Customer> customers = new ArrayList<Customer>();

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(CustomerServiceImplTest.class);
		customers = getCustomerList();
	}

	private static List<Customer> getCustomerList() {
		Customer cust1 = new Customer();
		Customer cust2 = new Customer();
		Address addr1 = new Address();
		Address addr2 = new Address();
		
		cust1.setId(new Long(1));
		cust1.setOrgName("New hospital");
		cust1.setOrgPersonName("first customer");
		cust1.setOrgPersonPhone("123654897");
		cust1.setOrgPhone("12633544");
		addr1.setAddress1("New street 1");
		addr1.setAddress2("new street 2");
		cust1.setAddress(addr1);
		
		cust2.setId(new Long(2));
		cust2.setOrgName("New hospital 2");
		cust2.setOrgPersonName("secund customer");
		cust2.setOrgPersonPhone("120000000");
		cust2.setOrgPhone("22222222");
		addr2.setAddress1("New street2 1");
		addr2.setAddress2("new street2 2");
		cust2.setAddress(addr2);
		customers.add(cust1);
		customers.add(cust2);
		return customers;
	}

	
	@Test
	public void getCustomerByIdSuccessTest() {
		Customer customer = customers.get(0);
		Mockito.when(dao.getCustomerById(customer.getId())).thenReturn(customer);
		assertEquals(customerService.getCustomerById(customer.getId()), customer);
	}
	@Test(expected=ApplicationException.class)
	public void getCustomerByIdForNullIdTest() {
		customerService.getCustomerById(null);
	}
	@Test(expected=ApplicationException.class)
	public void getCustomerByIdORMExpTest() {

		Customer customer = customers.get(0);
		Mockito.when(dao.getCustomerById(new Long(500))).thenThrow(ORMException.class);
		customerService.getCustomerById(new Long(500));
	}

	@Test
	public void saveCustomerSuccessTest() {
		CustomerForm custForm = new CustomerForm();
		Customer customer = customers.get(0);
		custForm.setOrgName(customer.getOrgName());
		custForm.setOrgPersonName(customer.getOrgPersonName());
		custForm.setOrgPersonEmail(customer.getOrgEmail());
		custForm.setCityName("Abc");
		custForm.setStateName("New Mexico");
		custForm.setCountryName("US");
		assertEquals(customerService.saveCustomer(custForm).getOrgName(),customer.getOrgName());
	}
	@Test(expected=ApplicationException.class)
	public void saveCustomerForNullTest() {
		CustomerForm custForm = new CustomerForm();
		Customer customer = customers.get(0);
		custForm.setOrgName(customer.getOrgName());
		custForm.setOrgPersonName(customer.getOrgName());
		custForm.setOrgPersonEmail(customer.getOrgEmail());
		customerService.saveCustomer(custForm);
	}
	@Test(expected=ApplicationException.class)
	public void saveCustomerByIdORMExpTest() {
		
		CustomerForm custForm = new CustomerForm();
		Customer customer = customers.get(0);
		custForm.setOrgName(customer.getOrgName());
		custForm.setOrgPersonName(customer.getOrgName());
		custForm.setOrgPersonEmail(customer.getOrgEmail());
		custForm.setCityName("Abc");
		custForm.setStateName("New Mexico");
		custForm.setCountryName("US");
		Mockito.doThrow(new ORMException()).when(dao).saveCustomer(ArgumentMatchers.any());
		customerService.saveCustomer(custForm);
		
	}
	@Test
	public void updateCustomerSuccessTest() {
		CustomerForm custForm = new CustomerForm();
		Customer customer = customers.get(0);
		custForm.setId(customer.getId());
		custForm.setOrgName(customer.getOrgName());
		custForm.setOrgPersonName(customer.getOrgName());
		custForm.setOrgPersonEmail(customer.getOrgEmail());
		custForm.setCityName("Abc");
		custForm.setStateName("New Mexico");
		custForm.setCountryName("US");
		Mockito.when(dao.getCustomerById(customer.getId())).thenReturn(customer);
		assertEquals(customerService.updateCustomer(custForm).getOrgName(),customer.getOrgName());
	}
	@Test(expected=ApplicationException.class)
	public void updateCustomerForNullTest() {
		CustomerForm custForm = new CustomerForm();
		Customer customer = customers.get(0);
		custForm.setId(null);
		custForm.setOrgName(customer.getOrgName());
		custForm.setOrgPersonName(customer.getOrgName());
		custForm.setOrgPersonEmail(customer.getOrgEmail());
		customerService.updateCustomer(custForm);
	}
	
}
