import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class die de logica bevat voor de datastructuren
 */
public class VirusLogica {
    //key = host id, value = hostname
    private static Map<Object, Object> map = new HashMap<Object, Object>();
    //key = host id, value = String(virus id)
    private static Map<Object, Object> virusInfo = new HashMap<Object, Object>();
    //key = classification, value = String(host id)
    private static Map<Object, Object> viralMap = new HashMap<Object, Object>();

    /**
     * open en lees het bestand door en zet het virusId, hostId, hostName en classification in een String.
     * vul de mappen map, virusInfo, value.
     * @param bestandnaam
     */
    public VirusLogica(String bestandnaam){
        BufferedReader infile;

        try {
            infile = new BufferedReader(new FileReader(bestandnaam));
            infile.readLine();

            ArrayList<Virus> virusID = new ArrayList<Virus>();
            ArrayList<Virus> sort = new ArrayList<Virus>();
            String file;
            String[] line;
            while((file = infile.readLine()) != null) {
                line = file.split("\t", -1);
                virusID.add(new Virus(line[0], line[7], line[8], line[2] + "\n"));
                map.put(line[7], line[8]);

            }

            //gemaakt met behulp van Thijs Weenink.
            for (Virus v : virusID ){
                if (virusInfo.containsKey(v.getTaxId())){
                    String value = (String) virusInfo.get(v.getTaxId());
                    value += ";" + v.getVirusId();
                    virusInfo.put(v.getTaxId(), value);
                }
                else{
                    virusInfo.put(v.getTaxId(), v.getVirusId());
                    virusInfo.remove("");
                }
            }

            for (Virus v : virusID ){
                String[] classi = v.getClassification().toString().split(";");
                classi = classi[1].split(",");

                Object classi1 = Arrays.asList(classi[0]).toString().replace("\n", "").replace("[", "").replace("]", "");
                if (viralMap.containsKey(classi1)){
                    String value1 = (String) viralMap.get(classi1);
                    value1 += ";" + v.getTaxId();
                    viralMap.put(classi1, value1);
                }
                else{
                    viralMap.put(classi1, v.getTaxId());
                }
            }
            infile.close();
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Bestand niet gevonden");
        } catch (IOException ioe) {
            System.out.println("Kan niet lezen in bestand");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Warning: ArrayIndexOutOfBoundsException");
        } catch (Exception e) {
            System.out.println("Onbekende fout: raadpleeg uw systeembeheerder");
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * @return map met host Id en hist naam.
     */
    public static Map<Object, Object> getMap() { return map; }

    /**
     * @return map met host Id en value Id's.
     */
    public static Map<Object, Object> getVirusInfo() { return virusInfo; }

    /**
     * @return map met classification en host Id's.
     */
    public static Map<Object, Object> getViralMap() { return viralMap; }
}
