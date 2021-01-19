package isa.apoteka.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.User;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.UserDTO;
import isa.apoteka.service.UserService;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping("/currentUser")
    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_PHARM_ADMIN') or hasRole('ROLE_SYSTEM_ADMIN') or hasRole('ROLE_SUPPLIER') or hasRole('ROLE_PHARM') or hasRole('ROLE_DERM')")
    public User user() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();
        return this.userService.findByEmail(email);
    }
	
	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByEmail(user.getName());
	}
	
	@GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }
	
	@GetMapping(value = "/findAllDerms")
	public ResponseEntity<List<UserDTO>> getAllDerms() {

		List<User> users = userService.findAllDerms();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User u : users) {
			userDTO.add(new UserDTO(u));
		}

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllPharms")
	public ResponseEntity<List<UserDTO>> getAllPharms() {

		List<User> users = userService.findAllPharms();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User u : users) {
			userDTO.add(new UserDTO(u));
		}

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	

}
