
### 과제 조건
- 이미지를 검색하여 보관함을 수집하는 안드로이드 앱 구현
- 검색 API 활용
    - 이미지 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image )
    - 동영상 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video )
    - 두 검색 결과를 datetime 필드를 이용해 정렬하여 출력합니다. (최신부터 나타나도록)
- UI 구성
    - Home Fragment
        - 검색어를 입력할 수 있습니다.
        - 검색된 이미지 리스트가 나타납니다. 각 아이템에는 이미지와 함께 날짜와 시간을 표시합니다.
        - 스크롤을 통해 다음 페이지를 불러옵니다.
        - 리스트에서 특정 이미지를 선택하여 '내 보관함'으로 저장할 수 있습니다.
        - 이미 보관된 이미지는 특별한 표시를 보여줍니다. (좋아요/별표/하트 등)
        - 보관된 이미지를 다시 선택하여 보관함에서 제거 가능합니다.
    - Bookmark Fragment
        - 검색 결과에서 보관했던 이미지들이 보관한 순서대로 보입니다.
        - 보관한 이미지 리스트는 앱 재시작 후 다시 보여야 합니다. (DB 관련 라이브러리 사용 금지. SharedPreferences 사용 권장)

### 기술 스택 및 라이브러리
- 최소 SDK 레벨 24
- Jetpack Compose 기반, Coroutines, Flow 활용
- Jetpack
    - Compose : UI를 빌드하기 위한 Android 최신 툴킷
    - ViewModel : UI 관련 데이터 홀더 및 수명 주기 인식
    - Navigation : Hilt Navigation Compose를 활용하여 종속 항목 주입 및 화면 탐색
    - Hilt : 의존성 주입
- Retrofit2 / Okhttp3 : REST API 및 네트워크 데이터 구성
- Moshi : Kotlin/Java 전용의 최신 JSON 라이브러리
- ksp : Kotlin Symbol 처리 API


### 모듈화
- app
- core
    - designsystem
    - extension
    - model
    - navigation
    - network
    - preferences
    - testing
- feature
    - home
    - bookmark
 