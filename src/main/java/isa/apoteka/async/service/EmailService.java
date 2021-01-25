package isa.apoteka.async.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.User;


@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Async
	public void sendPromotionNotificaitionAsync(User user, Promotion promotion) throws MailException, InterruptedException {
		System.out.println("Async metoda se izvrsava u drugom Threadu u odnosu na prihvaceni zahtev. Thread id: " + Thread.currentThread().getId());
		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(promotion.getTitle());
		mail.setText("Pozdrav " + user.getFirstName() + ",\n" + "\nPočetak promocije: " + new SimpleDateFormat("dd. MMM yyyy.").format(promotion.getStartOfPromotion())
				+ "\nKraj promocije: " + new SimpleDateFormat("dd. MMM yyyy.").format(promotion.getEndOfPromotion()) + "\n\n" + promotion.getContent());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

}
