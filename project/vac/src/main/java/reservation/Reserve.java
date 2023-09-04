package reservation;

import java.util.*;

public class Reserve extends SeatType {
	
	int run(){ //run() 메소드
		int num; // 메뉴번호를 저장
		
		while(true)
		{
			Scanner input = new Scanner(System.in);
			System.out.print("예약(1), 조회(2), 취소(3), 끝내기(4) >> ");
			num = input.nextInt(); // 메뉴번호를 사용자에게 입력 받음.
			
			switch(num) // 입력한 숫자에 따라 메뉴시작
			{
				case 1:
					book(s,a,b); // seatType에 있는 book()메소드를 사용
					break;
				case 2:
					check(s,a,b); // seatType에 있는 check()메소드를 사용 
					break;
				case 3:
					cancel(s,a,b); // seatType에 있는 cancel()메소드를 사용
					break;
				case 4:
					end(); // seatType에 있는 end()메소드를 사용
					break;
				default:
					System.out.println("잘못입력하셧습니다.");
					break;
			}
		}
	}

}