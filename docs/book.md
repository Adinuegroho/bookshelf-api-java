# BOOK API SPEC

## List Book

Mendapatkan daftar semua buku dalam perpustakaan.

Endpoint : GET /books

Request Header : 

Response Body (Success) :
```json
{
  "data" : [
    {
      "id" : "randomstring",
      "price" : "hargabuku",
      "title" : "judulbuku",
      "writter" : "penulisbuku"
    }
  ]
}
```

Response Body (Failed) :
```json
{
  "errors" : "book is not found"
}
```

## Detail Book

Mendapatkan detail buku berdasarkan ID.

Endpoint : GET /books/{id}

Request Header :

Response Body (Success) :
```json
{
  "data" :  {
      "id" : "randomstring",
      "price" : "hargabuku",
      "title" : "judulbuku",
      "writter" : "penulisbuku"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "book is not found"
}
```

## Create Book

Menambahkan buku baru ke dalam perpustakaan.

Endpoint : POST /books

Request Header :

Request Body (Success) :
```json
{
  "price" : "hargabuku",
  "title" : "judulbuku",
  "writter" : "penulisbuku"
}
```

Response Body (Success) :
```json
{
  "data" :  {
    "id" : "randomstring",
    "price" : "hargabuku",
    "title" : "judulbuku",
    "writter" : "penulisbuku"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "book is not found"
}
```

## Update Book

Memperbarui informasi buku berdasarkan ID.

Endpoint : PUT /books/{id}

Request Header :

Request Body (Success) :
```json
{
  "price" : "hargabuku",
  "title" : "judulbuku",
  "writter" : "penulisbuku"
}
```

Response Body (Success) :
```json
{
  "data" :  {
    "id" : "randomstring",
    "price" : "hargabuku",
    "title" : "judulbuku",
    "writter" : "penulisbuku"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "book is not found"
}
```

## Delete Book 

Menghapus buku dari perpustakaan berdasarkan ID.

Endpoint : DELETE /books/{id}

Request Header :

Response Body (Success) :
```json
{
  "data": "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "book is not found"
}
```