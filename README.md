与IAM组件集成，itoken使用用户登录IAM后Cookie中的itoken，每一次请求均先验证itoken中租户

##1、Service API
service名称包括租户id和自定义名称两部分组成，每次service请求，使用租户id匹配service名称来实现指定租户的service操作
###Create service
```
POST /api/app/{tenant}/services -d 'JSON字符串'
```
提交数据（JSON）：
* Name:自定义service名称，后端会加前缀-租户id，最终生成的service名称为"<租户id>__<自定义service名称>"
* TaskTemplate： ContainerSpec：
                    - Image：选择service的镜像名称
 					- Mounts：卷信息，{Target: 容器路径, Source: 宿主主机卷}
 					- Env：环境变量，list，["env1=v1","env2=v2"...]
* Mode: 
 	- Replicated: Replicas: 定义服务的container数量
* EndpointSpec: Ports: 定义服务暴露的端口号，{TargetPort: 容器端口号, Protocol: 协议类型(tcp/udp)}

测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=eyJhbGciOiJOR0lOWE1ENSIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ3d3cuaW1haWNsb3VkLmNvbSIsImlzcyI6ImlhbS5pbnNwdXIuY29tIiwiZXhwIjoxNDc3OTkwODU4NDE4LCJpYXQiOjE0Nzc5ODkwNTg0MTgsImlkIjoiZEo1eXZvSEtTcXFGbUdfaFkxT2wzUSIsInVuYW1lIjoiMTExQHFxLmNvbSIsInVpZCI6IjExMUBxcS5jb20iLCJ0bnQiOiJGV3ZkMmk3ZFJRR3R6TWVDRkNIRzV3IiwiZ3JvdXAiOiIifQ.BQcKI3Y9jYq33_Uu4s8W6Q' \ 
-X POST https://dev.imaicloud.com/dc/api/app/fwvd2i7drqgtzmecfchg5w/services 
-d '{"Name":"stomcat","TaskTemplate":{"ContainerSpec":{"Image":"tomcat"}}, "Mode":{"Replicated":{"Replicas": 1}}, "EndpointSpec": {"Ports":[{"TargetPort":8080, "Protocol":"tcp"}]} }'
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
-X GET http://dev.imaicloud.com/dc/api/app/fwvd2i7drqgtzmecfchg5w/services
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
-X GET http://dev.imaicloud.com/dc/api/app/fwvd2i7drqgtzmecfchg5w/services/aj1aqoqrqjt53h0dwq9sfsolr
```

###Update a service
```
PUT /api/app/{tenant}/services/{service_id}?version=<service-version> -d '更新数据JSON'
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
-X PUT http://dev.imaicloud.com/dc/api/app/fwvd2i7drqgtzmecfchg5w/services/aj1aqoqrqjt53h0dwq9sfsolr?version=191
-d '{"Mode":{"Replicated":{"Replicas": 2}}}'
```

###Delete a service
```
DELETE /api/app/{tenant}/services/{service_id}
```
删除指定service  
测试：
```
curl -H 'Content-type:application/json' \ 
-b 'itoken=<iam itoken>' 
-X DELETE http://dev.imaicloud.com/dc/api/app/fwvd2i7drqgtzmecfchg5w/services/aj1aqoqrqjt53h0dwq9sfsolr
```

###List all containers
```
GET /api/app/{tenant}/services/{service_id}/containers
```
列出指定租户下指定service的所有container


##2、Container API
使用租户id匹配container的label来实现指定租户的service操作：com.dc.inspur.tenant=< tenant-id >

###Inspect a container
```
GET /api/app/{tenant}/containers/{container-id}?node-id={node-id}
```
查询指定container
查询参数：node-id: docker节点id

###Start a container
```
POST /api/app/{tenant}/containers/{container-id}/start?node-id={node-id}
```
启动container

###Stop a container
```
POST /api/app/{tenant}/containers/{container-id}/stop?node-id={node-id}
```
停止container

###Remove a container
```
DELETE /api/app/{tenant}/containers/{container-id}?node-id={node-id}
```
删除container

###Container log
```
WS /ws/api/app/{tenant}/containers/{container_id}/logs?node-id={node-id}
```
查询container的启动log  


