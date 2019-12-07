import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

public class PredictCrimeInNextYear
{
    public static TreeMap<Integer,Integer> totalCrimesInEachYear;

    public PredictCrimeInNextYear()
    {
        int minYear = getMinYear();
        int maxYear = getMaxYear();
        totalCrimesInYear(minYear,maxYear);
    }

    public static int getMinYear()
    {
        int minYear = 0;
        String getMinYearQuery = "$select=min(year) as min_year";
        SendHttpRequest httpRequest = new SendHttpRequest(getMinYearQuery);
        Reader reader = httpRequest.sendHttpRequest();
        minYear = JsonParser.crimeCounter(reader,"min_year");
        return minYear;
    }
    public static int getMaxYear()
    {
        int maxYear = 0;
        String getMinYearQuery = "$select=max(year) as max_year";
        SendHttpRequest httpRequest = new SendHttpRequest(getMinYearQuery);
        Reader reader = httpRequest.sendHttpRequest();
        maxYear = JsonParser.crimeCounter(reader,"max_year");
        return maxYear;
    }

    public static void totalCrimesInYear(int minYear, int maxYear)
    {
        int count;
        TreeMap<Integer,Integer> totalCrimes= new TreeMap<>();
        for(int i =minYear;i<=maxYear;i++) {
            String totalCrimesQuery = "$select=count(id) as total_crime where year="+i;
            System.out.println("Sending HTTPRequest for finding count of crimes in Year :"+i);
            SendHttpRequest httpRequest = new SendHttpRequest(totalCrimesQuery);
            Reader reader = httpRequest.sendHttpRequest();
            count = JsonParser.crimeCounter(reader,"total_crime");
            totalCrimes.put(i,count);
        }
        totalCrimesInEachYear = totalCrimes;
    }

    public static TreeMap<Integer,Integer> getTotalCrimesInYears()
    {
        return totalCrimesInEachYear;
    }

    public static LinearRegression generateArraysForRegression()
    {
        PredictCrimeInNextYear predictCrimeInNextYear = new PredictCrimeInNextYear();
        double[] x = new double[predictCrimeInNextYear.getTotalCrimesInYears().size()];
        double[] y = new double[predictCrimeInNextYear.getTotalCrimesInYears().size()];
        int i=0;
        for(Map.Entry<Integer,Integer> pair : predictCrimeInNextYear.getTotalCrimesInYears().entrySet())
        {
            x[i] = pair.getKey();
            y[i] = pair.getValue();
            i++;
        }
        LinearRegression linearRegression = new LinearRegression(x,y);
        return linearRegression;
    }

    public static int predictTotalCrimes()
    {
        LinearRegression linearRegression = generateArraysForRegression();
        return (int) linearRegression.predict(2020);

    }

    public static double numberofCrimesInaDistrict(CharSequence districtNum, CharSequence yearNum) {

        int totalCount=0,districtCount=0;
        System.out.println("inside numberofCrimesInaDistrict method");
        System.out.println(districtNum +"  "+yearNum);
        String year= yearNum+"";
        String district= districtNum+"";
        String query = "$select=count(*) as total_count where year="+year;

        System.out.println("Sending HTTPRequest for finding count of crimes in Year :"+year);
        SendHttpRequest httpRequest = new SendHttpRequest(query);
        Reader reader = httpRequest.sendHttpRequest();
        totalCount = JsonParser.crimeCounter(reader,"total_count");

        System.out.println("Sending HTTPRequest for finding count of crimes in Year :"+year+" in district "+district);
         query = "$select=count(*) as total_count_district where year="+year+" and District=\""+district+"\"";
        httpRequest = new SendHttpRequest(query);
         reader = httpRequest.sendHttpRequest();

        districtCount = JsonParser.crimeCounter(reader,"total_count_district");
        System.out.println(districtCount);
        System.out.println(totalCount);
        return ((double)districtCount/totalCount) *100;

    }

//    public static void main(String[] args) {
//        PredictCrimeInNextYear predictCrimeInNextYear = new PredictCrimeInNextYear();
//        System.out.println(predictCrimeInNextYear.getTotalCrimesInYears());
//        double[] x = new double[predictCrimeInNextYear.getTotalCrimesInYears().size()];
//        double[] y = new double[predictCrimeInNextYear.getTotalCrimesInYears().size()];
//        int i=0;
//       for(Map.Entry<Integer,Integer> pair : predictCrimeInNextYear.getTotalCrimesInYears().entrySet())
//       {
//           x[i] = pair.getKey();
//           y[i] = pair.getValue();
//           i++;
//       }
//       for(int j=0;j<x.length;j++)
//       {
//           System.out.println("x: "+x[j]+","+"y: "+y[j]);
//       }
//       LinearRegression linearRegression = new LinearRegression(x,y);
//        System.out.println((int)linearRegression.predict(2020));
//    }

}