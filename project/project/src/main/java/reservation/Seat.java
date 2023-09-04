package reservation;

import java.util.Scanner;

public class Seat {
	
	int i;
	int j;
	String[][] s = new String[10][2];
	String[][] a = new String[15][2];
	String[][] b = new String[20][2];
	
	Seat(){ // 자리를 초기화 하는 생성자
		for(i=0; i<10; i++)
		{
			s[i][0] = "--- ";
			s[i][1] = "0";
		}
		for(i=0; i<15; i++)
		{
			a[i][0] = "--- ";
			a[i][1] = "0";
		}
		for(i=0; i<20; i++)
		{
			b[i][0] = "--- ";
			b[i][1] = "0";
		}
	}
	
	void seatPerson(int seat, int number, String name){ // 예약자를 할당하는 메소드
		switch(seat)
		{
		case 1:
			s[number][0] = name;
			s[number][1] = "1";
			break;
		case 2:
			a[number][0] = name;
			a[number][1] = "1";
			break;
		case 3:
			b[number][0] = name;
			b[number][1] = "1";
			break;
		}
	}
	
	String getName(){ //좌석 예약자 이름을 받는 메소드
		System.out.print("이름>>");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		return name;
	}
	
	int getSeatNumber(){ //좌석 번호를 받는 메소드
		System.out.print("번호>>");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		return num;
	}
	
	int seatChk(int seat, int number , String name){ //이미 예약되어 있는지를 판별하는 메소드
		int ret = 0;
		
		switch(seat)
		{
		case 1:
			if(s[number][1] == "1") // 이미 예약이 되어 있는 자리인 경우
				{
					System.out.println("해당 좌석이 예약되어있습니다. 다시 예약해 주세요.");
					ret = 0;
				}
				
			else // 예약에 성공 한 경우
			{
				seatPerson(seat, number, name);
				System.out.println("예약되었습니다!");
				ret = 1;
			}	
			break;
		case 2:
			if(a[number][1] == "1") // 이미 예약이 되어 있는 자리인 경우
			{
				System.out.println("해당 좌석이 예약되어있습니다. 다시 예약해 주세요.");
				ret = 0;
			}
			else // 예약에 성공 한 경우
			{
				seatPerson(seat, number, name);
				System.out.println("예약되었습니다!");
				ret = 1;
			}
				break;
		case 3:
			if(b[number][1] == "1") // 이미 예약이 되어 있는 자리인 경우
			{
				System.out.println("해당 좌석이 예약되어있습니다. 다시 예약해 주세요.");
				ret = 0;
			}
			else // 예약에 성공 한 경우
			{
				seatPerson(seat, number, name);
				System.out.println("예약되었습니다!");
				ret = 1;
			}
				break;
		}
		return ret;
	}
	
	int cancleChk(int num, String name){ // 예약을 취소하는 메소드
		
		switch(num)
		{
		case 1:
			while(true)
		{
			
			for(i=0; i<10; i++)
			{
				if(name.equals(s[i][0])) //같은 이름의 예약자가 있으면 예약을 취소시킴
				{
					s[i][0] = "--- ";
					s[i][1] = "0";
					break;
				}
			}
			
			if(i == 10) // 같은 이름의 예약자가 없을 경우
			{
				System.out.println("예약목록이 없습니다. 다시 입력해주세요!");
				return 0;
			}
			else //같은 이름의 예약자가 있어 취소를 했을 경우
			{
				System.out.println("예약이 취소되었습니다!");
				break;
			}
			
		}
			break;
			
		case 2:
			while(true)
		{
			
			for(i=0; i<15; i++)
			{
				if(name.equals(a[i][0])) //같은 이름의 예약자가 있으면 예약을 취소시킴
				{
					a[i][0] = "--- ";
					a[i][1] = "0";
					break;
				}
			}
			
			if(i == 15) // 같은 이름의 예약자가 없을 경우
			{
				System.out.println("예약목록이 없습니다. 다시 입력해주세요!");
				return 0;
			}
			else //같은 이름의 예약자가 있어 취소를 했을 경우
			{
				System.out.println("예약이 취소되었습니다!");
				break;
			}
			
		}
			break;
		case 3:
			while(true)
		{
			
			for(i=0; i<20; i++)
			{
				if(name.equals(b[i][0])) //같은 이름의 예약자가 있으면 예약을 취소시킴
				{
					b[i][0] = "--- ";
					b[i][1] = "0";
					break;
				}
			}
			
			if(i == 20) // 같은 이름의 예약자가 없을 경우
			{
				System.out.println("예약목록이 없습니다. 다시 입력해주세요!");
				return 0;
			}
			else //같은 이름의 예약자가 있어 취소를 했을 경우
			{
				System.out.println("예약이 취소되었습니다!");
				break;

			}
			
		}
			break;
	}
		return 1;
	}
}