# hw8
Завдання до лекцій 16-17

**Upload** zip/json file:
```
POST: http://localhost:8080/api/pep/upload
e.g. body:
multipart/form-data (.zip || .json)
```
**Search** by firstName or/and lastName or/and patronymic:
``` 
POST: http://localhost:8080/api/pep/_search
e.g. body:
{
    "firstName":"Єгор",
    "patronymic":"Володимирович",
    "size":"2",
    "page":"0"
}
```
Find **Top 10** firstNames with count:
```
GET: http://localhost:8080/api/pep/top10
```
