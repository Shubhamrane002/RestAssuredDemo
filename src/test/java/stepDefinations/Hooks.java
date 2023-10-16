package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination sd = new StepDefination();
		
		if(StepDefination.place_id==null) {
		sd.add_place_playload_with("Shubham", "Marathi", "India");
		sd.user_calls_with_post_http_request("AddPlaceAPI","POST");
		sd.verify_place_id_created_maps_to_using("Shubham","GetPlaceAPI");
		}
	}

}
