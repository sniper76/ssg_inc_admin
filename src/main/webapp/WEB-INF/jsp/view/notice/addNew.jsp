<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
.ck.ck-editor {
    max-width: 100%;
}

.ck-editor__editable {
    min-height: 350px;
}
</style>
					<div class="mid">
					 	<form name="noticeWriteForm" method="post" action="/notice/register.do">
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
														<input type="text"  name="title" class="inp_txt02"  value='${boardVO.title}' placeholder="제목을 입력해주세요"/>
														<spring:hasBindErrors name="noticeBoard">
							    							<c:if test="${errors.hasFieldErrors('title')}">
							    								<Strong>${errors.getFieldError('title').defaultMessage}</Strong>
							    							</c:if>
							    						</spring:hasBindErrors>
													</td>
													<td class="cell w3 th">조회수</td>
													<td class="cell w4">
														<input type="text" name="hit" class="inp_txt02"  value='${boardVO.hit}' placeholder="###"/>
													</td>
												</tr>

												<tr class="row">
													<td class="cell w3 th">등록일</td>
													<td class="cell w4 al_l" colspan="3"></td>
												</tr>
											</table>

											<div class="substance">
												<textarea name="contents" id="contents" placeholder="내용을 입력해주세요.">
												</textarea>
												<spring:hasBindErrors name="noticeBoard">
							    					<c:if test="${errors.hasFieldErrors('contents')}">
							    						<Strong>${errors.getFieldError('contents').defaultMessage}</Strong>
							    					</c:if>
							    				</spring:hasBindErrors>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_delete" onclick="javascript:history.go(-1)">취소</button>
											<button type="button" class="btn_comm btn_upload" onclick="javascript:register()">등록</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
					    </form>	
					</div>

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
 function register()
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
	 
	 form.submit();
 }

</script>
