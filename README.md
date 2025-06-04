
# API 명세서

## 공통 정보

- 인증 방식: `Authorization: Bearer <JWT_TOKEN>`
- 데이터 형식: `application/json`
- HTTP 상태 코드: `200 OK`, `204 No Content`, `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `409 Conflict`

---
## 1. 회원가입 API 명세서
| 항목             | 내용                     |
| -------------- | ---------------------- |
| **HTTP Method** | `POST`                 |
| **URL**  | `/api/member/register` |
| **설명**         | 사용자가 이메일과 비밀번호로 회원가입   |

### 요구사항

- 이메일 형식 유효성 
- 비밀번호: 영문 대소문자 + 숫자 + 특수문자 1자 이상 포함, 최소 8자 
- 이메일 중복 가입 불가

### 요청 예시
```json
{
  "email": "test@example.com",
  "password": "Password123!",
  "nickname": "kun"
}
```
### 응답 예시
```json
{
    "message": "회원가입이 완료되었습니다.",
    "userId": 1
}

```
### 예외
| 코드    | 사유             |
| ----- | -------------- |
| `400` | 이메일/비밀번호 형식 오류 |
| `409` | 이메일 중복         |
| `500` | 서버 내부 오류       |

---
## 로그인 API 명세서
| 항목               | 내용                                |
| ---------------- | --------------------------------- |
| **HTTP Method**  | `POST`                            |
| **URL**          | `/api/member/login`               |
| **설명**           | 사용자가 이메일/비밀번호로 로그인하여 JWT 토큰을 발급받음 |


### 요청 예시
```json
{
  "email": "test@example.com",
  "password": "Password123!"
}
```
### 응답 예시
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
### 예외
| 코드    | 사유              |
| ----- | --------------- |
| `401` | 이메일 또는 비밀번호 불일치 |
| `404` | 존재하지 않는 사용자     |
| `500` | 서버 내부 오류        |

#### Authorization 헤더 없음 / 잘못된 형식
```json
{
  "error": "401",
  "message": "토큰이 유효하지 않습니다."
}
```
#### JWT 토큰 만료됨
```json
{
  "error": "401",
  "message": "토큰이 만료되었습니다."
}
```
<br/>

---
<br/>

---
# 2. 프로필, 회원가입

## 2 - 1. 내 프로필 조회

| 항목               | 내용      |
| ---------------- |---------|
| **HTTP Method**  | `GET`   |
| **URL**          | `/api/member/me` |
| **설명**           | 현재 로그인한 사용자의 프로필을 조회합니다. |

### 응답 예시

```json
{
  "email": "test@example.com",
  "image": "https://example.com/new-image.jpg",
  "nickname": "ahyoung"
}
```

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 401 | 인증 토큰 누락 또는 만료 |

---

## 2 - 2 내 프로필 수정
| 항목               | 내용                      |
| ---------------- |-------------------------|
| **HTTP Method**  | `PATCH`                 |
| **URL**          | `/api/member/me`        |
| **설명**           | 현재 로그인한 사용자의 정보를 수정합니다. |

### 요청 예시

```json
{
  "image": "https://example.com/new-image.jpg",
  "nickname": "newnickname"
}
```

### 응답 예시

```json
{
  "image": "https://example.com/new-image.jpg",
  "email":"ahyoung@example.com",
  "nickname": "newnickname"
}
```

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 400 | 정보 누락 |
| 409 | 정보 중복 |

---

## 2 - 3 비밀번호 변경
| 항목               | 내용                        |
| ---------------- |---------------------------|
| **HTTP Method**  | `PATCH`                   |
| **URL**          | `/api/member/me/password` |
| **설명**           | 현재 로그인한 사용자의 비밀번호를 변경합니다. |


- **요구사항**
    - 본인 확인을 위해 현재 비밀번호를  입력하여 같은 경우에만 변경 가능
    - 현재 비밀번호와 동일한 비밀번호로는 변경 불가
    - 비밀번호 형식이 올바르지 않은 경우 변경 불가

### 요청 예시

```json
{
  "currentPassword": "oldpass123",
  "newPassword": "newpass456"
}
```

### 응답 예시

```json
{
  "message": "비밀번호가 변경되었습니다."
}
```

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 400 | 정보 누락 |
| 403 | 현재 비밀번호 기존과 다름 |
| 409 | 새 비밀번호가 기존과 동일 |

---

## 2 - 4 회원 탈퇴

| 항목               | 내용                         |
| ---------------- |----------------------------|
| **HTTP Method**  | `DELETE`                   |
| **URL**          | `/api/member/status`       |
| **설명**           | 현재 로그인한 사용자의 계정을 탈퇴 처리합니다. |


- **요구사항**
    - 탈퇴 처리 시 비밀번호를 확인한 후 일치할 때 탈퇴 처리 (soft delete)
    - 탈퇴한 사용자의 아이디는 재사용 불가, 복구 불가
    - 사용자 아이디와 비밀번호가 일치하지 않는 경우, 이미 탈퇴한 사용자 아이디인 경우 예외처리

### 요청 예시

```json
{
  "password": "userPassword123"
}
```

### 응답 예시

- HTTP 204 No Content

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 400 | 정보 누락 |
| 403 | 비밀번호 기존과 다름 |


<br/>

---
## 2 - 5 다른 사용자 프로필 조회


| 항목               | 내용                            |
| ---------------- |-------------------------------|
| **HTTP Method**  | `GET`                         |
| **URL**          | `/api/member/{id}`            |
| **설명**           | 자신이 아닌 다른 사용자의 공개 프로필을 조회합니다. |


- **요구사항:** 자신이 아닌 경우, email, password같은 민감정보는 제거

### 응답 예시

```json
{
  "image": "https://example.com/image.jpg",
  "nickname": "ahyoung"
}
```

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 404 | 사용자가 존재하지 않음 |

<br/>

---

<br/>

---
# 3. 게시글
## 3 - 1 게시물  작성  

| 항목               | 내용          |
| ---------------- |-------------|
| **HTTP Method**  | `POST`      |
| **URL**          | `/api/post` |
| **설명**           | 게시물을 작성합니다. |




###  요청 예시

``` Json  
{
    "content" : "내용"
 }   
