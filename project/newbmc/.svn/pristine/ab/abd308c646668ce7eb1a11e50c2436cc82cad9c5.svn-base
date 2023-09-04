<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.portal.common.NuriType"%>

<%-- ============================== 공공누리 유형 ==============================
<c:if test="${!isMinwon}">
	<tr>
		<th scope="row"><span class="red">*</span><label for="bNuriType">공공누리 유형</label></th>
		<td>
			<ul>
				<li><form:radiobutton path="bNuriType" value="0" label="표시안함" checked="checked"/></li>
				<c:forEach var="t" items="<%=NuriType.values() %>" varStatus="s">
					<c:if test="${s.index != 0}">
						<li><form:radiobutton path="bNuriType" value="${t.code}" label="${t.desc}"/></li>
					</c:if>
				</c:forEach>
			</ul>
			<c:if test="${not empty bbsMngVO.bPrivatecode}">
				<p class="info">시민이 작성한 글입니다. 공공누리 유형을 선택해도 반영되지 않습니다.</p>
			</c:if>
		</td>
	</tr>
</c:if>
--%>
<%-- <c:if test="${!isMinwon && empty bbsMngVO.bPrivatecode}">
	<div class="mT20">
		<div style="position:relative;width:100%;background:#f7f7f7;padding-bottom:20px;border-bottom:1px solid #dbdbdb;">
			<h3 style="width:100%;height:27px;margin:0;padding:0;line-height:27px;border-bottom:1px solid #dbdbdb;background:#918f8f;color:#fff;font-family:'돋움',dotum;font-size:14px;font-weight:bold;text-align:center;">공공누리 유형</h3>
			<p style="margin:0;padding:10px 20px;border-top:1px solid #fff;line-height:150%;font-size:11px;color:#595959;font-family:'돋움', dotum;">저작권법 24조의2에 따라 국가나 지방자치단체, 공공기관이 업무상 작성하여 공표한 저작물이나 저작재산권 전부를 보유한 저작물은 국민이 허락 없이 이용할 수 있으며, 이에 따라 개방기관은 공공저작물 자유이용에 관한 표시를 하여야 합니다. </p>
		<a href="http://www.kogl.or.kr/info/freeUse.do" target="_blank" style="position:absolute;right:20px;bottom:10px;font-size:11px;color:#595959;font-family:'돋움', dotum;"><span style="font-size:10px;color:#595959;">▶</span> 상세내용 : 공공누리 홈페이지 참조</a>
		</div>

		<div style="position:relative;margin-top:5px;vertical-align:top;">
			<h4 style="position:absolute;left:0;top:0;margin:0;padding:0;width:186px;height:290px; border-left:1px solid  #dbdbdb;border-top:1px solid  #dbdbdb;border-right:1px solid  #dbdbdb;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/title_codetype.gif" alt="공공누리 유형선택 open 공공저작물 자유이용 허락표시 기준적용" style="width:186px;height:135px;"></h4>
			<ul style="position:relative;list-style:none;margin:0;padding:0;padding-left:192px;">
				<li style="position:relative;margin:0;margin-bottom:2px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType0" path="bNuriType" value="0" style="position:absolute;left:10px;top:22px;z-index:10;" data-validator="on" data-required="y" data-fieldName="공공누리 유형"/>
					<label for="bNuriType0" style="display:block;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:40px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
						사용하지 않음
					</label>
				</li>
				<li style="position:relative;margin:0;margin-bottom:2px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType1" path="bNuriType" value="1" style="position:absolute;left:10px;top:22px;z-index:10;"/>
					<label for="bNuriType1" style="display:block;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:280px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
					<img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode1.jpg" alt="" style="position:absolute;left:40px;top:2px;vertical-align:middle;width:149px;height:54px;">
					1유형 : 출처표시 (상업적 이용 및 변경 가능)
					</label>
				</li>
				<li style="position:relative;margin:0;margin-bottom:2px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType2" path="bNuriType" value="2" style="position:absolute;left:10px;top:22px;z-index:10;"/>
					<label for="bNuriType2" style="display:block;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:280px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
					<img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode2.jpg" alt="" style="position:absolute;left:40px;top:2px;vertical-align:middle;width:183px;height:54px;">
					2유형 : 출처표시 + 상업적이용금지
					</label>
				</li>
				<li style="position:relative;margin:0;margin-bottom:2px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType3" path="bNuriType" value="3" style="position:absolute;left:10px;top:22px;z-index:10;"/>
					<label for="bNuriType3" style="display:block;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:280px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
					<img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode3.jpg" alt="" style="position:absolute;left:40px;top:2px;vertical-align:middle;width:183px;height:54px;">
					3유형 : 출처표시 + 변경금지
					</label>
				</li>
				<li style="position:relative;margin:0;margin-bottom:2px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType4" path="bNuriType" value="4" style="position:absolute;left:10px;top:22px;z-index:10;"/>
					<label for="bNuriType4" style="display:block;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:280px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
					<img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode4.jpg" alt="" style="position:absolute;left:40px;top:2px;vertical-align:middle;width:219px;height:54px;">
					4유형 : 출처표시 + 상업적이용금지 + 변경금지
					</label>
				</li>
				<li style="position:relative;margin:0;margin-bottom:0px;padding:0;min-height:60px;border-top:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;background:#f7f7f7;">
					<form:radiobutton id="bNuriType5" path="bNuriType" value="5" style="position:absolute;left:10px;top:22px;z-index:10;"/>
					<label for="bNuriType5" style="display:inline-block;*display:inline;*zoom:1;position:relative;margin:0;padding:20px 10px 20px 0;padding-left:280px;font-size:12px;color:#231f20;font-weight:bold;line-height:130%;cursor:pointer;">
					<img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode0.jpg" alt="" style="position:absolute;left:40px;top:2px;vertical-align:middle;width:219px;height:54px;">
					자유이용 불가 (저작권법 제24조의2 제1항 제1호 ~ 4호 중 어느 하나에 해당됨)
					</label>
					<a href="http://www.law.go.kr/법령/저작권법" target="_blank" style="margin-left:10px;font-size:11px;color:#595959;font-family:'돋움', dotum;">
					<span style="font-size:10px;color:#595959;">▶</span> 해당사항 확인 (국가법령정보센터)</a>
				</li>
			</ul>

			<h5 style="margin:0;padding:0;position:absolute;left:0;top:286px;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/title_ex.gif" alt="유형선택 적용예시" style="width:186px;height:32px;border-left:1px solid  #dbdbdb;border-bottom:1px solid  #dbdbdb;border-right:1px solid  #dbdbdb;"></h5>

			<div class="codeView01" style="display: none; position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:25px 15px 30px 190px;font-size:12px;color:#292929;font-weight:bold;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode1.jpg" alt="" style="position:absolute;left:20px;top:12%;vertical-align:middle;width:149px;height:54px;"> 본 공공저작물은 공공누리 "출처표시" 조건에 따라 이용할 수 있습니다.</div>
			<div class="codeView02" style="display: none; position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:25px 15px 30px 225px;font-size:12px;color:#292929;font-weight:bold;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode2.jpg" alt="" style="position:absolute;left:20px;top:12%;vertical-align:middle;width:183px;height:54px;"> 본 공공저작물은 공공누리  “출처표시+상업적이용금지”  조건에  따라  이용할  수  있습니다.</div>
			<div class="codeView03" style="display: none; position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:25px 15px 30px 225px;font-size:12px;color:#292929;font-weight:bold;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode3.jpg" alt="" style="position:absolute;left:20px;top:12%;vertical-align:middle;width:183px;height:54px;"> 본 공공저작물은 공공누리  “출처표시+변경금지”  조건에  따라  이용할  수  있습니다.</div>
			<div class="codeView04" style="display: none; position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:25px 15px 30px 260px;font-size:12px;color:#292929;font-weight:bold;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opencode4.jpg" alt="" style="position:absolute;left:20px;top:12%;vertical-align:middle;width:219px;height:54px;"> 본 공공저작물은 공공누리 “출처표시+상업적이용금지+변경금지”  조건에  따라  이용할  수  있습니다.</div>
			<div class="codeView05" style="display: none; position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:17px 15px  17px 60px;font-size:12px;color:#292929;font-weight:bold;"><img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/img_opencode0_1.jpg" alt="" style="position:absolute;left:20px;top:25%;vertical-align:middle;width:27px;height:27px;"> 자유이용을 불가합니다.</div>
		</div>

		<div style="border-top:1px solid #6c6b6b;background:#f7f7f7;margin-top:5px;padding:10px 20px 10px 27px">
			<p style="font-size:11px;color:#0c0c0c;font-family:'돋움',dotum;margin-bottom:5px;text-indent:-7px;"> · <strong style="font-size:11px;color:#dd494e;font-weight:bold;">공공누리 제2~4유형</strong> 의 적용은 공동저작물 등 제3자의 권리가 포함된 저작물에 한하여 제3자의 이용허락 범위에 따라 <strong style="font-size:11px;color:#dd494e;font-weight:bold;">제한적으로 적용</strong></p>
			<p style="font-size:11px;color:#0c0c0c;font-family:'돋움',dotum;margin-bottom:7px;text-indent:-7px;"> · 공공저작권 관련 상담센터 <span style="margin-left:10px;"> <img src="http://www.kogl.or.kr/open/web/images/images_2014/codetype/icon_codetel.gif" alt="전화">1670-0052</span></p>
		</div>
	</div>
</c:if>
<c:if test="${not empty bbsMngVO.bPrivatecode}">
	<p class="info red">시민이 작성한 글은 공공누리 유형을 선택할 수 없습니다.</p>
</c:if>
--%>