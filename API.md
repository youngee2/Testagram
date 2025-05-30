# 1. 게시물  작성  
## POST
###  /api/post


###  요청 Request

| 키   | 데이터 타입 | 설명  |
|-----|--------|-----|
| content | String | 내용  |

  

### JSON 예시
``` Json  
{
    "content" : "내용"
 }   
```  
<br/>

### 응답  Response
<br/>
성공  

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |
```JSON
{
  "status" : 200,
  "message" : "게시물을 작성하였습니다."
}
```
<br/>  

실패

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |  
```JSON
{
  "status" : 400,
  "message" : "작성을 실패하였습니다."
}

```
<br/>


-----

# 2. 게시물 전체조회
## GET
###  /api/post


###  요청 Request

| 키    | 데이터 타입 | 설명          |
|------|--------|-------------|
| page | int    | 페이지         |
| size | int    | 페이지 당 데이터 수 |


### JSON 예시
``` Json  
-
```  
<br/>

### 응답  Response
<br/>
성공  

| 키           | 데이터 타입        | 설명     |
|-------------|---------------|--------|
| status      | int           | 상태코드   |
| message     | String        | 메세지    |
| posts       | page          | 페이지    |  
| -postsId    | Long          | 게시물아이디 |  
| - writerId  | Long          | 작성자아이디 |
| -  nickName | String        | 닉네임    |  
| - content   | String        | 내용     |  
| - createdAt | Localdatetime | 생성일시   |  
| - updatedAt | Localdatetime | 수정일시   |  
| pageInfo    | -             | 수정일시   |  

<br/>

```JSON
{
  "status": 200,
  "message": "전체 조회가 완료 되었습니다.",
  "posts": {
    "content": [
      {
        "postId": 2,
        "writerId": 1,
        "nickName": "테스트유저",
        "content": "내용"
      },
      {
        "postId": 1,
        "writerId": 1,
        "nickName": "테스트유저",
        "content": "내용"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "sort": {
        "empty": false,
        "unsorted": false,
        "sorted": true
      },
      "offset": 0,
      "unpaged": false,
      "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "numberOfElements": 2,
    "empty": false
  }
}
```
<br/>  

실패

| 키         | 데이터 타입        | 설명   |
|-----------|---------------|------|
| status    | int           | 상태코드 |
| message   | String        | 메세지  |  
 
```JSON
{
  "status" : 400,
  "message" : "작성을 실패하였습니다."
}

```
<br/>

--------
# 3. 게시물 단건조회
## GET
###  /api/post/{postid}


###  요청 Request
Param 

| 키      | 데이터 타입 | 설명     |
|--------|--------|--------|
| postId | Long   | 게시물아이디 |




<br/>

### 응답  Response
<br/>
성공  

| 키       | 데이터 타입        | 설명   |
|---------|---------------|------|
| status  | int           | 상태코드 |
| message | String        | 메세지  | 
| content | String        | 내용   |  
| createdAt | Localdatetime | 생성일시 |  
| updatedAt | Localdatetime | 수정일시 |  
```JSON
{
  "status": 200,
  "message": "조회를 완료했습니다.",
  "nickName": "ggg",
  "content": "dfdf",
  "createdAt": "2025-05-16",
  "updatedAt": "2025-05-16"
    }
```
<br/>  

실패

| 키         | 데이터 타입        | 설명   |
|-----------|---------------|------|
| status    | int           | 상태코드 |
| message   | String        | 메세지  |  

```JSON
{
  "status" : 400,
  "message" : "작성을 실패하였습니다."
}

```
<br/>


# 4. 게시물 수정 

## PUT
###  /api/post{postid}


###  요청 Request

| 키       | 데이터 타입 | 설명      |
|---------|--------|---------|
| postId  | long   | 게시물 아이디 |
| content | String | 내용      |



### JSON 예시
``` Json  
{
    "postId" : 123334,
    "content" : "수정 내용"
 }   
```  
<br/>

### 응답  Response
<br/>
성공  

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |
```JSON
{
  "status" : 200,
  "message" : "게시물을 수정하였습니다."
}
```
<br/>  

실패

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |  
```JSON
{
  "status" : 400,
  "message" : "수정을 실패하였습니다."
}

```
<br/>


# 5. 게시물 삭제

## DELETE
###  /api/post{postid}


###  요청 Request

| 키       | 데이터 타입 | 설명      |
|---------|--------|---------|
| postId  | long   | 게시물 아이디 |



### JSON 예시
``` Json  
{
    "postId" : 123334,

 }   
```  
<br/>

### 응답  Response
<br/>
성공  

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |
```JSON
{
  "status" : 200,
  "message" : "게시물을 삭제하였습니다."
}
```
<br/>  

실패

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |  
```JSON
{
  "status" : 400,
  "message" : "삭제를 실패하였습니다."
}

```
<br/>
