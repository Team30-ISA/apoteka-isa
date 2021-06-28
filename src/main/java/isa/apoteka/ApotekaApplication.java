package isa.apoteka;

import java.io.IOException;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import isa.apoteka.grpc.ErrandImpl;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;

@SpringBootApplication
public class ApotekaApplication {
	
	private static ErrandService errandService;
	@Autowired
	private ErrandService errand;
	private static MedicineQuantityService medicineQuantityService;
	@Autowired
	private MedicineQuantityService medicineQuantity;
	
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
		errandService = errand;
		medicineQuantityService = medicineQuantity;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApotekaApplication.class, args);
		System.out.println("Startujem...");		
		Server server = ServerBuilder
		          .forPort(9001)
		          .addService(new ErrandImpl(errandService, medicineQuantityService)).build();
		        try {
					server.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					server.awaitTermination();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

}
