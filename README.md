# theCodaCollectionCodingChallenge

Thank you for taking the time to look at my response to The Coda Collection's backend engineering coding challenge!

# Project Structure

## Java files

Inside of the controller folder you will find CodingChallengeController.java. This file sets up all endpoints for the project and manages request handling. 

Inside of the entities folder you will find five files. There is one for each table in the database used for mapping the table datastructure into a java object. Additionally, there is the ReqTitleDTO which is used for taking in the data on a request body so it can be used to create or update a title in the database.

Inside of the repository folder you will find four files. Inside of each is an interface that allows for connecitons to and queries on one of the tables in the database. 

Finally, is the service folder in which you will find CodingChallengeService.java. Which is used to handle some of the more complicated business logic of the project including things such as my abstraction of the upload_to_amazon function as well as a method for creating a new title which requires several steps.

And CodingchallengeApplication.java runs the project. 

## Resources files

The application.properties file creates and populates an in-memory H2 database.

The data.sql file holds all data to be upload into the database upon creation.

The schema.sql file creates all necessary schema (tables and sequences) for the database.

## Endpoints

By default, the project runs on port 8080

There are six endpoints created by this project:

### GET theCodaCollection/topArtists

Returns a list of the artists with the most view on the platform.

Parameters <br />
Name: top <br />
Type: Integer <br />
Description: Number of artists to return, default is 10

#### Example:
GET /theCodaCollection/topArtists?top=15

<details>
<summary>Response</summary>
    
```yaml
[
    "Jimi Hendrix",
    "The Rolling Stones",
    "Stiff Little Fingers",
    "Arc Angels",
    "Jesus Jones",
    "The Wonder Stuff",
    "Paul Simon",
    "Hawkwind",
    "Marvin Gaye",
    "Stevie Ray Vaughan",
    "Human League",
    "Pearl Jam",
    "Noel Gallagher",
    "Desmond Dekker",
    "Jane's Addiction"
]
```
    
</details>

### GET theCodaCollection/{artist}

Returns all films for the selected artists

#### Example
GET /theCodaCollection/Jimi Hendrix

<details>
<summary>Response</summary>
    
```yaml
[
    {
        "id": 16,
        "title": "Live at Woodstock",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": "Woodstock Music and Art Fair",
        "active": true
    },
    {
        "id": 17,
        "title": "Electric Church",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 18,
        "title": "Blue Wild Angel: Live at the Isle of Wight",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": "Isle of Wight Festival",
        "active": true
    },
    {
        "id": 19,
        "title": "Music, Money, Madness...Jimi Hendrix in Maui",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 56,
        "title": "BBC Sessions",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 57,
        "title": "At Last...The Beginning: The Making of Electric Ladyland",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 58,
        "title": "The Dick Cavett Show Documentary",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 59,
        "title": "American Landing : Jimi Hendrix Live at Monterey",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": "Monterey County Fairgrounds",
        "active": true
    },
    {
        "id": 60,
        "title": "Jimi Plays Berkeley",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": "Berkeley Community Theatre",
        "active": true
    },
    {
        "id": 61,
        "title": "Band of Gypsys Documentary",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    },
    {
        "id": 62,
        "title": "Hear My Train A Cominü",
        "genre": "Classic Rock",
        "artist": "Jimi Hendrix",
        "venue": null,
        "active": true
    }
]
```

</details>

### POST theCodaCollection/upload

Allows the user to upload a film

Parameters <br />
Name: title <br />
Type: String <br />
Description: Name of title to upload

#### EXAMPLE
POST /theCodaCollection/upload?title=superCoolConcert

<details>
<summary>Response</summary>

```yaml
Upload of superCoolConcert10 in progress!
```
    
</details>

### GET theCodaCollection/uploadStatus

If there is a film currently uploading, returns the current upload percentage.

#### Example
GET /theCodaCollection/uploadStatus

<details>
<summary>Response</summary>
    
```yaml   
current upload status: 80%
```
    
</details>

### POST theCodaCollection/

Allows the user to place a new title in the body of the request to add a new title to the collection.

#### Example

POST /theCodaCollection/

<details>
<summary>Request</summary>
    
```yaml
{
    "artist" : {
        "name" : "Super Idol",
        "genre" : "Pop"
    }, 
    "venue" : {
        "city" : "Dallas",
        "country" : "United States",
        "name" : "The Hit Pavillion"
    }, 
    "title" : {
        "title" : "Super Idol's Super Summer",
        "genre" : "Pop",
        "artist" : "Super Idol",
        "venue" : "The Hit Pavillion",
        "active" : true
    }
}
```
    
</details>

### POST theCodaCollection/updateTitle


Allows the user to update an existing title.

Parameters <br />
Name: title <br />
Type: String <br />
Description: REQUIRED - name of title to update<br />

Name: activeStatus <br />
Type: boolean <br />
Description: Set film as active or inactive.

#### Example
POST /theCodaCollection/updateTitle?title=Super Idol's Super Summer&activeStatus=False

<details>
<summary>Request</summary>
    
```yaml
{
    "venue" : {
        "city" : "Dallas",
        "country" : "United States",
        "name" : "My Mom's Backyard"
    }
}
```
    
</details>   
