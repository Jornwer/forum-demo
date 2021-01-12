package com.jornwer.forumdemo.controller;

import com.jornwer.forumdemo.dto.AuthenticationRequestDTO;
import com.jornwer.forumdemo.dto.UserDTO;
import com.jornwer.forumdemo.model.User;
import com.jornwer.forumdemo.security.JwtTokenProvider;
import com.jornwer.forumdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Controller
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.header}")
    private String authorizationHeader;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(AuthenticationRequestDTO request, Model model, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userService.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Cookie cookie = new Cookie(authorizationHeader, token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:/";
        } catch (AuthenticationException e) {
            model.addAttribute("invalidCredentials", true);
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("UserDTO", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerNewUser(@Validated UserDTO request,
                                  BindingResult errors,
                                  Model model) {
        if (userService.isUserRegistered(request.getEmail())) {
            model.addAttribute("NonUniqueEmail", true);
            return "signup";
        }
        if (errors.hasErrors() || model.containsAttribute("NonUniqueEmail")) {
            return "signup";
        }
        userService.registerUser(request);
        return "redirect:/";
    }
}
