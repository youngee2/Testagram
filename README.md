## 팔로우

| 기능            | Method | URL               | RequestBody               | Response                                                                                                                                            | 상태 |
|---------------|--------|-------------------|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------| -- |
| 팔로우 하기        | POST   | `/api/follows`    | `{ "targetMemberId": 2 }` | `{"followerId": 1, "followingId": 2, "followingNickName": "테스트유저2", "followingEmail": "member2@example.com"}`                                       | 완료 |
| 팔로우 멤버 조회     | GET    | `/api/follows/followings` | 없음| `[{"followingMemberId": 2,"followingMemberNickname": "테스트유저2","followingMemberEmail": "member2@example.com"}, ...]`                                 | 완료 |
| 팔로잉 멤버 조회     | GET    | `/api/follows/followers` | 없음| `[{"followerMemberId": 1,"followerMemberNickname": "테스트유저1","followerMemberEmail": "member1@example.com"}, ...]`                                    | 완료 |
| 언팔로우          | DELETE | `/api/follows/{targetMemberId}` | 없음| 없음                                                                                                                                                  | 완료 |
| 팔로우 멤버 게시글 조회 | GET    | `/api/follows/followings/posts` | page, size| `"postId": 2,"writerId": 2,"writerNickname": "테스트유저2","writerEmail": "member2@example.com","content": "테스트게시물2","createdAt": "2025-05-28 13:00:00"` | 완료 |

## 게시글

| 기능           | Method | URL                                | RequestBody      | Response                                                                                                                 | 상태 |
|--------------|--------| ---------------------------------- |------------------|--------------------------------------------------------------------------------------------------------------------------| -- |
| 게시글 검색       | GET    | `/api/posts/search` | from, to, page, size | `[{ "postId": 2,"writerId": 2,"writerNickname": "테스트유저2","content": "테스트게시물2","createdAt": "2025-05-28 13:00:00"}, ...]` | 완료 |



