package io.bootify.biblioteca_no_s_q_l.controller;

import io.bootify.biblioteca_no_s_q_l.model.EstadoLibro;
import io.bootify.biblioteca_no_s_q_l.model.PrestamoDTO;
import io.bootify.biblioteca_no_s_q_l.service.PrestamoService;
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
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(final PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoLibroValues", EstadoLibro.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prestamos", prestamoService.findAll());
        return "prestamo/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prestamo") final PrestamoDTO prestamoDTO) {
        return "prestamo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/add";
        }
        prestamoService.create(prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.create.success"));
        return "redirect:/prestamos";
    }

    @GetMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final Model model) {
        model.addAttribute("prestamo", prestamoService.get(idBiblioteca));
        return "prestamo/edit";
    }

    @PostMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/edit";
        }
        prestamoService.update(idBiblioteca, prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.update.success"));
        return "redirect:/prestamos";
    }

    @PostMapping("/delete/{idBiblioteca}")
    public String delete(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final RedirectAttributes redirectAttributes) {
        prestamoService.delete(idBiblioteca);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("prestamo.delete.success"));
        return "redirect:/prestamos";
    }

}
