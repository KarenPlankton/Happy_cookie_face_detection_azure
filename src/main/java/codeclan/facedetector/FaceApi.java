package codeclan.facedetector;


import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

@Service
public class FaceApi {

    @Value("${subscriptionKey}")
    private String subscriptionKey;

    public String getFaceInfoOctet(String bytes) {


        String responseStr="";
        HttpClient httpclient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(this.getURI())
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/octet-stream")
                    .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(bytes)))
                    .build();
            HttpResponse response =
                    httpclient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

            responseStr=response.body().toString();
            //System.out.println(response.statusCode());
            //System.out.println(response.body());

        }catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    private URI getURI() throws URISyntaxException {

        String uriBase="https://codeclan-app.cognitiveservices.azure.com/face/v1.0/detect";
        String faceAttributes = "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";
        URIBuilder builder = new URIBuilder(uriBase);
        builder.setParameter("overload", "stream");
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceLandmarks", "false");
        builder.setParameter("returnFaceAttributes", faceAttributes);
        return builder.build();
    }

}
