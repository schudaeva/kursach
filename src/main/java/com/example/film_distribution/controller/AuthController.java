package com.example.film_distribution.controller;

import com.example.film_distribution.entity.Cinema;
import com.example.film_distribution.entity.Film;
import com.example.film_distribution.entity.Session;
import com.example.film_distribution.service.CinemaService;
import com.example.film_distribution.service.FilmService;
import com.example.film_distribution.service.SessionService;
import jakarta.validation.Valid;
import com.example.film_distribution.dto.UserDto;
import com.example.film_distribution.entity.User;
import com.example.film_distribution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @Autowired
    private FilmService service;

    @RequestMapping("/")
    // Аннотация @RequestMapping используется для мапинга (связывания) с URL для всего класса или для конкретного метода
    // обработчика.
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Film> listFilm = service.listAll(keyword);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @RequestMapping("/index")
    public String viewHome(Model model, @Param("keyword") String keyword) {
        List<Film> listFilm = service.listAll(keyword);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @RequestMapping("/admin")
    public String viewAdmin(Model model, @Param("keyword") String keyword) {
        List<Film> listFilm = service.listAll(keyword);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("keyword", keyword);
        return "admin";
    }

    @RequestMapping("/new")
    public String showNewFilmForm(Model model) {
        Film film = new Film();
        model.addAttribute("film", film);
        return "new_film";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFilm(@ModelAttribute("film") Film film) {
        service.save(film);
        return "redirect:/admin";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditFilmForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_film");
        Film film = service.get(id);
        mav.addObject("film", film);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteFilm(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/admin";
    }
    @RequestMapping("/description/{id}")
    public ModelAndView showDesc(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("description");
        Film film = service.get(id);
        mav.addObject("film", film);
        return mav;
    }

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping("/index/cinema_list")
    public String viewCinemaList(Model model, @Param("keyword") String keyword) {
        List<Cinema> listCinema = cinemaService.listAll(keyword);
        model.addAttribute("listCinema", listCinema);
        model.addAttribute("keyword", keyword);
        return "index_cin";
    }

    @RequestMapping("/admin/cinema_list")
    public String viewAdminCinema(Model model, @Param("keyword") String keyword) {
        List<Cinema> listCinema = cinemaService.listAll(keyword);
        model.addAttribute("listCinema", listCinema);
        model.addAttribute("keyword", keyword);
        return "admin_cin";
    }

    @RequestMapping("/new_cinema")
    public String showNewCinemaForm(Model model) {
        Cinema cinema= new Cinema();
        model.addAttribute("cinema", cinema);
        return "new_cinema";
    }

    @RequestMapping(value = "/save_cinema", method = RequestMethod.POST)
    public String saveCinema(@ModelAttribute("cinema") Cinema cinema) {
        cinemaService.save(cinema);
        return "redirect:admin/cinema_list";
    }


    @RequestMapping("/edit_cinema/{id}")
    public ModelAndView showEditCinemaForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_cinema");
        Cinema cinema = cinemaService.get(id);
        mav.addObject("cinema", cinema);
        return mav;
    }

    @RequestMapping("/delete_cinema/{id}")
    public String deleteCinema(@PathVariable(name = "id") Long id) {
        cinemaService.delete(id);
        return "redirect:/admin/cinema_list";
    }

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/index/session_list")
    public String viewSessionList(Model model, @Param("keyword") String keyword) {
        List<Session> listSession = sessionService.listAll(keyword);
        model.addAttribute("listSession", listSession);
        model.addAttribute("keyword", keyword);
        return "index_sl";
    }

    @RequestMapping("/admin/session_list")
    public String viewAdminSession(Model model, @Param("keyword") String keyword) {
        List<Session> listSession = sessionService.listAll(keyword);
        model.addAttribute("listSession", listSession);
        model.addAttribute("keyword", keyword);
        return "admin_sl";
    }
    @RequestMapping("/new_session")
    public String showNewSessionForm(Model model) {
        Session ses= new Session();
        model.addAttribute("ses", ses);
        return "new_session";
    }

    @RequestMapping(value = "/save_session", method = RequestMethod.POST)
    public String saveSession(@ModelAttribute("ses") Session ses) {
        sessionService.save(ses);
        return "redirect:admin/session_list";
    }


    @RequestMapping("/edit_session/{id}")
    public ModelAndView showEditSessionForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_session");
        Session ses= sessionService.get(id);
        mav.addObject("ses", ses);
        return mav;
    }

    @RequestMapping("/delete_session/{id}")
    public String deleteSession(@PathVariable(name = "id") Long id) {
        sessionService.delete(id);
        return "redirect:/admin/session_list";
    }
    @GetMapping("/admin/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/index/author")
    public String author(){
        return "author";
    }




}