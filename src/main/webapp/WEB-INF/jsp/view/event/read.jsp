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
					<form name="eventWriteForm" enctype="multipart/form-data">
					<input type="hidden" name="boardIdx"   class="inp_txt02" value='${boardVO.boardIdx}'/>
					<div class="mid">
						<ul>
							<li class="mid_in">
								<div class="mid_in_detail">
									<div class="box_tit">
										<span class="tit">고객지원 - 이벤트 등록</span>
									</div>
									<div class="box_con">
										<div class="table_wrap">
											<table class="table">
												<caption>테이블</caption>
												<tr class="row">
													<td class="cell th">타이틀</td>
													<td class="cell" colspan="3">
															<input name="title" type="text" class="inp_txt02" value="${boardVO.title}" placeholder="타이틀을 입력해주세요" />
													</td>
													<td class="cell th">등록일</td>
													<td class="cell"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></td>
												</tr>
												
												<tr class="row">
													<td class="cell th">시작일</td>
													<td class="cell">
														<input name="startDatetime" type="text" class="inp_txt02" id="from" value="${boardVO.startDatetime}" />
													</td>
													<td class="cell th">종료일</td>
													<td class="cell">
														<input  name="endDatetime" type="text" class="inp_txt02" id="to" value="${boardVO.endDatetime}"/>
													</td>
													<td class="cell ongoing" colspan="2">${boardVO.eventState}</td>
												</tr>

												<tr class="row">
													<td class="cell th">배너</td>
													<td class="cell">
														<span class="input_file_massk">
															<input name="eventImage" class="file_comm file03 inp_file" title="Choose file to upload" type="file" accept=".jpg,.png">
															<input class="inp_txt02 inputFileMaskText3"  value="${boardVO.eventOrgImageName}" readonly="readonly" placeholder="(권장 사이즈: 가로 x 세로 px)" type="text">
															<span class="btn_find">
																<a href="#none"><span>등록하기</span></a>
															</span>
														</span>
													</td>
													<td class="cell th">url</td>
													<td class="cell" colspan="3">
														<input name="eventUrl" type="text" class="inp_txt02" value="${boardVO.eventUrl}" placeholder="이동할 url을 입력해주세요" />
													</td>
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
// 			toolbar: ['undo', 'redo', 'bold', 'italic', 'blockQuote', 'heading', '|', 'link', 'numberedList', 'bulletedList', 'mediaEmbed'],

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

 function modify()
 {
	
	 var form = document.eventWriteForm;
	 
	 if(!form.title.value){
		 alert("제목을 입력하여주세요");
		 form.title.focus();
		 return;
	 }
	 
	 
	 if(!form.startDatetime.value){
		 alert("시작일을 입력하여주세요");
		 form.startDatetime.focus();
		 return;
	 }
	 
	 if(!form.endDatetime.value){
		 alert("종료일을 입력하여주세요");
		 form.startDatetime.focus();
		 return;
	 }
	 form.action="/event/modify.do";
	 form.method="POST"
	 form.submit();
 }
 
 function remove()
 {
	 var form = document.eventWriteForm;
	 
	 form.action="/event/remove.do";
	 form.method="POST"
	 form.submit();
 }

</script>
