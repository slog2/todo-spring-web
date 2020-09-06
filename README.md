# todo-spring-web

## 개요
spring boot + kotlin 환경에서 기본적인 rest api 를 구현해본다.
(할일목록 API)

## 개발 환경
- spring boot 2.3.2
- kotlin 1.3.72
- maven

## 코드 구성
- todo controller
- todo service
- todo data class
- error handler (controller advice)


## API TEST with curl

### create todo
curl -i -X POST http://localhost:8080/todo -H 'content-type: application/json' -d '{"id":11, "name":"do anything"}'

### create todo (json parse error 케이스)
curl -i -X POST http://localhost:8080/todo -H 'content-type: application/json' -d '{{"id":11, "name":"do anything"}'

### update todo
curl -i -X PUT http://localhost:8080/todo/11 -H 'content-type: application/json' -d '{"id":11, "name":"do anything!!!!"}'

### get todo
curl -i http://localhost:8080/todo/11

### get todos
curl -i http://localhost:8080/todos?nameFilter=anything

### delete todo
curl -i -X DELETE http://localhost:8080/todo/11

