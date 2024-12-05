# high-traffic Community
### 🌈 Goals
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

### 🛠️ Project Architecture
![ProjectArchitecture.png](readmeImages/ProjectArchitecture.png)
- Language : Java (JDK 17)
- Framework : SpringBoot 3.3.5
- Database : MySQL 8.0 & MyBatis, Redis 3.1.0
- Test : Locust & Python3

### ✏️ Project Docs
|**ERD**|**Sequence**|
|:---:|:---:|
|![ERD.png](readmeImages/ERD.png)|![Sequence-postSearch.png](readmeImages/Sequence-postSearch.png)|

### 🚀 Locust 성능테스트
#### 1️⃣ 데이터 준비
|                **성능테스트를 위한 DB 데이터 자동삽입**                 | **게시글 조회 시뮬레이션 코드** |
|:--------------------------------------------------------:|:-------------------:|
| ![Locust-AddPost.png](readmeImages%2FLocust-AddPost.png) |![Locust-SearchPost.png](readmeImages%2FLocust-SearchPost.png)|

#### 2️⃣ STRESS 테스트
- 500명의 동시 사용자가 초당 50번 호출 →  분당(5분) 사용자를 50씩 늘린 후 지표확인

|                            **개선 전**                            |                            **개선 후**                            |
|:--------------------------------------------------------------:|:--------------------------------------------------------------:|
| ![Locust-Stress-B.png](readmeImages%2FLocust-Stress-B.png)<br>![Locust-Stress-B-Chart.png](readmeImages%2FLocust-Stress-B-Chart.png) | ![Locust-Stress-A.png](readmeImages%2FLocust-Stress-A.png)<br>![Locust-Stress-A-Chart.png](readmeImages%2FLocust-Stress-A-Chart.png) |

| **비교항목**       |**개선 전**|**개선 후**| **개선효과**    |
|----------------|-------|-------|-------------|
| 요청 합계          |25,646|118,879| - 4.6배 증가   |
| 평균 응답 시간(ms)   |5309.41|6.01| - 99.88% 감소 |
| 초당 평균 요청 수(RPS)|85.21|394.09| - 4.6배 증가   |

#### 3️⃣ ENDURANCE 테스트
- 100명의 동시 사용자가 초당 100번을 호출 → 10분 동안의 지표확인

|                               **개선 전**                                |                               **개선 후**                               |
|:---------------------------------------------------------------------:|:--------------------------------------------------------------------:|
| ![Locust-Endurance-B.png](readmeImages%2FLocust-Endurance-B.png)<br>![Locust-Endurance-B-Chart.png](readmeImages%2FLocust-Endurance-B-Chart.png)|![Locust-Endurance-A.png](readmeImages%2FLocust-Endurance-A.png)<br>![Locust-Endurance-A-Chart.png](readmeImages%2FLocust-Endurance-A-Chart.png)|

|**비교항목**|**개선 전**|**개선 후**| **개선효과**   |
|---|---|---|------------|
|요청 합계|38,883|388,759| - 10배 증가   |
|평균 응답 시간(ms)|43.22|25.75| - 40.5% 감소 |
|초당 평균 요청 수(RPS)|64.8|664.4| - 10배 증가   |

#### 4️⃣ PEAK 테스트
- 100명의 동시 사용자가 초당 50번씩 호출 → 1분에 1000명으로 사용자를 한번에 늘려 지표확인

|**개선 전**| **개선 후** |
|:---:|:--------:|
|![Locust-Peak-B.png](readmeImages%2FLocust-Peak-B.png)<br>![Locust-Peak-B-Chart.png](readmeImages%2FLocust-Peak-B-Chart.png)|![Locust-Peak-A.png](readmeImages%2FLocust-Peak-A.png)<br>![Locust-Peak-A-Chart.png](readmeImages%2FLocust-Peak-A-Chart.png)|

|**비교항목**|**개선 전**|**개선 후**| **개선효과**   |
|---|---|---|------------|
|요청 합계|9,180|37,919| - 4배 증가    |
|평균 응답 시간(ms)|3992.89|16.67| - 99.6% 감소 |
|초당 평균 요청 수(RPS)|75.88|313.84| - 4.1배 증가  |

### 🌈 성능개선 Point
#### 1️⃣ 데이터베이스 단
- 쿼리 성능 분석

| **개선 전**|                                                   **개선 후** |
|-----------------------------------------------------------|---|
| ![PostSearchQuery.png](readmeImages%2FPostSearchQuery.png) | ![PostSearchQuery.png](readmeImages%2FPostSearchQuery.png) |
| - Rows : 10,261개(전체 데이터) <br> - Filtered : 11.11%|             - Rows : 10,261개(전체 데이터) <br> - Filtered : 50% |

- 개선 방법
  - `title` column에 인덱스 추가
  - 전체 테이블을 스캔하지 않고 인덱스를 활용하여 검색 성능 향상
  - 조건 부합 데이터 비율이 50%로 4.5배 개선완료

#### 2️⃣ 어플리케이션 단
- **Redis 만료시간 축소**
  - `3,600`ms → `600`ms
  - 메모리 공간 효율적 운영 가능
  - 작은 데이터 집합으로 처리성능 최적화
  - 최신데이터 유지로 접근시간 최적화


- **서비스 단 검색 메서드 비동기처리**
  - `@Async` 어노테이션 사용
  - 메서드 호출을 비동기로 실행하여 응답속도 최적화
  - 병렬로 별도의 스레드에서 메서드가 실행되므로 메인 스레드가 차단되지 않음


- **서비스 단 검색 메서드 캐시 키 설정**
  - `@Cacheable(key = "A+B+C")` 캐시 키 설정
  - 캐시 키를 요청 파라미터에 포함시켜 캐시 적중률을 높이고, 이로인해 반복적인 외부 데이터 접근이 줄어들면서 데이터 조회속도 최적화

### 🎯 Trouble Shooting
#### 1️⃣ String Parameter를 활용한 application.properties 민감정보 관리
- 👀 [See details on velog](https://velog.io/@mallang/TroubleShooting-Jenkins-Build-Failed-String-Parameter를-활용한-application.properties-민감정보-관리)

#### 2️⃣ Amazon Linux 인스턴스 Jenkins 설치에러
- 👀 [See details on velog](https://velog.io/@mallang/TroubleShooting-CD-EC2-Amazon-Linux-인스턴스에-Jenkins-설치-에러)

#### 3️⃣ 프로젝트 목표 도달 관련
- 목표 : 1000TPS 이상의 게시글검색 API 구현
- 스펙 비교

  | 구분 |로컬 개발 환경|평균 서버 환경(가정)|
  |:---:|:---:|:---:|
  | CPU |1.4 GHz 쿼드 코어 Intel Core i5|Intel Xeon 이상|
  |RAM|8GB|32~64GB|
  |Storage|512GB|512GB|

  - 평균 서버 환경이 위와 같다고 가정할 때, 로컬 개발 환경에 비해 약 5배 이상 연산 성능이 차이남
- 결론 : 로컬 개발 환경의 성능 한계를 고려하여, 상기 Locust TPS 수치를 5배로 상향 조정한 값을 최종 TPS 수치로 본다