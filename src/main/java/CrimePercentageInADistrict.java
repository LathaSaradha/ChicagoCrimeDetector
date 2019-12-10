import java.io.Reader;

public class CrimePercentageInADistrict {
    public static double numberOfCrimesInADistrict(CharSequence districtNum, CharSequence yearNum) {

        int totalCount, districtCount;
        String year = yearNum + "";
        String district = districtNum + "";
        String query = "$select=count(*) as total_count where year=" + year;

        totalCount = getTotalCount(query);
        districtCount = getDistrictCount(year, district);
        return ((double) districtCount / totalCount) * 100;

    }

    public static int getDistrictCount(String year, String district) {
        String query;
        int districtCount;
        SendHttpRequest httpRequest;
        Reader reader;

        query = "$select=count(*) as total_count_district where year=" + year + " and District=\"" + district + "\"";
        httpRequest = new SendHttpRequest(query);
        reader = httpRequest.sendHttpRequest();

        districtCount = JsonParser.crimeCounter(reader, "total_count_district");
        return districtCount;
    }

    public static int getTotalCount(String query) {
        int totalCount;
        SendHttpRequest httpRequest = new SendHttpRequest(query);
        Reader reader = httpRequest.sendHttpRequest();
        totalCount = JsonParser.crimeCounter(reader, "total_count");
        return totalCount;
    }

}
