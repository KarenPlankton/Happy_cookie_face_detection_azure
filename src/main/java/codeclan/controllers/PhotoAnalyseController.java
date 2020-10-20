package codeclan.controllers;

import codeclan.facedetector.FaceApi;
import codeclan.models.PhotoAnalyse;
import codeclan.models.StatisticsAnalysis;
import codeclan.repositories.PhotoAnalyseRepository;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class PhotoAnalyseController {


    @Autowired
    PhotoController photoController;

    @Autowired
    PhotoAnalyseRepository photoAnalyseRepository;

    @Autowired
    private Environment env;

    @Autowired
    private FaceApi faceApi;

    @GetMapping(value = "/photoanalysislist")
    public List<PhotoAnalyse> getAllPhotosAnalysys() {
        return photoAnalyseRepository.findAll();

    }
    @GetMapping(value = "/photoAnalysisdelete/{id}")
    public RedirectView deletePhotoAnalysis(@PathVariable Long id) {
            photoAnalyseRepository.deleteById(id);
        return new RedirectView("http://localhost:3100/statisticsRecords.html" );
    }

    @GetMapping(value = "/retrieveAnalysis/{id}")
    public String getPhotoAnalysis(@PathVariable Long id) {
        AtomicReference<String> value = new AtomicReference<>();
        String faceInfo = "";
        if (photoController.getPhoto(id).isPresent()) {
            photoController.getPhoto(id).ifPresent(
                    photo -> {
                        String photoName = photo.getImageName();
                        value.set(photoName);
                    });
        }
        try {

            String realpath = this.env.getProperty("imageDestination") + "/"
                    + value.get();

            faceInfo = this.faceApi.getFaceInfoOctet(realpath);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return faceInfo;
    }

    @GetMapping(value = "/saveAnalysisDeletePhoto/{id}")
    public RedirectView savePhotoAnalysis(@PathVariable Long id) {
        String analysis = this.getPhotoAnalysis(id);

        AtomicReference<String> value = new AtomicReference<>();
        if (photoController.getPhoto(id).isPresent()) {
            photoController.getPhoto(id).ifPresent(
                    photo -> {
                        String photoName = photo.getImageName();
                        value.set(photoName);
                    });
        }
        String fileName=value.get();

        try {
            JSONArray jsonA = new JSONArray(analysis);
            for(int n = 0; n < jsonA.length(); n++ ){
                JSONObject a = jsonA.getJSONObject(n);
                //System.out.println(a.get("faceAttributes").toString());
                JSONObject faceAttr= (JSONObject )a.get("faceAttributes");
                double age= (Double)faceAttr.get("age");
                String gender= (String)faceAttr.get("gender");
                String glasses = (String)faceAttr.get("glasses");
                JSONObject emotions= (JSONObject )faceAttr.get("emotion");
                double anger = (Double) emotions.get("anger");
                double contempt = (Double) emotions.get("contempt");
                double disgust = (Double) emotions.get("disgust");
                double fear = (Double) emotions.get("fear");
                double happiness = (Double) emotions.get("happiness");
                double neutral = (Double) emotions.get("neutral");
                double sadness = (Double) emotions.get("sadness");
                double surprise = (Double) emotions.get("surprise");
                JSONObject makeup= (JSONObject )faceAttr.get("makeup");
                boolean eyemakeup = (Boolean) makeup.get("eyeMakeup");
                boolean lipmakeup = (Boolean) makeup.get("lipMakeup");
                PhotoAnalyse photoanalysis= new PhotoAnalyse(fileName,age,anger,contempt,disgust,fear,
                        happiness,neutral,sadness,surprise,gender,eyemakeup,lipmakeup,glasses);
                System.out.println("Saving photo:"+ photoanalysis.toString());
                photoAnalyseRepository.save(photoanalysis);
                //deleting photo
                photoController.deletePhoto(id);

            }

        }catch (Exception e) {

            e.printStackTrace();
        }
        return new RedirectView("http://localhost:3100/photoanalysis.html" );
    }

    @GetMapping(value = "/statisticsAnalysisDetails")
    public ResponseEntity<String> savePhotoAnalysis()  {

        StatisticsAnalysis stats= new StatisticsAnalysis();
        String json="";
        try{
            //count all entries in db
            double allEntries = photoAnalyseRepository.findAllRecords();

            double men = photoAnalyseRepository.findAllMen();
            double getPercentageMale=men/allEntries;
            stats.setPercentageMale(getPercentageMale);

            double women = photoAnalyseRepository.findAllWomen();
            double getPercentageFemale=women/allEntries;
            stats.setPercentageFemale(getPercentageFemale);

            double age1030 = photoAnalyseRepository.findAge1030();
            double getPercentageAge1030=age1030/allEntries;
            stats.setPercentage1030(getPercentageAge1030);

            double age3050 = photoAnalyseRepository.findAge3050();
            double getPercentageAge3050=age3050/allEntries;
            stats.setPercentage3050(getPercentageAge3050);

            double age5070 = photoAnalyseRepository.findAge5070();
            double getPercentageAge5070=age5070/allEntries;
            stats.setPercentage5070(getPercentageAge5070);

            double age70plus = photoAnalyseRepository.findAge70plus();
            double getPercentageAge70plus=age70plus/allEntries;
            stats.setPercentage70plus(getPercentageAge70plus);

            double angry= photoAnalyseRepository.findAnger();
            double getPercentageOfAngry=angry/allEntries;
            stats.setPercentageAnger(getPercentageOfAngry);

            double contempt= photoAnalyseRepository.findContempt();
            double getPercentageOfContempt=contempt/allEntries;
            stats.setPercentageContempt(getPercentageOfContempt);

            double disgust= photoAnalyseRepository.findDisgust();
            double getPercentageOfDisgust=disgust/allEntries;
            stats.setPercentageDisgust(getPercentageOfDisgust);

            double fear= photoAnalyseRepository.findFear();
            double getPercentageOfFear=fear/allEntries;
            stats.setPercentagefear(getPercentageOfFear);

            double happiness= photoAnalyseRepository.findHappiness();
            double getPercentageOfHappiness=happiness/allEntries;
            stats.setPercentageHappiness(getPercentageOfHappiness);
            //System.out.println("Happiness"+happiness+"percent"+getPercentageOfHappiness);

            double neutral= photoAnalyseRepository.findNeutral();
            double getPercentageOfNeutral=neutral/allEntries;
            stats.setPercentageNeutral(getPercentageOfNeutral);

            double sadness= photoAnalyseRepository.findSadness();
            double getPercentageOfSadness=sadness/allEntries;
            stats.setPercentageSadness(getPercentageOfSadness);

            double surprise= photoAnalyseRepository.findSurprise();
            double getPercentageOfSurprise=surprise/allEntries;
            stats.setPercentageSurprise(getPercentageOfSurprise);

            json = new Gson().toJson(stats);
        }catch (Exception e) {

            e.printStackTrace();
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        //System.out.println("Statistic Analysis: "+json);
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }
}
