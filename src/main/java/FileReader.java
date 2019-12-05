import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FileReader {

    //ID,Case Number,Date,Block,IUCR,Primary Type,Description,Location Description,Arrest,Domestic,Beat,District,Ward,Community Area,FBI Code,X Coordinate,Y Coordinate,Year,Updated On,Latitude,Longitude,Location
    String filename;

    public FileReader(String filename) {
        this.filename = filename;

    }

    public List<String[]> readFile() {
        long count = 0;
        List<String[]> list = null;

        Path path = new File(FileReader.class.getResource(filename).getFile()).toPath();

        try (Stream<String> lines = Files.lines(path)) {

            list = lines.map(s -> s.split(","))
                    .collect(toList());
            System.out.println("Number of Rows " + list.size());
            System.out.println("Number of Columns " + list.get(1).length);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void numberofCrimesInaDistrict(String districtNum)
    {

        List<String[]> list = readFile();

        List<String> ListDistrict = list.stream().map(s -> s[11])
                .collect(toList());

        long countOfCrimes = 0;

        countOfCrimes = ListDistrict.stream()
                .filter(s -> s.equals(districtNum))
                .count();

        System.out.println("Crime Count in " + districtNum + " is " + countOfCrimes);
    }
    public void CrimeTypeInCommunityArea(String area)
    {
        List<String[]> list = readFile();
        List<String> listOfArea = list.stream()
                .map(s -> s[13])
                .collect(toList());
        List<String> primaryType = list.stream()
                .map(s -> s[5])
                .collect(toList());
        List<String> description = list.stream()
                .map(s -> s[6])
                .collect(toList());
        List<String> location = list.stream()
                .map(s -> s[7])
                .collect(toList());
        List<String> stringList = new ArrayList<>();
        int j = 1;
        for(int i = 1; i < list.size(); i++)
        {
            if(listOfArea.get(i).equals(area))
            {
                //System.out.print("Primary crime Type: ");
                stringList.add(primaryType.get(i) + " " + "Description for the crime: " + stringList.add(description.get(j) + " /n" + "Location of the crime" + location.get(j)));
            }
        }
        System.out.println(stringList);
    }
    public void timeOfCrime()
    {
        List<String[]> list = readFile();
        List<String> listOfDates = list.stream()
                .map(s -> s[2])
                .collect(toList());
        System.out.println(listOfDates);
        Date d;
        String hours;
        for (String str : listOfDates)
        {
            //d = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").parse(str);
            String originalString = str;
            try{
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
                hours = new SimpleDateFormat("H:mm:ss").format(date); // 9:00
                System.out.println(hours);
            }
            catch (Exception e)
            {
                System.out.println("Cannot be parsed");
            }
            //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
            //hours = new SimpleDateFormat("H:mm:ss").format(date); // 9:00

        }
        //SimpleDateFormat parseFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");


    }
    public void arrestRate()//(String crimeType)
    {
        List<String[]> list = readFile();
        List<String> arrestType = list.stream()
                .map(s -> s[8])
                .collect(toList());
        //System.out.println(arrestType);

        List<String> typeOfCrime = list.stream()
                .map(s -> s[5])
                .collect(toList());
        //System.out.println(typeOfCrime);
        List<String> arrestedCrime = new ArrayList<>();
        int count = 0;
        int j = 1;
        for(int i = 1; i < arrestType.size(); i++)
        {
            if(arrestType.get(i).equals("true")) {
                arrestedCrime.add(typeOfCrime.get(j));
                j++;
                count++;
            }
            //System.out.println(arrestedCrime);
        }
        System.out.println("total arrested crimes are: " + count);
        List<String> crimes = arrestedCrime.stream()
                .distinct()
                .collect(toList());
        System.out.println("Types of crime arrested are: " + crimes);

    }


}
