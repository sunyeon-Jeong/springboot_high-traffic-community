# 성능 테스트를 위한 DB 데이터 삽입
# - Category : 10개 생성
# - Post : 10만개 생성

from locust import HttpUser, task, between
import random

class AddPosts(HttpUser) :
    
    # 스레드 별 1~2초 사이에 여유
    wait_time = between(1, 2)
    
    # locust 실행 시 1번만 동작 ; 로그인
    def on_start(self) :
        self.client.post("/users/login", json={"username" : "mallang",
                                                "password" : "mallang123!",
                                                "nickName" : "말랑이"})
        
    # locust 스레드 단위
    @task
    def add_post(self) :
        self.client.post("/posts/create-post", json={
            "title" : "제목" + str(random.randint(1, 100000)),
            "contents" : "본문" + str(random.randint(1, 100000)),
            "fileId" : random.randint(1, 10),
            "categoryId" : random.randint(3, 12)
        })