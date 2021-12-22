# ProgettoEsameCocciaDiSabatinoGennaio2022

# Indice
- [Introduzione](#Introduzione)
- [Applicazione](#Applicazione)
  - [Funzionamento](#Funzionamento)
- [Rotte disponibili](#Rotte-disponibili)
- [Statistiche](#Statistiche)
- [Come si usa](#Come-si-usa)
- [Documentazione](#Documentazione)
- [Test](#Test)
- [Strumenti utilizzati](#Strumenti-utilizzati)
- [Autori](#Autori)


# Introduzione
Il nostro applicativo è un RESTful Web Service, ovvero un sistema software che, comunicando tramite il controllo HTTP, è in grado di mettersi al servizio di un Client, che può 
essere un'applicazione, un sito web o Postman, così da consentire agli utenti che vi si collegano di usufruire delle azioni che mette a disposizione.

Il progetto implementa un servizio meteo che, raccogliendo informazioni meteo generali su varie città, permette all'utente di scegliere due di queste così da poter restituire, mettendole a confronto, delle statistiche calcolate sulle loro pressioni, quali pressione massima,minima,differenza fra le due e la media, in un determinato periodo di tempo comunque stabilito dall'utente.

A scopo dimostrativo, durante il periodo di sviluppo e testing dell'applicazione sono stati raccolti i dati di varie città, tra cui Milano, Tokyo, Londra, Parigi e New York. 
Il salvataggio è avvenuto in diversi giorni, ad esempio il 17/12/2021, 18/12/2021, 19/12/2021 e 20/12/2021, ma non è assicurata la totale copertura delle fasce orarie, per cui, nel caso di inserimento di orari in cui non si sono verificati salvataggi di dati, avrete semplicemente dei valori pari a zero nelle statistiche richieste.
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
Per prima cosa, bisogna scegliere due diverse città di cui si vogliano calcolare le statistiche per poi confrontarle, per semplicità abbiamo considerato solo cinque città di cui abbiamo salavto i dati, tra cui Milano, Tokyo, Londra, Parigi e New York, di cui abbiamo accumulato per ciascuna, durante determinati giorni, alcuni valori restituiti da Postman. Fatto ciò è possibile controllare gli attuali valori meteo di qualsiasi città tramite la chiamata "GET /getCity"(prototipo iniziale del progetto), che restituisce alcuni valori da noi selezionati, come il nome della città, il nome della nazione, longitudine, latitudine e via dicendo, compresa anche la pressione. E' bene ricordare che, durante la fase di inserimento, le città vanno passate come parametro in lingua inlgese, ad esempio Milan, Paris ecc...
2. *Salvataggio*                                                                                                                                                            
Scelte le due città, queste vengono "sottoposte" ad una fase di salvataggio in cui, tramite la chiamata "GET /save", inizia un processo di salvataggio dei
dati da Postman su un file JSON locale. Questo salvataggio viene eseguito ogni trenta minuti, utilizzando la libreria "Timer" di Java. Inoltre, tra i vari valori che abbiamo deciso di salvare, abbiamo considerato il tempo, indicato come 'dt' nel file JSON riportato precedentemente e il valore della pressione 'pressure', così da semplificare poi la successiva lettura del file locale per il calcolo delle statistiche. Nel caso di inserimento di una città inesistente, potrete accertarvi dal vostro IDE Eclipse o altri, il lancio dell'eccezione e del corrispettivo messaggio "Città inserita inesistente". Un esempio del file che viene salvato in locale:                                                                        
```
{"dt":1639727225,"pressure":1040}
{"dt":1639729053,"pressure":1041}
{"dt":1639730946,"pressure":1041}
{"dt":1639732627,"pressure":1041}
{"dt":1639734670,"pressure":1041}
{"dt":1639736503,"pressure":1041}
{"dt":1639738037,"pressure":1042}
{"dt":1639739877,"pressure":1041}
{"dt":1639742688,"pressure":1041}
{"dt":1639749068,"pressure":1040}
{"dt":1639749630,"pressure":1040}
{"dt":1639750945,"pressure":1040}
{"dt":1639751516,"pressure":1040}
{"dt":1639752751,"pressure":1040}
{"dt":1639753291,"pressure":1040}
{"dt":1639754606,"pressure":1040}
{"dt":1639755172,"pressure":1040}
{"dt":1639756336,"pressure":1040}
{"dt":1639756945,"pressure":1040}
{"dt":1639758218,"pressure":1040}
{"dt":1639758739,"pressure":1040}
{"dt":1639759436,"pressure":1040}
{"dt":1639760104,"pressure":1040}
{"dt":1639760711,"pressure":1040}
{"dt":1639761211,"pressure":1040}
```

3. *Compare, lettura e calcolo statistiche*                                                                                                                                             
Dopo aver effettuato il salvataggio delle singole città, è possibile effettuare una chiamata "GET /compare" la quale, sottopone il file locale ad una lettura che acquisice, in base al periodo scelto dall'utente, due diversi valori, il tempo e la pressione. Questi valori vengono utilizzati per calcolare varie statistiche, nel nostro caso la pressione minima e massima di ogni città, la media tra tutte le pressioni e la differenza tra la pressione  massima e la pressione minima.
"GET /compare" è eseguita passando come parametri il nome delle due città, l'istante iniziale della ricerca e
l'istante finale. Questi ultimi sono rappresentati nel file con 'dt' in secondi, ovvero tutti i secondi trascorsi dal 1 Gennaio 1970(Epoch), per cui, nel momento
in cui l'utente passa come parametri le date e i rispettivi orari nei formati dd/MM/yyyy oppure dd/MM/yyyy HH:mm:ss(non è possibile inserire solo l'ora), abbiamo dovuto effettuare tramite alcuni metodi la conversione da data in secondi, per effettuare il matching. Effettuata questa chiamata, si vede restituire per ogni città, il valore minimo, massimo, la differenza tra i due e la media di tutti i valori salvati, così da poter avere una visuale più chiara su quale tra le due città ha registrato valori maggiori, minori ecc... E' bene ricordare che, in caso di inserimento di date errate, come ad esempio nel caso in cui la data iniziale risulta posticipata rispetto alla data finale, o anche per errori legati al formato, questi ultimi vengono gestiti correttamente mostrando un messaggio di errore all'utente.
Nel caso invece ci siano errori di logica nell' inserimento delle date, come potrebbe essere l'inserimento errato del giorno rispetto al mese o del mese rispetto all'anno, ad esempio 32/12/2021 o 10/13/2021, il programma ricalcolarebbe le date corretteamente e restituirebbe invece 01/01/2022 e 10/01/2022. Tale requisito si applica anche per gli orari e quindi 30/12/2021 25:61:66 verrebbe considerato come 31/12/2021 01:02:06.

Questo è un esempio del JSON restituito:                                                                                          

```
{
    "Valori di pressione minimi": {
        "Valore di pressione minima New York": 1010.0,
        "Valore di pressione minima Milan": 1019.0
    },
    "Valori di pressione medi": {
        "Valore di pressione medi New York": 1016.0,
        "Valore di pressione medi Milan": 1024.0
    },
    "Differenze di pressione": {
        "Differenze di pressione Milan": 12.0,
        "Differenze di pressione New York": 13.0
    },
    "Valori di pressione massimi": {
        "Valore di pressione massima Milan": 1031.0,
        "Valore di pressione massima New York": 1023.0
    }
}
```
4. *Altro*                                                                                                                                                                    
Inoltre, affinchè sia possibile accertarsi dei reali valori di media, pressione minima, massima e differenza tra le due, abbiamo deciso di aggiungere anche una rotta che
restituisca tutti i valori di pressione registrati per quella città. La rotta in questione si richiama tramite il metodo "GET /getAllPressure" e, passando come parametro
il nome della città, si ottiene la lista di tutte le pressioni, ordinate anche per data e ora.
Questo è un esempio di file JSON che si ottiene(riporto solo parte del file JSON per motivi di lunghezza):                                                                                                                                                                                                    
```
{
    {
    "Statistics": {
        "Differenze di pressione totale": 12,
        "Numero totale di info raccolte": 171,
        "Valore di pressione minima totale": 1025,
        "Valore di pressione massima totale": 1037,
        "Valore di pressione medi totale": 1031.0
    },
    "info": [
        {
            "date": "17/12/2021 08:45:56",
            "unix time": 1639727156,
            "index": 1,
            "pressure": 1036
        },
        {
            "date": "17/12/2021 09:16:54",
            "unix time": 1639729014,
            "index": 2,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 09:46:18",
            "unix time": 1639730778,
            "index": 3,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 10:17:46",
            "unix time": 1639732666,
            "index": 4,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 10:53:49",
            "unix time": 1639734829,
            "index": 5,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 11:14:16",
            "unix time": 1639736056,
            "index": 6,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 11:55:31",
            "unix time": 1639738531,
            "index": 7,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 12:15:01",
            "unix time": 1639739701,
            "index": 8,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 13:01:21",
            "unix time": 1639742481,
            "index": 9,
            "pressure": 1036
        },
        {
            "date": "17/12/2021 14:50:02",
            "unix time": 1639749002,
            "index": 10,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:02:59",
            "unix time": 1639749779,
            "index": 11,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:24:38",
            "unix time": 1639751078,
            "index": 12,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:34:57",
            "unix time": 1639751697,
            "index": 13,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:00:07",
            "unix time": 1639753207,
            "index": 14,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:00:07",
            "unix time": 1639753207,
            "index": 15,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:24:50",
            "unix time": 1639754690,
            "index": 16,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:32:42",
            "unix time": 1639755162,
            "index": 17,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:56:00",
            "unix time": 1639756560,
            "index": 18,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 17:06:52",
            "unix time": 1639757212,
            "index": 19,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 17:27:10",
            "unix time": 1639758430,
            "index": 20,
            "pressure": 1035
        }
     ]
}
 ```

## Rotte disponibili
|      Rotta        |  Metodo | Parametri                                            |       Funzione                                                       |
|-------------------|---------|------------------------------------------------------|----------------------------------------------------------------------|
|/save              |GET      |Nome della città                                      |Salva il file JSON della città restituita da Postman                  |
|/compare           |GET      |Nomi delle due città, istante iniziale, istante finale|Compara le statistiche calcolate delle due città                      |
|/getAllPressure    |GET      |Nome della città                                      |Restituisce tutti i valori delle pressioni registrate per quella città|
|/getCity           |GET      |Nome della città                                      |Resitutisce tutti i valori utilizzati nell'applicativo per la città   |
|/getMilan          |GET      |//                                                    |Chiamata di prova che restituisce i valori di Milano                  |

## Statistiche
Le statistiche riguardanti tutti i valori richiesti delle pressioni, come il valore minimo, massimo, media o differenza, vengono calcolati dopo essere stati letti da un file
JSON che ci siamo creati localmente, dopo aver ottenuto il JSON principale restituito da Postman, ovvero come quello riportato nella prima parte del README.md. Questo file è stato creato utilizzando solo due caratteristiche, il tempo e il valore della pressione. Per crearlo abbiamo utilizzato la libreria "Timer" di Java la quale, periodicamente, rinnova la chiamata del metodo, che ogni volta si fa restituire i valori da Postman, salvando solo ciò che è necessario in questo file locale, che viene utilizzato per il calcolo delle statistiche. Quest'ultime sono state calcolate utilizzando metodi e funzioni di libreria. Ad esempio, per il calcolo del valore massimo e minimo, ci è bastato 
scorrere il vettore di Pressure in cui abbiamo salvato volta per volta i dati e, confrontando, ci siamo trovati i valori di pressione massimi e minimi. Ergo, per la media, il 
procedimento è analogo ma, tutti i valori contenuti nel vettore vengono sommati ed assegnati ad una variabile, che viene poi divisa per il numero di misure rilevate.
Infine, per la differenza tra la pressione massima e minima, si è trattato di implementare solo una piccola differenza tra i due valori già ricavati in precedenza.

# Come si usa
Clonando questa repository sul vostro computer e importando nell'IDE Eclipse il progetto PressureChecker sarete subito pronti a partire: infatti, nel pacchetto scaricato, oltre all'applicativo, sono già presenti i file di configurazione predefiniti e il file di database contenente un cospicuo set di campioni su cui fare le prove. Una volta aperto Eclipse, per avviare il programma, basta selezionare PressureCheckerApplication nel proprio package explorer e dare il comando Run as -> Spring Boot App. L'avvio dell'applicazione è riconoscibile dalla comparsa del logo di Spring e di molte righe di informazioni scritte in formato logging. L'applicativo espone i propri Endpoint sulla rete interna all'indirizzo localhost, sulla porta 8080 dove, se tutto è stato lanciato in modo corretto, potrete accertarvi della partenza del server Tomcat. Per usufruire delle  funzionalità potete collegarvi alle rotte messe a disposizione con un'applicazione come Postman, o direttamente dal browser.  

Usare questo comando dal Prompt dei Comandi per clonare la repository, altrimenti è possibile scaricare direttamente il file zip ed estrarlo
> git clone https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022.git

***Metodo di utilizzo degli endpoint***    
- *localhost:8080/save*                                                                                                                                                       
Utilizzando come parametro il nome di una città(Value) che corrisponde alla Key "name", consente di salvare in locale un file JSON con tutti i valori di pressione aggiornati ogni trenta minuti. Questo è il file che viene poi utilizzato per calcolare le statistiche e così restituire i valori richiesti. Nel caso di immissione giusta dei parametri, vedrete restituirvi il messaggio: "File creato/aggiornato con successo", ad indicare la riuscita creazione/aggiornamento del file. Ecco un esempio(il cui parametro passato come città è Tokyo):                 

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/saveTokyo.png)

- *localhost:8080/compare*                                                                                                                                                      
Vi mostrerà le statistiche calcolate per le due città passate come parametro, nel lasso di tempo scelto(Key: "timeIniti", tempoiniziale, Key: "endTime", tempo finale). Ecco un esempio(ho passato come città Milan e New York nei giorni 18/12/2021 13:43:12 e 19/12/2021 17:15:54:                                                                                  

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/chiamataCompareNew.png)                                                               


E questo è ciò che restituisce:                                                                                                                                               
```
{
    "Valori di pressione minimi": {
        "Valore di pressione minima New York": 1010.0,
        "Valore di pressione minima Milan": 1019.0
    },
    "Valori di pressione medi": {
        "Valore di pressione medi New York": 1016.0,
        "Valore di pressione medi Milan": 1024.0
    },
    "Differenze di pressione": {
        "Differenze di pressione Milan": 12.0,
        "Differenze di pressione New York": 13.0
    },
    "Valori di pressione massimi": {
        "Valore di pressione massima Milan": 1031.0,
        "Valore di pressione massima New York": 1023.0
    }
}
```

- *localhost:8080/getAllPressure*                                                                                                                                               
Otterrete una lista di tutte le misure rilevate/salvate con le rispettive date e orari in cui sono avvenute (nell'esempio ho considerato come parametro/città 
Paris(Key: "city"), riportando solo alcune delle numerose rilevazioni)                                                                                                                                                                                                          
![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/getAllPressureParis.png)                                                           

Questo è ciò che viene restituito:                                                                                                                                              
```
{
   {
    "Statistics": {
        "Differenze di pressione totale": 12,
        "Numero totale di info raccolte": 171,
        "Valore di pressione minima totale": 1025,
        "Valore di pressione massima totale": 1037,
        "Valore di pressione medi totale": 1031.0
    },
    "info": [
        {
            "date": "17/12/2021 08:45:56",
            "unix time": 1639727156,
            "index": 1,
            "pressure": 1036
        },
        {
            "date": "17/12/2021 09:16:54",
            "unix time": 1639729014,
            "index": 2,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 09:46:18",
            "unix time": 1639730778,
            "index": 3,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 10:17:46",
            "unix time": 1639732666,
            "index": 4,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 10:53:49",
            "unix time": 1639734829,
            "index": 5,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 11:14:16",
            "unix time": 1639736056,
            "index": 6,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 11:55:31",
            "unix time": 1639738531,
            "index": 7,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 12:15:01",
            "unix time": 1639739701,
            "index": 8,
            "pressure": 1037
        },
        {
            "date": "17/12/2021 13:01:21",
            "unix time": 1639742481,
            "index": 9,
            "pressure": 1036
        },
        {
            "date": "17/12/2021 14:50:02",
            "unix time": 1639749002,
            "index": 10,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:02:59",
            "unix time": 1639749779,
            "index": 11,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:24:38",
            "unix time": 1639751078,
            "index": 12,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 15:34:57",
            "unix time": 1639751697,
            "index": 13,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:00:07",
            "unix time": 1639753207,
            "index": 14,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:00:07",
            "unix time": 1639753207,
            "index": 15,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:24:50",
            "unix time": 1639754690,
            "index": 16,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:32:42",
            "unix time": 1639755162,
            "index": 17,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 16:56:00",
            "unix time": 1639756560,
            "index": 18,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 17:06:52",
            "unix time": 1639757212,
            "index": 19,
            "pressure": 1035
        },
        {
            "date": "17/12/2021 17:27:10",
            "unix time": 1639758430,
            "index": 20,
            "pressure": 1035
        }
     ]
}
```

- *localhost:80807getCity*                                                                                                                                                    
In questo caso, passando come parametro una qualsiasi città, riceverete alcune informazioni su di essa, come nome, id, nazione e, inoltre, i valori di pressione,
massime, minime, medie e differenza, che si sono verificate (nel mio esempio, il parametro passato è stato Tokyo)                                                                 

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/getCityTokyo.png)                                                                  

Di seguito, ciò che restituisce:                                                                                                                                                
```
{
    "Country": "JP",
    "Lon": 139.6917,
    "City": "Tokyo",
    "Id": 1850144,
    "Pressure_Data": {
        "Pressure_med": 1010.0,
        "Pressure_diff": 0,
        "Pressure_max": 1010,
        "Pressure_min": 1010
    },
    "Lat": 35.6895
}
```

# Documentazione
Al fine di ottenere una maggiore chiarezza durante la lettura del codice, è possibile usufruire della documentazione messa a disposisizone. Essa è accessibile aprendo il file "index.html" dal package "doc", attraverso un browser. Permette all'utente di scegliere in primis un package e, successivamente, navigare singolarmente per le varie classi contenute in esso.                                                                                                                                                           

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/packagesDoc.png)

![](https://github.com/Walter-Di-Sabatino/ProgettoEsameCocciaDiSabatinoGennaio2022/blob/Main/classDoc.png)

# Test
Inoltre, durante la fase di sviluppo dell'applicativo, sono stati implementati vari test sulle classi implementate:
- *PressureCityTest e PressureTest* per il testing riguardanti le classi City e Pressure
- *PressureStatsTest* per il testing sul calcolo delle statistiche
- *PressureServiceTest* per il testing sul lacio di una eccezione

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
