
CREATE TABLE `book` (
  `isbn` char(12) NOT NULL,						-- 책 번호
  `title` varchar(100) NOT NULL,				-- 책 제목
  `author` varchar(50) NOT NULL,				-- 저자
  `price` int NOT NULL,							-- 가격
  `describ` text,								-- 책 설명
  `img` varchar(100) DEFAULT NULL,				-- 이미지
  PRIMARY KEY (`isbn`)
);

insert into book(isbn, title, author, price, describ, img)
values
('979-11-91905','스프링 부트 3','신선영',32000,'자바 백엔드 개발자가 되고 싶다면','spring.jpg'),
('979-11-5839','모던 자바스크립트','이웅모',45000,'자바스크립트 기본 개념과 동작 원리','javascript.jpg'),
('979-11-1234','실전 react','동글양',35000,'동글양과 함게 배우는 react','react.jpg');


create table members(
	 id 		varchar(30) primary key    
	,password	varchar(30) not null
    ,name		varchar(30) not null
    ,email		varchar(50) 
    ,address	varchar(200)
    ,phone		varchar(15)
);


insert into members(id, password, name, email, address, phone)
values
('jaen','1111','자앤','jaen@andoridjava.com','서울시 서초구','02'),
('eureka','1111','LG','eureka@uplus.com','서울시 강남구','02'),
('mulcam','1111','멀캠','mulcam@multicampus.com','서울시 강남구','02'),
('kdg','1111','김동근','kdg@andoridjava.com','서울시 성북구','010');

commit;