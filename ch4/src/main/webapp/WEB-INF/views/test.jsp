
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>Comment Test</h2>
comment : <input type ="text" name="comment"><br>
<button id="sendBtn" type="button">SEND</button>
<button id="modifyBtn" type="button">수정</button>
<div id="commentList"></div>
<div id="replyForm" style="display:none">
  <input type="text" name="replyComment">
  <button id="wrtReplyBtn" type="button">등록</button>
</div>
<script>
  let bno = 1419;
  let showList = function(bno){
    $.ajax({
      type:'GET',       // 요청 메서드
      url: '/ch4/comments?bno='+bno,  // 요청 URI
      success : function(result){
        $("#commentList").html(toHtml(result));
      },
      error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()
  }

  let toHtml = function(comments){
    let tmp= "<ul>";

    comments.forEach(function(comment){
      tmp += '<li data-cno='+ comment.cno
      tmp += ' data-pcno=' + comment.pcno
      tmp += ' data-bno=' + comment.bno + '>'
      if(comment.cno!=comment.pcno)
        tmp += 'ㄴ'
      tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
      tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
      tmp += ' up_date=' + comment.up_date
      tmp += ' <button id ="delBtn" type="button">삭제</buton>'
      tmp += ' <button id ="modBtn" type="button">수정</buton>'
      tmp += ' <button id ="replyBtn" type="button">답글</buton>'
      tmp += '</li>'
    })

    return tmp + "</ul>";
  }

  $(document).ready(function(){
    showList(bno);
    $('#wrtReplyBtn').click(function() {
      let comment = $("input[name=replyComment]").val();
      let pcno = $("#replyForm").parent().attr("data-pcno");

      if (comment.trim() == '') {
        alert("댓글내용을 입력해 주세요.");
        $("input[name=comment]").focus();
        return;
      }

      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result);       // result는 서버가 전송한 데이터
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      $("#replyForm").css("display","none");
      $("input[name=replayComment]").val('');
      $("#replayForm").appendTo("body");
    });

    $('#sendBtn').click(function(){
      let comment = $("input[name=comment]").val();
      if (comment.trim()=='') {
        alert("댓글내용을 입력해 주세요.");
        $("input[name=comment]").focus();
        return;
      }

      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result);       // result는 서버가 전송한 데이터
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

    });

    $('#modifyBtn').click(function(){
      let cno = $(this).attr("data-cno");
      let comment = $("input[name=comment]").val();
      if (comment.trim()=='') {
        alert("댓글내용을 입력해 주세요.");
        $("input[name=comment]").focus();
        return;
      }

      $.ajax({
        type:'PATCH',       // 요청 메서드
        url: '/ch4/comments/'+cno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result);       // result는 서버가 전송한 데이터
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

    });

    $("#commentList").on("click","#replyBtn", function(){
      //1. reply form을 옮기고
      $("#replyForm").appendTo($(this).parent());
      //1. 답글 입력 form 보여주고
      $("#replyForm").css("display","block");
    });

    $('#commentList').on("click","#modBtn", function() {
      let cno = $(this).parent().attr("data-cno");
      let comment = $("span.comment", $(this).parent()).text();
      // 1. comment의 내용을 input내용에 뿌림
      $("input[name=comment]").val(comment);
      // 2. cno 전달하기
      $("#modifyBtn").attr("data-cno", cno);

    });

    $("#commentList").on("click","#delBtn", function(){
      let cno = $(this).parent().attr("data-cno");
      let bno = $(this).parent().attr("data-bno");
      $.ajax({
        type:'DELETE',       // 요청 메서드
        url: '/ch4/comments/'+cno+'?bno='+bno,  // 요청 URI
        success : function(result){
          alert(result);
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    });


  });
</script>
</body>
</html>