<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.ck.ck-editor {
    max-width: 100%;
}

.ck-editor__editable {
    min-height: 350px;
}
</style>
					<form name="noticeWriteForm">
					<input type="hidden" name="boardIdx" class="inp_txt02" value='${boardVO.boardIdx}'/>
					<div class="mid">
						<ul>
							<li class="mid_in">
								<div class="mid_in_detail">
									<div class="box_tit">
										<span class="tit">고객지원 - 공지사항 등록</span>
									</div>
									<div class="box_con">
										<div class="table_wrap">
											<table class="table">
												<tr class="row">
													<td class="cell w1 th">제목</td>
													<td class="cell w2">
														<input type="text" name="title" class="inp_txt02" value='${boardVO.title}' placeholder="제목을 입력해주세요"/>
													</td>
													<td class="cell w3 th">조회수</td>
													<td class="cell w4">
														<input type="text" name="hitCnt" class="inp_txt02" value='${boardVO.hitCnt}' placeholder="###"/>
													</td>
												</tr>

												<tr class="row">
													<td class="cell w3 th">등록일</td>
													<td class="cell w4 al_l" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></td>
												</tr>
											</table>

											<div class="substance">
												<textarea name="contents" id="contents" placeholder="내용을 입력해주세요.">${boardVO.contents}</textarea>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_delete" onclick="javascript:remove()">삭제</button>
											<button type="button" class="btn_comm btn_upload" onclick="javascript:modify()">수정</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					</form>
<script type="text/javascript" src="/ckeditor5/ckeditor.js"></script>
<script type="text/javascript">
	ClassicEditor
		.create( document.querySelector( '#contents' ), {
// 			toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ],
			toolbar: ['undo', 'redo', 'bold', 'italic', 'blockQuote', 'heading', '|', 'link', 'numberedList', 'bulletedList', 'mediaEmbed'],

			language: 'ko',        
			ckfinder: {
				uploadUrl: '/upload/ckeditorupload'
			}
			  
		} )
		.then( editor => {
			window.editor = editor;
		} )
		.catch( err => {
			console.error( err.stack );
		} );
</script>
<script type="text/javascript">
 function modify()
 {
	 var form = document.noticeWriteForm;
	 
	 if(!form.title.value){
		 alert("제목을 입력하여주세요");
		 form.title.focus();
		 return;
	 }
	 
	 if(!form.contents.value){
		 alert("내용을 입력하여주세요");
		 form.title.focus();
		 return;
	 }
	 form.action="../notice/modify.do";
	 form.method="POST"
	 form.submit();
 }
 
 function remove()
 {
	 var form = document.noticeWriteForm;
	 
	 form.action="../notice/remove.do";
	 form.method="POST"
	 form.submit();
 }

</script>
