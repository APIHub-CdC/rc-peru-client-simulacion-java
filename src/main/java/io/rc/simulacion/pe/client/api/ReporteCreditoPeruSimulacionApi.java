package io.rc.simulacion.pe.client.api;

import io.rc.simulacion.pe.client.ApiClient;
import io.rc.simulacion.pe.client.ApiException;
import io.rc.simulacion.pe.client.ApiResponse;
import io.rc.simulacion.pe.client.Configuration;
import io.rc.simulacion.pe.client.Pair;
import io.rc.simulacion.pe.client.ProgressRequestBody;
import io.rc.simulacion.pe.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import io.rc.simulacion.pe.client.model.Peticion;
import io.rc.simulacion.pe.client.model.Respuesta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteCreditoPeruSimulacionApi {
    private ApiClient apiClient;
    public ReporteCreditoPeruSimulacionApi() {
        this(Configuration.getDefaultApiClient());
    }
    public ReporteCreditoPeruSimulacionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
    public ApiClient getApiClient() {
        return apiClient;
    }
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
    
    public okhttp3.Call getRCCall(String xApiKey, Peticion request, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = request;
        String localVarPath = "";
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xApiKey != null)
        localVarHeaderParams.put("x-api-key", apiClient.parameterToString(xApiKey));
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);
        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);
        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
                @Override
                public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
                    okhttp3.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }
        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    private okhttp3.Call getRCValidateBeforeCall(String xApiKey, Peticion request, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        if (xApiKey == null) {
            throw new ApiException("Missing the required parameter 'xApiKey' when calling getRC(Async)");
        }
        if (request == null) {
            throw new ApiException("Missing the required parameter 'request' when calling getRC(Async)");
        }
        
        okhttp3.Call call = getRCCall(xApiKey, request, progressListener, progressRequestListener);
        return call;
    }
    
    public Respuesta getRC(String xApiKey, Peticion request) throws ApiException {
        ApiResponse<Respuesta> resp = getRCWithHttpInfo(xApiKey, request);
        return resp.getData();
    }
    
    public ApiResponse<?> getGenericRC(String xApiKey, Peticion request) throws ApiException {
        ApiResponse<?> resp = getRCWithHttpInfo(xApiKey, request);
        return resp;
    }
    
    public ApiResponse<Respuesta> getRCWithHttpInfo(String xApiKey, Peticion request) throws ApiException {
        okhttp3.Call call = getRCValidateBeforeCall(xApiKey, request, null, null);
        Type localVarReturnType = new TypeToken<Respuesta>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

}
