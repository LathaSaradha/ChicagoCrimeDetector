import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PredictCrimeInNextYear implements Wait {
    public static TreeMap<Integer,Integer> totalCrimesInEachYear;

    public PredictCrimeInNextYear()
    {
        int minYear = getMinYear();
        int maxYear = getMaxYear();
        totalCrimesInYear(minYear,maxYear);
    }

    public static int getMinYear()
    {
        int minYear;
        String getMinYearQuery = "$select=min(year) as min_year";
        SendHttpRequest httpRequest = new SendHttpRequest(getMinYearQuery);
        Reader reader = httpRequest.sendHttpRequest();
        minYear = JsonParser.crimeCounter(reader,"min_year");
        return minYear;
    }
    public static int getMaxYear()
    {
        int maxYear;
        String getMinYearQuery = "$select=max(year) as max_year";
        SendHttpRequest httpRequest = new SendHttpRequest(getMinYearQuery);
        Reader reader = httpRequest.sendHttpRequest();
        maxYear = JsonParser.crimeCounter(reader,"max_year");
        return maxYear;
    }

    public void totalCrimesInYear(int minYear, int maxYear)
    {
        TreeMap<Integer,Integer> totalCrimes= new TreeMap<>();
        ExecutorService service= null;
        try {
            service = Executors.newFixedThreadPool(50);
            for (int i = minYear; i <= maxYear; i++) {
                String totalCrimesQuery = "$select=count(id) as total_crime where year=" + i;
                int finalI = i;
                service.submit(()->{
                    SendHttpRequest httpRequest = new SendHttpRequest(totalCrimesQuery);
                    Reader reader = httpRequest.sendHttpRequest();
                    int count = JsonParser.crimeCounter(reader, "total_crime");

               totalCrimes.put(finalI, count);});
            }
            totalCrimesInEachYear = totalCrimes;
        } finally {
            wait(service, 4);
        }


    }

    public static TreeMap<Integer,Integer> getTotalCrimesInYears()
    {
        return totalCrimesInEachYear;
    }

    public static LinearRegression generateArraysForRegression()
    {
        double[] x = new double[getTotalCrimesInYears().size()];
        double[] y = new double[getTotalCrimesInYears().size()];
        int i=0;
        for(Map.Entry<Integer,Integer> pair : getTotalCrimesInYears().entrySet())
        {
            x[i] = pair.getKey();
            y[i] = pair.getValue();
            i++;
        }
        return new LinearRegression(x,y);
    }

    public static int predictTotalCrimes()
    {
        LinearRegression linearRegression = generateArraysForRegression();
        return (int) linearRegression.predict(2020);

    }

    public static double numberofCrimesInaDistrict(CharSequence districtNum, CharSequence yearNum) {

        int totalCount,districtCount;
        System.out.println(districtNum +"  "+yearNum);
        String year= yearNum+"";
        String district= districtNum+"";
        String query = "$select=count(*) as total_count where year="+year;

        totalCount = getTotalCount(query);
        districtCount = getDistrictCount(year, district);
        System.out.println(districtCount);
        System.out.println(totalCount);
        return ((double)districtCount/totalCount) *100;

    }

    public static int getDistrictCount(String year, String district) {
        String query;
        int districtCount;
        SendHttpRequest httpRequest;
        Reader reader;

        query = "$select=count(*) as total_count_district where year="+year+" and District=\""+district+"\"";
        httpRequest = new SendHttpRequest(query);
        reader = httpRequest.sendHttpRequest();

        districtCount = JsonParser.crimeCounter(reader,"total_count_district");
        return districtCount;
    }

    public static int getTotalCount(String query) {
        int totalCount;
        SendHttpRequest httpRequest = new SendHttpRequest(query);
        Reader reader = httpRequest.sendHttpRequest();
        totalCount = JsonParser.crimeCounter(reader,"total_count");
        return totalCount;
    }



    @Override
    public void wait(ExecutorService service, int seconds) {
        try {
            if (service != null) {
                service.awaitTermination(seconds, TimeUnit.SECONDS);

                if (service.isTerminated()) {
                    //System.out.println(totalCrimesInEachYear);
                    System.out.println("Terminated");

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
