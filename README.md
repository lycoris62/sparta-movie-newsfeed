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

## 구현 사항 
### **필수 구현 기능**

- [x]  **사용자 인증 기능**
    - 회원가입 기능
        - 새로운 사용자가 ID와 비밀번호의 형태로 서비스에 가입할 수 있어야 합니다.
            - 이 때, 비밀번호는 안전하게 암호화되어 저장되어야 합니다!
    - 로그인 및 로그아웃 기능
        - 사용자는 자신의 계정으로 서비스에 로그인하고 로그아웃할 수 있어야 합니다.
- [x]  **프로필 관리**
    - 프로필 수정 기능
        - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
        - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
- [x]  **게시물 CRUD 기능**
    - 게시물 작성, 조회, 수정, 삭제 기능
        - 게시물 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
        - 예컨대, 내가 작성한 글을 남이 삭제할 수는 없어야 하고 오로지 본인만 삭제할 수 있어야겠죠?
    - 게시물 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 게시물 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 게시물 내용을 화면에 보여줄 수 있도록 해야 합니다!
- [x]  **뉴스 피드 기능**
    - 뉴스 피드 페이지
        - 사용자가 다른 사용자의 게시물을 한 눈에 볼 수 있는 뉴스 피드 페이지가 있어야 합니다.
     
### **[⭐](https://emojipedia.org/star/)** 추가 구현 기능 **[⭐](https://emojipedia.org/star/)** - 이것들까지 구현하면 너무 좋아요!

- [x]  **댓글 CRUD 기능**
    - 댓글 작성, 조회, 수정, 삭제 기능
        - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
        - 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용되어야 합니다.
    - 댓글 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 댓글 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 댓글 목록을 화면에 보여줄 수 있도록 해야 합니다!
        
- [ ]  **이메일 가입 및 인증 기능**
    - 이메일 가입 시 이메일 인증 기능을 포함하는 것이 좋습니다.
    
- [x]  **좋아요 기능**
    - 게시물 및 댓글 좋아요/좋아요 취소 기능
        - 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있어야 합니다.
        - 이 때, 본인이 작성한 게시물과 댓글에 좋아요는 남길 수 없도록 해봅니다!

- [ ]  **프론트엔드 만들어보기**
    - 백엔드에서 제공하는 API를 통해 서버와 통신하는 프론트엔드를 구현합니다.
    - 와이어프레임에 나온 명세를 최대한 구현해보면 금상첨화겠죠?
    - 웹개발 종합반에서 배웠던 부트스트랩을 활용해봐도 좋아요~

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


