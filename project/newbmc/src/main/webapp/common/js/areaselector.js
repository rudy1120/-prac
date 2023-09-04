function area_select(form)
{
	areaselector = form.area_nm;
	cityselector = form.city_nm;

	selected_value = areaselector.options[areaselector.selectedIndex].value;
	area = areaselector.options[areaselector.selectedIndex].text;

	o2 = new Array;
	i = 0;

	if(area == '지역선택')
	{
		o2[i++]=new Option('도시선택', '');
	}
	else if(area == '서울경기')
	{		
		o2[i++]=new Option('서울', '108');
		o2[i++]=new Option('인천', '112');
		o2[i++]=new Option('수원', '119');
		o2[i++]=new Option('동두천', '098');
		//o2[i++]=new Option('이천', '203');
		//o2[i++]=new Option('양평', '202');
		//o2[i++]=new Option('강화', '201');
	}
	else if(area == '충청남도')
	{		
		o2[i++]=new Option('대전', '133');
		//o2[i++]=new Option('부여', '236');
		//o2[i++]=new Option('보령', '235');
		o2[i++]=new Option('서산', '129');
		//o2[i++]=new Option('금산', '238');
		//o2[i++]=new Option('천안', '232');
	}
	else if(area =='충청북도')
	{		
		o2[i++]=new Option('청주', '131');
		o2[i++]=new Option('충주', '127');
		//o2[i++]=new Option('제천', '221');
		//o2[i++]=new Option('보은', '226');
		o2[i++]=new Option('추풍령', '135');
	}
	else if(area == '강원도')
	{		
		o2[i++]=new Option('춘천', '101');
		o2[i++]=new Option('철원', '095');
		//o2[i++]=new Option('홍천', '212');
		o2[i++]=new Option('원주', '114');
		o2[i++]=new Option('영월', '121');
		o2[i++]=new Option('강릉', '105');
		o2[i++]=new Option('대관령', '100');
		//o2[i++]=new Option('태백', '216');
		o2[i++]=new Option('속초', '090');
		//o2[i++]=new Option('인제', '211');
		o2[i++]=new Option('울릉', '115');
		o2[i++]=new Option('동해', '106');

	}
	else if(area == '전라남도' )
	{		
		o2[i++]=new Option('광주', '156');
		//o2[i++]=new Option('순천', '256');
		//o2[i++]=new Option('장흥', '260');
		//o2[i++]=new Option('고흥', '262');
		o2[i++]=new Option('여수', '168');
		o2[i++]=new Option('완도', '170');
		//o2[i++]=new Option('해남', '261');
		//o2[i++]=new Option('목포', '165');
		o2[i++]=new Option('흑산도', '169');
	}
	else if(area == '전라북도' )
	{		
		o2[i++]=new Option('전주', '146');
		//o2[i++]=new Option('군산', '140');
		//o2[i++]=new Option('부안', '243');
		//o2[i++]=new Option('정읍', '245');
		//o2[i++]=new Option('임실', '244');
		//o2[i++]=new Option('장수', '248');
		//o2[i++]=new Option('남원', '247');
	}
	else if(area == '경상남도')
	{		
		o2[i++]=new Option('부산', '159');
		o2[i++]=new Option('마산', '155');
		//o2[i++]=new Option('밀양', '288');
		o2[i++]=new Option('울산', '152');
		o2[i++]=new Option('진주', '192');
		//o2[i++]=new Option('거창', '284');
		//o2[i++]=new Option('합천', '285');
		o2[i++]=new Option('통영', '162');
		//o2[i++]=new Option('거제', '294');
		//o2[i++]=new Option('남해', '295');
		//o2[i++]=new Option('산청', '289');
	}
	else if(area == '경상북도')
	{		
		o2[i++]=new Option('대구', '143');
		o2[i++]=new Option('포항', '138');
		//o2[i++]=new Option('영천', '281');
		//o2[i++]=new Option('구미', '279');
		//o2[i++]=new Option('의성', '278');
		//o2[i++]=new Option('문경', '273');
		o2[i++]=new Option('안동', '136');
		//o2[i++]=new Option('영덕', '277');
		//o2[i++]=new Option('영주', '272');
		//o2[i++]=new Option('춘양', '271');
		o2[i++]=new Option('울진', '130');
	}
	else if(area == '제주도')
	{		
		o2[i++]=new Option('제주', '184');
		//o2[i++]=new Option('성산포', '265');
		o2[i++]=new Option('서귀포', '189');
		o2[i++]=new Option('제주고층', '185');
	}

	if(i == 0)
	{
		alert('Error: 개체가 비어있읍니다');
	}
	else
	{
		cityselector.length = o2.length;

		for(i = 0; i < o2.length; i++)
		{
			cityselector.options[i] = o2[i];
		}

		cityselector.options[0].selected=true;
	}
}
/*------------------------------------------*/
function a_setValue( objId, value ){
	var obj = document.getElementById( objId );
	if ( obj != null && value != "" && value != null )
	{
		obj.value = value;
	}
}