```  
<br/>

### 응답 예시
<br/>
성공  

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

## 3 - 2 게시물 전체조회


| 항목               | 내용             |
| ---------------- |----------------|
| **HTTP Method**  | `GET`          |
| **URL**          | `/api/post`    |
| **설명**           | 게시물을 전체 조회합니다. |


###  요청 예시

| 키    | 데이터 타입 | 설명          |
|------|--------|-------------|
| page | int    | 페이지         |
| size | int    | 페이지 당 데이터 수 |


``` Json  
{
  "page": 0,
  "size": 10
}
```  
<br/>

### 응답 예시
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

---


## 3 - 3 게시물 단건조회

| 항목               | 내용                   |
| ---------------- |----------------------|
| **HTTP Method**  | `GET`                |
| **URL**          | `/api/post/{postId}` |
| **설명**           | 게시물을 단건 조회합니다.       |


###  요청 예시

Param 

| 키      | 데이터 타입 | 설명     |
|--------|--------|--------|
| postId | Long   | 게시물아이디 |




<br/>

### 응답 예시
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

---
# 3 - 4 게시물 수정 

| 항목               | 내용                   |
| ---------------- |----------------------|
| **HTTP Method**  | `PUT`                |
| **URL**          | `/api/post/{postID}` |
| **설명**           | 게시물을 수정합니다.          |



###  요청 예시

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
#### JSON 예시
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
<br/>
#### JSON 예시

```JSON
{
  "status" : 400,
  "message" : "수정을 실패하였습니다."
}

