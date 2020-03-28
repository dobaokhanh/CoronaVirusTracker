package corona.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import corona.entity.LocationStat;

/**
 * @author Do Bao Khanh
 *
 */
@Service
public class CoronaVirusDataService {
	private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private List<LocationStat> locationStats = new ArrayList<>();
	
	/** Fetch data in CSV format from dataset in github
	 * Using HTTP to fetch data
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStat> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStat locationStat = new LocationStat();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int lastestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevFromLastestCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLastestReportedCases(lastestCases);
			locationStat.setDifferentFromPreviousDay(lastestCases - prevFromLastestCases);
			newStats.add(locationStat);
		}
		this.locationStats = newStats;
	}

	public List<LocationStat> getLocationStats() {
		return locationStats;
	}

	public void setLocationStats(List<LocationStat> locationStats) {
		this.locationStats = locationStats;
	}
	
}
