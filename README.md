# high-traffic Community
### 🎯 Goals
- 대용량 트래픽 처리 어플리케이션 개발 (1000TPS 이상의 게시글검색 API 구현)
- 객체지향(OOP) 및 관점지향(AOP) 프로그래밍 원칙 준수를 통한 확장성 & 유지보수성을 고려한 설계
- Redis 활용을 통한 게시글검색 API 성능 최적화 및 응답속도 향상
- AWS SNS와 Webhook을 활용한 실시간 알림서비스 구현
- Jenkins를 활용한 CI/CD 파이프라인 구축으로 개발 생산성 및 효율성 향상

### 🗂️ Implemented Feature List
|구분| 구현기능                                                                                                         |
|:---:|--------------------------------------------------------------------------------------------------------------|
|User| - 회원가입, 회원탈퇴 <br> - 로그인, 로그아웃 <br> - 회원정보 조회, 비밀번호 변경                                                        |
|Category| - 카테고리 등록, 수정, 삭제                                                                                            |
|Post| - 게시글 등록, 수정, 삭제 <br> - 사용자 게시글 조회 <br> - 사용자가 입력한 제목 & 본문 & 카테고리 기반 게시글 조회 및 정렬 <br> - 사용자가 입력한 태그 기반 게시글 조회|
|Comment| - 게시글 댓글 등록, 수정, 삭제|
|Tag|- 태그 등록, 수정, 삭제|
|SNS|- SNS 토픽 생성 <br> - 토픽 구독 <br> - 메시지 게시|

### ✏️ ERD
![ERD_대규모트래픽커뮤니티.png](..%2F..%2F..%2FDesktop%2F%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84%2F%ED%8C%A8%EC%8A%A4%ED%8A%B8%EC%BA%A0%ED%8D%BC%EC%8A%A4%2F%EB%8C%80%EC%9A%A9%EB%9F%89%20%ED%8A%B8%EB%9E%98%ED%94%BD%3A%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%B2%98%EB%A6%AC%28%EB%A7%90%EB%9E%91%29%2FProject1%2FERD_%EB%8C%80%EA%B7%9C%EB%AA%A8%ED%8A%B8%EB%9E%98%ED%94%BD%EC%BB%A4%EB%AE%A4%EB%8B%88%ED%8B%B0.png)