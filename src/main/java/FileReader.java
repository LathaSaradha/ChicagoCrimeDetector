import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader
{

    //ID,Case Number,Date,Block,IUCR,Primary Type,Description,Location Description,Arrest,Domestic,Beat,District,Ward,Community Area,FBI Code,X Coordinate,Y Coordinate,Year,Updated On,Latitude,Longitude,Location
    String filename;
    public FileReader(String filename)
    {this.filename=filename;

    }

    public void readFile()
    {
        long count=0;

        Path path=new File(FileReader.class.getResource(filename).getFile()).toPath();

        try(Stream<String> lines= Files.lines(path))
        {

            lines
                    .map(s->s.split(","))
                   // .map()
                   .forEach(System.out::println);

           // System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
