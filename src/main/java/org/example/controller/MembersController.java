package org.example.controller;

import jakarta.validation.Valid;
import org.example.dao.MemberDAO;
import org.example.model.Member;
import org.example.util.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MembersController {

    private final MemberDAO dao;
    private final MemberValidator validator;

    @Autowired
    public MembersController(MemberDAO dao, MemberValidator validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("members", dao.index());
        return "members/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id) {

        Optional<Member> optionalMemberToBeShowed = dao.show(id);
        if (optionalMemberToBeShowed.isEmpty()) {
            return "error/not-found";
        }

        Member memberToBeShowed = optionalMemberToBeShowed.get();
        model.addAttribute("member", memberToBeShowed);

        return "members/show";
    }

    @GetMapping("/new")
    public String newMember(@ModelAttribute("member") Member member) {
        return "members/new";
    }

    @PostMapping()
    public String createNewMember(@ModelAttribute("member") @Valid Member newMember,
                                  BindingResult bindingResult) {

        validator.validate(newMember, bindingResult);
        if (bindingResult.hasErrors()) {
            return "members/new";
        }

        dao.save(newMember);
        return "redirect:/members";
    }

    @GetMapping("/{id}/edit")
    public String editMember(Model model,
                             @PathVariable("id") int id) {

        Optional<Member> optionalMemberToBeEdited = dao.show(id);
        if (optionalMemberToBeEdited.isEmpty()) {
            return "error/not-found";
        }

        Member memberToBeEdited = optionalMemberToBeEdited.get();
        model.addAttribute("member", memberToBeEdited);

        return "members/edit";
    }

    @PatchMapping("/{id}")
    public String saveEditedMember(@ModelAttribute("member") @Valid Member editedMember,
                                   BindingResult bindingResult,
                                   @PathVariable("id") int id) {

        validator.validate(editedMember, bindingResult);
        if (bindingResult.hasErrors()) {
            return String.format("members/%d/edit", id);
        }

        dao.update(editedMember, id);
        return "redirect:/members";
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect:/members";
    }
}
