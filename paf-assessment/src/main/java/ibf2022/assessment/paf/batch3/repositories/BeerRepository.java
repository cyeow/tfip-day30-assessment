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

		while (rs.next()) {
			Style s = new Style();
			s.setStyleId(rs.getInt("style_id"));
			s.setName(rs.getString("name"));
			s.setBeerCount(rs.getInt("beer_count"));
			styles.add(s);
		}

		return styles;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(Integer styleId, String styleName) {
		// TODO: Task 3
		// both styleId and styleName have to match
		SqlRowSet rs = jdbc.queryForRowSet(SELECT_BREWERIES_BY_BEER_STYLE, styleId, styleName);

		List<Beer> breweries = new LinkedList<>();

		while (rs.next()) {
			breweries.add(createBeer(rs));
		}

		return breweries;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(Integer breweryId) {
		// TODO: Task 4
		SqlRowSet rs = jdbc.queryForRowSet(SELECT_BEERS_BY_BREWERY, breweryId);

		Brewery b = new Brewery();
		b.setBreweryId(breweryId);

		if (rs.next()) {
			// set brewery details
			b.setName(rs.getString("brewery_name"));
			b.setAddress1(rs.getString("address1"));
			b.setAddress2(rs.getString("address2"));
			b.setCity(rs.getString("city"));
			b.setPhone(rs.getString("phone"));
			b.setWebsite(rs.getString("website"));
			b.setDescription(rs.getString("description"));
		} else {
			return Optional.empty();
		}

		List<Beer> beers = new LinkedList<>();

		do {
			beers.add(createBeer(rs));
		} while (rs.next());

		b.setBeers(beers);

		return Optional.of(b);
	}

	private Beer createBeer(SqlRowSet rs) {
		Beer b = new Beer();
		b.setBeerId(rs.getInt("beer_id"));
		b.setBeerName(rs.getString("beer_name"));
		b.setBeerDescription(rs.getString("beer_description"));
		b.setBreweryId(rs.getInt("brewery_id"));
		b.setBreweryName(rs.getString("brewery_name"));
		return b;
	}
}
