<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ============================== 내용 ============================== --%>
<tr>
	<th rowspan="2" scope="row"><span class="red">*</span><label for="bContent">내용</label></th>
	<td>
		<form:textarea path="bContent" rows="20" class="input99" maxlength="1000000" data-validator="on" data-required="y" data-fieldName="내용"></form:textarea>
		<jsp:include page="/plugin/SmartEditor/smartEditor.jsp"></jsp:include>
	</td>
</tr>
<tr>
	<td>
		<input type="button" id="preview" class="btn_cyan" value="미리보기"/><br/>
		1. 일반게시판의 경우 사용자에게 나타나는 화면을 미리 확인할 수 있습니다.<br/>
		2. 포토게시판의 경우 첨부파일로 올린 이미지가 상단에 중앙정렬로 나타납니다. 첨부파일 이미지는 미리보기가 지원이 안됩니다.<br/>		
		3. FAQ게시판의 경우 배경색과 크기등의 다른 요소가 많을 수 있습니다.
	</td>
</tr>
<%-- <c:if test="${isWebzine}"> --%>
<%-- 웹진형 게시판일경우 javascript 선언부 START --%>
<script src="${pageContext.request.contextPath}/sys/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
/* tinymce.init({
	  language : 'ko_KR',
	  selector: 'textarea:first',
	  setup: function (editor) {
          editor.on('change', function () {
              tinymce.triggerSave();
          });
	  },
	  height: 500,
	  plugins: [
	    "advlist autolink lists link image charmap print preview anchor",
	    "searchreplace visualblocks code fullscreen",
	    "insertdatetime media table paste imagetools wordcount"
	  ],
	  toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	  // without images_upload_url set, Upload tab won't show up
	  images_upload_url: '/sys/editor/image/upload.do',
	  relative_urls : false,
	  // we override default upload handler to simulate successful upload
	  images_upload_handler: function (blobInfo, success, failure) {
		var xhr, formData;

        xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.open('POST', '/sys/editor/image/upload.do');

        xhr.onload = function() {
            var json;

            if (xhr.status != 200) {
                failure('HTTP Error: ' + xhr.status);
                return;
            }

            json = JSON.parse(xhr.responseText);

            if (!json || typeof json.location != 'string') {
                failure('Invalid JSON: ' + xhr.responseText);
                return;
            }

            success(json.location);
        };

        formData = new FormData();
        formData.append('file', blobInfo.blob(), blobInfo.filename());

        xhr.send(formData);
	  }
	}); */
</script>
<script>
	 $(function() {
		var sc = '${bbsConfigVO.ptSiteCode}';
		$('#preview').on('click', function() {
			if (sc == 'bmc') {
				var preview = window.open("/common/jsp/bbs/component/view/preview.html", "", "width=1500, height=800, scrollbars=yes");
			} else {
				if (sc == 'osiria') {
					var preview = window.open("/common/jsp/bbs/component/view/preview2.html", "", "width=1500, height=800, scrollbars=yes");
				} else {
					if (sc == 'history') {
						var preview = window.open("/common/jsp/bbs/component/view/preview3.html", "", "width=1500, height=800, scrollbars=yes");
					}
				}
			}
		});
	}); 

</script>
<%-- </c:if> --%>