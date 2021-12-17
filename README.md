# ProgettoEsameCocciaDiSabatinoGennaio2022
Il nostro applicativo è un RESTful Web Service, ovvero un sistema software che, comunicando tramite il controllo HTTP, è in grado di mettersi al servizio di un Client, che può 
essere un'applicazione, un sito web o Postman, così da consentire agli utenti che vi si collegano di usufruire delle azioni che mette a disposizione.

Il progetto implementa un servizio meteo che, raccogliendo le informazioni meteo generali su due città, restituisce successivamente, delle statistiche calcolate sulla pressione,
quali pressione minima, massima, differenza tra le due e la media, in un determinato periodo di tempo scelto, così da poter confrontare i diversi valori ottenuti nelle due città
precedentemente scelte.

A scopo dimostrativo, durante il periodo di sviluppo e testing dell'applicazione sono stati raccolti i dati di varie città, tra cui Milano, Roma, Londra, Parigi e New York. 
Questi dati sono stati analizzati e salvati in file JSON locali per poi essere stati utilizzati per calcolare le varie statistiche richieste.

La chiamata API di OpenWeather utilizzata nel nostro progetto è la seguente:                                                                                                  
http://api.openweathermap.org/data/2.5/weather?q={cityname}&appid={APIkey}

  - *cityname* è il nome della città scelta
  - *APIkey* è la chiave di accesso al servizio
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
    "cod": 200}

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
- ***dt*** indicates the time passed since 1 Jenuary 1970(Epoch) expressed in seconds
- ***sys*** We have considered only the following value                                                                                                                         
  -*"country"* indicates the city's country  
- ***id*** indicates the city's id (identification code)                                                                                                                       
- ***name*** indicates the city's name                                                                                                                                           

# Utilità del progetto
Questo progetto interessa tutti coloro che hanno bisogno di conoscere i valori delle pressioni di alcune città in un certo intervallo di tempo, ad esempio Società che 
operano nell'ambito portuale, marittimo ecc.

# Applicazione
## Funzionamento
1. *Inizio*                                                                                                                                                                 
Per prima cosa, bisogna scegliere due diverse città di cui si vogliano calcolare le statistiche per poi confrontarle, per semplicità abbiamo considerato solo cinque città di cui abbiamo salavto i dati, tra cui Milano, Roma, Londra, Parigi e New York, di cui abbiamo accumulato per ciascuno, per alcuni giorni, i valori restituiti. Fatto ciò è possibile controllare gli attuali valori meteo di quella città tramite la chiamata "GET /getCity", che restituisci tutti i valori che abbiamo settato come fondamentali per l'applicativo, come il nome della città, il nome della nazione e via dicendo, compresi anche tutti i possibili valori di pressione.
2. *Salvataggio*                                                                                                                                                            
Scelte le due città, queste vengono "sottoposte" ad una fase di salvataggio in cui, tramite la chiamata "GET /save", inizia un processo di salvataggio dei
dati da Postman su un file locale. Questo salvataggio viene eseguito ogni ora, utilizzando la libreria "Timer" di Java. Inoltre tra i vari valori che abbiamo deciso di
salvare, abbiamo considerato il tempo, indicato come 'dt' nel file JSON riportato precedentemente e il valore della pressione 'pressure', così da semplificare poi la successiva lettura del file locale per il calcolo delle statistiche. Un esempio del file che viene salvato in locale:                                                                                                  

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/fileSaved.png)

3. *Lettura e calcolo statistiche*                                                                                                                                             
Dopo aver effettuato il salvataggio delle singole città, il file locale viene sottoposto ad una lettura che acquisice i due diversi valori, tempo e pressione. Questi 
vengono utilizzati per calcolare le varie statistiche, nel nostro caso la pressione minima e massima di ogni città , la media tra tutte le pressioni e la differenza tra la pressione  massima e la pressione minima.
4. *Compare*                                                                                                                                                                
Avute le statistiche per ogni città, è possibile effettuare una chiamata "GET /compare" passando come parametro il nome delle due città, l'istante iniziale della ricerca e
l'istante finale, questi ultimi sono rappresentati nel file con 'dt' in secondi, ovvero tutti i secondi trascorsi dal 1 Gennaio 1970(Epoch), per cui, nel momento
in cui l'utente passa come parametri le date e i rispettivi orari, abbiamo dovuto effettuare tramite alcuni metodi, la conversione da data in secondi per effettuare il
matching. Effettuata questa chiamata, si vede restituire per ogni città, il valore minimo, massimo, la differenza tra i due e la media di tutti i valori salvati, così da poter
avere una visuale più chiara su quale tra le due città ha registrato valori maggiori, minori ecc...
Questo è un esempio del JSON restituito:                                                                                                                                       

