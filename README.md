# javaspringboot
## Links fue die Endpunkte
GET (): /todo <br>
GET (search by id): /todo/{id} <br>
GET (search todo containing {name}): /todo/name/{name} <br>
GET (search by completion status; true or false): /todo/status/{status} <br>

POST: /todo
PUT: /todo/{id}
DELETE: /todo/{id}

## Java Spring Boot Todo-Webservice
Aufgabe
Ihr Ziel ist es, einen einfachen Todo-Webservice mit Spring Boot zu erstellen. Der Webservice sollte die grundlegenden CRUD-Operationen (Create, Read, Update, Delete) fuer Todo-Eintraege unterstuetzen.
Anforderungen
1.	Projektinitialisierung:
    Erstellen Sie ein neues Spring Boot-Projekt mittels Spring Initializr 
2.	Datenmodell:
    Definieren Sie eine einfache Entitaet Todo mit den passenden Feldern:
3.	Repository:
    Erstellen Sie ein Spring Data JPA-Repository fuer die Entitaet Todo.
4.	Controller:
    Erstellen Sie einen Spring MVC-Controller, der Endpunkte fuer die CRUD-Operationen bereitstellt.
    Verwenden Sie RESTful-Endpunkte (z.B., GET /todos, POST /todos, PUT /todos/{id}, DELETE /todos/{id}).
5.	Tests:
    Schreiben Sie Unit-Tests fuer Ihre Repository- und Controller-Klassen.

Bonuspunkte (optional)
    Implementieren Sie eine einfache Validierung fuer die Eingabedaten.
    Fuegen Sie Paginierung oder Sortierung fuer die GET /todos-Anfrage hinzu.
    Implementieren Sie eine einfache Benutzerauthentifizierung fuer den Zugriff auf die Todo-Ressourcen.

Abgabe
    Laden Sie Ihr Projekt auf GitHub hoch und teilen Sie uns den Link zu Ihrem Repository mit.

Bewertungskriterien
Ihre Loesung wird anhand der folgenden Kriterien bewertet:
    Erfuellung der Grundanforderungen
    Codequalitaet und Struktur
    Verwendung von Best Practices in Spring Boot
    Vollstaendigkeit der Tests
    Klarheit und Vollstaendigkeit der Dokumentation
    Verwendung von CleanCode Prinzipen
