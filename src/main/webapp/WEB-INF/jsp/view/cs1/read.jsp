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
					<div class="mid">
						<ul>
							<li class="mid_in">
								<div class="mid_in_detail">
									<div class="box_tit">
										<span class="tit">상품문의 - 문의하기 보기</span>
									</div>
									<div class="box_con">
										<div class="table_wrap">
											<table class="table">
												<caption>테이블</caption>
												<tr class="row">
													<td class="cell th">제목</td>
													<td class="cell" colspan="3">${boardVO.title}</td>
													<td class="cell th">등록일</td>
													<td class="cell"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></td>
												</tr>

												<tr class="row">
													<td class="cell th">이름</td>
													<td class="cell">${boardVO.csUserName}</td>
													<td class="cell th">직급</td>
													<td class="cell">${boardVO.csUserTitle}</td>
													<td class="cell th">회사명</td>
													<td class="cell">${boardVO.csUserCompany}</td>
												</tr>

												<tr class="row">
													<td class="cell th">전화번호</td>
													<td class="cell">${boardVO.csUserTel}</td>
													<td class="cell th">이메일</td>
													<td class="cell" colspan="3">${boardVO.csUserEmail}</td>
												</tr>
											</table>

											<div class="substance">
												<textarea name="contents" id="contents" placeholder="내용을 입력해주세요.">${boardVO.contents}</textarea>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_list" onclick="location.href='/cs1/listPage.do?page=1'">목록</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
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