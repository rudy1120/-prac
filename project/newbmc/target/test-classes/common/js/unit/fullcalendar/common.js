/**
 * fullcalendar 공통 스크립트 (베이스: 금오산 야영장)
 *
 * @author sonys
 * @since 2016.07.05
 */

//jQuery 인쇄 플러그인
jQuery.fn.print = function(pgTitle){
if (this.size() > 1) {
	this.eq( 0 ).print();
	return;
} else if (!this.size()){
	return;
}

var strFrameName = ("printer-" + (new Date()).getTime());
var jFrame = $( "<iframe name='" + strFrameName + "'>" );

jFrame
	.css( "width", "1px" )
	.css( "height", "1px" )
	.css( "position", "absolute" )
	.css( "left", "-9999px" )
	.appendTo( $( "body:first" ) )
;

var objFrame = window.frames[ strFrameName ];
var objDoc = objFrame.document;
var jStyleDiv = $( "<div>" ).append( $( "style" ).clone() );

objDoc.open();
objDoc.write( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" );
objDoc.write( "<html>" );
objDoc.write( "<body>" );
objDoc.write( "<head>" );
objDoc.write( "<title>" );
objDoc.write( pgTitle==""?document.title:pgTitle );
objDoc.write( "</title>" );
objDoc.write( jStyleDiv.html() );
objDoc.write( "</head>" );
objDoc.write( this.html() );
objDoc.write( "</body>" );
objDoc.write( "</html>" );
objDoc.close();

objFrame.focus();
objFrame.print();

setTimeout( function(){ jFrame.remove(); }, (60 * 1000) );

}

//로딩이미지 추가
//id : loadingResv
function loading_bar(obj_id){
	$("#" + obj_id).after("<img id=\"loadingResv\" src=\"/common/yhdcalendar-1.0/img/loading_b.gif\">");
}

