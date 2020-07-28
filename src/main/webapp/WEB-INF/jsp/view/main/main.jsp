<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
					<div class="mid">
						<ul>
							<li class="mid_in">
								<div class="mid_in_list">
									<div class="box_tit">
										<span class="tit">메인 - 메인관리</span>
										<button type="button" id="saveBtn">저장하기</button>
									</div>
									<div class="box_con">
										<form action="<c:url value='/main/register.do' />" id="form1" name="form1" enctype="multipart/form-data" method="post">
										
										<div class="btn_wrap_puls">
											<button id="addBtn" type="button"><img src="/images/comm/icon_plus.png" alt="close" /></button>
										</div>
										</form>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<form id="fileUploadForm" method="post" hidden=""></form>
					
					
<script id="tmpl_list" type="text/x-jsrender">
										<div class="table_wrap">
											<div class="tit">
												<span>메인{{:idx}}</span>
												<i><img onclick="javascript:delDiv(this, '{{:boardIdx}}');" src="/images/comm/popup_close.png" alt="close" />
												<input name="boardIdx" class="file_comm file08 inp_file" value="{{:boardIdx}}" type="hidden" ></i>
											</div>
											<table class="table table_main_admin">
												<caption>메인 관리 테이블</caption>
												<tr>
													<td class="cell th">타이틀(H1)</td>
													<td class="cell" colspan="3">
														<textarea name="title" id="title" placeholder="타이틀을 입력해주세요.">{{:title}}</textarea>
													</td>
												</tr>
												<tr>
													<td class="cell th">부제(H2)</td>
													<td class="cell" colspan="3">
														<textarea name="subTitle" id ="subTitle" placeholder="부제를 입력해주세요.">{{:subTitle}}</textarea>
													</td>
												</tr>

												<tr class="row">
													<td class="cell th">이미지(PC)</td>
													<td class="cell" colspan="3">
														<span class="input_file_massk">
															<input name="pcImage" value="" class="inp_txt02 inputFileMaskText8"  placeholder="(권장 : 1920 x 1080)" type="file" accept=".jpg,.png">
															<input name="pcImageName" value="{{:pcImageName}}" type="hidden">
															<input name="pcImageUrl" value="{{:pcImageUrl}}" type="hidden">
															<span class="btn_find">
																<a href="#none" onclick="javascript:fire_ajax_submit(this);"><span>등록하기</span></a>
															</span>
														</span>
{{if pcOrgImageName}}
														<span class="input_file_massk">
															<input class="inp_txt02 inputFileMaskText11" value="{{:pcOrgImageName}}" readonly="readonly" placeholder="(권장 : 1080  x 1920)" type="text">
															<!-- <img src="/upload/displayFile?fileName={{:pcImageUrl}}"} -->
														</span>
{{/if}}
													</td>
												</tr>
												<tr class="row">
													<td class="cell th">이미지(MO)</td>
													<td class="cell" colspan="3">
														<span class="input_file_massk">
															<input name="moImage" value="" class="inp_txt02 inputFileMaskText9"  placeholder="(권장 : 1080  x 1920)" type="file" accept=".jpg,.png">
															<input name="moImageName" value="{{:moImageName}}" type="hidden">
															<input name="moImageUrl" value="{{:moImageUrl}}" type="hidden">
															<span class="btn_find">
																<a href="#none" onclick="javascript:fire_ajax_submit(this);"><span>등록하기</span></a>
															</span>
														</span>
{{if moOrgImageName}}
														<span class="input_file_massk">
															<input class="inp_txt02 inputFileMaskText11" value="{{:moOrgImageName}}" readonly="readonly" placeholder="(권장 : 1080  x 1920)" type="text">
														</span>
{{/if}}
													</td>
												</tr>
												<tr>
													<td class="cell th">버튼명</td>
													<td class="cell">
														<input type="text" name="buttonName" value="{{:buttonName}}" class="inp_txt02" placeholder="버튼명을 입력해주세요."/>
													</td>
													<td class="cell th">버튼 url</td>
													<td class="cell">
														<input type="text" name="buttonUrl" value="{{:buttonUrl}}" class="inp_txt02" placeholder="url을 입력해주세요."/>
													</td>
												</tr>
											</table>
										</div>
