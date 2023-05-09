### 💡 프로젝트 소개
MSA와 같은 서버 아키텍쳐 구성은 많이 있어도 서버를 구성하는 방식은 대부분 WebMVC로 개발하고 서버는 동기/블락킹 방식으로 작동하는데 이것을 MSA와 맞춘 ReactiveProgramming방식으로 개발하기 위해
기존의 WebMVC로 개발하던 것들을 Reactive방식으로 Migration하는 프로젝트 입니다.!

### 트러블 슈팅
1. h2
- WebMVC에선 yaml프로퍼티 설정과 security로 해당 도메인만 해제해주면 됐으나 reactive방식에서는 그렇게 해도 404오류만 표출되어 하단의 링크를 통해 해결
<https://stackoverflow.com/questions/52949088/h2-db-not-accessible-at-localhost8080-h2-console-when-using-webflux><br>
