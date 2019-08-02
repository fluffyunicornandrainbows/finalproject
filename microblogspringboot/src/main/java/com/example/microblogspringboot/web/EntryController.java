package com.example.microblogspringboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.microblogspringboot.domain.Entry;
import com.example.microblogspringboot.repository.EntryRepository;

@Controller
public class EntryController {
	@Autowired
    EntryRepository entryRepository;

    @RequestMapping("/")
    public String home(Model model){
        List<Entry> allEntries = entryRepository.findAll();
        model.addAttribute("entries", allEntries);
        return "home"; 
    
    }
    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public String newEntry(Model model) {
        model.addAttribute("pageTitle", "New Entry");
        model.addAttribute("givenAction", "/entry");
        model.addAttribute("givenTitle", "");
        model.addAttribute("givenContent", "");
        return "entry";
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String addEntry(@RequestParam String title, @RequestParam String content) {
        Entry newEntry = new Entry(title, content);
        entryRepository.save(newEntry);
        return "redirect:/";
    }
    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
    public String editEntry(@PathVariable(value = "id") Long entryId, Model model) {
        Entry entry = entryRepository.findById(entryId).get();
        model.addAttribute("pageTitle", "Edit Entry");
        model.addAttribute("givenAction", "/entry/" + entryId);
        model.addAttribute("givenTitle", entry.getTitle());
        model.addAttribute("givenContent", entry.getContent());
        return "entry";
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.POST)
    public String updateEntry(@PathVariable(value = "id") Long entryId,
                              @RequestParam String title,
                              @RequestParam String content) {
        Entry entry = entryRepository.findById(entryId).get();
        entry.setTitle(title);
        entry.setContent(content);
        entryRepository.save(entry);
        return "redirect:/";
    }

    @RequestMapping(value = "/entry/delete/{id}", method = RequestMethod.GET)
    public String deleteEntry(@PathVariable(value = "id") Long entryId) {
        entryRepository.deleteById(entryId);
        return "redirect:/";
    }
    
    @RequestMapping("/myblog")
    public String myBlog() {
    	return "myblog";
    }
    
    @RequestMapping("/home")
    public String home() {
    	return "home";
    }
}
