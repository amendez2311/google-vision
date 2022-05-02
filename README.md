# Proyecto IA - Reconocimiento de placas de carros con Google Vision
Este repositorio contiene el proyecto desarrollado para el curso de Inteligencia Artificial de UPC, el cual comprende una aplicación web Java que carga imagenes de placas de carro y por medio del API de Google Vision se procesa para reconocer el texto de la placa y el reconocimiento del modelo del carro.
Asimismo, alguna otra caracteristica resaltante que pueda identificar el api.

### Integrantes:

  - Anthony Ambrosio
  - Andres Mendez
  - Eros Quispe
  - Brayan Leon
  - Sergio

Repositorio: https://github.com/amendez2311/google-vision.git

Prueba desde aplicación
![Pagina_IA_1](https://user-images.githubusercontent.com/11338076/166185010-334b25c6-198d-4021-acb6-23415642a61a.png)
![Pagina_IA_2](https://user-images.githubusercontent.com/11338076/166185318-f14d6616-118b-48d4-a439-c4b4d9b08862.png)
![Pagina_IA_3](https://user-images.githubusercontent.com/11338076/166185376-29d0064e-a6d8-408b-89c4-d84a615274f2.png)
Resultado:

![Pagina_IA_5](https://user-images.githubusercontent.com/11338076/166185448-a69fe415-f361-44f4-bee4-613331143140.png)
![Pagina_IA_4](https://user-images.githubusercontent.com/11338076/166185418-331d0168-2d55-4569-95bb-d0494de48ad1.png)

### Desarrollo

1. CREAR PROYECTO EN GOOGLE CLOUD PLATFORM (GCP)
```sh
$ google-vision-java-car-plates
```

2. ACTIVAR EL API POR MEDIO DE LA CONSOLA DE GCP:
```sh
$ gcloud auth list
$ gcloud config list project
$ gcloud services enable vision.googleapis.com
```

3. CREAR CUENTA DE SERVICIO EN GCP
```sh
$ service-google-vision-java
```
4. CREAR KEY FILE Y DESCARGAR EN FORMATO JSON. CONFIGURARLO COMO VARIABLE DE ENTORNO.
5. ACTIVAR BILLING PARA USAR API DE GOOGLE
3. CREAR CUENTA DE SERVICIO EN GCP
https://console.developers.google.com/billing/enable?project=XXXXXXX
