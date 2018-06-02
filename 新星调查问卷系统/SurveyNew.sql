--调用存储过程创建文件夹
exec sp_configure 'show advanced options',1 reconfigure

exec sp_configure 'xp_cmdshell',1 reconfigure

exec xp_cmdshell 'mkdir D:\SQL\SURVEY' reconfigure


--用master查看所创建的库文件是否存在

use master

--创建survey库，如果survey库已经存在就把原来的库删掉重新创建

if exists(select * from sysdatabases where name = 'survey')

 drop database survey


go

--创建数据库survey

create database survey
on
(
  name = 'survey_data',
  filename = 'D:\SQL\SURVEY\survey_data.mdf',
  size = 3mb,
  maxsize = unlimited,
  filegrowth = 15%
)
log on
(
  name = 'survey_log',
  filename = 'D:\SQL\SURVEY\survey_log.ldf',
  size = 1mb,
  maxsize = unlimited,
  filegrowth = 15%
)

go

-----调查问卷系统数据库

use survey

--1.创建调查项类型表
if exists(select * from sysobjects where name = 'ItemTypeTable')
 drop table ItemTypeTable

create table ItemTypeTable
(
ItemType int constraint ItemTypeTable_pk primary key,
Statement varchar(50) not null
)
go

--向调查项类型表插入数据，0表示单选，1表示多选，2表示文本
insert into ItemTypeTable
select 0,'RadioItem' union
select 1,'CheckboxItem' union
select 2,'TextItem'

select * from ItemTypeTable 
go

--2.创建调查项属性表
if exists(select * from sysobjects where name = 'ItemAttributeTable')
 drop table ItemAttributeTable

create table ItemAttributeTable
(
ItemAttribute int constraint ItemAttributeTable_pk primary key,
Statement varchar(30) not null
)
--向调查项属性表插入数据，0表示公有，1表示私有
insert into ItemAttributeTable
select 0,'public' union
select 1,'private'

select * from ItemAttributeTable
go


--3.创建是否默认选中表
if exists(select * from sysobjects where name = 'IfDefaultSelectedTable')
 drop table IfDefaultSelectedTable

create table IfDefaultSelectedTable
(
DefaultSelected int constraint IfDefaultSelectedTable_pk primary key,
Statement varchar(30) not null
)
--向是否默认选中表中插入数据，0表示默认不选中，1表示默认选中
insert into IfDefaultSelectedTable
select 0,'NotSelected' union
select 1,'Selected'

select * from IfDefaultSelectedTable 
go

--4.创建管理员类型表
if exists(select * from sysobjects where name = 'UserTypeTable')
 drop table UserTypeTable


create table UserTypeTable
(
UserType int constraint UserTypeTable_pk primary key,
Statement varchar(20) not null
)
go
--向管理员类型表中插入数据，0为普通管理员，1为系统管理员
insert into UserTypeTable
select 0,'GeneralManager' union
select 1,'Administrator'

select * from UserTypeTable
go


--5.创建管理员信息表
if exists(select * from sysobjects where name = 'UserTable')
 drop table UserTable

create table UserTable
(
UserID int constraint UserTable_pk primary key identity(1000,1),
UserPassword varchar(50) not null,
UserName varchar(50)not null unique,
UserType int not null
)
go
--向管理员表插入系统管理员，类型为1
insert into UserTable(UserPassword,UserName,UserType)
values('admin','admin',1)
go
select * from UserTable

--6.创建调查问卷信息表
if exists(select * from sysobjects where name = 'SurveyTable')
 drop table SurveyTable

create table SurveyTable
(
SurveyID int constraint SurveyTable_pk primary key identity(1,1),
SurveyTitle varchar(150),
SurveyOwnerID int constraint SurveyTable_OwnerID_fk foreign key references UserTable(UserID),
SurveyLink varchar(100),
SurveyCreateDateTime DateTime default(getdate()),--调查问卷创建时间
SurveyExpirationDateTime varchar(12),--调查问卷过期时间

)

select * from SurveyTable
go

--7.创建访问者信息表

