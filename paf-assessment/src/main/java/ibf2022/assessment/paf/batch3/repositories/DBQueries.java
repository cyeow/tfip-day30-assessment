package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {

        public static final String SELECT_STYLES = """
                        SELECT
                        styles.id as style_id,
                        styles.style_name as name,
                        count(beers.id) as beer_count
                        FROM styles
                        join beers on styles.id = beers.style_id
                        group by style_id
                        order by beer_count DESC;
                            """;

        public static final String SELECT_BREWERIES_BY_BEER_STYLE = """
                        SELECT
                        beers.id AS beer_id,
                        beers.name AS beer_name,
                        beers.descript AS beer_description,
                        breweries.id AS brewery_id,
                        breweries.name AS brewery_name
                        FROM beers
                        JOIN breweries ON beers.brewery_id = breweries.id
                        JOIN styles ON beers.style_id = styles.id
                        WHERE styles.id = ? AND styles.style_name = ?
                        ORDER BY beer_name ASC;
                                """;

        public static final String SELECT_BEERS_BY_BREWERY = """
                        SELECT
                        breweries.id as brewery_id,
                        breweries.name as brewery_name,
                        breweries.address1 as address1,
                        breweries.address2 as address2,
                        breweries.city as city,
                        breweries.phone as phone,
                        breweries.website as website,
                        breweries.descript as description,
                        beers.id as beer_id,
                        beers.name as beer_name,
                        beers.descript as beer_description
                        FROM breweries
                        JOIN beers ON beers.brewery_id = breweries.id
                        WHERE breweries.id = ?
                        ORDER BY beer_name ASC;
                            """;
}
