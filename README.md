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
    ![JSON]("C:\Users\gians_ji5genm\OneDrive\Desktop\Progetto OOP\ProgettoEsameCocciaDiSabatinoGennaio2022-Main\ProgettoEsameCocciaDiSabatinoGennaio2022-Main\Immagine 2021-12-16 113408.png")
