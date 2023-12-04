package io.bootify.biblioteca_no_s_q_l.controller;

import io.bootify.biblioteca_no_s_q_l.model.LectorDTO;
import io.bootify.biblioteca_no_s_q_l.service.LectorService;
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
@RequestMapping("/lectors")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(final LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("lectors", lectorService.findAll());
        return "lector/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("lector") final LectorDTO lectorDTO) {
        return "lector/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("lector") @Valid final LectorDTO lectorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lector/add";
        }
        lectorService.create(lectorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("lector.create.success"));
        return "redirect:/lectors";
    }

    @GetMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final Model model) {
        model.addAttribute("lector", lectorService.get(idBiblioteca));
        return "lector/edit";
    }

    @PostMapping("/edit/{idBiblioteca}")
    public String edit(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @ModelAttribute("lector") @Valid final LectorDTO lectorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lector/edit";
        }
        lectorService.update(idBiblioteca, lectorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("lector.update.success"));
        return "redirect:/lectors";
    }

    @PostMapping("/delete/{idBiblioteca}")
    public String delete(@PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            final RedirectAttributes redirectAttributes) {
        lectorService.delete(idBiblioteca);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("lector.delete.success"));
        return "redirect:/lectors";
    }

}
