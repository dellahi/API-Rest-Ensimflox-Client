package modelexo3;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestTemplateBasicAuth {
    private final RestTemplateBuilder builder;
    private final RestTemplate restTemplate;

    public RestTemplateBasicAuth(String mail, String token) {
        builder = new RestTemplateBuilder();
        restTemplate = builder.basicAuthorization(mail, token).build();
        restTemplate.getInterceptors().add((httpRequest, bytes, clientHttpRequestExecution) -> {
            httpRequest.getHeaders().set("User-Agent", "toto");
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        });
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
