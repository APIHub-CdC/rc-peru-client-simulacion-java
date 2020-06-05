# rc-peru-client-simulacion-java

Reporte de Crédito de personas naturales y jurídicas que considera fuentes públicas y privadas.

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/io/rc/simulacion/pe/client/api/ApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la url. Modificar la URL ***('the_url')***, como se muestra en el siguiente fragmento de código:

```java
@Before()
public void setUp() {
	this.apiClient = api.getApiClient();
	this.apiClient.setBasePath("the_url");
	OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
	apiClient.setHttpClient(okHttpClient);
}
```

En el archivo **ApiTest.java**, que se encuentra en ***src/test/java/io/rc/simulacion/pe/client/api/ApiTest.java*** se deberá modificar el siguiente fragmento de código con los datos correspondientes:

```java
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
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```
