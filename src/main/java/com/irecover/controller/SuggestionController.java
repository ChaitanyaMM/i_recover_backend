package com.irecover.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irecover.entity.ProfessionEnum;
import com.irecover.entity.Suggestion;
import com.irecover.entity.SuggestionFor;
import com.irecover.entity.Type;
import com.irecover.entity.UserBlog;
import com.irecover.service.SuggestionService;
import com.irecover.service.UserBlogService;
import com.irecover.util.DataUtil;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

	@Autowired
	DataUtil dataUtil;

	@Autowired
	private Neo4jClient neo4jClient;

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private UserBlogService userBlogService;

	@PostMapping("/")
	public ResponseEntity<?> createSuggestion(@RequestBody Suggestion suggestion) {

		Suggestion savedSuggestion = suggestionService.create(suggestion);

		return new ResponseEntity<>(savedSuggestion, HttpStatus.CREATED);
	}

	@PutMapping("/{suggestionId}")
	public ResponseEntity<?> updateSuggestion(@PathVariable Long suggestionId, @RequestBody Suggestion suggestion) {

		Suggestion updatedContent = suggestionService.update(suggestionId, suggestion);

		return new ResponseEntity<>(updatedContent, HttpStatus.OK);
	}

	@DeleteMapping("/{suggestionId}")
	public ResponseEntity<?> deleteSuggestion(@PathVariable Long suggestionId) {
		suggestionService.deleteById(suggestionId);

		return new ResponseEntity<>(suggestionId, HttpStatus.OK);

	}

	@GetMapping("/{suggestionId}")
	public ResponseEntity<?> getSuggestion(@PathVariable Long suggestionId) {
		Optional<Suggestion> fetched = suggestionService.findById(suggestionId);

		return new ResponseEntity<>(fetched.get(), HttpStatus.OK);

	}

	@GetMapping("/list")
	public ResponseEntity<?> getSuggestionsList() {
		Iterable<Suggestion> suggestionsList = suggestionService.findAll();
		return new ResponseEntity<>(suggestionsList, HttpStatus.OK);

	}

	@GetMapping("/type/{suggestionType}")
	public ResponseEntity<?> getSuggestionByType(@PathVariable(value = "suggestionType") Type suggestionType) {

		List<?> data = null;
		switch (suggestionType) {

		case HOSPITAL:
			data = dataUtil.getHospitalInfo();
			break;
		case BLOOD:
			data = dataUtil.bloodInfo();
			break;
		case PLASMA:
			data = dataUtil.plasmaInfo();
			break;
		case DOCTOR:
			data = dataUtil.doctorsInfo();
			break;
		default:
			data = new ArrayList<>();
			break;
		}

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<Map<String, String>> getneo4jSuggestion(@RequestParam String name,
			@RequestParam ProfessionEnum profession, @RequestParam SuggestionFor suggestionfor) {

		final String hospital = "hospital";
		final String food = "food";
		final String music = "music";
		final String book = "book";
		final String drink = "drink";
		final String quote = "quote";

		Map<String, String> data = new HashMap<>();
		String foodSuggestion = "";
		String hospitalSuggestion = "";
		String musicSuggestion = "";
		String drinkSuggestion = "";
		String quoteSuggestion = "";
		String bookSuggestion = "";

		if (suggestionfor.name().equalsIgnoreCase(hospital)) {
			hospitalSuggestion = neo4jClient
					.query(" MATCH (bruce:User {name: '" + name + "'}),\n"
							+ "      (user: User)-[:WORKS_IN]->(:Job {name: '" + profession.name() + "'}),\n"
							+ "      (user)-[:HAS_SYMPTOM]-(symptom),\n" + "      (user)-[:TAKEN_TO]-("
							+ suggestionfor.name() + ")\n" + "      \n" + "Return hospital.name")
					.fetchAs(String.class).one().get();
			data.put("hospital", hospitalSuggestion);
			return new ResponseEntity<>(data, HttpStatus.OK);

		} else if (suggestionfor.name().equalsIgnoreCase(food)) {
			foodSuggestion = neo4jClient
					.query("MATCH (geetha:User {name: 'geetha'}),\n" + "      (user: User)-[:WORKS_IN]->(:Job {name: '"
							+ profession.name() + "'}),\n" + "      (user)-[:HAS_SYMPTOM]-(symptom),\n"
							+ "      (user)-[:ATE]-(" + suggestionfor.name() + ")\n" + "      \n" + "Return food.name")
					.fetchAs(String.class).one().get();
			data.put("food", foodSuggestion);

			return new ResponseEntity<>(data, HttpStatus.OK);

		} else if (suggestionfor.name().equalsIgnoreCase(music)) {
			musicSuggestion = neo4jClient.query("MATCH (" + name + ":User {name: '" + name + "'}),\n"
					+ "      (user: User)-[:WORKS_IN]->(:Job {name: '" + profession.name() + "'}),\n"
					+ "      (user)-[:HAS_SYMPTOM]-(symptom),\n" + "      (user)-[:LISTEN_TO]-(music)\n" + "      \n"
					+ "Return music.name").fetchAs(String.class).one().get();
			data.put("music", musicSuggestion);

			return new ResponseEntity<>(data, HttpStatus.OK);

		}

		else if (suggestionfor.name().equalsIgnoreCase(book)) {
			bookSuggestion = neo4jClient
					.query("MATCH (" + name + ":User {name: '" + name + "'}),\n"
							+ "      (user: User)-[:WORKS_IN]->(:Job {name: '" + profession.name() + "'}),\n"
							+ "      (user)-[:HAS_SYMPTOM]-(symptom),\n" + "      (user)-[:READ]-("
							+ suggestionfor.name() + ")\n" + "      \n" + "Return book.name")
					.fetchAs(String.class).one().get();
			data.put("book", bookSuggestion);

			return new ResponseEntity<>(data, HttpStatus.OK);

		} else if (suggestionfor.name().equalsIgnoreCase(drink)) {
			drinkSuggestion = neo4jClient.query("MATCH (" + name + ":User {name: '" + name + "'}),\n"
					+ "      (user: User)-[:WORKS_IN]->(:Job {name: '" + profession.name() + "'}),\n"
					+ "      (user)-[:HAS_SYMPTOM]-(symptom),\n" + "      (user)-[:DRINKS]-(drink)\n" + " \n"
					+ "Return drink.name").fetchAs(String.class).one().get();
			data.put("drink", drinkSuggestion);

			return new ResponseEntity<>(data, HttpStatus.OK);

		} else if (suggestionfor.name().equalsIgnoreCase(quote)) {
			quoteSuggestion = neo4jClient.query(
					"MATCH (bruce:User {name: '" + name + "'}),\n" + "      (user: User)-[:WORKS_IN]->(:Job {name: '"
							+ profession.name() + "'}),\n" + "      (user)-[:HAS_SYMPTOM]-(symptom),\n"
							+ "      (user)-[:READ_QUOTE]-(quote)\n" + "      \n" + "Return quote.name")
					.fetchAs(String.class).one().get();
			data.put("quote", quoteSuggestion);

			return new ResponseEntity<>(data, HttpStatus.OK);

		}

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	private Map<String, String> payload() {
		Map<String, String> payload = new HashMap<>();
		payload.put("covid", "47");
		payload.put("fever", "56");
		payload.put("cough", "90");
		payload.put("bodypains", "560");
		payload.put("headache", "321");
		payload.put("asthma", "12");

		return payload;
	}

	@GetMapping("/recoveredcount")
	private Map<String, String> recoveredStoriesBySymptom() {
		return payload();
	}

	@GetMapping("/recoveredlist")
	public List<UserBlog> getRecoveredStoriesListBySymptom(@RequestParam String hashTag) {

		List<UserBlog> listOfRecoverdStories = userBlogService.finByHashTag(hashTag);

		return listOfRecoverdStories;
	}

}
