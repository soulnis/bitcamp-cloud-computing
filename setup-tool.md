#프로그래밍 준비
## 개발 도구 설치
- openjdk 10.0.1
- eclipse photon (JAVA EE IDE)
- visual studio code
- git client
- scoop(package manager) 
- scoop install gradle 설치
- scoop install mariadb
- scoop install mariadb@10.3.7 OR
- scoop install mysql 설치
- scoop install nodejs 설치(node package managment npm 자바스크립트 라이브러리 관리)
-예제는 106기에서 다운받는다.

## mysql 설정
'''
서버에 접속
> mysql -u root -p
Enter password: (암호 없기 때문에 그냥 엔터 친다.)

root사용자의 암호 설정  (authentication_string 사용자암호 저장)
mysql> use mysql;
mysql> update user set  
authentication_string=password('1111') 
where user='root';

암호 변경 후 권한 갱신
mysql> flush privileges;
mysql> quit

다시접속
>mysqld -u root -p 1111
Enter password : 1111

애플리케이션에서 DB에 접속할 때 사용할 사용자 추가
mysql> create user 'study'@'localhost' identified by '1111';

stydy 사용자가 사용할 데이터베이스 생성
mysql> create database studydb character set utf8 collate utf8_general_ci;

studydb의 데이터베이스의 사용 권한을 study 사용자에게 부여
mysql> grant all privileges on studydb.* to 'study'@'localhost';

study 사용자로 접속한다.
mysql> quit 또는 exit
> mysql -u study -p
Enter password : 1111

study 사용자가 사용할수있는 데이터 목록 보기.
mysql> show databases;

사용할 데이터베이스 선택
mysql > use studydb;

studydb에 존재하는 테이블 목록 보기
mysql> show tables;

studydb에 테이블 생성
=> bitcamp-sql/pms-ddl.sql 실행
mysql> SQL을 복사하여 붙여넣는다.

'''
## Tomcat 설치 (자바 기반이란 JDK설치돼있어야함)
...
1) 톰캣 다운로드
    - tomcat.apache.org 에서 다운로드
2) 톰캣 설치
    - C:\apps 폴더에 압축 푼다.
...

## eclise 설정
...

워크스페이스 설정 
1. 문자집합을 utf-8 설정하기
 - 도구 Windows/Preferences/General/Workspace/Text file encoding
    -utf-8로 설정.
2. 에이터 기본 설정
 -도구 Windows/Preferences/General/Editors/Text Editors
    -탭 크기를 2 또는 4로 설정한다.
    -탭을 스페이스로 표현(insert spaces for tabs 체크)
    -한 줄 크기는 80자 정도.(show print margin 체크)
    -Allow editors to override the margin column 체크
    -탭이나 공백에 대해 흐릿하게 표시
3. 자바 환경 설정
 -도구 Windows/Preferences/java/
    - Installed JREs : JDK위치 지정하기
    - Code Style/Formatter :  자바 에디터 탭 정보 설정
    - Compiler: 기본 컴파일 버전 설정 
4. 웹 환경 설정
 -도구 Windows/Preferences/Web/
    -CSS Files : 문자 집합 UTF-8로 설정
    -HTML Files : 문자 집합을 UTF-8로 설정
    -JSP Files : 문자 집합을 UTF-8로 설정
5. WAS 서버 설정
 -도구 Windows/Preferences/Server/
    -Rumtime Enviroments : 톰캣 서버의 파일 위치 설정
6. 애플리케이션을 배포하고 테스트할 톰캣 실행 환경 설정
 - Servers 뷰
    - 테스트용 실행 서버 추가 

...

## 웹 프로젝트 폴더 준비
...
1.예제 프로젝트 복사
java106-java-project를 
bitcamp-cloud-compting 폴더로 복사한다.
2.프로젝트 폴더를 이클립스 프로젝트로 만든다.
-파월쉘에서 해당 프로젝트로 접근하여
 'gradle eclipse'를 실행하여 이클립스 설정파일을 생성.
    -.project,.classpath,.settings/ 등이 있어만 이클립스를가 읽을수있는 프로젝트로 인식
...
