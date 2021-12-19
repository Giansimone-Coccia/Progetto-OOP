# ProgettoEsameCocciaDiSabatinoGennaio2022
Il nostro applicativo è un RESTful Web Service, ovvero un sistema software che, comunicando tramite il controllo HTTP, è in grado di mettersi al servizio di un Client, che può 
essere un'applicazione, un sito web o Postman, così da consentire agli utenti che vi si collegano di usufruire delle azioni che mette a disposizione.

Il progetto implementa un servizio meteo che, raccogliendo le informazioni meteo generali su due città, restituisce successivamente, delle statistiche calcolate sulla pressione,
quali pressione minima, massima, differenza tra le due e la media, in un determinato periodo di tempo scelto, così da poter confrontare i diversi valori ottenuti nelle due città
precedentemente scelte.

A scopo dimostrativo, durante il periodo di sviluppo e testing dell'applicazione sono stati raccolti i dati di varie città, tra cui Milano, Tokyo, Londra, Parigi e New York. 
Il salvataggio è avvenuto in diversi giorni, ad esempio il 16/12/21, 17/12/21, 18/12/21 e 19/12/21, ma non è assicurata la totale copertura delle fasce orarie, per cui, nel caso di inserimento di
orari in cui non si sono verificate registrazioni di dati, avrete sempicemente dei valori pari a zero nelle statistiche richieste.
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
in cui l'utente passa come parametri le date e i rispettivi orari nei formati dd/MM/yyyy oppure dd/MM/yyyy HH:mm:ss, abbiamo dovuto effettuare tramite alcuni metodi, la conversione da data in secondi per effettuare il matching. Effettuata questa chiamata, si vede restituire per ogni città, il valore minimo, massimo, la differenza tra i due e la media di tutti i valori salvati, così da poter avere una visuale più chiara su quale tra le due città ha registrato valori maggiori, minori ecc... E' bene ricordare che, in caso di inserimento di date errate, come ad esempio nel caso in cui la data iniziale risulta posticipata rispetto alla data finale, o anche per errori legati al formato, questi ultimi vengono gestiti correttamente mostrando un messaggio di errore all'utente.                                                                                                  Questo è un esempio del JSON restituito:                                                                                                                                       

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
Questo è un esempio di file JSON che si ottiene(riporto solo parte del file JSON per motivi di lunghezza):                                                                                                                                                                                                    
```
{
    "Statistics": {
        "Differenze di pressione totale": 3,
        "Numero totale di info raccolte": 73,
        "Valore di pressione minima totale": 1039,
        "Valore di pressione massima totale": 1042,
        "Valore di pressione medi totale": 1039.0
    },
    "info": [
        {
            "info n.61": {
                "date": "18/12/2021 12:06:57",
                "pressure": 1040
            },
            "info n.60": {
                "date": "18/12/2021 11:34:54",
                "pressure": 1040
            },
            "info n.63": {
                "date": "18/12/2021 13:07:00",
                "pressure": 1039
            },
            "info n.62": {
                "date": "18/12/2021 12:39:46",
                "pressure": 1039
            },
            "info n.29": {
                "date": "17/12/2021 18:53:21",
                "pressure": 1040
            },
            "info n.28": {
                "date": "17/12/2021 18:43:50",
                "pressure": 1040
            },
            "info n.25": {
                "date": "17/12/2021 18:13:31",
                "pressure": 1040
            },
            "info n.69": {
                "date": "18/12/2021 16:00:53",
                "pressure": 1039
            },
            "info n.24": {
                "date": "17/12/2021 18:05:11",
                "pressure": 1040
            },
            "info n.68": {
                "date": "18/12/2021 15:30:58",
                "pressure": 1039
            },
            "info n.27": {
                "date": "17/12/2021 18:36:10",
                "pressure": 1040
            },
            "info n.26": {
                "date": "17/12/2021 18:23:02",
                "pressure": 1040
            },
            "info n.21": {
                "date": "17/12/2021 17:32:19",
                "pressure": 1040
            },
            "info n.65": {
                "date": "18/12/2021 14:03:14",
                "pressure": 1039
            },
            "info n.20": {
                "date": "17/12/2021 17:23:38",
                "pressure": 1040
            },
            "info n.64": {
                "date": "18/12/2021 13:33:11",
                "pressure": 1039
            },
            "info n.23": {
                "date": "17/12/2021 17:55:04",
                "pressure": 1040
            },
            "info n.67": {
                "date": "18/12/2021 15:10:37",
                "pressure": 1039
            },
            "info n.22": {
                "date": "17/12/2021 17:43:56",
                "pressure": 1040
            },
            "info n.66": {
                "date": "18/12/2021 14:35:22",
                "pressure": 1039
            }
        }
 }
 ```

