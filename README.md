# java-ecommerce
Program ecommerce dengan menggunakan Database SQLite

## About
Program ini merupakan program java mengenai sebuah sistem E-COMMERCE dengan menggunakan Backend API dan mengahasilkan data berupa sebuah JSON. Berikut merupakan request method pada API: <br/>
**GET** untuk mendapatkan list atau detail data dari entitas. <br/>
**POST** untuk membuat data entitas baru. <br/>
**PUT** untuk mengubah data dari entitas. <br/>
**DELETE** untuk menghapus data dari entitas. <br/>

Semua data yang akan di kelola oleh Request API tersebut akan disimpan kedalam database SQLite yang bernama **db_ecommerce** yang dimana akan di tes pada Aplikasi **Postman**

## Spesifikasi API dalam Aplikasi e-Commerce 
### **GET**
- GET /users untuk mendapatkan daftar semua user yang termuat di dalam database. <br/>
**http://localhost:8127/users**

'''
[
    
    {
        "firstName": "Rama",
        "lastName": "Putra",
        "addresses": [],
        "phoneNumber": "082147309044",
        "id": 1,
        "type": "Buyer",
        "email": "rama@gmail.com"
    },
    {
        "firstName": "Sindhu",
        "lastName": "Wedana",
        "addresses": [],
        "phoneNumber": "082374893723",
        "id": 2,
        "type": "Seller",
        "email": "sindhu@gmail.com"
    },
    {
        "firstName": "Roby",
        "lastName": "Dwimartha",
        "addresses": [],
        "phoneNumber": "082347837482",
        "id": 3,
        "type": "Seller",
        "email": "roby@gmail.com"
    },
    {
        "firstName": "Nanda",
        "lastName": "Febian",
        "addresses": [],
        "phoneNumber": "087493784736",
        "id": 4,
        "type": "Seller",
        "email": "nanda@gmail.com"
    },
    {
        "firstName": "Made",
        "lastName": "Jauhari",
        "addresses": [],
        "phoneNumber": "082736478392",
        "id": 5,
        "type": "Buyer",
        "email": "deari@gmail.com"
    },
    {
        "firstName": "Putu",
        "lastName": "Nigraha",
        "addresses": [],
        "phoneNumber": "089374837263",
        "id": 6,
        "type": "Buyer",
        "email": "sukma@gmail.com"
    }
]
'''
- GET /users/{id} untuk mendapatkan informasi user dan alamatnya. <br/>
**http://localhost:8127/users/1**

'''
[

    {
        "firstName": "Rama",
        "lastName": "Putra",
        "addresses": [
            {
                "province": "Bali",
                "city": "Badung",
                "postcode": "8016"
            }
        ],
        "phoneNumber": "082147309044",
        "id": 1,
        "type": "Buyer",
        "email": "rama@gmail.com"
    }
]
'''
- GET /users/{id}/products untuk mendapatkan daftar produk milik user. <br/>
**http://localhost:8127/users/2/products**

'''
[

    {
        "seller": 2,
        "price": "2500",
        "description": "Berpalis 3 Coklat",
        "id": 1,
        "title": "Beng-Beng",
        "stock": 50
    },
    {
        "seller": 2,
        "price": "2000",
        "description": "Dilapisi dengan wafer yang lezat",
        "id": 5,
        "title": "Astor Coklat",
        "stock": 55
    }
]
'''
- GET /users/{id}/orders untuk mendapatkan daftar order milik user. <br/>
**http://localhost:8127/users/1/orders**

'''
[

    {
        "note": 2,
        "total": 5000,
        "discount": 0,
        "id": 1,
        "is_paid": "1",
        "buyer": 1
    },
    {
        "note": 3,
        "total": 50000,
        "discount": 20,
        "id": 3,
        "is_paid": "1",
        "buyer": 1
    }
]
'''
- GET /users/{id}/reviews untuk mendapatkan daftar review yang dibuat oleh user. <br/>
**http://localhost:8127/users/1/reviews**

'''
[

    {
        "nama": "Rama",
        "star": 5,
        "description": "Coklatnya lumer banget",
        "order": 1
    }
]
'''
- GET /orders/{id} untuk mendapatkan informasi order, buyer, detail order, review, product title, beserta pricenya. <br/>
**http://localhost:8127/orders/1**

'''
[

    {
        "note": 2,
        "total": 5000,
        "reviews": [
            {
                "star": 5,
                "description": "Coklatnya lumer banget"
            }
        ],
        "discount": 0,
        "id": 1,
        "is_paid": "1",
        "order_detail": [
            {
                "product": "Beng-Beng",
                "quantity": 2,
                "price": 2500
            }
        ],
        "buyer": 1
    }
]
'''

- GET /products untuk mendapatkan daftar semua produk. <br/>
**http://localhost:8127/products**

'''
[

    {
        "seller": 2,
        "price": "2500",
        "description": "Berpalis 3 Coklat",
        "id": 1,
        "title": "Beng-Beng",
        "stock": 50
    },
    {
        "seller": 3,
        "price": "3000",
        "description": "Mengandung Vitamin C",
        "id": 2,
        "title": "Nutrisari",
        "stock": 58
    },
    {
        "seller": 4,
        "price": "5000",
        "description": "Berenergi",
        "id": 3,
        "title": "ExtraJoss",
        "stock": 30
    },
    {
        "seller": 3,
        "price": "10000",
        "description": "Dilengkapi dengan kauh yang lezat",
        "id": 4,
        "title": "Bubur ayam",
        "stock": 20
    },
    {
        "seller": 2,
        "price": "2000",
        "description": "Dilapisi dengan wafer yang lezat",
        "id": 5,
        "title": "Astor Coklat",
        "stock": 55
    },
    {
        "seller": 3,
        "price": "12000",
        "description": "Paket lengkap dengan telur",
        "id": 6,
        "title": "Bubur sapi",
        "stock": 10
    },
    {
        "seller": 4,
        "price": "17000",
        "description": "Kuahnya enak",
        "id": 8,
        "title": "Bubur kambing",
        "stock": 10
    }
]
'''

- GET /products/{id} untuk mendapatkan informasi produk dan seller. <br/>
**http://localhost:8127/products/2**

'''
[

    {
        "seller": 2,
        "price": "2500",
        "description": "Berpalis 3 Coklat",
        "id": 1,
        "title": "Beng-Beng",
        "stock": 50,
        "first_name": "Sindhu"
    },
    {
        "seller": 2,
        "price": "2000",
        "description": "Dilapisi dengan wafer yang lezat",
        "id": 5,
        "title": "Astor Coklat",
        "stock": 55,
        "first_name": "Sindhu"
    }
]
'''
### **POST**
- Menambahkan data baru kedalam tabel user <br/>
**http://localhost:8127/users**

'''
{
    
    "id":"",
    "first_name":"Dayu",
    "last_name":"Shri",
    "email":"dayu@gmail.com",
    "phone_number":"087365289384",
    "type":"Seller"
}
'''
### **PUT**
- Mengubah data pada tabel users <br/>
**http://localhost:8127/users/7**

'''
{

    "id":"",
    "first_name":"Dayu",
    "last_name":"Shri",
    "email":"dayushriaja@gmail.com",
    "phone_number":"087365289384",
    "type":"Seller"
}
'''
### **DELETE**
- Menghapus data dari entitas tertentu dimana saya mencoba untuk menghapus data yang terdapat pada tabel ... <br/>
**http://localhost:8127/users/7** <br>
![deleteSuccess](/img/deleteUser.png "deleteSuccess")