```
{
    "Valori di pressione minimi": {
        "Valore di pressione minima Milan": 1028.0,
        "Valore di pressione minima Rome": 1025.0
    },
    "Valori di pressione medi": {
        "Valore di pressione medi Milan": 1028.0,
        "Valore di pressione medi Rome": 1025.0
    },
    "Valori di pressione massimi ": {
        "Valore di pressione massima Rome": 1025.0,
        "Valore di pressione massima Milan": 1028.0
    },
    "Differenze di pressione": {
        "Differenze di pressione Milan": 0.0,
        "Differenze di pressione Rome": 0.0
    }
}
```
5. *Altro*                                                                                                                                                                    
Inoltre, affinchè sia possibile accertarsi dei reali valori di media, pressione minima, massima e differenza tra le due, abbiamo deciso di aggiungere anche una rotta che
restituisca tutti i valori di pressione registrati per quella città. La rotta in questione si richiama tramite il metodo "GET /getAllPressure" e, passando come parametro
il nome della città, si ottiene la lista di tutte le pressioni, ordinate anche per data e ora.
Questo è un esempio di file JSON che si ottiene:                                                                                                                              
```
{
    "info n.5": {
        "date": "16/12/2021 14:29:19",
        "pressure": 1025
    },
    "info n.4": {
        "date": "16/12/2021 14:29:19",
        "pressure": 1025
    },
    "info n.3": {
        "date": "16/12/2021 14:29:19",
        "pressure": 1025
    },
    "info n.2": {
        "date": "16/12/2021 14:29:19",
        "pressure": 1025
    },
    "info n.1": {
        "date": "16/12/2021 14:29:19",
        "pressure": 1025
    },
    "Statistics": {
        "Differenze di pressione totale": 0,
        "Valore di pressione minima totale": 1025,
        "Valore di pressione massima totale": 1025,
        "Valore di pressione medi totale": 1025.0
    }
}
```

## Rotte disponibili
|      Rotta        |  Metodo | Parametri                                            |       Funzione                                                       |
|-------------------|---------|------------------------------------------------------|----------------------------------------------------------------------|
|/save              |GET      |Nome città                                            |Salva il file JSON della città restituito da Postman                  |
|/compare           |GET      |Nomi delle due città, istante iniziale, istante finale|Compara le statistiche calcolate delle due città                      |
|/getAllPressure    |GET      |Nome della città                                      |Restituisce tutti i valori delle pressioni registrate per quella città|
|/getCity           |GET      |Nome della città                                      |Resitutisce tutti i valori utilizzati nell'applicativo per la città   |
|/getMilan          |GET      |//                                                    |Chiamata di prova che restituisce i valori di Milano                  |

## Statistiche
Le statistiche riguardanti tutti i valori richiesti delle pressioni, come il valore minimo, massimo, media o differenza, vengono calcolati dopo essere stati letti da un file
JSON che ci siamo creati localmente, dopo aver ottenuto il JSON principale restituito da Postman, ovvero come quello riportato nella prima parte del README.md. Questo file è stato creato utilizzando solo due caratteristiche, il tempo e il valore della pressione. Per crearlo abbiamo utilizzato la libreria "Timer" di Java la quale, periodicamente, rinnova la chiamata del metodo, che ogni volta si fa restituire i valori da Postman, salvando solo ciò che è necessario in questo file locale, che viene utilizzato per il calcolo delle statistiche. Quest'ultime sono state calcolate utilizzando metodi e funzioni di libreria. Ad esempio, per il calcolo del valore massimo e minimo, ci è bastato 
scorrere il vettore di Pressure in cui abbiamo salvato volta per volta i dati e, confrontando, ci siamo trovati i valori di pressione massimi e minimi. Ergo, per la media, il 
procedimento è analogo ma, tutti i valori contenuti nel vettore vengono sommati ed assegnati ad una variabile che viene poi divisa per il numero di misure rilevate.
Infine, per la differenza tra la pressione massima e minima, si è trattato di implementare solo una piccola differenza tra i due valori già ricavati in precedenza.

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
