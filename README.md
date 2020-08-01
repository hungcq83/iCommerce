# iCommerce

This is a POC of iCommerce backend services. ERD files and Architecture Diagram can be found in project root. Due to time constraint, only 1 unit test was written for the Product service as an example. 

# Project overview
### Components
- API GW - Netflix Zuul
- Discovery Server - Netflix Eureka
- Product Service
- Audit Service

### Notable Dependencies
- Lombok - avoid repetitive code
- Hibernate Envers - entity versioning
- OpenFeign - Declarative REST Client
- H2 Database - In-memory database for POC purpose
- Model Mapper - mapping from DTO to Entity and vice versa
- Hystrix - Circuit Breaker
- Spring Data JPA - Data access abstraction

# Installation guide
### Pre-requisites
- Maven
- Java 8
- Git

### Steps to run
```sh
$ git clone https://github.com/hungcq83/iCommerce.git
$ cd iCommerce
$ ./run_all.ssh
```
Wait a few minutes for all 4 apps to start up. Once all apps are up running, use below command lines to test (you can also use the Postman collection in this project to test using Postman).

### Audit API
Create Audit
```sh
curl -X POST \
  http://localhost:8090/api/audits \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c0afa549-9404-450b-b3cc-360a9dc8133f' \
  -d '{
	"productCode": "abc4",
	"activity": "Search",
	"query": "macbook",
	"filter": "color:red",
	"sortBy": "price",
	"order": "asc"
}'
```
Get Audit
```sh
curl -X GET \
  http://localhost:8090/api/audits/2 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 169400c1-29ac-4e88-9b98-7ae4540fea12'
```
### Product API
Get Product by ID
```sh
curl -X GET \
  http://localhost:8090/api/products/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 426ab814-edc6-4cb0-8b81-5d96407afb67'
```
Search Products with filters
```sh
curl -X GET \
  'http://localhost:8090/api/products?q=a&sortBy=category.name&order=desc&filter=brand:apple' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 64775f49-e7f8-47c7-99e3-ef4a47517509'
```
Search Products without filters
```sh
curl -X GET \
  'http://localhost:8090/api/products?q=a&sortBy=code&order=desc&page=0' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 5b691596-5142-44c8-b464-b069c06300dd'
```
Create Product
```sh
curl -X POST \
  http://localhost:8090/api/products \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cc3f8bc1-25d1-4a67-bcd5-28e229129061' \
  -d '{
	"name": "test",
	"code": "TEST1",
	"price": 12.90,
	"brand": "Test Brand",
	"color": "Red",
	"categoryId": 2
}'
```
Update Product
```sh
curl -X PUT \
  http://localhost:8090/api/products \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 5a8b6542-c333-417c-927d-67f1683d88e5' \
  -d '{
	"id": 5,
	"name": "testNew3",
	"code": "TESTNEW3",
	"price": 12.90,
	"brand": "Test Brand",
	"color": "Red",
	"categoryId": 1
}'
```
Get Products by category code
```sh
curl -X GET \
  'http://localhost:8090/api/products?category=laptop' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: afe60449-7d26-478d-97aa-33ac02877670'
```

