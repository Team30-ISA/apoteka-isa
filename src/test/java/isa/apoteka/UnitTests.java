package isa.apoteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import isa.apoteka.domain.Offer;
import isa.apoteka.domain.Supplier;
import isa.apoteka.dto.ComplaintDTO;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.repository.ComplaintRepository;
import isa.apoteka.repository.OfferRepository;
import isa.apoteka.repository.SupplierRepository;
import isa.apoteka.service.impl.ComplaintServiceImpl;
import isa.apoteka.service.impl.OfferServiceImpl;
import isa.apoteka.service.impl.SupplierServiceIml;

import static isa.constants.PharmacyConstants.ERRAND_ID;
import static isa.constants.PharmacyConstants.SUPPLIER_ID;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTests {
	
	@Mock
	private OfferRepository offerRepositoryMock;

	@InjectMocks
	private OfferServiceImpl offerService;
	
	@Mock
	private ComplaintRepository complaintRepositoryMock;

	@InjectMocks
	private ComplaintServiceImpl complaintService;
	
	@Mock
    private SupplierRepository supplierRepositoryMock;
	
    @Mock
    private Supplier supplierMock;
    
    @InjectMocks
    private SupplierServiceIml supplierServiceMock;
	
    @Test
	public void test_Find_Offers_By_SupplierId() throws Exception{
		List<Offer> list = new ArrayList<Offer>();
		
		list = offerRepositoryMock.findAllBySupplierId(SUPPLIER_ID);
		
		assertNotNull(list);
		verify(offerRepositoryMock, times(1)).findAllBySupplierId(SUPPLIER_ID);
	    verifyNoMoreInteractions(offerRepositoryMock);
	}
    
    @Test
	public void test_Find_Offers_By_ErrandId_And_SupplierId_NotFound() throws Exception{
    	Offer offer = offerRepositoryMock.findByErrandIdAndSupplierId(ERRAND_ID, SUPPLIER_ID);
		
		assertNull(offer);
		verify(offerRepositoryMock, times(1)).findByErrandIdAndSupplierId(ERRAND_ID, SUPPLIER_ID);
	    verifyNoMoreInteractions(offerRepositoryMock);
	}
    
	@Test
	public void test_Find_All_Offers_For_Errand() throws Exception{
		List<SupplierDTO> list = new ArrayList<>();
		
		list = offerService.findAllOffersForErrand(ERRAND_ID);
		
		assertNotNull(list);
	}
	
	@Test
	public void test_Get_One_Supplier_By_Id() throws Exception{
		when(supplierRepositoryMock.findById(SUPPLIER_ID)).thenReturn(Optional.of(supplierMock));

	    Supplier supplier = supplierServiceMock.findById(SUPPLIER_ID);

	    assertEquals(supplierMock, supplier);
	    verify(supplierRepositoryMock, times(1)).findById(SUPPLIER_ID);
	    verifyNoMoreInteractions(supplierRepositoryMock);
	}
	
	@Test
	public void test_Get_All_Complaints() throws Exception{
		List<ComplaintDTO> list = new ArrayList<ComplaintDTO>();
		
		list = complaintService.getAllComplaints();
		
		assertNotNull(list);	
	}
	
}
