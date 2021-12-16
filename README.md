# ProgettoEsameCocciaDiSabatinoGennaio2022
Il nostro applicativo è un RESTful Web Service, ovvero un sistema software che, comunicando tramite il controllo HTTP, è in grado di mettersi al servizio di un Client, che può 
essere un'applicazione, un sito web o Postman, così da consentire agli utenti che vi si collegano di usufruire delle azioni che mette a disposizione.

Il progetto implementa un servizio meteo che, raccogliendo le informazioni meteo generali su due città, restituisce successivamente, delle statistiche calcolate sulla pressione,
quali pressione minima, massima, differenza tra le due e la media in un determinato periodo di tempo scelto, così da poter confrontare i diversi valori ottenuti nelle due città
precedentemente scelte.

A scopo dimostrativo, durante il periodo di sviluppo e testing dell'applicazione sono stati raccolti i dati di varie città, tra cui Milano, Roma, Londra, Parigi e New York. 
Questi dati sono stati analizzati e salvati in file JSON locali per poi essere stati utilizzati per calcolare le varie statistiche richieste.

La chiamata API di OpenWeather utilizzata nel nostro progetto è la seguente:                                                                                                  
http://api.openweathermap.org/data/2.5/weather?q={cityname}&appid={APIkey}

  - cityname è il nome della città scelta
  - APIkey è la chiave di accesso al servizio
    - Ad esempio richiamando attraverso il metodo HTTP GET la seguente API:                                                                                                     
    http://api.openweathermap.org/data/2.5/weather?q=Milan&appid=10b2f29f8e21bd179b27ff96923bca4a otteniamo il seguente JSON:
        
    ```
    {
    "coord": {
        "lon": 9.1895,
        "lat": 45.4643
    },
    "weather": [
        {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 280.66,
        "feels_like": 280.16,
        "temp_min": 276.36,
        "temp_max": 285.87,
        "pressure": 1029,
        "humidity": 82
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.34,
        "deg": 240,
        "gust": 1.79
    },
    "clouds": {
        "all": 19
    },
    "dt": 1639650695,
    "sys": {
        "type": 2,
        "id": 2010245,
        "country": "IT",
        "sunrise": 1639637832,
        "sunset": 1639669242
    },
    "timezone": 3600,
    "id": 3173435,
    "name": "Milan",
    "cod": 200
}
```
