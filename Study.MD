<h1>Jgh CRUD_Board </h1>

<h3>회원가입, post CURD, 이미지 업로드, 댓글, 즐겨찾기 목록(카테고리), 페이징 기능 게시판 사이트</h3>

<b>8월 27일 회원가입 기능 완료

<b>8월 30일 post CRUD 기능 완성 목표 </b>

<b>9월 6일 페이징 기능 완료</b>
-> 페이징에 앞서 그 외 BoardPost에 달릴 Comment 내용 추가를 위한 연기
<b>9월 10일 Comment 기능 완료</b>


- Web Publish> Thymeleaf, Bootstrap사용, 웹 연결
- SQL > Mysql
- Server > Dcoker(예정)
- SpringBoot Project


2024 08 19

`Day 1 `
`Home, BoardPost, Member 관련 Controller, Service, Repository `

> 기본 Home, BoardPost, Member 작성
> - MySQL 연결
> - Git 연결

> - <b>과제</b> 
> - Docker 연결, 기능 심화



2024 08 20

`Day 2` 
`CreateMember, MemberForm, MemberList`

> Error(Web Publish 관련)
> - template might not exist or might not be accessible by any of the configured Template Resolvers] with root cause
> - 웹상의 member와 Post 폴더의 위치가 잘못 되었었음. Controller와 웹간의 연결은 문제 없었음.
> - templates > web 순으로 폴더 설정이 되어 있지 않아, 이 부분을 수정해주니 정상 동작 함.

> Error(회원가입 [DB]관련)
> - Caused by: java.sql.SQLSyntaxErrorException: (conn=291) You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'sequence member_seq' at line 1
> - SQL에서 Member 테이블의 Sequence를 PK로 하여 Auto_increment를 적용시켜 주고, 다시 JPA에서 @GeneratedValue로 지정해주니 발생한 오러다.
> - SQL에서는 PK로만 지정하고 Auto_increment 설정을 제거해주었다. 제거 방법은 수정으로 다음의 명령어를 사용했다.
> - <b>(alter table member drop primary key, change `sequence` `sequence` int primary key;)</b>
> - @GeneratedValue(strategy = GenerationType.IDENTITY)를 사용해서 sequence가 Auto_increment가 되게 해주었다.
> - SQL에서는 PK로만 지정하고, JPA에서는 Column을 @GeneratedValue(strategy...)로 해주어 정상적으로 DB에 sequence가 새 회원정보가 생길 때 증가하게 해주었다.

> Error(회원가입 [Thymeleaf, Parse]관련)
> - Caused by: org.attoparser.ParseException: Error during execution of processor 'org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor' (template: "member/updateMemberForm" - line 13, col 34)
> - Model에 form 만든 것을 Attribute해주지 않고 있어서 발생한 문제. 때문에 Form을 만들었어도 보내주지 않았고 위의 오류가 발생하였다.

> Update 관련하여, 수정이 안되는 문제가 있었으나,
> Service 부분에서 Method에     @Transactional을 붙여서 변경을 감지하여 준영속 엔티티를 변경하게 해야 했다.
> 이 부분을 잊으면, 준영속 엔티티 변경을 감지하지 못하고 Date를 헛보내서 Update가 되지 않는다.
> 현재는 @Transactional을 Method에 붙여주어 수정이 되게 하였다.

2024 08 23
> 계속해서 Get 에러로 인해 PostMapping 방식으로 Delete를 하는 것이 아닌 RequestMapping 의 Get을 사용해서 요청을 받음.
> 

2024 09 02
> CRUD Board_Post Mapping부분
            <tr th:each="board_Post : ${boardPostList}">

                <!--board_post Root Entity를 못찾는다고 했다. th:each="board_post"를 th:each=""로 바꾸어서 진행.-->

                <td th:text="${board_Post.board_sequence}"></td>
                <td th:text="${board_Post.recommands}"></td>
                <td th:text="${board_Post.author}"></td>
                <td th:text="${board_Post.category}"></td>
                <td th:text="${board_Post.content}"></td>
                <td th:text="${board_Post.title}"></td>
                <td th:text="${board_Post.views}"></td>
                <td>
                    <a href="#" th:href="@{/post/{board_id}/edit (board_sequence=${board_Post.board_sequence})}" class="btn btn-primary" role="button">게시글 수정</a>
                </td>
                <td>
                    <a href="#" th:href="@{/post/{sequence}/delete (board_sequence=${board_Post.board_sequence})}" class="btn btn-danger" role="button">게시글 삭제</a>
                </td>
            </tr>
> board_Post로 Mapping을 받는다.
> Thymeleaf와 Spring간의 Mapping 이 항상 문제였는데, 주로 어디가 매핑되는 지는 잘 알아도, 대소문자 구분으로 인해서 매핑이 잘 안되던 부분이 많았다.
> Entity(Domanin)에서의 SQL과의 매핑만 잘 신경 써주면 주로 백엔드단과 프론트단의 대소문자로 인한 매핑 문제만 잘 봐주면 된다.

2024 09 05
> Member, BoardPost Entity명 변경 및 추가 후 에러 수정
> 

2024 09 12
> ID값을 Long으로 쓰는 이유는, Int의 범위가 Long보다 작고, Long의 경우 null을 쓸 수 있기 때문이다.
> 

2024 09 13
> Spring Validation, Spring Security 적용