```

<br/>

---

# 3 - 5 게시물 삭제

| 항목               | 내용                   |
| ---------------- |----------------------|
| **HTTP Method**  | `DELETE`             |
| **URL**          | `/api/post/{postID}` |
| **설명**           | 게시물을 삭제합니다.          |


##  요청 예시

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

## 응답 예시
<br/>
성공  

| 키       | 데이터 타입 | 설명   |
|---------|--------|------|
| status  | int    | 상태코드 |
| message | String | 메세지  |
### JSON 예시
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
### JSON 예시
```JSON
{
  "status" : 400,
  "message" : "삭제를 실패하였습니다."
}

```
<br/>

---

<br/>

---
# 4. 댓글



## 4 - 1 댓글 작성
| 항목               | 내용              |
| ---------------- |-----------------|
| **HTTP Method**  | `POST`          |
| **URL**          | `/api/comments` |
| **설명**           | 댓글을 작성합니다.      |

### JSON 요청예시
```JSON
{
  "postId": 1,
  "content": "좋은 글이네요!"
}

```



### JSON 응답예시
```JSON
{
  "commentId": 10
}

```
<br/>

---

## 4 - 2 댓글 작성
| 항목               | 내용                   |
| ---------------- |----------------------|
| **HTTP Method**  | `PUT`                |
| **URL**          | `/api/comments/{id}` |
| **설명**           | 댓글을 수정합니다.           |

### JSON 요청예시
```JSON
{
  "content": "수정된 댓글 내용"
}

```



### JSON 응답예시
```JSON
{
  "commentId": 10
}

```
<br/>

---
## 4 - 3 댓글 삭제
| 항목               | 내용                   |
| ---------------- |----------------------|
| **HTTP Method**  | `DELETE`             |
| **URL**          | `/api/comments/{id}` |
| **설명**           | 댓글을 삭제합니다.           |
<br/>

---

## 4 - 4 게시글 댓글 조회
| 항목               | 내용                                |
| ---------------- |-----------------------------------|
| **HTTP Method**  | `GET`                             |
| **URL**          | `/api/comments/{postId}/comments` |
| **설명**           | 게시물의 댓글을 조회합니다.                   |




### JSON 응답예시
```JSON
{
  "commentId": 10,
  "content": "댓글"
}

```
<br/>

---
<br/>

---
# 5. 좋아요



## 5 - 1 게시글 좋아요

| 항목               | 내용                         |
| ---------------- |----------------------------|
| **HTTP Method**  | `POST`                     |
| **URL**          | `/api/posts/{postId}/likes` |
| **설명**           | 게시물의 좋아요를 등록합니다.           |

### JSON 응답예시
```JSON
{
  "liked": true
}

```

<br/>

---

## 5 - 2 게시글 좋아요 취소

| 항목               | 내용                          |
| ---------------- |-----------------------------|
| **HTTP Method**  | `DELETE`                    |
| **URL**          | `/api/posts/{postId}/likes` |
| **설명**           | 게시물의 좋아요를 취소합니다.            |
### JSON 응답예시
```JSON
{
  "liked": false
}

```
<br/>

---

## 5 - 3 게시글 좋아요 수 조회

| 항목               | 내용                          |
| ---------------- |-----------------------------|
| **HTTP Method**  | `GET`                       |
| **URL**          | `/api/posts/{postId}/likes` |
| **설명**           | 게시물의 좋아요를 조회합니다.            |

### JSON 응답예시
```JSON
{
  "count": 5
}

```
<br/>

---

<br/>

---
# 6. 댓글 좋아요




## 6 - 1 댓글 좋아요

| 항목               | 내용                             |
| ---------------- |--------------------------------|
| **HTTP Method**  | `POST`                         |
| **URL**          | `/api/comments/{postId}/likes` |
| **설명**           | 댓글의 좋아요를 등록합니다.                |

### JSON 응답예시
```JSON
{
  "liked": true
}

