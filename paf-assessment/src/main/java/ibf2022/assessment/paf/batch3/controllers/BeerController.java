package ibf2022.assessment.paf.batch3.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.LineItem;
import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	private BeerService svc;

	@Autowired
	private BeerRepository repo;

	// TODO Task 2 - view 0
	@GetMapping(path = "/")
	public String goToLanding(Model model) {
		model.addAttribute("styles", repo.getStyles());
		return "view0";
	}

	// TODO Task 3 - view 1
	@GetMapping(path = "/beer/style/{styleId}")
	public String browseByStyle(@PathVariable Integer styleId, @RequestParam String styleName, Model model) {
		model.addAttribute("breweries", repo.getBreweriesByBeer(styleId));
		model.addAttribute("styleName", styleName);
		return "view1";
	}

	// TODO Task 4 - view 2
	@GetMapping(path = "/brewery/{breweryId}")
	public String goToBrewery(@PathVariable Integer breweryId, Model model) {
		Optional<Brewery> optB = repo.getBeersFromBrewery(breweryId);
		if (optB.isPresent()) {
			model.addAttribute("brewery", optB.get());
		} else {
			model.addAttribute("brewery", null);
		}
		return "view2";
	}

	// TODO Task 5 - view 2, place order
	@PostMapping(path = "/brewery/{breweryId}/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String placeOrder(@PathVariable Integer breweryId, @RequestParam HashMap<String, String> orderMap,
			Model model) {
		// parse order header
		Order o = new Order();
		o.setBreweryId(breweryId);
		o.setDate(LocalDate.now());

		// parse order details
		// orderMap.keySet().forEach(key -> System.out.println(key.getClass()));
		orderMap.keySet().forEach(beerId -> {
			if (!orderMap.get(beerId).isBlank() && Integer.parseInt(orderMap.get(beerId)) > 0) {
				o.getOrders().add(
						new LineItem(Integer.parseInt(beerId), Integer.parseInt(orderMap.get(beerId))));
			}
		});

		// place order and assign orderId
		o.setOrderId(svc.placeOrder(o));

		model.addAttribute("orderId", o.getOrderId());
		return "view3";
	}

}
