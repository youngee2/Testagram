# API 명세서 

## 공통 정보

- 인증 방식: `Authorization: Bearer <JWT_TOKEN>`
- 데이터 형식: `application/json`
- HTTP 상태 코드: `200 OK`, `204 No Content`, `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `409 Conflict`

---

## 1. 내 프로필 조회

- **METHOD**: `GET`
- **URL**: `/api/member/me`
- **설명**: 현재 로그인한 사용자의 프로필을 조회합니다.

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

## 2. 내 프로필 수정

- **METHOD**: `PATCH`
- **URL**: `/api/member/me`
- **설명**: 현재 로그인한 사용자의 정보를 수정합니다.

### 요청 바디

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

## 3. 비밀번호 변경

- **METHOD**: `PATCH`
- **URL**: `/api/member/me/password`
- **설명**: 현재 로그인 사용자의 비밀번호를 변경합니다.
- **요구사항**
    - 본인 확인을 위해 현재 비밀번호를  입력하여 같은 경우에만 변경 가능
    - 현재 비밀번호와 동일한 비밀번호로는 변경 불가
    - 비밀번호 형식이 올바르지 않은 경우 변경 불가

### 요청 바디

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

## 4. 회원 탈퇴

- **METHOD**: `PATCH`
- **URL**: `/api/member/me/status`
- **설명**: 현재 로그인한 사용자의 계정을 탈퇴 처리합니다.
- **요구사항**
    - 탈퇴 처리 시 비밀번호를 확인한 후 일치할 때 탈퇴 처리 (soft delete)
    - 탈퇴한 사용자의 아이디는 재사용 불가, 복구 불가
    - 사용자 아이디와 비밀번호가 일치하지 않는 경우, 이미 탈퇴한 사용자 아이디인 경우 예외처리

### 요청 바디

```json
{
  "password": "userPassword123"
}
```

### 응답

- HTTP 204 No Content

### 예외

| 상태 코드 | 설명 |
| --- | --- |
| 400 | 정보 누락 |
| 403 | 비밀번호 기존과 다름 |

---

## 5. 다른 사용자 프로필 조회

- **METHOD**: `GET`
- **URL**: `/api/member/{id}`
- **설명**: 자신이 아닌 다른 사용자의 공개 프로필을 조회합니다.
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

---
