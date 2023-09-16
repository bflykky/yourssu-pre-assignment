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




