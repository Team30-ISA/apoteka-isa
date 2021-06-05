package isa.apoteka.async.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Offer;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.domain.User;

import isa.apoteka.dto.QRcodeInformationDTO;


@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Async
	public void sendPromotionNotificaitionAsync(User user, Promotion promotion) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(promotion.getTitle());
		mail.setText("Pozdrav " + user.getFirstName() + ",\n" + "\nPočetak promocije: " + new SimpleDateFormat("dd. MMM yyyy.").format(promotion.getStartOfPromotion())
				+ "\nKraj promocije: " + new SimpleDateFormat("dd. MMM yyyy.").format(promotion.getEndOfPromotion()) + "\n\n" + promotion.getContent());
		javaMailSender.send(mail);
	}

	@Async
	public void sendVerificationEmail(User user, String hashedEmail) throws MailException{

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			mimeMessage.setContent(
					"<p>Verify your account bt clicking on the following link: <a href=\"http://localhost:8081/verifiedAccount.html?userId=" + user.getId()
							+ "&hash=" + hashedEmail +"\">Verify my account</a></p>", "text/html");
			MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, "utf-8");
			mail.setTo(user.getEmail());
			if (env.getProperty("spring.mail.username") == null) {
				return;
			}
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("Verify your account");

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	@Async
	public void sendMedicineReservation(ReservedMedicine rm, Patient p) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(p.getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Rezervacija leka");
		mail.setText("Pozdrav, " + p.getFirstName() + "\n" + "\nLek je rezervisan do: " + rm.getDate()
				+ "\nNaziv leka: " + rm.getMedicine().getName() + "\n Sifra rezervacije: " + rm.getUid());
		javaMailSender.send(mail);

	}
	
	@Async
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void sendCounselingReservation(Counseling c, Patient p) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(p.getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Rezervacija pregleda kod dermatologa");
		mail.setText("Pozdrav, " + p.getFirstName() + "\n" + "\nPregled je rezervisan za datum: " + c.getStartDate()
				+ "\nNaziv dermatologa: " + c.getDermatologistWorkCalendar().getDermatologist().getFirstName() + " " + c.getDermatologistWorkCalendar().getDermatologist().getLastName() + "\n ");
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendExaminationReservation(Date start, PharmacistWorkCalendar pwc, Patient p) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(p.getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Rezervacija pregleda kod farmaceuta");
		mail.setText("Pozdrav, " + p.getFirstName() + "\n" + "\nPregled je rezervisan za datum: " + start
				+ "\nNaziv dermatologa: " + pwc.getPharmacist().getFirstName() + " " + pwc.getPharmacist().getLastName() + "\n ");
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendOfferNotificaitionAsync(Offer offer) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(offer.getSupplier().getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Ponuda za narudzbenicu");
		if(offer.getIsApproved() == true) {
		mail.setText("Pozdrav " + offer.getSupplier().getFirstName() + ",\n" + "\nVaša ponuda za narudžbenicu broj " + offer.getErrand().getId() + " je odobrena.");

		}else {
			mail.setText("Pozdrav " + offer.getSupplier().getFirstName() + ",\n" + "\nVaša ponuda za narudžbenicu broj " + offer.getErrand().getId() + "je nažalost odbijena.");
		}
		
		javaMailSender.send(mail);
	}
	
	@Async
	public void issuedMedicineReservation(String uid, Patient p) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(p.getEmail());
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Rezervacija leka izdata");
		mail.setText("Pozdrav, " + p.getFirstName() + "\n" + "\nRezervacija broj " + uid
				+ " je uspesno preuzeta!");
		javaMailSender.send(mail);
	}

	@Async
    public void sendComplaintAnswer(String recipientName, String response, String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		if(env.getProperty("spring.mail.username") == null) {
			return;
		}
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odgovor na zalbu");
		mail.setText("Odgovor na Vasu zalbu za " + recipientName + ". \n" + response);
		javaMailSender.send(mail);
    }
	
	@Async
    public Boolean informPatientAboutEreceipt(List<QRcodeInformationDTO> medications) {
        try {

            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(patient.getEmail());
            if(env.getProperty("spring.mail.username") == null) {
    			return false;
    		}
            mail.setSubject("EPrescription");
            mail.setFrom(env.getProperty("spring.mail.username"));
            StringBuilder text = new StringBuilder();
            for (QRcodeInformationDTO medication:medications) {
                text.append(medication.getMedicationName() + ", quantity: " + medication.getQuantity() + "\n");
            }
            mail.setText("Thank you for buying medicines with ePrescription!\n\nList of medicines:\n" +text.toString());

            javaMailSender.send(mail);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
