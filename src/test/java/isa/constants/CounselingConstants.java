package isa.constants;

import java.util.Date;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;

public class CounselingConstants {

	public static final Date NOW = new Date(1612738800000L);  				//08.02.2021. 00h
	public static final Date NOW_MINUS_1 = new Date(1612652400000L); 		//07.02.2021. 00h
	public static final Date DWC_1_START_DATE = new Date(1612911600000L);   //10.02.2021. 00h
    public static final Date DWC_1_END_DATE = new Date(1612998000000L);     //11.02.2021. 00h
    public static final Date COUNSELING_1_START = new Date(1612940400000L); //10.02.2021. 08h
    public static final Date COUNSELING_2_START = new Date(1612951200000L); //10.02.2021. 11h
    public static final Date COUNSELING_3_START = new Date(1612965600000L); //10.02.2021. 15h
    public static final Date COUNSELING_4_START = new Date(1612976400000L); //10.02.2021. 18h
    public static final Patient PATIENT = new Patient(1L, "Jelena", "Jelenovic");
	public static final Pharmacy PHARMACY = new Pharmacy(1L, "Apoteka 1", "Ulica 1");
	public static final Dermatologist DERMATOLOGIST = new Dermatologist(1L, "Petar", "Petrovic");
	public static final DermatologistWorkCalendar DWC = new DermatologistWorkCalendar(DERMATOLOGIST, PHARMACY, DWC_1_START_DATE, DWC_1_END_DATE);
	public static final Counseling COUNSELING = new Counseling(0L, COUNSELING_2_START, 30, DWC, PATIENT, 1000f, "");
	public static final Counseling COUNSELING0 = new Counseling(5L,COUNSELING_2_START, 30, null, null, 1000f, "");
	public static final Counseling COUNSELING1 = new Counseling(1L, COUNSELING_1_START, 15, DWC, null, 1000f, "");
    public static final Counseling COUNSELING2 = new Counseling(2L, COUNSELING_2_START, 15, DWC, null, 2000f, "");
    public static final Counseling COUNSELING3 = new Counseling(3L, COUNSELING_3_START, 15, DWC, PATIENT, 3000f, "");
    public static final Counseling COUNSELING4 = new Counseling(4L, COUNSELING_4_START, 15, DWC, PATIENT, 4000f, "");
    public static final Long COUNSELING_COUNT = 4L;
    public static final Long DERMATOLOGIST_ID = 1L;
    public static final Long COUNSELING_ID = 1L;
    public static final String  COUNSELING_DATE = "20210208 08:53:00 PM";
    public static final String COUNSELING_DURATION = "5";
    public static final String COUNSELING_PRICE = "1999.99";
    public static final String REPORT = "Izvestaj...";
}
