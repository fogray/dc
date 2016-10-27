#REST API
##Service
###1、查询所有services
GET /api/services
```
curl -H 'Content-Type: application/json' -X GET http://localhost/dc/api/services
```
参数：filters={"id":<NODE_ID>, "name":<NODE NAME>}
###2、查询指定service
GET /api/service/{SERVICE_ID}
```
curl -H 'Content-Type: application/json' -X GET http://localhost/dc/api/service/3efcd932-423e-4d72-9fab-434bf14dc3c8
```
###3、创建service
POST /api/service/create
```
curl -H 'Content-Type: application/json' -X POST http://localhost/dc/api/service/create \
-d '{"Name": "web","TaskTemplate": {"ContainerSpec": {"Image": "nginx:alpine"}}}'
```
