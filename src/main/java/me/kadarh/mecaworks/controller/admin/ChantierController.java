package me.kadarh.mecaworks.controller.admin;

import me.kadarh.mecaworks.domain.others.Chantier;
import me.kadarh.mecaworks.service.ChantierService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * PROJECT mecaworks
 *
 * @author kadarH
 * Created at 20/02/18
 */

@Controller
@RequestMapping("/admin/chantiers")
public class ChantierController {

    private final ChantierService chantierService;

    public ChantierController(ChantierService chantierService) {
        this.chantierService = chantierService;
    }

    @GetMapping("")
    public String list(Model model, Pageable pageable, @RequestParam(defaultValue = "") String search) {
        model.addAttribute("page", chantierService.chantierList(pageable, search));
        model.addAttribute("search", search);
        return "admin/chantiers/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("chantier", new Chantier());
        return "admin/chantiers/add";
    }

    @PostMapping("/add")
    public String addPost(Model model, @Valid Chantier chantier, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/chantiers/add";
        }
        chantierService.add(chantier);
        return "redirect:/admin/chantiers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("chantier", chantierService.get(id));
        model.addAttribute("edit", true);
        return "admin/chantiers/add";
    }

    @PostMapping("/{id}/edit")
    public String editPost(Model model, @Valid Chantier chantier, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            return "admin/chantiers/add";
        }
        chantierService.update(chantier);
        return "redirect:/admin/chantiers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        chantierService.delete(id);
        return "redirect:/admin/chantiers";
    }

}
