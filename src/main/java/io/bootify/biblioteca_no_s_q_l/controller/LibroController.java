package io.bootify.biblioteca_no_s_q_l.controller;

import io.bootify.biblioteca_no_s_q_l.model.EstadoLibro;
import io.bootify.biblioteca_no_s_q_l.model.LibroDTO;
import io.bootify.biblioteca_no_s_q_l.service.LibroService;
import io.bootify.biblioteca_no_s_q_l.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(final LibroService libroService) {
        this.libroService = libroService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", EstadoLibro.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("libroes", libroService.findAll());
        return "libro/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("libro") final LibroDTO libroDTO) {
        return "libro/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("libro") @Valid final LibroDTO libroDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/add";
        }
        libroService.create(libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.create.success"));
        return "redirect:/libros";
    }

    @GetMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final Model model) {
        model.addAttribute("libro", libroService.get(idBiblioteca));
        return "libro/edit";
    }

    @PostMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @ModelAttribute("libro") @Valid final LibroDTO libroDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/edit";
        }
        libroService.update(idBiblioteca, libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.update.success"));
        return "redirect:/libros";
    }

    @PostMapping("/delete/{idBiblioteca}")
    public String delete(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final RedirectAttributes redirectAttributes) {
        libroService.delete(idBiblioteca);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("libro.delete.success"));
        return "redirect:/libros";
    }

}
