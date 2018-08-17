-- 수강신청
DROP TABLE IF EXISTS P1_APPL RESTRICT;

-- 학생
DROP TABLE IF EXISTS P1_STUD RESTRICT;

-- 강사
DROP TABLE IF EXISTS P1_TCH RESTRICT;

-- 강의실
DROP TABLE IF EXISTS P1_CLS_ROOM RESTRICT;

-- 매니저
DROP TABLE IF EXISTS P1_MGR RESTRICT;

-- 개설교육과정
DROP TABLE IF EXISTS P1_OPEN_CORS RESTRICT;

-- 프로젝트
DROP TABLE IF EXISTS P1_PRJ RESTRICT;

-- 과목
DROP TABLE IF EXISTS P1_SUB RESTRICT;

-- 강의과목
DROP TABLE IF EXISTS P1_TCH_SUB RESTRICT;

-- 프로젝트 참여자
DROP TABLE IF EXISTS P1_PRJ_MEMB RESTRICT;

-- 교육과정 과목
DROP TABLE IF EXISTS P1_CORS_SUB RESTRICT;

-- 교육과정 수강생
DROP TABLE IF EXISTS P1_CORS_STUD RESTRICT;

-- 강의실 사진
DROP TABLE IF EXISTS P1_CR_PHOT RESTRICT;

-- 주소
DROP TABLE IF EXISTS P1_ADDR RESTRICT;

-- 교육과정
DROP TABLE IF EXISTS P1_CORS RESTRICT;

-- 회원
DROP TABLE IF EXISTS P1_MEMB RESTRICT;

