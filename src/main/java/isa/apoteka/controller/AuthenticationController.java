package isa.apoteka.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isa.apoteka.dto.UserVerificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isa.apoteka.auth.JwtAuthenticationRequest;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.domain.UserTokenState;
import isa.apoteka.exception.ResourceConflictException;
import isa.apoteka.security.TokenUtils;
import isa.apoteka.service.UserService;
import isa.apoteka.service.impl.CustomUserDetailsService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		//
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
		try {
			User existUser = this.userService.findUserByEmail(userRequest.getEmail());
			if (existUser != null)
				throw new ResourceConflictException(userRequest.getId(), "Username already exists");

			return new ResponseEntity<>(this.userService.save(userRequest), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<Boolean> verifyUser(@RequestBody UserVerificationDTO verificationData) {
		try {
			this.userService.verifyUser(verificationData);
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}


	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token
	// osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@PostMapping(value = "/change-password",consumes = "application/json")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	@PostMapping("/logout")
	// @ResponseStatus(HttpStatus.OK)
	public void logout() {
		SecurityContextHolder.clearContext();
	}

	@GetMapping("/getRole")
	public ResponseEntity<String> getRole() {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_DERM"))) {
			return ResponseEntity.ok("DERM");
		}
		else if(SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PHARM"))) {
			return ResponseEntity.ok("PHARM");
		}
		else if(SecurityContextHolder.getContext().getAuthentication()

				.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return ResponseEntity.ok("ADMIN");
		}
		else if(SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))) {
			return ResponseEntity.ok("PATIENT");
		}
		return ResponseEntity.ok("NONE");
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}