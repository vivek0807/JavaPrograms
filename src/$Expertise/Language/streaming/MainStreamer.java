package $Expertise.Language.streaming;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class GetResponseFromEndpoints{

public void getExchanges(){
    try {
        HttpRequest httpRequest= HttpRequest.newBuilder()
                .GET().uri(URI.create("https://api.coingecko.com/api/v3/onchain/networks/eth/dexes/sushiswap/pools")).
                header("Accept","Application/json")
                .header("x-cg-demo-api-key","CG-gBN1WsDyU5rkXTfemZFPRt68").build();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpResponse<String> response=httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    catch (Exception e){
        System.out.println(e);
    }

}
}
public class MainStreamer {
    public static void main(String[] args) {
        GetResponseFromEndpoints getResponseFromEndpoints= new GetResponseFromEndpoints();
        getResponseFromEndpoints.getExchanges();
    }
}