</script>
<script>
	$(document).ready(function(){
		$("#saveBtn").click(function(){
			var pcCheck = 0, moCheck = 0;
			$('input[name=pcImageName]').each(function(i,o) {
				if(o.value == '') {
					pcCheck++;
				}
			});
			$('input[name=moImageName]').each(function(i,o) {
				if(o.value == '') {
					moCheck++;
				}
			});
			if(pcCheck > 0) {
				alert("PC 이미지를 등록해주세요");
				//pcFile1.foucs();
				return;
			} else if (moCheck > 0) {
				alert("Mobile 이미지를 등록해주세요");
				//moFile1.focus();
				return;
			}
			
			document.form1.action = "<c:url value='/main/register.do' />";
			document.form1.submit();
		});
		$("#addBtn").click(function(){
			 addDiv();
		});
		
		//리스트 조회
		getList();
		
		//$("input[name=pcImage], input[name=moImage]").change(function(ev) {
			//addFiles(ev);
		//});
	});
	
	$(document).on("change", 'input[name=pcImage], input[name=moImage]', function (ev) {
		//addFiles(ev);
	});
	
	function delDiv(obj, key) {
		//console.log('key', $(obj).closest('div.table_wrap').siblings('div').length);
		if($(obj).closest('div.table_wrap').siblings('div').length == 1) {
			alert('최소 한건은 존재해야 합니다.');
			return;
		}
		if(key == '') {
			//remove div
			//console.log('closest', $(obj).closest('div.table_wrap'));
			$(obj).closest('div.table_wrap').remove();
		}
		else {

		    $.ajax({
		        type: "POST",
		        url: "<c:url value='/main/deleteAjax.do' />",
		        dataType: "json",
				contentType:"application/json;charset=UTF-8",
		    	data		:  JSON.stringify({boardIdx:key}), 
		        success: function (data) {

		            console.log("del SUCCESS : ", data);
		            if(data.result == 'success') {
		            	$('div.table_wrap').remove();
		            	getList();
		            }

		        },
		        error: function (e) {

		            console.log("del ERROR : ", e);

		        }
		    });
		}
	}
	
	function getList() {
	    $.ajax({
	        type: "POST",
	        url: "<c:url value='/main/listAjax.do' />",
	        dataType: "json",
			contentType:"application/json;charset=UTF-8",
	    	data		:  JSON.stringify({currentPage:1}), 
	        success: function (data) {

	            console.log("list SUCCESS : ", data);
	            
	            if(data.list.length <= 0) {
	            	addDiv();
	            }
	            else {
	            	drawDiv(data.list);
	            }

	        },
	        error: function (e) {

	            console.log("list ERROR : ", e);

	        }
	    });
	}
	 
	var filesTempArr = [];
	// 파일 추가
	function addFiles(e) {
	    var files = e.target.files;
	    var filesArr = Array.prototype.slice.call(files);
	    console.log('filesArr', filesArr);
	    var filesArrLen = filesArr.length;
	    var filesTempArrLen = filesTempArr.length;
	    for( var i=0; i<filesArrLen; i++ ) {
	        filesTempArr.push(filesArr[i]);
	    }
	    console.log('filesTempArr', filesTempArr);
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
	
	function addDiv(args) {
		if(args == undefined) {
			args = {
						idx:$('#addBtn').parent().siblings().length+1,
						boardIdx:'',
						title:'',
						subTitle:'',
						pcImageName:'',
						pcOrgImageName:'',
						pcImageUrl:'',
						moImageName:'',
						moOrgImageName:'',
						moImageUrl:'',
						buttonName:'',
						buttonUrl:''
					};
		}
		var tObj = $("#addBtn").parent();
		//console.log('tObj', tObj);
		
		tObj.before($("#tmpl_list").render(args));
	}
	
	function drawDiv(args) {
		var tObj = $("#addBtn").parent();
		console.log('args', args);
		$(args).each(function(i,o) {
			console.log('o', o);
			o.idx = i+1;
			tObj.before($("#tmpl_list").render(o));
		});
	}
	
</script>

<script type="text/javascript" src="/ckeditor5/ckeditor.js"></script>
<script>
	ClassicEditor
		.create( document.querySelector( '#title'), {
			// toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ]
		} )
		.then( editor => {
			window.editor = editor;
		} )
		.catch( err => {
			console.error( err.stack );
		} );
</script>
<script>
	ClassicEditor
		.create( document.querySelector( '#subTitle' ), {
			// toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ]
		} )
		.then( editor => {
			window.editor = editor;
		} )
		.catch( err => {
			console.error( err.stack );
		} );
</script>