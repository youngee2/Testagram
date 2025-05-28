## 댓글 남기기

| 기능        | Method | URL                                   | RequestBody                              | Response                                   | 상태 |
| --------- | ------ | ------------------------------------- | ---------------------------------------- | ------------------------------------------ | -- |
| 댓글 작성     | POST   | `/api/comments`                | `{ "postId": 1, "content": "좋은 글이네요!" }` | `{ "commentId": 10 }`                      | 완료 |
| 댓글 수정     | PUT    | `/api/comments/{id}`           | `{ "content": "수정된 댓글 내용" }`             | `{ "commentId": 10 }`                      | 완료 |
| 댓글 삭제     | DELETE | `/api/comments/{id}`           | 없음                                       | 없음                                         | 완료 |
| 게시글 댓글 조회 | GET    | `/api/posts/{postId}/comments` | 없음                                       | `[ { "commentId": 10, "content": "댓글" } ]` | 완료 |



## 게시글 좋아요

| 기능           | Method | URL                                | RequestBody | Response             | 상태 |
| ------------ | ------ | ---------------------------------- | ----------- | -------------------- | -- |
| 게시글 좋아요 등록   | POST   | `/api/posts/{postId}/likes` | 없음          | `{ "liked": true }`  | 완료 |
| 게시글 좋아요 취소   | DELETE | `/api/posts/{postId}/likes` | 없음          | `{ "liked": false }` | 완료 |
| 게시글 좋아요 수 조회 | GET    | `/api/posts/{postId}/likes` | 없음          | `{ "count": 5 }`     | 완료 |



## 댓글 좋아요

| 기능          | Method | URL                                      | RequestBody | Response             | 상태 |
| ----------- | ------ | ---------------------------------------- | ----------- | -------------------- | -- |
| 댓글 좋아요 등록   | POST   | `/api/comments/{commentId}/likes` | 없음          | `{ "liked": true }`  | 완료 |
| 댓글 좋아요 취소   | DELETE | `/api/comments/{commentId}/likes` | 없음          | `{ "liked": false }` | 완료 |
| 댓글 좋아요 수 조회 | GET    | `/api/comments/{commentId}/likes` | 없음          | `{ "count": 3 }`     | 완료 |


