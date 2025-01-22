# kotlin-with-java



# Mongo Table Schema

<h3>`comments`</h3>

```
_id: ObjectId
date: ISODate
email: String
movie_id: ObjectId
name: String
text: String
```


<h3>`embedded_movies`</h3>

```
_id: ObjectId
awards: Object
cast: Array
countries: Array
directors: Array
fullplot: String
genres: Array
imdb: Object
languages: Array
lastupdated: String
num_mflix_comments: Int32
plot: String
poster: String
rated: String
released: ISODate
runtime: Int32
title: String
tomatoes: Object
type: String
writers: Array
year: Int32
plot_embedding: Array
```


<h3>`movies`</h3>

```
_id: ObjectId
awards: Object
cast: Array
countries: Array
directors: Array
fullplot: String
genres: Array
imdb: Object
languages: Array
lastupdated: String
num_mflix_comments: Int32
plot: String
poster: String
rated: String
released: ISODate
runtime: Int32
title: String
tomatoes: Object
writers: Array
year: Int32
type: String
```

<h3>`sessions`</h3>

```
_id: ObjectId
jwt: String
user_id: String
```


<h3>`theaters`</h3>

```
_id: ObjectId
location: Object
theaterId: Int32
```

<h3>`users`</h3>

```
_id: ObjectId
email: String
name: String
password: String
```