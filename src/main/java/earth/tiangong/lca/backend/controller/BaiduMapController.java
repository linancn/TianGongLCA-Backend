package earth.tiangong.lca.backend.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> 0abea054ba077c9680e0d91346a76e65da945d45
=======
import java.util.Map;
>>>>>>> 0abea054ba077c9680e0d91346a76e65da945d45

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/bmap")
public class BaiduMapController {
    @GetMapping("/geocoding/{lat}/{lng}")
    public ResponseEntity<String> getGeocoding(@PathVariable Double lat, @PathVariable Double lng) {
        String httpUrl = "https://api.map.baidu.com/reverse_geocoding/v3/?ak=ubgUmyXLYYQ0QyVUTvGZupEDaobsM6gb&output=json&coordtype=wgs84ll&location=" + lat + "," + lng + "";
        return ok(sendRequest(httpUrl));
    }

    @GetMapping("/direction/{originLat}/{originLng}/{destinationLat}/{destinationLng}")
    public ResponseEntity<String> getDirection(@PathVariable Double originLat, @PathVariable Double originLng, @PathVariable Double destinationLat, @PathVariable Double destinationLng) {
        String httpUrl = "https://api.map.baidu.com/direction/v2/driving?ak=5dvC078T5xmV20ZOPlYr5Xwk49kkmqfL&origin=" + originLat + "," + originLng + "&destination=" + destinationLat + "," + destinationLng + "";
        return ok(sendRequest(httpUrl));
    }

    private String sendRequest(String httpUrl) {
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
}
