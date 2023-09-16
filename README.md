<h1>유어슈 백엔드 직군 사전과제</h1>

<ul>
  <li><a href="#지원자의-성명-김강연">지원자의 성명: 김강연</a></li>
  <li><a href="#데이터베이스-테이블-구조">데이터베이스 테이블 구조</a></li>
  <li><a href="#API-명세">API 명세</a></li>
  <li><a href="#구현한-예외-처리-ResponsePOSTMAN-테스트-캡쳐"></a></li>
</ul>

---
## 지원자의 성명: 김강연

## 데이터베이스 테이블 구조
!!! 사용한 MySQL 버전은 8.1.0입니다.<br/>
![유어슈 백엔드 사전과제 테이블 다이어그램](https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/30b462a5-0544-46ad-af70-955e85029870)

---

## API 명세

| URI                                        |  HTTP METHOD   |         기능         |
|--------------------------------------------|:--------------:|:------------------:|
| /users                                     |      POST      |        회원가입        |
| /users                                     |     DELETE     |        회원탈퇴        |
| /articles                                  |      POST      |       게시글 작성       |
| /articles/:articleId                       |     PATCH      |       게시글 수정       |
| /articles/:articleId                       |     DELETE     |       게시글 삭제       |
| /articles/:articleId/comments              |      POST      |       댓글 작성        |
| /articles/:articleId/comments/:commentId   |     PATCH      |       댓글 수정        |
| /articles/:articleId/comments/:commentId   |     DELETE     |       댓글 삭제        |


## 구현한 예외 처리 Response(POSTMAN 테스트 캡쳐)

### 이미 회원가입한 이메일로 회원가입을 시도한 경우
<img width="586" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/bb935b3a-ba24-42e9-81b8-caccdb69bb31">


### 올바르지 않은 비밀번호로 회원탈퇴를 시도한 경우
<img width="584" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/386d940c-b16e-4c41-b008-8f295806934e">


### 게시글의 제목을 공백, 빈 문자열, 공백 문자열로 입력하여 요청한 경우
<img width="592" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/aa292c7f-1d83-4ff9-a843-77782ae596c0">

### 게시글의 내용을 공백, 빈 문자열, 공백 문자열로 입력하여 요청한 경우
<img width="570" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/8c72bedc-6879-4366-ad47-b6512fa098b1">

### 댓글의 내용을 공백, 빈 문자열, 공백 문자열로 입력하여 요청한 경우
<img width="561" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/a57990cc-6e04-4a2b-88a2-011c1becea24">

### 작성자가 아닌 회원이 게시글을 수정/삭제하려 시도한 경우
<img width="567" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/9722ff6b-6059-4218-8040-c2465c2d23ff">

### 작성자가 아닌 회원이 댓글을 수정/삭제하려 시도한 경우
<img width="578" alt="image" src="https://github.com/bflykky/yourssu-pre-assignment/assets/67828333/4394bc30-7ae5-4884-aad8-ae0bb6eaeb88">








