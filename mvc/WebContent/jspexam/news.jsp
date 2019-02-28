<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
   <%@ page import="model.vo.NewsVO, java.util.ArrayList" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>

hr{
   width : 1000px;
}

h2{
text-align : center;
color : black; 
text-shadow: 2px 2px 2px gray;
font-style: italic;
}

table{
 margin:auto;
 /* background-image: url("/edu/images/mulcam.png"); */
 }




td{
   height : 20px;
   border-bottom : 2px dotted black;
}
th{
    padding: 1px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
   background-color : #a9d8c9;
   color : white;
}
tr:hover{
   background-color : #d9d9d9;
}

textarea{
   width : 50%;
   height : 100px;
}

button{
  padding-top: 10px;
  background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:40px;
  width : 150px;
  font-size:1.0em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
 border-radius : 5px;
}

button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}

button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}

button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}


button:hover{
  background:#fff;
  color:#1AAB8A;
}

div{
   text-align : center;
   margin-left : auto;
   margin-right : auto;
}

#name{
   font-weight : bold;
}

#title{
   color : red;
}

.A{
   background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:40px;
  width : 100px;
  font-size:0.7em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
 border-radius : 5px;
}

.A:hover{
  background:#b3ffb3;
  color:#1AAB8A;
}

</style>




<body>
<hr>
<h2>MultiCampus News</h2>
<hr>

<div>
 <%                               
   ArrayList<NewsVO> list = (ArrayList<NewsVO>)request.getAttribute("list");
   /* list를 가져와야한다.  */
   if(list != null){    
%>    
   <table> 

      <tr>
         <th width="10%" style="color: black">번호</th>
         <th width="30%" style="color: black">제목</th>
         <th width="10%" style="color: black">작성자</th>
         <th width="15%" style="color: black">작성일</th>
         <th width="5%" style="color: black">조회수</th>
         
      </tr>
    <%
   for(NewsVO vo : list){
   %>    
    
      <tr> <!-- 표현식 태그 -->
         <td><%= vo.getId()  %> </td>
         <td ><a href="/mvc/news?id=<%=vo.getId()%>&action=read"><%= vo.getTitle()  %></a> </td>
         <td><%= vo.getWriter()  %> </td>
         <td><%= vo.getWritedate()  %> </td>       <!-- 3개의 열, 행은 입력되는 만큼  -->   
         <td><%= vo.getCnt() %></td>
         
         
      </tr>
   <%
   }
   %>
   </table>
   

 <% 
    } else{
%>
      <h2>${msg }</h2>
<%
 }
%>

<!-- 뉴스 내용 폼 -->
      <button onclick="block()">Create News</button>
  
    <div id="sub_form"  style ='display: none'>
         <form method="POST" action="/mvc/news">
            <input id="name" type="text" name="writer" placeholder="작성자명을 입력해주세요." style="width:50%"><br>
            <input id="title" type="text" name="title" placeholder="제목을 입력해주세요." style="width:50%"><br>
            <textarea name="content" placeholder="내용을 입력해주세요." style="width:400px height:300px"></textarea><br>
            <input class="A"  type="submit" value="저장">
            <input class="A" type="reset" value="재작성">
            <input class="A" type="button" value="취소" onclick="none()">
            <input type='hidden' name='action' value='insert'>
            
         </form>
      </div>  

</div>
 <!--  뉴스 내용 폼--> 
  
    <% NewsVO list1 = (NewsVO) request.getAttribute("list1");
    if(list1 !=  null) {%>
      <div id="sub_form2">
         <form method="POST" action="/mvc/news">
            <input id="name" type="text" name="writer" value=<%=list1.getWriter() %> style="width:500px"><br>
            <input id="title" type="text" name="title" value=<%=list1.getTitle() %> style="width:500px"><br>
           <textarea name="content" style="width:500px, height:300px"><%=list1.getContent() %></textarea><br>
            <input class="A" type="button" value="확인" onclick='none2()'>
            <input class="A" type="submit" value="수정">
           <input class="A" type='button' onclick="location.href='/mvc/news?action=delete&id=<%=list1.getId()%>'" value="삭제">
            <input type='hidden' name='action' value='update'>
            <input type='hidden' name='id' value=<%=list1.getId() %>>
         </form>
         
      </div>

   <%} %>


<script>
/* 보이고 안보이게 하는 자바 스크립트!!! */
    function block(){
      (document.getElementById("sub_form")).style.display = "block";
      (document.getElementById("sub_form2")).style.display = "none";/* 숨기기  */
   }
   
   function none(){
      document.getElementById("sub_form").style.display = "none";
   }
   
   function block2(){
      (document.getElementById("sub_form")).style.display = "none";  
      (document.getElementById("sub_form2")).style.display = "block";  /* 숨기기  */
      }
      
      function none2(){
         document.getElementById("sub_form2").style.display = "none";  /* 숨기기  */
      }
</script>

</body>
</html>