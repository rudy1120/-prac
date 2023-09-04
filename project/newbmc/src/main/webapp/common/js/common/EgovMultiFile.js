/**
 * Convert a single file-input element into a 'multiple' input list
 * Usage:
 *
 *   1. Create a file input element (no name)
 *      eg. <input type="file" id="first_file_element">
 *
 *   2. Create a DIV for the output to be written to
 *      eg. <div id="files_list"></div>
 *
 *   3. Instantiate a MultiSelector object, passing in the DIV and an (optional) maximum number of files
 *      eg. var multi_selector = new MultiSelector( document.getElementById( 'files_list' ), 3 );
 *
 *   4. Add the first element
 *      eg. multi_selector.addElement( document.getElementById( 'first_file_element' ) );
 */

function MultiSelector( list_target, max, comment ){

	/*
	for (Key in list_target){
		document.writeln("list_target["+Key +"] = "+list_target[Key]);
	}
	*/

	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};

	/**
	 * Add a new file input element
	 */
	this.addElement = function( element ){

		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){

			// Element name -- what number am I?
			element.name = 'file_' + this.id++;

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function(){

				// New file input
				var new_element = document.createElement( 'input' );
				new_element.type = 'file';

				new_element.setAttribute("class", "input400");

				// Add new element
				this.parentNode.insertBefore( new_element, this );

				// Apply 'update' to element
				this.multi_selector.addElement( new_element );

				// Update list
				this.multi_selector.addListRow( this );

				// Hide this: we can't use display:none because Safari doesn't like it
				this.style.position = 'absolute';
				this.style.left = '-1000px';
				this.style.top = '-1000px';
				this.style.display = 'none';
				this.style.visibility = 'hidden';
				this.style.width = '0';
				this.style.height = '0';
				this.style.overflow = 'hidden';

				new_element.onkeypress = function(){
					return false;
				};

			};

			// 20151105 J.Ryeon Lee 추가. 기존 등록된 첨부파일 개수도 확인하도록 수정
			//var already_updated_file_cnt = jQuery(".list_file").children().length;
			// 관리자단에서 파일 개수 체크가 되지 않아 수정 - 20170510 권태성
			var already_updated_file_cnt = $(element).parent().prev().prev().children().find("li").length;
			var total_file_cnt = already_updated_file_cnt + this.count;

			// If we've reached maximum number, disable input element
			if (this.max != -1 && total_file_cnt >= this.max) {
				element.disabled = true;
			};

			// File element counter
			this.count++;
			// Most recent element
			this.current_element = element;

		} else {
			// This can only be applied to file input elements!
			alert('Error: not a file input element');
		};

	};

	var fileCnIdx = 0;

	/**
	 * Add a new row to the list of files
	 */
	this.addListRow = function( element ){

		// Row ul
		var new_row = document.createElement( 'li' );

		// Delete button
		var new_row_button = document.createElement( 'input' );
		new_row_button.type = 'button';
		new_row_button.value = '';
		new_row_button.title = '파일삭제';

		new_row_button.setAttribute("class", "btn_dele");

		// References
		new_row.element = element;

		// Delete function
		new_row_button.onclick= function(){

			// Remove element from form
			this.parentNode.element.parentNode.removeChild( this.parentNode.element );

			// Remove this row from the list
			this.parentNode.parentNode.removeChild( this.parentNode );

			// Decrement counter
			this.parentNode.element.multi_selector.count--;

			// Re-enable input element (if it's disabled)
			this.parentNode.element.multi_selector.current_element.disabled = false;

			//    which nixes your already queued uploads
			return false;
		};

		// Set row value
		new_row.innerHTML = "<span>" + element.value + "</span>";

		// Add button
		new_row.appendChild( new_row_button );

		// create file comment input tag
		var isImg = element.value.match(/\.(gif|jpg|jpeg|png)$/i);
		if(comment == "false"){
			
		}
		else if (isImg && (typeof(showComment) == "undefined" || showComment == true)) {
			//파일 설명 DIV
			var new_file_cndiv = document.createElement( 'div' );
			new_file_cndiv.value = '';
			new_file_cndiv.setAttribute("class", "pT5");

			//파일 설명 태그 추가
			var new_file_cn = document.createElement( 'input' );
			new_file_cn.name = 'file_cn';
			new_file_cn.type = 'text';
			new_file_cn.value = '';
			new_file_cn.setAttribute("class", "input400");
			new_file_cn.setAttribute("id", "file_cn" + fileCnIdx);

			new_file_cndiv.appendChild( new_file_cn );

			new_file_cndiv.innerHTML= "<label for='file_cn" + fileCnIdx + "'>설명글</label>: " + new_file_cndiv.innerHTML;

			new_row.appendChild( new_file_cndiv );

			fileCnIdx++;
		}

		//new_row.innerHTML = new_row.innerHTML + "<div class=\"pT5\">설명글 : <input type=\"text\" name=\"file_cn\" value=\"\" class=\"input400\"/></div>";

		//<div class="pT5">설명글 : <input type="text" name="file_cn" value="${fileVO.fileCn}" class="input400"/></div>

		//파일 설명 태그 추가
		//var new_file_cn = document.createElement( 'input' );
		//new_file_cn.name = 'file_cn';
		//new_file_cn.type = 'text';
		//new_file_cn.value = '';
		//new_file_cn.setAttribute("class", "input400");

		//new_row.appendChild( new_file_cn );

		//new_row.innerHTML = new_row.innerHTML + "</div>";

		// Add it to the list
		this.list_target.appendChild( new_row );

		//웹표준관련 기본 <li>태그 제거
		if(jQuery('#noFile')) { jQuery('#noFile').remove(); }

	};

};