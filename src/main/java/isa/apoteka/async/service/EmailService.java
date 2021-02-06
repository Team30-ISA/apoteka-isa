package isa.apoteka.async.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.domain.User;


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

}
