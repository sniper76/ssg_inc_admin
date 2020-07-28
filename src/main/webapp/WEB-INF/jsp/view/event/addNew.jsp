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
						<form name="eventWriteForm" method="post"  enctype="multipart/form-data" action="../event/register.do">
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
														<input  name="title" type="text" class="inp_txt02" placeholder="타이틀을 입력해주세요" />
														<spring:hasBindErrors name="eventBoard">
							    							<c:if test="${errors.hasFieldErrors('title')}">
							    								<Strong>${errors.getFieldError('title').defaultMessage}</Strong>
							    							</c:if>
							    						</spring:hasBindErrors>
													</td>
													<td class="cell th">등록일</td>
													<td class="cell"></td>
												</tr>

												<tr class="row">
													<td class="cell th">시작일</td>
													<td class="cell">
														<input name="startDatetime" type="text" class="inp_txt02" id="from"/>
													</td>
													<td class="cell th">종료일</td>
													<td class="cell">
														<input  name="endDatetime" type="text" class="inp_txt02" id="to"/>
													</td>
													<td class="cell ongoing" colspan="2"></td>
												</tr>

												<tr class="row">
													<td class="cell th">배너</td>
													<td class="cell">
														<span class="input_file_massk">
															<input name="eventImage" class="file_comm file03 inp_file" title="Choose file to upload" type="file" accept=".jpg,.png">
															<input name="eventOrgImageName" class="inp_txt02 inputFileMaskText3" readonly="readonly" placeholder="(권장 : 446 x 280)" type="text">
															<span class="btn_find">
																<a href="#none"><span>등록하기</span></a>
															</span>
														</span>
													</td>
													<td class="cell th">url</td>
													<td class="cell" colspan="3">
														<input name="eventUrl" type="text" class="inp_txt02" placeholder="이동할 url을 입력해주세요" />
													</td>
												</tr>
											</table>

											<div class="substance">
												<textarea name="contents" id="contents" placeholder="내용을 입력해주세요."></textarea>
												<spring:hasBindErrors name="eventBoard">
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
<form id="fileUploadForm" method="post" hidden=""></form>
<script language="javascript">
 function register()
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
	 
	 form.submit();
 }
 
 function fire_ajax_submit(aObj) {
		if($(aObj).parent().siblings('input').first().val() == '') {
			alert('등록할 파일을 선택하세요.');
			return;
		}
		console.log('file', $(aObj).parent().siblings('input').first()[0].files[0]);

	    // Get form
	    var formData = new FormData();

		// 파일 데이터
		//for(var i=0, filesTempArrLen = filesTempArr.length; i<filesTempArrLen; i++) {
		   formData.append("file", $(aObj).parent().siblings('input').first()[0].files[0]);
		//}
		console.log('formdata', formData);

	    $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "<c:url value='/upload/uploadAjax' />",
	        data: formData,
	        processData: false, //prevent jQuery from automatically transforming the data into a query string
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
	        	
	        	$(aObj).parent().siblings('input[type=hidden]').first().val(data);
	        	$(aObj).parent().siblings('input[type=hidden]').last().val(data);

	            console.log("SUCCESS : ", data);

	        },
	        error: function (e) {

	            console.log("ERROR : ", e);

	        }
	    });

	}

</script>
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
</script>
