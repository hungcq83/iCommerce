# iCommerce

This is a POC of iCommerce backend services. ERD files and Architecture Diagram can be found in project root. Due to time constraint, only 1 unit test was written for the Product service as an example. 

- [Architecture design](#architecture-design)
  * [Frontend](#frontend)
  * [Backend](#backend)
  * [Authentication & Authorization](#authentication-and-authorization)
  * [Entity relationship diagram](#entity-relationship-diagram)
- [Project overview](#project-overview)
  * [Components](#components)
  * [Notable dependencies](#notable-dependencies)
- [Installation guide](#installation-guide)
  * [Pre-requisites](#pre-requisites)
  * [Steps to run](#Steps-to-run)
- [APIs](#apis)
  * [Audit API](#audit-api)
  * [Product API](#product-api)

## Architecture design
![alt text](https://raw.githubusercontent.com/hungcq83/iCommerce/master/iCommerce%20-%20ArchitectureDiagram.png)
### Frontend
- Single Page Application built using ReactJs/AngularJs
### Backend
- An API GW that acts as a single entry point for all requests from Frontend. API GW responsible for authenticate/authorize the requests before routing them to corresponding microservices.
- Service Discovery - for service register and discovery, provides an abstract way for service-to-service communication
- A set of microservices: Product, Audit, Cart (not yet implemented) and Order. 
### Authentication and Authorization
- OAuth2 Authorization Server (not yet implemented) - Centralized Authorization server to validate users credentials, generate & validate access token
- User authentication & authorization: Use OAuth2 Authorization Code flow with PKCE for enhanced security since our Frontend is SPA. Traditional webapp can use Authorization Code flow (without PCKE).
- API GW --> microservices: Use OAuth2 Client Credentials flow for system-to-system authentication. If microservices are deployed to a private network which only API GW has access to, then this authentication may not be required.
> All components should be deployed to Cloud platform(s) to utilize High Availability/Auto Scaling cloud features.
### Entity relationship diagram

Product DB

![alt text](https://raw.githubusercontent.com/hungcq83/iCommerce/master/Product%20ERD.png)

Order DB

![alt text](https://raw.githubusercontent.com/hungcq83/iCommerce/master/Order%20ERD.png)

Audit DB

![alt text](https://raw.githubusercontent.com/hungcq83/iCommerce/master/Audit%20ERD.png)

## Project overview
### Components
- API GW - Netflix Zuul (can be upgraded to Spring Cloud Gateway)
- Discovery Server - Netflix Eureka
- Product Service (supports Get, Search, Sort, Filter, Pagination, Create and Update)
- Audit Service (supports Get & Create)
- Order Service (supports Get & Create)

### Notable dependencies
- Lombok - avoid repetitive code
- Hibernate Envers - entity versioning
- OpenFeign - Declarative REST Client
- H2 Database - In-memory database for POC purpose
- Model Mapper - mapping from DTO to Entity and vice versa
- Hystrix - Circuit Breaker
- Spring Data JPA - Data access abstraction
- Spring Cloud Sleuth - Distributed tracing

## Installation guide
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
Wait a few minutes for all 4 apps to start up. Once all apps are up running, use below command lines to test (you can also use the Postman collection in this project to test using Postman). Service discovery will take about <mark>1-2 mins<mark> to register/discover all the services.

## APIs
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
  'http://localhost:8090/api/products?q=a&sortBy=category.name&order=asc&filter=brand:apple' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: bb4e05f8-dc16-42ae-8c7b-8e06ff8c136c'
```
Search Products without filters
```sh
curl -X GET \
  'http://localhost:8090/api/products?q=a&sortBy=code&order=desc&page=0' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: f398ec61-b8e2-41fc-bfa5-30ec244cf8a4'
```
Create Product
```sh
curl -X POST \
  http://localhost:8090/api/products \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: d2d9c563-e387-43a4-8754-d50f1d49ed73' \
  -d '{
	"name": "test",
	"code": "TEST1",
	"price": 12.90,
	"brand": "Test Brand",
	"color": "Red",
	"categoryId": 2,
	"supplierId": 1
}'
```
Update Product
```sh
curl -X PUT \
  http://localhost:8090/api/products \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7d1d236f-6268-41cd-8a1b-caa3e13532f9' \
  -d '{
	"id": 5,
	"name": "testNew3",
	"code": "TESTNEW3",
	"price": 12.90,
	"brand": "Test Brand",
	"color": "Red",
	"categoryId": 1,
	"supplierId": 1
}'
```
Get Products by category code
```sh
curl -X GET \
  'http://localhost:8090/api/products?category=laptop' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 92c00118-e1a3-40b2-87c4-9bd1ade8e3df'
```

### Order API
Get Order by ID
```sh
curl -X GET \
  http://localhost:8090/api/orders/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 5bc748b9-2ada-43ea-ab97-b21e08f7efd2'
```
Create Order
```sh
curl -X POST \
  http://localhost:8090/api/orders \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a55ce0ad-28ea-41cb-857a-c28e42875087' \
  -d '{
    "orderStatus": "Ordered",
    "orderedDate": "2020-08-03T12:46:19.389+00:00",
    "orderTotal": 999.8,
    "orderItems": [
        {
            "name": "iPad Mini 2018",
            "code": "IPADMINI2018-1",
            "brand": "Apple",
            "price": 499.9,
            "color": "White",
            "size": "Mini"
        }
    ],
    "shipment": {
        "shipmentCode": "FEDEX111111",
        "shipmentMethod": "FEDEX Express",
        "shippingCharge": 20.9,
        "shippingAddress": "110 Nguyen Thi Thap, District 2, HCMC"
    },
    "payment": {
        "code": "VISAPA0009",
        "amount": 9998.8,
        "cardNumber": "400000111199999",
        "cardType": "VISA"
    }
}'
```
