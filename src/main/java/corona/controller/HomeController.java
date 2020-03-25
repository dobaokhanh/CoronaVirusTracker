package corona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import corona.entity.LocationStat;
import corona.service.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		int totalCases = 0;
		for (LocationStat stat : coronaVirusDataService.getLocationStats()) {
			totalCases += stat.getLastestReportedCases();
		}
		model.addAttribute("locationStats", coronaVirusDataService.getLocationStats());
		model.addAttribute("totalCases", totalCases);
		return "home";
	}
}
