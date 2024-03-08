package com.jutjoy.controller.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.service.news.NewsCreateService;
import com.jutjoy.domain.service.news.NewsListService;

@Controller
public class NewsController {
	@Autowired //インスタンスがセット
	private NewsCreateService newsCreateService;
	//2-4追加
	@Autowired
	private NewsListService newsListService; 

	@GetMapping("/news/create")
	public String create(@ModelAttribute("form") NewsCreateForm newsCreateForm) {
		return "news/create";
	}

	@PostMapping("/news/create")
	public String create(@Validated @ModelAttribute("form") NewsCreateForm newsCreateForm,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "news/create";
		}

		newsCreateService.create(newsCreateForm);

		return "redirect:/news/create/complete"; /*リダイレクト*/

	}

	@GetMapping("/news/create/complete") /*リダイレクトはgetなので@getMapping*/
	public String complete() {
		return "news/complete";
	}
	
	//2-4追加
    @GetMapping("/news/list")
    public String list(@RequestParam(name = "title", required = false) String title, Model model) {

        List<News> newsList = newsListService.list(title);
        model.addAttribute("newsList", newsList);
        model.addAttribute("title", title);

        return "news/list";
    }
    
}