if exists(select * from sysobjects where name = 'VisitorTable')
 drop table VisitorTable

create table VisitorTable
(
VisitorIP varchar(30) not null,
visiteDateTime DateTime not null default(getdate()),
VisitorNumber int constraint VisitorTable_pk primary key identity(1,1),
VisiteSurveyID int constraint VisitorTable_SurveyID_fk foreign key references SurveyTable(SurveyID),
VisiteSurveyData varchar(1000) not null--考虑是不是调查项数据大小问题
)

select * from VisitorTable
go

--8.创建调查项信息表

if exists(select * from sysobjects where name = 'ItemTable')
 drop table ItemTable

create table ItemTable
(
ItemID int constraint ItemTable_pk primary key identity(1,1),
ItemCaption varchar(100) not null,
ItemType int constraint ItemTable_Type_fk foreign key references ItemTypeTable(ItemType),
ItemAttribute int constraint ItemTable_Attribute_fk foreign key references ItemAttributeTable(ItemAttribute) default(1),
ItemOwnerID int constraint ItemTable_Owner_fk foreign key references UserTable(UserID),
ItemOwnerSurveyID int constraint ItemTable_OwnerSurvey_fk foreign key references SurveyTable(SurveyID),
RadioCheckboxCount int not null
)

go
select * from ItemTable

--9.创建调查问卷和调查项的关联表

if exists(select * from sysobjects where name = 'SurveyAssociateItem')
 drop table SurveyAssociateItem

create table SurveyAssociateItem
(
SurveyID int,
ItemID int,
constraint SurveyAssociateItemTable_pk primary key(SurveyID,ItemID)
)

go

select * from SurveyAssociateItem

--10.创建单选表

if exists(select * from sysobjects where name = 'RadioTable')
 drop table RadioTable

create table RadioTable
(
RadioOwnerID int constraint RadioTable_Owner_fk foreign key references ItemTable(ItemID),
RadioIndex int not null,
constraint RadioTable_pk primary key(RadioOwnerID,RadioIndex),
RadioCaption varchar not null,
DefaultSelected int constraint RadioTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

---修改后的数据库表
create table RadioTable
(
  RadioID int  constraint RadioTable_pk primary key(RadioID) identity(1,1),
  RadioOwnerID int constraint RadioTable_Owner_fk foreign key references ItemTable(ItemID),
  RadioIndex int not null,
  RadioCaption varchar(300) not null,
  DefaultSelected int constraint RadioTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
  SelectedCount int not null
)

go
select * from RadioTable

--11.创建复选框表

if exists(select * from sysobjects where name = 'CheckboxTable')
 drop table CheckboxTable

create table CheckboxTable
(
CheckboxOwnerID int constraint CheckboxTable_Owner_fk foreign key references ItemTable(ItemID),
CheckboxIndex int not null,
constraint CheckboxTable_pk primary key(checkboxOwnerID,CheckboxIndex),
CheckboxCaption varchar(50),
DefaultSelected int constraint RadioTable_Owner_fk foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

----修改后的数据库表
create table CheckboxTable
(
CheckboxID int  constraint CheckboxTable_pk primary key(CheckboxID) identity(1,1),
CheckboxOwnerID int constraint CheckboxTable_Owner_fk foreign key references ItemTable(ItemID),
CheckboxIndex int not null,
CheckboxCaption varchar(50),
DefaultSelected int constraint CheckboxTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

select * from CheckboxTable
go

--12.创建文字表
if exists(select * from sysobjects where name = 'TextTable')
 drop table TextTable


create table TextTable
(
TextOwnerID int constraint TextTable_OwnerID_fk foreign key references ItemTable(ItemID),
TextCaption varchar(100),
constraint TextTable_pk primary key(TextOwnerID,TextCaption),
TextContent varchar(1000)
)

--修改后的数据库表

create table TextTable
(
  TextID int  constraint TextTable_pk primary key(TextID) identity(1,1),
  
  TextOwnerID int constraint TextTable_OwnerID_fk foreign key references ItemTable(ItemID),

  TextCaption varchar(100),
  
  TextContent varchar(1000)
)

select * from TextTable