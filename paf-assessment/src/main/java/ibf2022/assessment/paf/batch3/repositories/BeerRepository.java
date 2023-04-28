package ibf2022.assessment.paf.batch3.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import static ibf2022.assessment.paf.batch3.repositories.DBQueries.*;
@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate jdbc;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		SqlRowSet rs = jdbc.queryForRowSet(SELECT_STYLES);

		List<Style> styles = new LinkedList<>();

		while(rs.next()) {
			Style s = new Style();
			s.setStyleId(rs.getInt("style_id"));
			s.setName(rs.getString("name"));
			s.setBeerCount(rs.getInt("beer_count"));
			styles.add(s);
		}

		return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(Integer styleId) {
		// TODO: Task 3
		SqlRowSet rs = jdbc.queryForRowSet(SELECT_BREWERIES_BY_BEER_STYLE, styleId);

		List<Beer> breweries = new LinkedList<>();

		while(rs.next()) {
			Beer b  = new Beer();
			b.setBeerId(rs.getInt("beer_id"));
			b.setBeerName(rs.getString("beer_name"));
			b.setBeerDescription(rs.getString("beer_description"));
			b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName(rs.getString("brewery_name"));
			breweries.add(b);
		}
		
		return breweries;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */) {
		// TODO: Task 4

		return Optional.empty();
	}
}