## Rotte disponibili
|      Rotta        |  Metodo | Parametri                                            |       Funzione                                                       |
|-------------------|---------|------------------------------------------------------|----------------------------------------------------------------------|
|/save              |GET      |Nome della città                                      |Salva il file JSON della città restituito da Postman                  |
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

# Come si usa
Clonando questa repository sul vostro computer e importando nell'IDE Eclipse il progetto PressureChecker sarete subito pronti a partire: infatti, nel pacchetto scaricato, oltre all'applicativo, sono già presenti i file di configurazione predefiniti e il file di database contenente un cospicuo set di campioni su cui fare le prove. Una volta aperto Eclipse, per avviare il programma, basta selezionare PressureCheckerApplication nel proprio package explorer e dare il comando Run as -> Spring Boot App. L'avvio dell'applicazione è riconoscibile dalla comparsa del logo di Spring e di molte righe di informazioni scritte in formato logging. L'applicativo espone i propri Endpoint sulla rete interna all'indirizzo localhost, sulla porta 8080 dove, se tutto è stato lanciato in modo corretto, potrete accertarvi della partenza del server Tomcat. Per usufruire delle  funzionalità potete collegarvi alle rotte messe a disposizione con un'applicazione come Postman.                                                                                
***Metodo di utilizzo degli endpoint***    
- *localhost:8080/save*                                                                                                                                                       
Utilizzando come parametro il nome di una città, consente di salvare in locale, un file JSON con tutti i valori di pressioni aggiornati ogni 30 minuti. Questo è il file che viene poi utilizzato per calcolare le statistiche e così restituire i valori richiesti. Nel caso di immissione giusta dei parametri, vedrete restituirvi un messaggio del genere: "File creato/aggiornato con successo", ad indicare la riuscita creazione/aggiornamento del file. Ecco un esempio(il cui parametro passato come città è Tokyo):                 

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/saveTokyo.png)

- *localhost:8080/compare*                                                                                                                                                      
Vi mostrerà le statistiche calcolate per le due città passate come parametro, ecco un esempio(ho passato come città Milan e New York nei giorni 16/12/2021 13:43:12 e 17/12/2021 17:15:54:                                                                                  

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/chiamataCompare.png)                                                               


E questo è ciò che restituisce:                                                                                                                                               
```
{
    "Valori di pressione minimi": {
        "Valore di pressione minima New York": 1012.0,
        "Valore di pressione minima Milan": 1028.0
    },
    "Valori di pressione medi": {
        "Valore di pressione medi New York": 1016.0,
        "Valore di pressione medi Milan": 1029.0
    },
    "Differenze di pressione": {
        "Differenze di pressione Milan": 4.0,
        "Differenze di pressione New York": 7.0
    },
    "Valori di pressione massimi": {
        "Valore di pressione massima Milan": 1032.0,
        "Valore di pressione massima New York": 1019.0
    }
}
```

