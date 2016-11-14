与IAM组件集成，itoken使用用户登录IAM后Cookie中的itoken，每一次请求均先验证itoken中租户

##1、Service API
service名称包括租户id和自定义名称两部分组成，每次service请求，使用租户id匹配service名称来实现指定租户的service操作
###Create service
```
POST /api/app/{tenant}/services -d 'JSON字符串'
```
提交数据（JSON）：
* Name:自定义service名称，后端会加前缀-租户id，最终生成的service名称为"<租户id>__<自定义service名称>"
* TaskTemplate：
* ContainerSpec：
 - Image：选择service的镜像
 - Mounts：卷信息，{Target: 容器路径, Source: 宿主主机卷}
 - Env：环境变量，list，["env1=v1","env2=v2"...]

测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=eyJhbGciOiJOR0lOWE1ENSIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ3d3cuaW1haWNsb3VkLmNvbSIsImlzcyI6ImlhbS5pbnNwdXIuY29tIiwiZXhwIjoxNDc3OTkwODU4NDE4LCJpYXQiOjE0Nzc5ODkwNTg0MTgsImlkIjoiZEo1eXZvSEtTcXFGbUdfaFkxT2wzUSIsInVuYW1lIjoiMTExQHFxLmNvbSIsInVpZCI6IjExMUBxcS5jb20iLCJ0bnQiOiJGV3ZkMmk3ZFJRR3R6TWVDRkNIRzV3IiwiZ3JvdXAiOiIifQ.BQcKI3Y9jYq33_Uu4s8W6Q' \ 
-X POST http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/services/create 
-d '{"Name":"service-tomcat__8080","TaskTemplate":{"ContainerSpec":{"Image":"tomcat"}}}'
```
###List service
```
GET /api/app/{tenant}/services
```
列出租户所有service  
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X GET http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/services
```

###Inspect service
```
GET /api/app/{tenant}/services/{service_id}
```
查看指定service  
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X GET http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/services/21opqnqg8ss5cp9bqq17pyzt1
```

###Update a service
```
PUT /api/app/{tenant}/services/${service_id}?version=<service-version> -d '更新数据JSON'
```
更新指定service  
提交数据JSON：
* 同Create a service
查询参数：
* version：指定service的版本号
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X PUT http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/services/21opqnqg8ss5cp9bqq17pyzt1?version=191
-d '{"Name":"service-tomcat__8080","TaskTemplate":{"ContainerSpec":{"Image":"tomcat"}}}'
```

###Delete a service
```
DELETE /api/app/{tenant}/services/${service_id}
```
删除指定service  
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X DELETE http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/services/21opqnqg8ss5cp9bqq17pyzt1
```

###List all containers
```
GET /api/app/{tenant}/services/${service_id}/containers
```
列出指定租户下指定service的所有container


###Start a service
```
POST /api/app/{tenant}/services/{service_id}/start
```
通过查询出service下的所有container，然后循环启动container来实现service的启动，前提是service不能自动重启

###Stop a service
```
POST /api/app/{tenant}/services/{service_id}/stop
```
停止service，原理同start

##2、Container API
使用租户id匹配container的label来实现指定租户的service操作：com.dc.inspur.tenant=<tenant-id>
###List containers
```
GET /api/app/<tenant>/containers
```
列出指定租户下所有container  
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X GET http://dev.imaicloud.com/dc/api/app/ukqkrj-nspxdh/containers
```

###Inspect a container
```
GET /api/app/<tenant>/containers/<container-id>
```
查询指定container

###Start a container
```
POST /api/app/<tenant>/containers/<container-id>/start
```
启动container

###Stop a container
```
POST /api/app/<tenant>/containers/<container-id>/stop
```
停止container

###Remove a container
```
DELETE /api/app/<tenant>/containers/<container-id>
```
删除container