```
<br/>

---
## 6 - 2 댓글 좋아요 취소

| 항목               | 내용                             |
| ---------------- |--------------------------------|
| **HTTP Method**  | `DELETE`                       |
| **URL**          | `/api/comments/{postId}/likes` |
| **설명**           | 댓글의 좋아요를 취소합니다.                |
### JSON 응답예시
```JSON
{
  "liked" : false
}

```
<br/>

---

## 6 - 3 댓글 좋아요 수 조회

| 항목               | 내용                             |
| ---------------- |--------------------------------|
| **HTTP Method**  | `GET`                            |
| **URL**          | `/api/comments/{postId}/likes` |
| **설명**           | 댓글의 좋아요를 조회합니다.                |

### JSON 응답예시
```JSON
{
  "count" : 3
}

```

<br/>

---
<br/>

---
# 7. 팔로우



## 7 - 1 팔로우 하기

| 항목               | 내용             |
| ---------------- |----------------|
| **HTTP Method**  | `POST`         |
| **URL**          | `/api/follows` |
| **설명**           | 팔로우를 합니다.      |

### JSON 요청예시
```JSON
{
  "targetMemberId": 2
}

```

### JSON 응답예시
```JSON
{
  "followerId": 1,
  "followingId": 2,
  "followingNickName": "테스트유저2",
  "followingEmail": "member2@example.com"
}

```
<br/>

---
## 7 - 2 팔로우 맴버 조회

| 항목               | 내용                        |
| ---------------- |---------------------------|
| **HTTP Method**  | `GET`                     |
| **URL**          | `/api/follows/followings` |
| **설명**           | 팔로우 멤버 조회를 합니다.           |



### JSON 응답예시
```JSON
{
  "followingMemberId": 2,
  "followingMemberNickname": "테스트유저2",
  "followingMemberEmail": "member2@example.com"},
...
}

```
<br/>

---

## 7 - 3 팔로잉 맴버 조회

| 항목               | 내용                        |
| ---------------- |---------------------------|
| **HTTP Method**  | `GET`                     |
| **URL**          | `/api/follows/followings` |
| **설명**           | 팔로잉 맴버 조회를 합니다.           |

### JSON 응답예시
```JSON
{
  "followerMemberId": 1,
  "followerMemberNickname": "테스트유저1",
  "followerMemberEmail": "member1@example.com"},
...
}

```
<br/>

---
## 7 - 4 언 팔로우

| 항목               | 내용                             |
| ---------------- |--------------------------------|
| **HTTP Method**  | `DELETE`                       |
| **URL**          | `/api/follows/{targetMemberId}` |
| **설명**           | 언 팔로우합니다.                      |

<br/>

---

## 7 - 5 팔로우 맴버 게시글 조회

| 항목               | 내용                             |
| ---------------- |--------------------------------|
| **HTTP Method**  | `GET`                          |
| **URL**          | `api/follows/followings/posts` |
| **설명**           | 팔로우 맴버 게시글을 조회를 합니다.           |

### JSON 요청예시
``` Json  
{
  "page": 0,
  "size": 10
}
```  

### JSON 응답예시
```JSON
{
  "postId": 2,
  "writerId": 2,
  "writerNickname": "테스트유저2",
  "writerEmail": "member2@example.com",
  "content": "테스트게시물2",
  "createdAt": "2025-05-28 13:00:00"
}

```
<br/>

---
<br/>

---
# 8. 게시글 검색



| 항목               | 내용                 |
| ---------------- |--------------------|
| **HTTP Method**  | `GET`              |
| **URL**          | `api/posts/search` |
| **설명**           | 게시글을 검색을 합니다.     |




### JSON 요청예시
``` Json  
{
  "from": "2025-06-01T00:00:00",
  "to": "2025-06-02T23:59:59",
  "page": 0,
  "size": 10
}
```  

### JSON 응답예시
```JSON
[
  {"postId": 2,
  "writerId": 2,
  "writerNickname": "테스트유저2",
  "content": "테스트게시물2",
  "createdAt": "2025-05-28 13:00:00"
  }, ...
]

```