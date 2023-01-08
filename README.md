# 회원 관리 서비스

## 회원 정보

- 이메일
  - [x] 이메일 ID는 `알파벳 대소문자`, `숫자`, `.`, `_`, `-` 만 가능하다.
  - [x] 이메일 도메인은 `알파벳 대소문자`, `숫자`, `.`, `-` 만 가능하다.
  - [x] 이메일 최상위 도메인은 `2~6 자 사이의 알파벳 대소문자`만 가능하다.
- 닉네임
  - [x] 닉네임은 `알파벳 대소문자`, `숫자`, `한글` 로만 이루어질 수 있다.
  - [x] 닉네임은 `2~10 자 사이`여야 한다.
- 비밀번호
  - [x] 비밀번호는 `알파벳 대소문자`, `숫자`, `특수문자` 로 이루어질 수 있다.
  - [x] 비밀번호는 `8~20 자 사이`여야 한다.
  - [x] 비밀번호는 `숫자`, `알파벳 대소문자`, `특수문자` 3 가지를 모두 포함해야 한다.
- 이름
  - [x] 이름은 `알파벳 대소문자`, `한글`, `중간 공백` 으로만 이루어질 수 있다. 
  - [x] 이름은 `2~100 자 사이`여야 한다.
- 전화번호
  - [x] 전화번호는 `숫자` 로만 이루어질 수 있다.
  - [x] 전화번호는 `숫자 10~11 자 사이`여야 한다.

## 회원 기능

- [x] 회원가입
  - [x] 이미 중복된 이메일이 존재하면 회원가입을 할 수 없다.
  - [x] 이미 중복된 닉네임이 존재하면 회원가입을 할 수 없다.
  - [x] 이미 중복된 전화번호가 존재하면 회원가입을 할 수 없다.
  - [x] 전화번호 인증 필요
- [x] 로그인
  - [x] 이메일 + 비밀번호
  - [x] 닉네임 + 비밀번호
  - [x] 전화번호 + 비밀번호
- [x] 내 정보 보기 기능
  - [x] 로그인 필요
  - [x] 나의 정보만 볼 수 있음
- [ ] 비밀번호 찾기 (재설정)
  - [ ] 전화번호 인증 필요