-- 수강신청
CREATE TABLE P1_APPL (
	ano  INTEGER NOT NULL COMMENT '수강신청번호', -- 수강신청번호
	ocno INTEGER NOT NULL COMMENT '개설교육과정', -- 개설교육과정
	COL3 INTEGER NOT NULL DEFAULT 0 COMMENT '상태', -- 상태
	pdt  DATE    NOT NULL COMMENT '신청일', -- 신청일
	sno  INTEGER NULL     COMMENT '회원번호' -- 회원번호
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE P1_APPL
	ADD CONSTRAINT PK_P1_APPL -- 수강신청 기본키
		PRIMARY KEY (
			ano -- 수강신청번호
		);

-- 수강신청 유니크 인덱스
CREATE UNIQUE INDEX UIX_P1_APPL
	ON P1_APPL ( -- 수강신청
		ocno ASC -- 개설교육과정
	);

ALTER TABLE P1_APPL
	MODIFY COLUMN ano INTEGER NOT NULL AUTO_INCREMENT COMMENT '수강신청번호';

-- 학생
CREATE TABLE P1_STUD (
	sno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	work     CHAR(1)      NOT NULL COMMENT '재직여부', -- 재직여부
	adrno    INTEGER      NULL     COMMENT '주소번호', -- 주소번호
	det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
	lst_schl VARCHAR(50)  NOT NULL COMMENT '최종학력', -- 최종학력
	maj      VARCHAR(50)  NOT NULL COMMENT '전공' -- 전공
)
COMMENT '학생';

-- 학생
ALTER TABLE P1_STUD
	ADD CONSTRAINT PK_P1_STUD -- 학생 기본키
		PRIMARY KEY (
			sno -- 회원번호
		);

-- 강사
CREATE TABLE P1_TCH (
	mno    INTEGER NOT NULL COMMENT '강사번호', -- 강사번호
	pay_hr INTEGER NOT NULL COMMENT '강사료' -- 강사료
)
COMMENT '강사';

-- 강사
ALTER TABLE P1_TCH
	ADD CONSTRAINT PK_P1_TCH -- 강사 기본키
		PRIMARY KEY (
			mno -- 강사번호
		);

-- 강의실
CREATE TABLE P1_CLS_ROOM (
	clrno  INTEGER     NOT NULL COMMENT '강의실번호', -- 강의실번호
	loc    VARCHAR(50) NOT NULL COMMENT '센터', -- 센터
	titl   VARCHAR(50) NOT NULL COMMENT '강의실명', -- 강의실명
	mx_qty INTEGER     NOT NULL COMMENT '최대수용인원' -- 최대수용인원
)
COMMENT '강의실';

-- 강의실
ALTER TABLE P1_CLS_ROOM
	ADD CONSTRAINT PK_P1_CLS_ROOM -- 강의실 기본키
		PRIMARY KEY (
			clrno -- 강의실번호
		);

-- 강의실 유니크 인덱스
CREATE UNIQUE INDEX UIX_P1_CLS_ROOM
	ON P1_CLS_ROOM ( -- 강의실
		titl ASC, -- 강의실명
		loc ASC   -- 센터
	);

-- 매니저
CREATE TABLE P1_MGR (
	mgno INTEGER     NOT NULL COMMENT '매니저번호', -- 매니저번호
	pos  VARCHAR(50) NOT NULL COMMENT '직위' -- 직위
)
COMMENT '매니저';

-- 매니저
ALTER TABLE P1_MGR
	ADD CONSTRAINT PK_P1_MGR -- 매니저 기본키
		PRIMARY KEY (
			mgno -- 매니저번호
		);

-- 개설교육과정
CREATE TABLE P1_OPEN_CORS (
	ocno   INTEGER     NOT NULL COMMENT '개설교육과정', -- 개설교육과정
	COL    VARCHAR(50) NULL     COMMENT '교육기수', -- 교육기수
	sdt    DATE        NOT NULL COMMENT '시작일', -- 시작일
	edt    DATE        NOT NULL COMMENT '종료일', -- 종료일
	tot_hr INTEGER     NOT NULL COMMENT '교육시간', -- 교육시간
	day_hr INTEGER     NOT NULL COMMENT '일 교육시간', -- 일 교육시간
	clrno  INTEGER     NULL     COMMENT '강의실번호', -- 강의실번호
	cno    INTEGER     NOT NULL COMMENT '교육과정번호', -- 교육과정번호
	mgno   INTEGER     NULL     COMMENT '매니저번호' -- 매니저번호
)
COMMENT '개설교육과정';

-- 개설교육과정
ALTER TABLE P1_OPEN_CORS
	ADD CONSTRAINT PK_P1_OPEN_CORS -- 개설교육과정 기본키
		PRIMARY KEY (
			ocno -- 개설교육과정
		);

ALTER TABLE P1_OPEN_CORS
	MODIFY COLUMN ocno INTEGER NOT NULL AUTO_INCREMENT COMMENT '개설교육과정';

-- 프로젝트
CREATE TABLE P1_PRJ (
	pno  INTEGER      NOT NULL COMMENT '프로젝트번호', -- 프로젝트번호
	ocno INTEGER      NOT NULL COMMENT '개설교육과정', -- 개설교육과정
	titl VARCHAR(255) NOT NULL COMMENT '프로젝트명', -- 프로젝트명
	cont TEXT         NOT NULL COMMENT '내용', -- 내용
	sdt  DATE         NULL     COMMENT '시작일', -- 시작일
	edt  DATE         NULL     COMMENT '종료일' -- 종료일
)
COMMENT '프로젝트';

-- 프로젝트
ALTER TABLE P1_PRJ
	ADD CONSTRAINT PK_P1_PRJ -- 프로젝트 기본키
		PRIMARY KEY (
			pno -- 프로젝트번호
		);

-- 프로젝트 인덱스
CREATE INDEX IX_P1_PRJ
	ON P1_PRJ( -- 프로젝트
		titl ASC -- 프로젝트명
	);

ALTER TABLE P1_PRJ
	MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '프로젝트번호';

-- 과목
CREATE TABLE P1_SUB (
	sno  INTEGER      NOT NULL COMMENT '과목번호', -- 과목번호
	titl VARCHAR(255) NOT NULL COMMENT '과목명', -- 과목명
	cont TEXT         NOT NULL COMMENT '과목설명' -- 과목설명
)
COMMENT '과목';

-- 과목
ALTER TABLE P1_SUB
	ADD CONSTRAINT PK_P1_SUB -- 과목 기본키
		PRIMARY KEY (
			sno -- 과목번호
		);

-- 과목 유니크 인덱스
CREATE UNIQUE INDEX UIX_P1_SUB
	ON P1_SUB ( -- 과목
		titl ASC -- 과목명
	);

ALTER TABLE P1_SUB
	MODIFY COLUMN sno INTEGER NOT NULL AUTO_INCREMENT COMMENT '과목번호';

-- 강의과목
CREATE TABLE P1_TCH_SUB (
	sno INTEGER NOT NULL COMMENT '과목번호', -- 과목번호
	mno INTEGER NOT NULL COMMENT '강사번호2' -- 강사번호2
)
COMMENT '강의과목';

-- 강의과목
ALTER TABLE P1_TCH_SUB
	ADD CONSTRAINT PK_P1_TCH_SUB -- 강의과목 기본키
		PRIMARY KEY (
			sno, -- 과목번호
			mno  -- 강사번호2
		);

-- 프로젝트 참여자
CREATE TABLE P1_PRJ_MEMB (
	pno INTEGER NOT NULL COMMENT '프로젝트번호', -- 프로젝트번호
	sno INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '프로젝트 참여자';

-- 프로젝트 참여자
ALTER TABLE P1_PRJ_MEMB
	ADD CONSTRAINT PK_P1_PRJ_MEMB -- 프로젝트 참여자 기본키
		PRIMARY KEY (
			pno, -- 프로젝트번호
			sno  -- 회원번호
		);

-- 교육과정 과목
CREATE TABLE P1_CORS_SUB (
	ocno INTEGER NOT NULL COMMENT '개설교육과정', -- 개설교육과정
	sno  INTEGER NOT NULL COMMENT '과목번호', -- 과목번호
	mno  INTEGER NULL     COMMENT '강사번호' -- 강사번호
)
COMMENT '교육과정 과목';

-- 교육과정 과목
ALTER TABLE P1_CORS_SUB
	ADD CONSTRAINT PK_P1_CORS_SUB -- 교육과정 과목 기본키
		PRIMARY KEY (
			ocno, -- 개설교육과정
			sno   -- 과목번호
		);

-- 교육과정 수강생
CREATE TABLE P1_CORS_STUD (
	ocno INTEGER NOT NULL COMMENT '개설교육과정', -- 개설교육과정
	sno  INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '교육과정 수강생';

-- 교육과정 수강생
ALTER TABLE P1_CORS_STUD
	ADD CONSTRAINT PK_P1_CORS_STUD -- 교육과정 수강생 기본키
		PRIMARY KEY (
			ocno, -- 개설교육과정
			sno   -- 회원번호
		);

-- 강의실 사진
CREATE TABLE P1_CR_PHOT (
	cpno  INTEGER      NOT NULL COMMENT '강의실사진번호', -- 강의실사진번호
	clrno INTEGER      NOT NULL COMMENT '강의실번호', -- 강의실번호
	photo VARCHAR(255) NOT NULL COMMENT '사진' -- 사진
)
COMMENT '강의실 사진';

-- 강의실 사진
ALTER TABLE P1_CR_PHOT
	ADD CONSTRAINT PK_P1_CR_PHOT -- 강의실 사진 기본키
		PRIMARY KEY (
			cpno -- 강의실사진번호
		);

ALTER TABLE P1_CR_PHOT
	MODIFY COLUMN cpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실사진번호';

-- 주소
CREATE TABLE P1_ADDR (
	adrno    INTEGER      NOT NULL COMMENT '주소번호', -- 주소번호
	postno   VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
	bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '주소';

-- 주소
ALTER TABLE P1_ADDR
	ADD CONSTRAINT PK_P1_ADDR -- 주소 기본키
		PRIMARY KEY (
			adrno -- 주소번호
		);

-- 주소 인덱스
CREATE INDEX IX_P1_ADDR
	ON P1_ADDR( -- 주소
		postno ASC -- 우편번호
	);

ALTER TABLE P1_ADDR
	MODIFY COLUMN adrno INTEGER NOT NULL AUTO_INCREMENT COMMENT '주소번호';

-- 교육과정
CREATE TABLE P1_CORS (
	cno  INTEGER      NOT NULL COMMENT '교육과정번호', -- 교육과정번호
	titl VARCHAR(255) NOT NULL COMMENT '과정명', -- 과정명
	cont TEXT         NOT NULL COMMENT '과정설명' -- 과정설명
)
COMMENT '교육과정';

-- 교육과정
ALTER TABLE P1_CORS
	ADD CONSTRAINT PK_P1_CORS -- 교육과정 기본키
		PRIMARY KEY (
			cno -- 교육과정번호
		);

-- 교육과정 인덱스
CREATE INDEX IX_P1_CORS
	ON P1_CORS( -- 교육과정
		titl ASC -- 과정명
	);

ALTER TABLE P1_CORS
	MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육과정번호';

-- 회원
CREATE TABLE P1_MEMB (
	mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	name     VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
	email    VARCHAR(40)  NOT NULL COMMENT 'email', -- email
	password VARCHAR(100) NOT NULL COMMENT 'password', -- password
	tel      VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
	photo    VARCHAR(255) NULL     COMMENT '사진' -- 사진
)
COMMENT '회원';

-- 회원
ALTER TABLE P1_MEMB
	ADD CONSTRAINT PK_P1_MEMB -- 회원 기본키
		PRIMARY KEY (
			mno -- 회원번호
		);

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_P1_MEMB
	ON P1_MEMB ( -- 회원
		email ASC -- email
	);

-- 회원 인덱스
CREATE INDEX IX_P1_MEMB
	ON P1_MEMB( -- 회원
		name ASC -- 이름
	);

ALTER TABLE P1_MEMB
	MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 수강신청
ALTER TABLE P1_APPL
	ADD CONSTRAINT FK_P1_STUD_TO_P1_APPL -- 학생 -> 수강신청
		FOREIGN KEY (
			sno -- 회원번호
		)
		REFERENCES P1_STUD ( -- 학생
			sno -- 회원번호
		);

-- 수강신청
ALTER TABLE P1_APPL
	ADD CONSTRAINT FK_P1_OPEN_CORS_TO_P1_APPL -- 개설교육과정 -> 수강신청
		FOREIGN KEY (
			ocno -- 개설교육과정
		)
		REFERENCES P1_OPEN_CORS ( -- 개설교육과정
			ocno -- 개설교육과정
		);

-- 학생
ALTER TABLE P1_STUD
	ADD CONSTRAINT FK_P1_ADDR_TO_P1_STUD -- 주소 -> 학생
		FOREIGN KEY (
			adrno -- 주소번호
		)
		REFERENCES P1_ADDR ( -- 주소
			adrno -- 주소번호
		);

-- 학생
ALTER TABLE P1_STUD
	ADD CONSTRAINT FK_P1_MEMB_TO_P1_STUD -- 회원 -> 학생
		FOREIGN KEY (
			sno -- 회원번호
		)
		REFERENCES P1_MEMB ( -- 회원
			mno -- 회원번호
		);

-- 강사
ALTER TABLE P1_TCH
	ADD CONSTRAINT FK_P1_MEMB_TO_P1_TCH -- 회원 -> 강사
		FOREIGN KEY (
			mno -- 강사번호
		)
		REFERENCES P1_MEMB ( -- 회원
			mno -- 회원번호
		);

-- 매니저
ALTER TABLE P1_MGR
	ADD CONSTRAINT FK_P1_MEMB_TO_P1_MGR -- 회원 -> 매니저
		FOREIGN KEY (
			mgno -- 매니저번호
		)
		REFERENCES P1_MEMB ( -- 회원
			mno -- 회원번호
		);

-- 개설교육과정
ALTER TABLE P1_OPEN_CORS
	ADD CONSTRAINT FK_P1_CLS_ROOM_TO_P1_OPEN_CORS -- 강의실 -> 개설교육과정
		FOREIGN KEY (
			clrno -- 강의실번호
		)
		REFERENCES P1_CLS_ROOM ( -- 강의실
			clrno -- 강의실번호
		);

-- 개설교육과정
ALTER TABLE P1_OPEN_CORS
	ADD CONSTRAINT FK_P1_MGR_TO_P1_OPEN_CORS -- 매니저 -> 개설교육과정
		FOREIGN KEY (
			mgno -- 매니저번호
		)
		REFERENCES P1_MGR ( -- 매니저
			mgno -- 매니저번호
		);

-- 개설교육과정
ALTER TABLE P1_OPEN_CORS
	ADD CONSTRAINT FK_P1_CORS_TO_P1_OPEN_CORS -- 교육과정 -> 개설교육과정
		FOREIGN KEY (
			cno -- 교육과정번호
		)
		REFERENCES P1_CORS ( -- 교육과정
			cno -- 교육과정번호
		);

-- 프로젝트
ALTER TABLE P1_PRJ
	ADD CONSTRAINT FK_P1_OPEN_CORS_TO_P1_PRJ -- 개설교육과정 -> 프로젝트
		FOREIGN KEY (
			ocno -- 개설교육과정
		)
		REFERENCES P1_OPEN_CORS ( -- 개설교육과정
			ocno -- 개설교육과정
		);

-- 강의과목
ALTER TABLE P1_TCH_SUB
	ADD CONSTRAINT FK_P1_TCH_TO_P1_TCH_SUB -- 강사 -> 강의과목
		FOREIGN KEY (
			mno -- 강사번호2
		)
		REFERENCES P1_TCH ( -- 강사
			mno -- 강사번호
		);

-- 강의과목
ALTER TABLE P1_TCH_SUB
	ADD CONSTRAINT FK_P1_SUB_TO_P1_TCH_SUB -- 과목 -> 강의과목
		FOREIGN KEY (
			sno -- 과목번호
		)
		REFERENCES P1_SUB ( -- 과목
			sno -- 과목번호
		);

-- 프로젝트 참여자
ALTER TABLE P1_PRJ_MEMB
	ADD CONSTRAINT FK_P1_PRJ_TO_P1_PRJ_MEMB -- 프로젝트 -> 프로젝트 참여자
		FOREIGN KEY (
			pno -- 프로젝트번호
		)
		REFERENCES P1_PRJ ( -- 프로젝트
			pno -- 프로젝트번호
		);

-- 프로젝트 참여자
ALTER TABLE P1_PRJ_MEMB
	ADD CONSTRAINT FK_P1_STUD_TO_P1_PRJ_MEMB -- 학생 -> 프로젝트 참여자
		FOREIGN KEY (
			sno -- 회원번호
		)
		REFERENCES P1_STUD ( -- 학생
			sno -- 회원번호
		);

-- 교육과정 과목
ALTER TABLE P1_CORS_SUB
	ADD CONSTRAINT FK_P1_OPEN_CORS_TO_P1_CORS_SUB -- 개설교육과정 -> 교육과정 과목
		FOREIGN KEY (
			ocno -- 개설교육과정
		)
		REFERENCES P1_OPEN_CORS ( -- 개설교육과정
			ocno -- 개설교육과정
		);

-- 교육과정 과목
ALTER TABLE P1_CORS_SUB
	ADD CONSTRAINT FK_P1_SUB_TO_P1_CORS_SUB -- 과목 -> 교육과정 과목
		FOREIGN KEY (
			sno -- 과목번호
		)
		REFERENCES P1_SUB ( -- 과목
			sno -- 과목번호
		);

-- 교육과정 과목
ALTER TABLE P1_CORS_SUB
	ADD CONSTRAINT FK_P1_TCH_TO_P1_CORS_SUB -- 강사 -> 교육과정 과목
		FOREIGN KEY (
			mno -- 강사번호
		)
		REFERENCES P1_TCH ( -- 강사
			mno -- 강사번호
		);

-- 교육과정 수강생
ALTER TABLE P1_CORS_STUD
	ADD CONSTRAINT FK_P1_STUD_TO_P1_CORS_STUD -- 학생 -> 교육과정 수강생
		FOREIGN KEY (
			sno -- 회원번호
		)
		REFERENCES P1_STUD ( -- 학생
			sno -- 회원번호
		);

-- 교육과정 수강생
ALTER TABLE P1_CORS_STUD
	ADD CONSTRAINT FK_P1_OPEN_CORS_TO_P1_CORS_STUD -- 개설교육과정 -> 교육과정 수강생
		FOREIGN KEY (
			ocno -- 개설교육과정
		)
		REFERENCES P1_OPEN_CORS ( -- 개설교육과정
			ocno -- 개설교육과정
		);

-- 강의실 사진
ALTER TABLE P1_CR_PHOT
	ADD CONSTRAINT FK_P1_CLS_ROOM_TO_P1_CR_PHOT -- 강의실 -> 강의실 사진
		FOREIGN KEY (
			clrno -- 강의실번호
		)
		REFERENCES P1_CLS_ROOM ( -- 강의실
			clrno -- 강의실번호
		);