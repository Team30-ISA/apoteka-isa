package isa.apoteka.grpc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import isa.apoteka.dto.MedicineQuantityDTO;
import isa.apoteka.grpc.ErrandServiceGrpc.ErrandServiceImplBase;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;

@Service
public class ErrandImpl extends ErrandServiceImplBase {
	
	private ErrandService errandService;
	private MedicineQuantityService medicineQuantityService;
	
	public ErrandImpl(ErrandService errandService, MedicineQuantityService medicineQuantityService) {
		this.errandService = errandService;
		this.medicineQuantityService = medicineQuantityService;
	}
	
	
	@Override
	public void newErrand(ErrandRequest request, StreamObserver<ErrandResponse> responseObserver) {
		Long id;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(request.getDeadline());
			id = errandService.save(date);
        } catch (Exception e) {
            id = (long)-1;
        }
		
		ErrandResponse response = ErrandResponse.newBuilder()
				.setId(id.intValue())
				.build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
		
		
	}
	
	@Override
	public void errandMedication(ErrandMedicationRequest request, StreamObserver<ErrandMedicationResponse> responseObserver) {
		List<ErrandMedication> errandMedications = request.getErrandMedicationsList();			
		List<MedicineQuantityDTO> dtos = new ArrayList<MedicineQuantityDTO>();
		for(ErrandMedication errandMedication : errandMedications) {
			dtos.add(new MedicineQuantityDTO(Long.valueOf(errandMedication.getId()), Long.valueOf(errandMedication.getErrandId()), errandMedication.getName(), errandMedication.getQuantity()));
		}
		ErrandMedicationResponse response = ErrandMedicationResponse.newBuilder()
				.setResponse(medicineQuantityService.insert(dtos))
				.build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();		
	}

}
