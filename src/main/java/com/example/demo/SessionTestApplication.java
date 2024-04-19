package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@SpringBootApplication
public class SessionTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionTestApplication.class, args);
	}

	@GetMapping("/guardar")
	public String storeData(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			// Si sesión no existe, la crea.
			session = request.getSession();
			session.setAttribute("username", "Usuario nuevo");
			return "sesión creada.";
		} else {
			// Sesión ya existe.
			session.setAttribute("username", "Usuario que ya existe");
			return "sesión ya existe.";
		}
	}

	@GetMapping("/")
	public String getData(HttpSession session) {

		Object username = session.getAttribute("username");
		if (username == null) {
			return "No hay sesión.";
			
		} else {
			return "Hola, " + username;
		}
	}

	@GetMapping("/borrar")
	public String logout(HttpSession session) {
		// Invalidate the session
		session.invalidate();
		return "Sesión borrada";
	}

}
