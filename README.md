1. 팔로우 하기
   Method: POST

URL: /api/follows

Request Body:

json
{
"memberId": 1
}
Response: 201 CREATED

json
{
"followId": 1,
"follower": {
"id": 1,
"email": "wonjun@example.com"
},
"following": {
"id": 2,
"email": "member1@example.com"
},
"followedAt": "2025-05-27 13:00:00"
}
2. 내가 팔로우한 목록 조회
   Method: GET

URL: /api/follows/followings

Response: 200 OK

json
[
{
"id": 1,
"email": "member1@example.com"
},
{
"id": 2,
"email": "member2@example.com"
}
]
3. 나를 팔로우한 목록 조회
   Method: GET

URL: /api/follows/followers

Response: 200 OK

json
[
{
"id": 1,
"email": "member1@example.com"
},
{
"id": 2,
"email": "member2@example.com"
}
]
4. 언팔로우
   Method: DELETE

URL: /api/follows?memberId=1

Query Parameter:

memberId: 언팔로우할 회원 고유식별자

Response: 204 NO_CONTENT

5. 팔로우한 사람들의 게시물 조회 (최신순)
   Method: GET

URL: /api/posts/following

Query Parameters:

page: 페이지 번호 (예: 0)

size: 페이지 당 항목 수 (예: 10)

Response:

json
{
"posts": [
{
"postId": 1,
"authorId": 1,
"authorEmail": "member1@example.com",
"content": "spring project",
"createdAt": "2025-05-27 13:00:00",
"updatedAt": "2025-05-27 13:00:00"
}
],
"currentPage": 0,
"pageSize": 10,
"totalCount": 30
}
6. 게시물 검색
   Method: GET

URL: /api/posts/search

Query Parameters:

from: 시작일 (2025-05-27)

to: 종료일 (2025-05-28)

page: 페이지 번호 (예: 0)

size: 페이지 당 항목 수 (예: 10)

Response: 200 OK

json
{
"posts": [
{
"postId": 1,
"authorId": 1,
"authorEmail": "wonjun@example.com",
"content": "spring project",
"createdAt": "2025-05-27 13:00:00",
"updatedAt": "2025-05-27 13:00:00"
}
],
"currentPage": 0,
"pageSize": 10,
"totalCount": 30
}