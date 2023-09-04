<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="isBasic" value="${bbsConfigVO.ptType == 'BRD001'}" scope="request"/>
<c:set var="isPhoto" value="${bbsConfigVO.ptType == 'BRD002'}" scope="request"/>
<c:set var="isMovie" value="${bbsConfigVO.ptType == 'BRD003'}" scope="request"/>
<c:set var="isBlog" value="${bbsConfigVO.ptType == 'BRD004'}" scope="request"/>
<c:set var="isMinwon" value="${bbsConfigVO.ptType == 'BRD005'}" scope="request"/>
<c:set var="isFAQ" value="${bbsConfigVO.ptType == 'BRD006'}" scope="request"/>
<c:set var="isCalendar" value="${bbsConfigVO.ptType == 'BRD007'}" scope="request"/>
<c:set var="isWebzine" value="${bbsConfigVO.ptType == 'BRD008'}" scope="request"/>
<c:set var="isDownload" value="${bbsConfigVO.ptType == 'BRD009'}" scope="request"/>

<c:set var="showThumb" value="${fn:substring(bbsConfigVO.ptOutFields, 1, 2) == 'Y'}" scope="request"/>
<c:set var="usePublic" value="${bbsConfigVO.ptPublicYn == 'Y'}" scope="request"/>
<c:set var="useCategory" value="${bbsConfigVO.ptCategoryYn == 'Y'}" scope="request"/>
<c:set var="useDept" value="${bbsConfigVO.ptCategoryGubun == 'A' || bbsConfigVO.ptCategoryGubun == 'B'}" scope="request"/>
<c:set var="useCustom" value="${bbsConfigVO.ptCategoryGubun == 'A' || bbsConfigVO.ptCategoryGubun == 'W'}" scope="request"/>
<c:set var="useComment" value="${bbsConfigVO.ptCommentYn == 'Y'}" scope="request"/>
<c:set var="useReply" value="${bbsConfigVO.ptReplyYn == 'Y'}" scope="request"/>
<c:set var="useBundleDel" value="${bbsConfigVO.ptBundleDelYn == 'Y'}" scope="request"/>
<c:set var="useTerm" value="${bbsConfigVO.ptTermYn == 'Y'}" scope="request"/>
<c:set var="useNterm" value="${bbsConfigVO.ptNtermYn == 'Y'}" scope="request"/>
<c:set var="allowedWriting" value="${fn:substring(bbsConfigVO.ptLevelInput, 1, 2) == 'Y' && !isFAQ}" scope="request"/>

<c:set var="isRootArticle" value="${empty searchVO.bIdx || bbsMngVO.bLevel == '0'}" scope="request"/>
<c:set var="isNew" value="${empty bbsMngVO.bTitle}" scope="request"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}" scope="request"/>