- *localhost:8080/getAllPressure*                                                                                                                                               
Otterrete una lista di tutte le misure rilevate/salvate con le rispettive date e orari in cui sono avvenute (nell'esempio ho considerato come parametro/città Paris, riportando solo alcune delle numerose rilevazioni)                                                                                                                                                                                                          
![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/getAllPressureParis.png)                                                           

Questo è ciò che viene restituito:                                                                                                                                              
```
{
    "Statistics": {
        "Differenze di pressione totale": 3,
        "Numero totale di info raccolte": 75,
        "Valore di pressione minima totale": 1034,
        "Valore di pressione massima totale": 1037,
        "Valore di pressione medi totale": 1035.0
    },
    "info": [
        {
            "info n.61": {
                "date": "18/12/2021 11:36:16",
                "pressure": 1035
            },
            "info n.60": {
                "date": "18/12/2021 00:06:57",
                "pressure": 1036
            },
            "info n.63": {
                "date": "18/12/2021 12:37:15",
                "pressure": 1035
            },
            "info n.62": {
                "date": "18/12/2021 12:12:05",
                "pressure": 1035
            },
            "info n.29": {
                "date": "17/12/2021 18:59:27",
                "pressure": 1035
            },
            "info n.28": {
                "date": "17/12/2021 18:47:39",
                "pressure": 1035
            },
            "info n.25": {
                "date": "17/12/2021 18:06:38",
                "pressure": 1035
            },
            "info n.69": {
                "date": "18/12/2021 15:34:22",
                "pressure": 1034
            },
            "info n.24": {
                "date": "17/12/2021 17:58:04",
                "pressure": 1035
            },
            "info n.68": {
                "date": "18/12/2021 15:08:16",
                "pressure": 1034
            },
            "info n.27": {
                "date": "17/12/2021 18:28:50",
                "pressure": 1035
            },
            "info n.26": {
                "date": "17/12/2021 18:28:50",
                "pressure": 1035
            },
            "info n.21": {
                "date": "17/12/2021 17:37:21",
                "pressure": 1035
            },
            "info n.65": {
                "date": "18/12/2021 13:38:24",
                "pressure": 1035
            },
            "info n.20": {
                "date": "17/12/2021 17:27:10",
                "pressure": 1035
            },
            "info n.64": {
                "date": "18/12/2021 13:10:01",
                "pressure": 1035
            },
            "info n.23": {
                "date": "17/12/2021 17:58:04",
                "pressure": 1035
            },
            "info n.67": {
                "date": "18/12/2021 14:36:39",
                "pressure": 1034
            },
            "info n.22": {
                "date": "17/12/2021 17:47:31",
                "pressure": 1035
            },
            "info n.66": {
                "date": "18/12/2021 14:05:07",
                "pressure": 1034
            },
            "info n.50": {
                "date": "17/12/2021 22:22:47",
                "pressure": 1035
            },
            "info n.52": {
                "date": "17/12/2021 22:44:35",
                "pressure": 1036
            },
            "info n.51": {
                "date": "17/12/2021 22:34:25",
                "pressure": 1035
            },
            "info n.18": {
                "date": "17/12/2021 16:56:00",
                "pressure": 1035
            }
        }
}
```

- *localhost:80807getCity*                                                                                                                                                    
In questo caso, passando come parametro una qualsiasi città, riceverete alcune informazioni su di essa, come nome, id, nazione e, inoltre, tutti i valori di pressione,
massime, minime, medie e differenza, che si sono verificate (nel mio esempio, il parametro passato è stato Tokyo)                                                                 

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/getCityTokyo.png)                                                                  

Di seguito, ciò che restituisce:                                                                                                                                                
```
{
    "Country": "JP",
    "Lon": 139.6917,
    "City": "Tokyo",
    "Id": 1850144,
    "Pressure_Data": [
        {
            "Pressure_med": 994.0,
            "Pressure_diff": 0,
            "Pressure_max": 994,
            "Pressure_min": 994
        }
    ],
    "Lat": 35.6895
}
```

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
