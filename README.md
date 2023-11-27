# sparta-movie-newsfeed

[노션 링크](https://www.notion.so/12-54cd42c0d52d446fab7a43b8e05b4216?pvs=4) 

## 개요 
> 무비토크 : 해시태그 기반의 영화 리뷰 뉴스피드 서비스    

## 팀 소개
> 팀 아이뽀 (B-12) : 팀장 김재윤, 팀원 이준영, 길경남, 유민아     
> 팀 모두가 I라서 아이뽀라고 지었습니다.    
<img width="475" alt="스크린샷 2023-11-27 오전 12 38 45" src="https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/c48ab6e1-7a3f-40f8-94a5-7fbfdf5fa756">

### 역할 분담 
* 김재윤
  * 유저 도메인 및 인증/인가 담당
* 길경남
  * 댓글 도메인 및 예외처리 담당
* 이준영
  * 리뷰 조회 (쿼리) 및 해시태그 담당
* 유민아
  * 리뷰 생성/수정/삭제 (커맨드) 및 좋아요 담당

## 협업 전략 
* 브랜치 전략 : 깃허브 플로우
* 기능 추가를 시작할 때 이슈를 생성해서 앞으로 할 일을 작성
* 브랜치를 분기하고, 1커밋당 1작업을 원칙으로 함.
* 작업이 끝나면 풀리퀘스트에 관련 이슈를 등록
* 최소 1명의 리뷰를 받아야 병합 가능, 메인 브랜치에 직접적으로 푸쉬 불가능

### 성과 
<img width="938" alt="스크린샷 2023-11-27 오전 12 15 19" src="https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/b8f2f69a-6831-4a56-91e5-8419bda46f41">

* 총 약 260개의 커밋을 했고, 모든 팀원이 50 전후의 커밋을 하여 적절한 업무 분담이 이뤄졌으며, 충돌도 거의 일어나지 않음. 

## 디렉토리 구조
> 크게 `domain`과 `global`로 나누었습니다.     
> `domain` 디렉토리에서는 `user(사용자)`, `review(리뷰)`, `comment(댓글)`로 나누어서 각각 3계층으로 나누었습니다.    
> 그리고 `global` 디렉토리에서는 예외 처리 및 시큐리티를 배치해두었습니다.     
```
.
├── MovietalkApplication.java
├── domain
│   ├── comment
│   │   ├── controller
│   │   │   └── CommentController.java
│   │   ├── dto
│   │   │   ├── request
│   │   │   │   ├── CommentCreateRequestDto.java
│   │   │   │   └── CommentUpdateRequestDto.java
│   │   │   └── response
│   │   │       ├── CommentCreateResponseDto.java
│   │   │       └── CommentResponseDto.java
│   │   ├── entity
│   │   │   └── Comment.java
│   │   ├── repository
│   │   │   └── CommentRepository.java
│   │   └── service
│   │       └── CommentService.java
│   ├── model
│   │   └── BaseEntity.java
│   ├── review
│   │   ├── controller
│   │   │   ├── ReviewCommandController.java
│   │   │   └── ReviewQueryController.java
│   │   ├── dto
│   │   │   ├── request
│   │   │   │   └── ReviewRequestDto.java
│   │   │   └── response
│   │   │       ├── ReviewDetailResponseDto.java
│   │   │       ├── ReviewPreviewResponseDto.java
│   │   │       └── ReviewResponseDto.java
│   │   ├── entity
│   │   │   ├── Hashtag.java
│   │   │   ├── Like.java
│   │   │   ├── Review.java
│   │   │   └── ReviewHashtag.java
│   │   ├── enums
│   │   │   └── ReviewSort.java
│   │   ├── repository
│   │   │   ├── HashtagRepository.java
│   │   │   ├── LikeRepository.java
│   │   │   ├── ReviewHashTagRepository.java
│   │   │   └── ReviewRepository.java
│   │   └── service
│   │       ├── ReviewCommandService.java
│   │       └── ReviewQueryService.java
│   └── user
│       ├── controller
│       │   ├── AuthController.java
│       │   └── UserController.java
│       ├── dto
│       │   ├── request
│       │   │   ├── UserLoginRequestDto.java
│       │   │   ├── UserPasswordUpdateRequestDto.java
│       │   │   ├── UserProfileUpdateRequestDto.java
│       │   │   └── UserSignupRequestDto.java
│       │   └── response
│       │       └── UserProfileResponseDto.java
│       ├── entity
│       │   ├── User.java
│       │   └── UserRoleEnum.java
│       ├── repository
│       │   └── UserRepository.java
│       └── service
│           ├── AuthService.java
│           └── UserService.java
└── global
    ├── config
    │   └── security
    │       ├── UserDetailsImpl.java
    │       ├── UserDetailsServiceImpl.java
    │       ├── WebSecurityConfig.java
    │       └── jwt
    │           ├── JwtFilter.java
    │           └── JwtUtil.java
    ├── error
    │   ├── ErrorCode.java
    │   ├── ErrorResponse.java
    │   └── GlobalExceptionHandler.java
    └── exception
        ├── InvalidAccessException.java
        └── NotFoundException.java

```

## 시스템 상황 분석 및 유스케이스

### 시스템 상황 분석 
![스크린샷 2023-11-27 오전 1 05 04](https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/62ccacfd-b6de-4a98-bd6f-4fd0e92b1a88)

### 유스케이스
![스크린샷 2023-11-27 오전 1 06 23](https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/53db6d03-5c8a-4cba-9b00-829b0fbabf03)

## API 명세서
[POSTMAN 링크](https://documenter.getpostman.com/view/16720681/2s9YeD9thP)

![스크린샷 2023-11-27 오전 1 22 11](https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/1bb24746-0e23-425c-828c-d9befbca2d78)

## ERD
[ERDCLOUD](https://www.erdcloud.com/d/3knE6r8jAvmStJMua)      

![스크린샷 2023-11-23 오후 8 35 24](https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/1c6b085c-658d-409d-bbd0-42358b684c31)

## 와이어 프레임
[피그마](https://www.figma.com/file/pCPC4xKo491BRk7xrZvkZT/%EC%95%84%EC%9D%B4%EB%BD%80-%EB%AC%B4%EB%B9%84%ED%86%A0%ED%81%AC?type=design&node-id=0%3A1&mode=design&t=wUmI442pSu8BxSZy-1)   
     
![스크린샷 2023-11-21 오후 4 27 50](https://github.com/lycoris62/sparta-movie-newsfeed/assets/55584664/c4059eac-d559-43cc-b129-3499b27ea6ab)   


