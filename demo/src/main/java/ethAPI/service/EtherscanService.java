package ethAPI.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EtherscanService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public String getBalance(String address) {
        String url = String.format("%s?module=account&action=balance&address=%s&tag=latest&apikey=%s",
                apiUrl,
                address,
                apiKey);
        String response =  restTemplate.getForObject(url, String.class);

        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
        String weiBalance = json.get("result").getAsString();

        BigDecimal balanceInEth = new BigDecimal(weiBalance).divide(
                new BigDecimal("1000000000000000000"),
                8,
                RoundingMode.HALF_UP);

        return balanceInEth + " ETH";
    }

}
