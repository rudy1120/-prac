package reservation;

import java.util.*;

public class SeatType extends Seat{
	
		void book(String[][] s,String[][] a,String[][] b){ // 좌석을 예약을 할 때 사용하는 메소드
			System.out.print("좌석구분 S(1), A(2), B(3) >> ");
			Scanner input = new Scanner(System.in);
			int seatT;
			String name;
			int seatN;
			seatT = input.nextInt(); // 좌석의 타입을 정하기 위해 사용자에게 입력받음
			
			if(seatT != 1 && seatT != 2 && seatT != 3) // 좌석타입을 잘 못 입력 했을 경우
			{
				System.out.println("잘 못 입력하셧습니다!");
				book(s,a,b);
			}
			
			switch(seatT)
			{
			case 1: // S석을 선택
				while(true)
				{
					System.out.print("S>> ");
					for(int i=0; i<10; i++) // s석의 좌석 현황을 출력
						System.out.print(s[i][0]);
					System.out.println();
					name = getName() + " "; // seat 클래스에 있는 getName() 메소드를 이용하여 이름을 받음  
					seatN = getSeatNumber() - 1; // seat 클래스에 있는 seatN() 메소드를 이용하여 좌석번호를 받음
					
					if(seatN >= 0 && seatN < 10) // 좌석번호가 유효한지 확인
					{
						if( seatChk(seatT,seatN,name) == 1) // seat 클래스에 있는 seatChk() 메소드를 이용하여 이미 예약된 자리인지를 확인
							break;
					}
					
					else
						System.out.println("자리번호 오류입니다. 다시 입력해주세요!");
				}
				break;
				
			case 2:
				while(true)
				{
					System.out.print("A>> ");
					for(int i=0; i<15; i++) // a석의 좌석 현황을 출력
						System.out.print(a[i][0]);
					System.out.println();
					name = getName() + " "; // seat 클래스에 있는 getName() 메소드를 이용하여 이름을 받음
					seatN = getSeatNumber() - 1; // seat 클래스에 있는 seatN() 메소드를 이용하여 좌석번호를 받음
					
					if(seatN >= 0 && seatN < 15)  // 좌석번호가 유효한지 확인
					{
						if( seatChk(seatT,seatN,name) == 1) // seat 클래스에 있는 seatChk() 메소드를 이용하여 이미 예약된 자리인지를 확인
							break;
					}
					
					else
						System.out.println("자리번호 오류입니다. 다시 입력해주세요!");

				}
				break;
			case 3:
				while(true)
				{
					System.out.print("B>> ");
					for(int i=0; i<20; i++) // b석의 좌석 현황을 출력
						System.out.print(b[i][0]);
					System.out.println();
					name = getName()+ " "; // seat 클래스에 있는 getName() 메소드를 이용하여 이름을 받음
					seatN = getSeatNumber() - 1; // seat 클래스에 있는 seatN() 메소드를 이용하여 좌석번호를 받음
					if(seatN >= 0 && seatN < 20) // 좌석번호가 유효한지 확인
					{
						if( seatChk(seatT,seatN,name) == 1) // seat 클래스에 있는 seatChk() 메소드를 이용하여 이미 예약된 자리인지를 확인
							break;
					}
					
					else
						System.out.println("자리번호 오류입니다. 다시 입력해주세요!");

				}
				break;
			}
			
		}
		
		void check(String[][] s, String[][] a, String[][] b){ // 조회를 할 때 사용하는 메소드
			System.out.print("S>> ");
			for(int i=0; i<10; i++) //s석의 좌석 현황을 출력
				System.out.print(s[i][0]);
			System.out.println();
			
			System.out.print("A>> "); 
			for(int i=0; i<15; i++) //a석의 좌석 현황을 출력
				System.out.print(a[i][0]);
			System.out.println();
			
			System.out.print("B>> ");
			for(int i=0; i<20; i++) //b석의 좌석 현황을 출력
				System.out.print(b[i][0]);
			System.out.println();
			
			System.out.println("<<<조회를 완료하였습니다.>>>");
		}
		
		void cancel(String[][] s, String[][] a, String[][] b){ // 취소를 할 때 사용하는 메소드

			System.out.print("좌석구분 S(1), A(2), B(3)>>");
			Scanner input = new Scanner(System.in);
			int num = input.nextInt(); //좌석의 타입을 정하기 위해 사용자에게 입력받음
			
			if(num != 1 && num != 2 && num != 3) // 좌석타입을 잘 못 입력했을경우
			{
				System.out.println("잘 못 입력하셧습니다!");
				cancel(s,a,b);
			}
			
			switch(num)
			{
			case 1:
				System.out.print("S>> ");
				for(int i=0; i<10; i++) //s석의 좌석 현황을 출력
					System.out.print(s[i][0]);
				System.out.println();
				break;
			case 2:
				System.out.print("A>> "); 
				for(int i=0; i<15; i++) //a석의 좌석 현황을 출력
					System.out.print(a[i][0]);
				System.out.println();
				break;
			case 3:
				System.out.print("B>> ");
				for(int i=0; i<20; i++) //b석의 좌석 현황을 출력
					System.out.print(b[i][0]);
				System.out.println();
				break;
			}
			
			String name = getName() + " "; // 취소할 이름을 입력 받음
			
			if(cancleChk(num, name) == 0) // 취소할 이름이 예약자 목록에 없을경우
				cancel(s,a,b); // 다시 cancel() 메소드를 호출하여 다시 받음 
		}
		
		void end (){ //프로그램 종료 메소드
			System.out.println("프로그램이 종료됩니다.");
			System.exit(0);
		}
}