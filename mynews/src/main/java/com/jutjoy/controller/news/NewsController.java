package com.jutjoy.controller.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.form.news.NewsEditForm;
import com.jutjoy.domain.service.news.NewsCreateService;
import com.jutjoy.domain.service.news.NewsDeleteService;
import com.jutjoy.domain.service.news.NewsEditService;
import com.jutjoy.domain.service.news.NewsListService;

@Controller
public class NewsController {
	@Autowired //インスタンスがセット
	private NewsCreateService newsCreateService;
	
	//2-4追加
	@Autowired
	private NewsListService newsListService;
	
	//2-5追加(編集)
	@Autowired
	private NewsEditService newsEditService;
	
	//2-5追加(削除)
	@Autowired
	private NewsDeleteService newsDeleteService;

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

	@GetMapping("/news/{action}/complete") /*リダイレクトはgetなので@getMapping*/
	public String complete(@PathVariable(name = "action") String action, Model model) {
		//completeメソッド 2-5編集：{action}の部分を任意の文字列で受け付けるようにし汎用性アップ
		
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

	//2-5追加(編集)
	@GetMapping("/news/edit/{id}") //{ }で囲まれた部分がパラメーター名(今回はid)
	public String edit(@ModelAttribute("form") NewsEditForm newsEditForm,
			@PathVariable(name = "id") int id, Model model) {
		//Pathvariableアノテーション：URLに含まれる動的なパラメーターの受取ができる
		//name属性にパラメーター名(id)を指定→URLの部分文字列を取得

		News news = newsEditService.findNews(id);
		model.addAttribute("news", news);

		return "news/edit";
	}

	@PostMapping("/news/edit/{id}")
	public String edit(@PathVariable(name = "id") int id,
			@Validated @ModelAttribute("form") NewsEditForm newsEditForm, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return edit(newsEditForm, id, model);
		}
		newsEditService.edit(id, newsEditForm);

		return "redirect:/news/edit/complete"; //登録完了後	completeにリダイレクト

	}
	//2-5追加(削除)
    @PostMapping("/news/delete") //PostでURL(/news/deleteにアクセス→deleteメソッドの呼出)
    public String delete(@RequestParam(name = "id", required = true) int id, Model model) {
    	//リクエストパラメーターとしてニュースidを受取、削除処理のパラメーターとして使用
        newsDeleteService.delete(id); 
        return "redirect:/news/list";
    }

}