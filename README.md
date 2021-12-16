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

Dove i campi riportati precedentemente hanno i seguenti significati:
- ***coord***
  - *"lat"* indicates the city's laitude
  - *"lon"* indicates the city's longitude
- ***weather***
  - *"id"* weather condition id
  - *"main"* weather parameters
  - *"description"* weather condition susch as rain, sun,...
  - *"icon"* weather icon id rappresentation
- ***base*** other parameter
- ***main***
  -*"temp"* indicates the temperature                                                                                                                                   
  -*"feels-like"* temperature percepetid                                                                                                                                         
  -*"temp_min"* minimum temperature's value                                                                                                                                   
  -*"temp_max"* maximum temperature's value                                                                                                                                      
  -*"pressure"* pressure's value                                                                                                                                            
  -*"humidity"* humidity's value                                                                                                                                                
- ***visibility*** the percentage of visibility
- ***wind*** contains some wind's values We've not considered for the project
- ***clouds*** contains cloud's values We've not considered
- ***dt*** indicates the time passed since 1 Jenuary 1970(Epoch) express in seconds
- ***sys*** We have considered only the following value
  -*"country"* indicates the city's country
-***id*** indicates the city's id (identification code)
-***name*** indicates the city's name

# Utilità del progetto
Questo progetto interessa tutti coloro che hanno bisogno di conoscere i valori delle pressioni di una certà città in un certo intervallo di tempo, ad esempio Società che 
operano nell'ambito portuale, marittimo ecc.

# Applicazione
## Avvio
Una volta avviata l'applicazione, vengono scelte le due città di cui si vuole comparare le statistiche e il range orario, fatto ciò tramite una chiamata ad un metodo GET di
Postman, in cui viene passato come parametro il nome della città, viene salvato in locale il file delle due città, identico a quello precedentemente mostrato. Fatto ciò, 
vi è la possibilità di effettuare un'altra chiamata tramite metodo GET per comparare le statistiche che vengono elaborate e poi mostrate, questo passando come parametri nella
chiamata, i nomi delle due città insimee al rispettivo istante iniziale in cui si vuole iniziare l'elaborazione dei dati e, il rispettivo istante finale.
## Rotte disponibili
|  Rotta  |  Metodo |       Funzione                                     |
|---------|---------|----------------------------------------------------|
|/save    |GET      |Salva il file JSON della città restituito da Postman|
|/compare |GET      |Compara le statistiche calcolate delle due città    |
|/getP    |GET      |Restituisce tutti i valori delle pressioni registrate per quella città|

## Statistiche
Le statistiche riguardanti tutti i valori richiesti delle pressioni, come il valore minimo, massimo, media o differenza vengono calcolati dopo essere stati letti da un file
JSON che ci siamo creati localmente dopo aver ottenuto il file JSON principale restituito da Postman, ovvero come quello sù riportato. Questo file è stato creato utilizzando
solo due caratteristiche, il tempo e il valore della pressione. Per crearlo abbiamo utilizzato la libreria "Timer" di Java la quale, periodicamente, rinnova la chiamata del
metodo, che ogni volta si fa restituire i valori da Postman, e salva solo quei valori interessati in questo file locale che viene utilizzato per il calcolo delle statistiche.

# Strumenti utilizzati
Per sviluppare questo applicativo abbiamo utilizzato i seguenti strumenti:
- Il framework ***Spring*** includedo i moduli:
  - *Spring boot*
  - *Spring web*
- La libreria ***JSON.simple*** per la conversione, lettura e scrittura di JSONObject 
- ***Javadoc*** per generare la documentazione del codice sorgente
- L'ambiente di sviluppo ***Eclipse*** per lo sviluppo 
- ***GIT*** e ***GitHub*** per il versioning del codice tra i vari componenti del gruppo
- L'applicativo ***Postman*** per richiamare le varie API utilizzate 
- Il framework ***JUnit5*** per lo unit testing

# Autori
- *Walter Di Sabatino* (referente)
- *Giansimone Coccia*
