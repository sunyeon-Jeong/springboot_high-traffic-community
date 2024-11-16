# 게시글 검색 성능 테스트
# 시나리오1 : STRESS 테스트 (500명의 동시사용자가 초당 50번을 호출하여 분당(5분) 사용자를 50씩 늘려 서버의 지표 확인)
# 시나리오2 : Endurance 테스트 (100명의 동시사용자가 초당 100번을 호출하였을때 10분동안 서버의 지표 확인)
# 시나리오3 : PEAK 테스트 (100명의 동시사용자가 초당 50번씩 호출하다 1분에 1000명으로 사용자를 한번에 늘려 서버의 지표 확인)

from locust import HttpUser, task, between
import random

class PostSearch(HttpUser) :
    
    # 스레드 별 1~2초 사이에 여유
    wait_time = between(1, 2)
    
    # locust 실행 시 1번만 동작 ; 로그인
    def on_start(self) :
        self.client.post("/users/login", json={"username" : "mallang",
                                                "password" : "mallang123!",
                                                "nickName" : "말랑이"})
        
    @task
    def post_search(self) :
        sortStatus = random.choice(["CATEGORIES", "NEWEST", "OLDEST"])
        categoryId = random.randint(3, 12)
        title = "제목".join(str(random.randint(1, 100000)))
        
        headers = {'Content-Type' : 'application/json'}
        data = {"sortStatus" : sortStatus,
                "title" : title,
                "categoryId" : categoryId}
        
        self.client.post("/search", json = data, headers = headers)