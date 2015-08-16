# QuickRest
Simple Java REST Client

## Features
### Easy, builder-style querying REST APIs
```
QuickRest rest = new QuickRest();
String response = rest.post("http://myapp.com/api/person")
  .header("Cache-Control", no-cache)
  .field("name","John")
  .field("age","35")
  .asString();
```

### Smart handling of query string in all HTTP methods (not only GET!) 
```
QuickRest rest = new QuickRest();
/* Will call POST to http://myapp.com/api/person/?name=John */
String response = rest.post("http://myapp.com/api/person/")
  .query("name","John")
  .asString();
  
/* Will call POST to http://myapp.com/api/person/?age=36&name=John */
String response = rest.post("http://myapp.com/api/person/?age=36")
  .query("name","John")
  .asString();
```

### Path parameters in URL
```
QuickRest rest = new QuickRest();
/* Will call GET from http://myapp.com/api/person/John */
String response = rest.get("http://{domain}.com/api/person/{name}")
  .pathParam("domain","myapp")
  .pathParam("name","John")
  .asString();
```

### Throw exception on unexpected HTTP response code
```
QuickRest rest = new QuickRest();
/* Will throw QuickRestException on 404 NOT FOUND because it expects 200 OK code as default */
String response = rest.get("http://myapp.com/api/noresource")
  .asString();

/* Won't throw exception on 404 NOT FOUND code as expected */
String response = rest.get("http://myapp.com/api/noresource")
  .expectResponseStatus(404)
  .asString();

```
