package io.rc.simulacion.pe.client.api;

import io.rc.simulacion.pe.client.ApiClient;
import io.rc.simulacion.pe.client.api.ApiTest;
import io.rc.simulacion.pe.client.ApiResponse;
import io.rc.simulacion.pe.client.api.ReporteCreditoPeruSimulacionApi;
import io.rc.simulacion.pe.client.ApiException;
import io.rc.simulacion.pe.client.model.Peticion;
import io.rc.simulacion.pe.client.model.Respuesta;
import okhttp3.OkHttpClient;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Before;
import java.util.concurrent.TimeUnit;

public class ApiTest {
	
	private Logger logger = LoggerFactory.getLogger(ApiTest.class.getName());
	private final ReporteCreditoPeruSimulacionApi api = new ReporteCreditoPeruSimulacionApi();
	private ApiClient apiClient = null;  
	private String xApiKey = "your_api_key";
    
	@Before()
	public void setUp() {
		this.apiClient = api.getApiClient();
		this.apiClient.setBasePath("the_url");
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
		apiClient.setHttpClient(okHttpClient);
	}
	
    @Test
    public void getRCTest() throws ApiException {
        Peticion request = new Peticion();
        Integer estatusOK = 200;
        Integer estatusNoContent = 204;
        
        try {
        	
        	request.setFolio("10000200");
        	request.setNumeroDocumento("67544489");
        	request.setTipoDocumento("1");
        	
        	ApiResponse<?> response = api.getGenericRC(xApiKey, request);
  
        	Assert.assertTrue(estatusOK.equals(response.getStatusCode()));
        	
        	if(estatusOK.equals(response.getStatusCode())) {
        		Respuesta responseOK = (Respuesta) response.getData();
        		logger.info(responseOK.toString());
        	}
        	
        }catch (ApiException e) {
        	if(!estatusNoContent.equals(e.getCode())) {
        		logger.info(e.getResponseBody());
        	}
        	Assert.assertTrue(estatusOK.equals(e.getCode()));
		}
        
    }
    
}
