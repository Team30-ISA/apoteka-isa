package isa.constants;

import java.util.Date;

import isa.apoteka.domain.Examination;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.domain.Pharmacy;

public class ExaminationConstants {

	public static final Date NOW = new Date(1612738800000L);  				//08.02.2021. 00h
	public static final Date NOW_MINUS_1 = new Date(1612652400000L); 		//07.02.2021. 00h
	public static final Date PWC_1_START_DATE = new Date(1612911600000L);   //10.02.2021. 00h
    public static final Date PWC_1_END_DATE = new Date(1612998000000L);     //11.02.2021. 00h
    public static final Date EXAMINATION_1_START = new Date(1612940400000L); //10.02.2021. 08h
    public static final Date EXAMINATION_2_START = new Date(1612951200000L); //10.02.2021. 11h
    public static final Date EXAMINATION_3_START = new Date(1612965600000L); //10.02.2021. 15h
    public static final Date EXAMINATION_4_START = new Date(1612976400000L); //10.02.2021. 18h
    public static final Patient PATIENT = new Patient(1L, "Jelena", "Jelenovic");
	public static final Pharmacy PHARMACY = new Pharmacy(1L, "Apoteka 1", "Ulica 1");
	public static final Pharmacist PHARMACIST = new Pharmacist(1L, "Petar", "Petrovic",new Date());
	public static final PharmacistWorkCalendar pwc = new PharmacistWorkCalendar(PHARMACIST, PHARMACY, PWC_1_START_DATE, PWC_1_END_DATE, new Date());
	public static final Examination EXAMINATION = new Examination(0L, EXAMINATION_2_START, 30, pwc, PATIENT, 1000f, "");
	public static final Examination EXAMINATION1 = new Examination(1L, EXAMINATION_1_START, 15, pwc, null, 1000f, "");
    public static final Examination EXAMINATION2 = new Examination(2L, EXAMINATION_2_START, 15, pwc, null, 2000f, "");
    public static final Examination EXAMINATION3 = new Examination(3L, EXAMINATION_3_START, 15, pwc, PATIENT, 3000f, "");
    public static final Examination EXAMINATION4 = new Examination(4L, EXAMINATION_4_START, 15, pwc, PATIENT, 4000f, "");
    public static final Long EXAMINATION_COUNT = 4L;
    public static final Long PHARMACIST_ID = 1L;
}
