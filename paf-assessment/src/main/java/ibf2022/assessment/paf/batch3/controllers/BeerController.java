package ibf2022.assessment.paf.batch3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	private BeerService svc;

	@Autowired
	private BeerRepository repo;

	//TODO Task 2 - view 0
	@GetMapping(path="/")
	public String goToLanding(Model model) {
		model.addAttribute("styles", repo.getStyles());
		return "view0";
	}
	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/{styleId}")
	public String browseByStyle(@PathVariable Integer styleId, @RequestParam String styleName, Model model) {
		model.addAttribute("breweries", repo.getBreweriesByBeer(styleId));
		model.addAttribute("styleName", styleName);
		return "view1";
	}

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}
