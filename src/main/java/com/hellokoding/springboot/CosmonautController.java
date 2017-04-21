package com.hellokoding.springboot;

import com.hellokoding.springboot.validation.CosmonautFormValidator;
import com.hellokoding.springboot.service.CosmonautService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

@Controller
public class CosmonautController extends WebMvcConfigurerAdapter {

    @Autowired
    private CosmonautService cosmonautService;

    @Autowired
    CosmonautFormValidator cosmonautFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(cosmonautFormValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getdata() {

        ModelAndView model = new ModelAndView("index");
        List<Cosmonaut> list = cosmonautService.findAll();
        model.addObject("list", list);
        model.addObject("not_empty", !list.isEmpty());
        model.addObject("cosmonautForm", new Cosmonaut());

        return model;
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("cosmonautForm") @Validated Cosmonaut cosmonaut, BindingResult result) {

        ModelAndView model = new ModelAndView("index");
        if (!result.hasErrors()) {
            cosmonautService.save(cosmonaut);
            model.addObject("cosmonautForm", new Cosmonaut());
        }

        List<Cosmonaut> list = cosmonautService.findAll();
        model.addObject("list", list);
        model.addObject("not_empty", !list.isEmpty());
        return model;
    }

    // delete user
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") String id) {
        cosmonautService.delete(Integer.parseInt(id));

        return "redirect:/";

    }

}

